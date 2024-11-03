package com.mycompany.meia_proyecto.classes;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {

    public static void saveToFile(String path, Maintenance data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

            //Writes the new data to file
            writer.write(data.toString());
            writer.newLine();
            JOptionPane.showMessageDialog(null, data.getName() + " guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        }

        updateIndexesFile(path);
    }

    public static void updateToFile(String path, Maintenance data) throws IOException {
        String indexesPath = path.substring(0, path.length() - 4) + "_index.txt";
        List<String> lines = Files.readAllLines(Paths.get(indexesPath));
        List<String> indexData = List.of(lines.get(getIndexByPK(data.getPk(), lines)).split(","));
        int startingIndex = Integer.parseInt(indexData.get(1));
        int length = Integer.parseInt(indexData.get(2));

        // String builder is used to replace the date
        StringBuilder content = new StringBuilder(Files.readString(Paths.get(path)));
        content.replace(startingIndex, startingIndex + length, data.toString() + "\r\n");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content.toString());
        }

        updateIndexesFile(path);
    }

    public static void deleteFromFile(String path, String PK) throws IOException {
        String indexesPath = path.substring(0, path.length() - 4) + "_index.txt";
        List<String> lines = Files.readAllLines(Paths.get(indexesPath));
        int PKIndex = getIndexByPK(PK, lines);
        List<String> indexData = List.of(lines.get(PKIndex).split(","));
        int startingIndex = Integer.parseInt(indexData.get(1));
        int length = Integer.parseInt(indexData.get(2));

        // String builder is used to replace the record
        StringBuilder content = new StringBuilder(Files.readString(Paths.get(path)));
        content.replace(startingIndex, startingIndex + length, "");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content.toString());
        }

        lines.remove(PKIndex);
        // Removes from index file
        try (BufferedWriter writerIndexes = new BufferedWriter(new FileWriter(indexesPath))) {
            for (String line : lines) {
                writerIndexes.write(line);
                writerIndexes.newLine();
            }
        }

        updateIndexesFile(path);
    }

    public static boolean fileContainsPK(String path, String PK) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            if (line.contains(PK)) {
                return true;
            }
        }
        return false;
    }

    private static void updateIndexesFile(String path) throws IOException {
        //Updates the position of each record
        List<String> indexesLines = new ArrayList<>();
        int fileIndex = 0;
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            List<String> lineData = List.of(line.split(","));

            String PK = lineData.get(0);
            int startingPositon = fileIndex;
            int endPostion = line.length() + 2;

            indexesLines.add(PK + "," + startingPositon + "," + endPostion);
            fileIndex = startingPositon + endPostion;
        }
        //Sorts alphabetically
        Collections.sort(indexesLines);

        // gets the path of the file where the indexes are being saved
        String indexesPath = path.substring(0, path.length() - 4) + "_index.txt";
        try (BufferedWriter writerIndexes = new BufferedWriter(new FileWriter(indexesPath))) {
            //Writes the indexes file
            for (String line : indexesLines) {
                writerIndexes.write(line);
                writerIndexes.newLine();
            }
        }
    }

    //Performs binary search to get the line with the specified PK
    private static int getIndexByPK(String PK, List<String> lines) throws IOException {
        int left = 0;
        int right = lines.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midPK = lines.get(mid).split(",")[0].trim(); // Extract PK

            int comparison = midPK.compareTo(PK);

            if (comparison == 0) {
                return mid; // Found the target PK
            } else if (comparison < 0) {
                left = mid + 1; // Search the right half
            } else {
                right = mid - 1; // Search the left half
            }
        }

        return -1; // PK not found
    }
}
