package com.mycompany.meia_proyecto.menus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.mycompany.meia_proyecto.MainFrame;
import com.mycompany.meia_proyecto.classes.FileManager;
import com.mycompany.meia_proyecto.classes.Vehicle;

public class VehicleMenu extends Menu {
    private final ArrayList<String> marcasList = new ArrayList<>();
    private final ArrayList<String> tiposList = new ArrayList<>();
    private final String marcasFilePath = "marcas_vehiculos.txt";
    private final String tiposFilePath = "types.txt";
    private final String registroFilePath = "Registro_Vehiculos.txt";

    public VehicleMenu(MainFrame parent) {
        super(parent);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        loadMarcasFromFile(); // Cargar marcas desde el archivo
        loadTiposFromFile(); // Cargar tipos desde el archivo

        this.add(column_Grabar("Grabar"));
        this.add(column_Actualizar("Actualizar"));
        this.add(delete());
        this.add(search());
    }

    private JPanel column_Grabar(String txt) {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(txt);
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lVin = new JLabel("VIN");
        JTextField tVin = new JTextField();
        lVin.setBorder(new CompoundBorder(lVin.getBorder(), tMargin));

        JLabel lModel = new JLabel("Marca");
        JComboBox<String> tModel = new JComboBox<>(marcasList.toArray(new String[0]));
        lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));
        tModel.setPreferredSize(new Dimension(100, 20));

        JLabel lType = new JLabel("Tipo de auto");
        JComboBox<String> tType = new JComboBox<>(tiposList.toArray(new String[0]));
        lType.setBorder(new CompoundBorder(lType.getBorder(), tMargin));

        JLabel lDescription = new JLabel("Descripción");
        JTextField tDescription = new JTextField();
        lDescription.setBorder(new CompoundBorder(lDescription.getBorder(), tMargin));

        JLabel lPlate = new JLabel("Placa");
        JTextField tPlate = new JTextField();
        lPlate.setBorder(new CompoundBorder(lPlate.getBorder(), tMargin));

        JButton save = new JButton("Guardar");
        save.addActionListener(e -> {
            String vin = tVin.getText();
            String marca = (String) tModel.getSelectedItem();
            String tipo = (String) tType.getSelectedItem();
            String descripcion = tDescription.getText();
            String placa = tPlate.getText();
            Vehicle vehicle = new Vehicle(vin, marca, tipo, descripcion, placa);

            try {
                FileManager.saveToFile(registroFilePath, vehicle);

                tDescription.setText("");
                tPlate.setText("");
                tVin.setText("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(title);
        panel.add(lVin);
        panel.add(tVin);
        panel.add(lModel);
        panel.add(tModel);
        panel.add(lType);
        panel.add(tType);
        panel.add(lDescription);
        panel.add(tDescription);
        panel.add(lPlate);
        panel.add(tPlate);
        panel.add(save);

        return panel;
    }

    private JPanel column_Actualizar(String txt) {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(txt);
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lVin = new JLabel("VIN");
        JTextField tVin = new JTextField();
        lVin.setBorder(new CompoundBorder(lVin.getBorder(), tMargin));

        JLabel lModel = new JLabel("Marca");
        JComboBox<String> tModel = new JComboBox<>(marcasList.toArray(new String[0]));
        lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));

        JLabel lType = new JLabel("Tipo de auto");
        JComboBox<String> tType = new JComboBox<>(tiposList.toArray(new String[0]));
        lType.setBorder(new CompoundBorder(lType.getBorder(), tMargin));

        JLabel lDescription = new JLabel("Nueva Descripción");
        JTextField tDescription = new JTextField();
        lDescription.setBorder(new CompoundBorder(lDescription.getBorder(), tMargin));

        JLabel lPlate = new JLabel("Placa");
        JTextField tPlate = new JTextField();
        lPlate.setBorder(new CompoundBorder(lPlate.getBorder(), tMargin));

        JButton update = new JButton("Actualizar");
        update.addActionListener(e -> {
            String vin = tVin.getText();
            String marca = (String) tModel.getSelectedItem();
            String tipo = (String) tType.getSelectedItem();
            String descripcion = tDescription.getText();
            String placa = tPlate.getText();
            Vehicle vehicle = new Vehicle(vin, marca, tipo, descripcion, placa);

            // Leer las líneas existentes y actualizar
            try {
                FileManager.updateToFile(registroFilePath, vehicle);

                tDescription.setText("");
                tPlate.setText("");
                JOptionPane.showMessageDialog(null,
                        "Vehiculo actualizado exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "No se pudo actualizar el vehiculo",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(title);
        panel.add(lVin);
        panel.add(tVin);
        panel.add(lModel);
        panel.add(tModel);
        panel.add(lType);
        panel.add(tType);
        panel.add(lDescription);
        panel.add(tDescription);
        panel.add(lPlate);
        panel.add(tPlate);
        panel.add(update);

        return panel;
    }

    private JPanel delete() {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Eliminar");
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lVin = new JLabel("VIN");
        JTextField tVin = new JTextField();
        lVin.setBorder(new CompoundBorder(lVin.getBorder(), tMargin));

        JButton delete = new JButton("Eliminar");
        delete.addActionListener(e -> {
            String placa = tVin.getText();

            // Leer las líneas existentes y eliminar
            try {
                FileManager.deleteFromFile(registroFilePath, placa);
                JOptionPane.showMessageDialog(null,
                        "Vehiculo eliminado exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(title);
        panel.add(lVin);
        panel.add(tVin);
        panel.add(delete);

        return panel;
    }

    private JPanel search() {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Buscar");
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lVin = new JLabel("VIN");
        JTextField tVin = new JTextField();
        lVin.setBorder(new CompoundBorder(lVin.getBorder(), tMargin));

        JButton delete = new JButton("Buscar");
        delete.addActionListener(e -> {
            String vin = tVin.getText();

            // Leer las líneas existentes y eliminar
            try {
                var data = FileManager.retrieveDataByPK(vin, registroFilePath);
                    JOptionPane.showMessageDialog(null,
                            data,
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "No se encontró el vehiculo",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(title);
        panel.add(lVin);
        panel.add(tVin);
        panel.add(delete);

        return panel;
    }

    private void loadMarcasFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(marcasFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String marca = parts[0].trim();
                marcasList.add(marca);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTiposFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(tiposFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String tipo = parts[0].trim();
                tiposList.add(tipo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}