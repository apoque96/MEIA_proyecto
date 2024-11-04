package com.mycompany.meia_proyecto.classes;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

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
        if (fileContainsPK(path, data.getPk()))
            throw new IOException();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

            //Writes the new data to file
            writer.write(data.toString());
            writer.newLine();
            JOptionPane.showMessageDialog(null, data.getName() + " guardado exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

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

    // Checks whenever the PK already exists
    public static boolean fileContainsPK(String path, String PK) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            if (line.contains(PK)) {
                return true;
            }
        }
        return false;
    }

    public static void exportToExcel(){
        String modelsPath = "marcas_vehiculos.txt";
        String linesPath = "lineas.txt";
        String typesPath = "types.txt";
        String vehicelsPath = "Registro_Vehiculos.txt";

        Workbook workbook = new XSSFWorkbook();

        try {
            Sheet modelsSheet = workbook.createSheet("Marcas");

            // Create header row
            Row headerRowModels = modelsSheet.createRow(0);
            headerRowModels.createCell(0).setCellValue("Modelo");
            headerRowModels.createCell(1).setCellValue("Año");
            headerRowModels.createCell(2).setCellValue("Fundador");

            //Gets the data of the file
            List<Model> RowDataModels = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(modelsPath));
            for (String line : lines) {
                try {
                    String[] data = line.split(",");
                    RowDataModels.add(new Model(data[0], data[1], data[2]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Write data to sheet
            int rowNum = 1;
            for (var rowData : RowDataModels) {
                Row row = modelsSheet.createRow(rowNum++);
                int colNum = 0;
                row.createCell(colNum++).setCellValue(rowData.getPk());
                row.createCell(colNum++).setCellValue(rowData.year);
                row.createCell(colNum).setCellValue(rowData.founder);
            }

            // Adjust column width
            for (int i = 0; i < 3; i++) {
                modelsSheet.autoSizeColumn(i);
            }

            Sheet typesSheet = workbook.createSheet("Tipos");

            // Create header row
            Row headerRowTypes = typesSheet.createRow(0);
            headerRowTypes.createCell(0).setCellValue("Tipo");
            headerRowTypes.createCell(1).setCellValue("Año");

            //Gets the data of the file
            List<Type> RowDataTypes = new ArrayList<>();
            lines = Files.readAllLines(Paths.get(typesPath));
            for (String line : lines) {
                try{
                String[] data = line.split(",");
                RowDataTypes.add(new Type(data[0], data[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Write data to sheet
            rowNum = 1;
            for (var rowData : RowDataTypes) {
                Row row = typesSheet.createRow(rowNum++);
                int colNum = 0;
                row.createCell(colNum++).setCellValue(rowData.getPk());
                row.createCell(colNum).setCellValue(rowData.year);
            }

            // Adjust column width
            for (int i = 0; i < 3; i++) {
                typesSheet.autoSizeColumn(i);
            }

            Sheet linesSheet = workbook.createSheet("Lineas");

            // Create header row
            Row headerRowLines = linesSheet.createRow(0);
            headerRowLines.createCell(0).setCellValue("Modelo");
            headerRowLines.createCell(1).setCellValue("Descripción");
            headerRowLines.createCell(2).setCellValue("Año");

            //Gets the data of the file
            List<Line> RowDataLines = new ArrayList<>();
            lines = Files.readAllLines(Paths.get(linesPath));
            for (String line : lines) {
                try{
                String[] data = line.split(",");
                RowDataLines.add(new Line(data[0], data[1], data[2]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Write data to sheet
            rowNum = 1;
            for (var rowData : RowDataLines) {
                Row row = linesSheet.createRow(rowNum++);
                int colNum = 0;
                row.createCell(colNum++).setCellValue(rowData.getPk());
                row.createCell(colNum++).setCellValue(rowData.description);
                row.createCell(colNum).setCellValue(rowData.year);
            }

            // Adjust column width
            for (int i = 0; i < 3; i++) {
                linesSheet.autoSizeColumn(i);
            }

            Sheet vehiclesSheet = workbook.createSheet("Vehiculos");

            // Create header row
            Row headerRowVehicles = vehiclesSheet.createRow(0);
            headerRowVehicles.createCell(0).setCellValue("Placa");
            headerRowVehicles.createCell(1).setCellValue("Modelo");
            headerRowVehicles.createCell(2).setCellValue("Tipo");
            headerRowVehicles.createCell(3).setCellValue("Descripción");

            //Gets the data of the file
            List<Vehicle> RowDataVehicles = new ArrayList<>();
            lines = Files.readAllLines(Paths.get(vehicelsPath));
            for (String line : lines) {
                try{
                String[] data = line.split(",");
                RowDataVehicles.add(new Vehicle(data[0], data[1], data[2], data[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Write data to sheet
            rowNum = 1;
            for (var rowData : RowDataVehicles) {
                Row row = vehiclesSheet.createRow(rowNum++);
                int colNum = 0;
                row.createCell(colNum++).setCellValue(rowData.getPk());
                row.createCell(colNum++).setCellValue(rowData.model);
                row.createCell(colNum++).setCellValue(rowData.type);
                row.createCell(colNum).setCellValue(rowData.description);
            }

            // Adjust column width
            for (int i = 0; i < 3; i++) {
                vehiclesSheet.autoSizeColumn(i);
            }

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("Reporte.xlsx")) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Se ha creado el archivo Reporte.xlsx", "Creado", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No se pudo exportar los datos", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Close the workbook
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "No se pudo exportar los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
