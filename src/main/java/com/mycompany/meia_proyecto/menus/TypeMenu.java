package com.mycompany.meia_proyecto.menus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Import para mensajes emergentes
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import com.mycompany.meia_proyecto.MainFrame;

public class TypeMenu extends Menu {
    
    private ArrayList<String> typesList = new ArrayList<>();
    
    private final String filePath = "types.txt";
    
    public TypeMenu(MainFrame parent) {
        super(parent);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        loadTypesFromFile(); // Cargar registros del archivo
        
        this.add(column_Grabar("Grabar"));
        this.add(column_Actualizar("Actualizar"));
        this.add(delete());
    }
    
    private JPanel column_Grabar(String txt) {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(txt);
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lType = new JLabel("Tipo de auto");
        JTextField tType = new JTextField();
        lType.setBorder(new CompoundBorder(lType.getBorder(), tMargin));
        tType.setPreferredSize(new Dimension(100, 20));

        JLabel lYear = new JLabel("Año");
        JTextField tYear = new JTextField();
        lYear.setBorder(new CompoundBorder(lYear.getBorder(), tMargin));

        JButton save = new JButton("Guardar");
        
        save.addActionListener(e -> {
            String type = tType.getText();
            String year = tYear.getText();
            if (!type.isEmpty() && !year.isEmpty() && !typesList.contains(type)) {
                typesList.add(type);
                saveToFile(type, year);
                JOptionPane.showMessageDialog(null, "Tipo de auto guardado exitosamente"); // Mensaje emergente
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos o verifique que el tipo no exista"); // Mensaje de error
            }
            
             // Limpiar los campos de texto
                tType.setText("");
                tYear.setText("");
                
        });

        panel.add(title);
        panel.add(lType);
        panel.add(tType);
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

        JLabel lType = new JLabel("Tipo de auto");
        JTextField tType = new JTextField();
        lType.setBorder(new CompoundBorder(lType.getBorder(), tMargin));
        tType.setPreferredSize(new Dimension(100, 20));

        JLabel lYear = new JLabel("Año");
        JTextField tYear = new JTextField();
        lYear.setBorder(new CompoundBorder(lYear.getBorder(), tMargin));

        JButton update = new JButton("Actualizar");
        update.addActionListener(e -> {
            String type = tType.getText();
            String year = tYear.getText();
            if (!type.isEmpty() && !year.isEmpty() && typesList.contains(type)) {
                updateFile(type, year);
                JOptionPane.showMessageDialog(null, "Tipo de auto actualizado exitosamente"); // Mensaje emergente
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos o verifique que el tipo exista"); // Mensaje de error
            }
            
                tType.setText("");
                tYear.setText("");
        });

        panel.add(title);
        panel.add(lType);
        panel.add(tType);
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

        JLabel lType = new JLabel("Tipo de auto");
        JTextField tType = new JTextField();
        lType.setBorder(new CompoundBorder(lType.getBorder(), tMargin));
        tType.setPreferredSize(new Dimension(100, 20));

        JButton delete = new JButton("Eliminar");
        delete.addActionListener(e -> {
            String type = tType.getText();
            if (!type.isEmpty() && typesList.contains(type)) {
                typesList.remove(type);
                deleteFromFile(type);
                JOptionPane.showMessageDialog(null, "Tipo de auto eliminado exitosamente"); // Mensaje emergente
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un tipo de auto existente"); // Mensaje de error
            }
            
                        // Limpiar el campo de texto
            tType.setText("");
            
        });

        panel.add(title);
        panel.add(lType);
        panel.add(tType);
        panel.add(delete);

        return panel;
    }

    private void saveToFile(String type, String year) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(type + "," + year);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFile(String type, String year) {
        try {
            ArrayList<String> updatedList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(type + ",")) {
                        updatedList.add(type + "," + year);
                    } else {
                        updatedList.add(line);
                    }
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String record : updatedList) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromFile(String type) {
        try {
            ArrayList<String> updatedList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(type + ",")) {
                        updatedList.add(line);
                    }
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String record : updatedList) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTypesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                typesList.add(parts[0]); // Agrega solo el tipo a la lista
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
