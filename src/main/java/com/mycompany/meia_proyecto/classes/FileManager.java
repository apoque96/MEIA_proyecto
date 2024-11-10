package com.mycompany.meia_proyecto.classes;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {

    public static void saveToFile(String path, Maintenance data) throws IOException {
        if (getDataByPK(data.getPk(), path) != null)
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
        List<String> indexData = List.of(getDataByPK(data.getPk(), path).split(","));
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
        List<String> indexData = List.of(getDataByPK(PK, path).split(","));
        int startingIndex = Integer.parseInt(indexData.get(1));
        int length = Integer.parseInt(indexData.get(2));

        // String builder is used to replace the record
        StringBuilder content = new StringBuilder(Files.readString(Paths.get(path)));
        content.replace(startingIndex, startingIndex + length, "");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content.toString());
        }

        int PKIndex = 0;

        for (String line : lines) {
            if (extractPrimaryKey(line).equals(indexData.get(0)))
                break;
            PKIndex++;
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

    public static void exportToExcel() {
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
                try {
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
                try {
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
            headerRowVehicles.createCell(0).setCellValue("Vin");
            headerRowVehicles.createCell(1).setCellValue("Placa");
            headerRowVehicles.createCell(2).setCellValue("Modelo");
            headerRowVehicles.createCell(3).setCellValue("Tipo");
            headerRowVehicles.createCell(4).setCellValue("Descripción");

            //Gets the data of the file
            List<Vehicle> RowDataVehicles = new ArrayList<>();
            lines = Files.readAllLines(Paths.get(vehicelsPath));
            for (String line : lines) {
                try {
                    String[] data = line.split(",");
                    RowDataVehicles.add(new Vehicle(data[0], data[1], data[2], data[3], data[4]));
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
                row.createCell(colNum++).setCellValue(rowData.plate);
                row.createCell(colNum++).setCellValue(rowData.model);
                row.createCell(colNum++).setCellValue(rowData.type);
                row.createCell(colNum).setCellValue(rowData.description);
            }

            // Adjust column width
            for (int i = 0; i < 4; i++) {
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
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo exportar los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updateIndexesFile(String path) throws IOException {
        String indexesPath = path.substring(0, path.length() - 4) + "_index.txt";
        List<RecordInfo> records = new ArrayList<>();

        // Read the original file and gather PK, index, and length information
        try (RandomAccessFile recordsFile = new RandomAccessFile(path, "r")) {
            String line;
            long index = 0;

            while ((line = recordsFile.readLine()) != null) {
                String pk = extractPrimaryKey(line);
                int length = line.getBytes().length;

                records.add(new RecordInfo(pk, index, length));
                index = recordsFile.getFilePointer();
            }
        }

        // Open the index file and prepare to write
        try (RandomAccessFile indexFile = new RandomAccessFile(indexesPath, "rw")) {
            // Sorted record lines
            List<String> sortedRecordsLines = Files.readAllLines(Paths.get(path));
            Collections.sort(sortedRecordsLines);

            // Write each record's information with the linked list structure
            RecordInfo previousRecord = null;

            // Reserve space for the first line (will update it after writing the records)
            indexFile.writeBytes("     \n");

            int index = 6;
            int pointer = 0;
            // Updates the indexToNext of each index
            for (String record : sortedRecordsLines) {
                if (previousRecord != null) {
                    if(!records.get(pointer).pk.equals(previousRecord.pk) && pointer==0){
                        previousRecord.indexToNext = index;
                        index += formatRecordLine(previousRecord).length() + 1;
                    }else {
                        index += formatRecordLine(previousRecord).length() + 1;
                        previousRecord.indexToNext = index;
                    }
                    pointer++;
                }

                previousRecord = records.stream().filter(r -> record.contains(r.pk)).collect(Collectors.toList()).get(0);
            }

            // Track the file pointer to the first record in the index file
            long firstRecordPosition = -1;

            // Saves to index file
            for (RecordInfo record : records) {
                if(record.pk.equals(extractPrimaryKey(sortedRecordsLines.get(0)))) {
                    firstRecordPosition = indexFile.getFilePointer();
                }
                indexFile.writeBytes(formatRecordLine(record) + "\n");
            }

            // Update the first line with the position of the first record in the index file
            indexFile.seek(0);
            indexFile.writeBytes(String.valueOf(firstRecordPosition));
        }
    }

    private static String extractPrimaryKey(String line) {
        return line.split(",")[0];
    }

    // Helper method to format each line for the index file
    private static String formatRecordLine(RecordInfo record) {
        return String.format("%s,%d,%d,%d",
                record.pk, record.index, record.length, record.indexToNext);
    }

    public static String getDataByPK(String PK,  String path) throws IOException {
        String indexesPath = path.substring(0, path.length() - 4) + "_index.txt";
        List<String> linesIndexes = Files.readAllLines(Paths.get(indexesPath));
        String fullFile = Files.readString(Paths.get(indexesPath));


        if (linesIndexes.size() == 0)
            return null;

        // pointer
        int index = Integer.valueOf(linesIndexes.get(0).trim());

        while (index != -1){
            String line = "";

            while (fullFile.charAt(index) != '\n'){
                line += fullFile.charAt(index);
                index++;
            }

            String[] s = line.split(",");
            if (s[0].equals(PK)) {
                return line;
            }

            index = Integer.valueOf(s[3]);
        }

        return null;
    }
}
