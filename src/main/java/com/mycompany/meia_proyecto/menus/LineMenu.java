package com.mycompany.meia_proyecto.menus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.mycompany.meia_proyecto.MainFrame;
import com.mycompany.meia_proyecto.classes.FileManager;
import com.mycompany.meia_proyecto.classes.Line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;


public class LineMenu extends Menu {

    private final ArrayList<String> marcasList = new ArrayList<>();
    private final String filePath = "marcas_vehiculos.txt";

    public LineMenu(MainFrame parent) {
        super(parent);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        loadMarcasFromFile(); // Cargar marcas desde el archivo

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

        JLabel lModel = new JLabel("Marca");
        JComboBox<String> tModel = new JComboBox<>(marcasList.toArray(new String[0]));
        lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));
        tModel.setPreferredSize(new Dimension(100, 20));

        JLabel lDescription = new JLabel("Descripción");
        JTextField tDescription = new JTextField();
        lDescription.setBorder(new CompoundBorder(lDescription.getBorder(), tMargin));

        JLabel lYear = new JLabel("Año");
        JTextField tYear = new JTextField();
        lYear.setBorder(new CompoundBorder(lYear.getBorder(), tMargin));

        JButton save = new JButton("Guardar");

        // ActionListener para guardar la línea en el archivo
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = (String) tModel.getSelectedItem();
                String descripcion = tDescription.getText();
                String año = tYear.getText();
                Line line = new Line(marca, descripcion, año);

                // Verificar si ya existe la marca
                if (marcaYaRegistrada(marca)) {
                    JOptionPane.showMessageDialog(panel, "La marca ya está registrada. No se pueden duplicar marcas.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                // Guardar en el archivo
                try {
                    FileManager.saveToFile("lineas.txt", line);

                    tDescription.setText("");
                    tYear.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(title);
        panel.add(lModel);
        panel.add(tModel);
        panel.add(lDescription);
        panel.add(tDescription);
        panel.add(lYear);
        panel.add(tYear);
        panel.add(save);

        return panel;
    }

    private JPanel column_Actualizar(String txt) {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(txt);
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lModel = new JLabel("Marca");
        JComboBox<String> tModel = new JComboBox<>(marcasList.toArray(new String[0]));
        lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));
        tModel.setPreferredSize(new Dimension(100, 20));

        JLabel lDescription = new JLabel("Nueva Descripción");
        JTextField tDescription = new JTextField();
        lDescription.setBorder(new CompoundBorder(lDescription.getBorder(), tMargin));

        JLabel lYear = new JLabel("Nuevo Año");
        JTextField tYear = new JTextField();
        lYear.setBorder(new CompoundBorder(lYear.getBorder(), tMargin));

        JButton update = new JButton("Actualizar");

        // ActionListener para actualizar la línea en el archivo
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = (String) tModel.getSelectedItem();
                String descripcion = tDescription.getText();
                String año = tYear.getText();
                Line line = new Line(marca, descripcion, año);

                // Leer las líneas existentes y actualizar
                try {
                    FileManager.updateToFile("lineas.txt", line);

                    tDescription.setText("");
                    tYear.setText("");
                    JOptionPane.showMessageDialog(null,
                            "Linea actualizada exitosamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(title);
        panel.add(lModel);
        panel.add(tModel);
        panel.add(lDescription);
        panel.add(tDescription);
        panel.add(lYear);
        panel.add(tYear);
        panel.add(update);

        return panel;
    }

    private JPanel delete() {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Eliminar");
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lModel = new JLabel("Marca");
        JComboBox<String> tModel = new JComboBox<>(marcasList.toArray(new String[0]));
        lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));
        tModel.setPreferredSize(new Dimension(100, 20));

        JButton delete = new JButton("Eliminar");

        // ActionListener para eliminar la línea en el archivo
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = (String) tModel.getSelectedItem();

                try {
                    FileManager.deleteFromFile("lineas.txt", marca);
                    JOptionPane.showMessageDialog(null,
                            "Linea eliminada exitosamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(title);
        panel.add(lModel);
        panel.add(tModel);
        panel.add(delete);

        return panel;
    }

    private JPanel search() {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Buscar");
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lModel = new JLabel("Marca");
        JComboBox<String> tModel = new JComboBox<>(marcasList.toArray(new String[0]));
        lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));
        tModel.setPreferredSize(new Dimension(100, 20));

        JButton search = new JButton("Buscar");

        // ActionListener para buscar la línea en el archivo
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = (String) tModel.getSelectedItem();

                try {
                    String data = FileManager.retrieveDataByPK(marca, "lineas.txt");
                    JOptionPane.showMessageDialog(null,
                            data,
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(title);
        panel.add(lModel);
        panel.add(tModel);
        panel.add(search);

        return panel;
    }

    // Método para verificar si la marca ya está registrada
    private boolean marcaYaRegistrada(String marca) {
        try {
            return FileManager.getDataByPK(marca, "lineas.txt") != null;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void loadMarcasFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Separa por coma
                String marca = parts[0]; // Extrae la marca
                marcasList.add(marca); // Agrega solo la marca a la lista
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
