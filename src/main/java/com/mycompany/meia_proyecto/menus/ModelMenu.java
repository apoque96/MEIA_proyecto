package com.mycompany.meia_proyecto.menus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.mycompany.meia_proyecto.MainFrame;

public class ModelMenu extends Menu {
    
        
    // Lista para almacenar las marcas
    private List<String> marcas = new ArrayList<>();

    public ModelMenu(MainFrame parent) {
        super(parent);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        loadMarcasFromFile();

        this.add(column("Grabar"));
        this.add(column_Actualizar("Actualizar"));
        this.add(delete());
    }

    private JPanel column(String txt) {
        Border tMargin = new EmptyBorder(30, 0, 0, 0);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(txt);
        title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

        JLabel lModel = new JLabel("Marca");
        JTextField tModel = new JTextField();
        lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));
        tModel.setPreferredSize(new Dimension(100, 20));

        JLabel lYear = new JLabel("Año");
        JTextField tYear = new JTextField();
        lYear.setBorder(new CompoundBorder(lYear.getBorder(), tMargin));

        JLabel lFounder = new JLabel("Fundador");
        JTextField tFounder = new JTextField();
        lFounder.setBorder(new CompoundBorder(lFounder.getBorder(), tMargin));

        // Botón para guardar los datos
        JButton save = new JButton("Guardar");

        // Acción al hacer clic en el botón "Guardar"
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = tModel.getText().trim();
                String year = tYear.getText().trim();
                String fundador = tFounder.getText().trim();

                // Validar que los campos no estén vacíos
                if (marca.isEmpty() || year.isEmpty() || fundador.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que la marca no esté repetida
                if (marcas.contains(marca)) {
                    JOptionPane.showMessageDialog(null, "La marca ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Si no está repetida, agregarla a la lista
                marcas.add(marca);

                // Guardar la información en un archivo .txt
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("marcas_vehiculos.txt", true));
                    writer.write("Marca: " + marca + ", Año: " + year + ", Fundador: " + fundador);
                    writer.newLine();
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Marca guardada exitosamente.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Limpiar los campos de texto
                tModel.setText("");
                tYear.setText("");
                tFounder.setText("");
            }
        });

        panel.add(title);
        panel.add(lModel);
        panel.add(tModel);
        panel.add(lYear);
        panel.add(tYear);
        panel.add(lFounder);
        panel.add(tFounder);
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
        JTextField tModel = new JTextField();
        lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));
        tModel.setPreferredSize(new Dimension(100, 20));

        JLabel lYear = new JLabel("Año");
        JTextField tYear = new JTextField();
        lYear.setBorder(new CompoundBorder(lYear.getBorder(), tMargin));

        JLabel lFounder = new JLabel("Fundador");
        JTextField tFounder = new JTextField();
        lFounder.setBorder(new CompoundBorder(lFounder.getBorder(), tMargin));

        JButton save = new JButton("Actualizar");

        // Acción al hacer clic en el botón "Actualizar"
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = tModel.getText().trim();
                String year = tYear.getText().trim();
                String fundador = tFounder.getText().trim();

                // Validar que los campos no estén vacíos
                if (marca.isEmpty() || year.isEmpty() || fundador.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Leer todas las líneas del archivo
                    List<String> lines = new ArrayList<>();
                    BufferedWriter writer = null;
                    boolean found = false;

                    // Lee el archivo existente
                    BufferedReader reader = new BufferedReader(new FileReader("marcas_vehiculos.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Marca: " + marca)) {
                            // Si encontramos la marca, la reemplazamos
                            lines.add("Marca: " + marca + ", Año: " + year + ", Fundador: " + fundador);
                            found = true;
                        } else {
                            lines.add(line);
                        }
                    }
                    reader.close();

                    if (found) {
                        // Sobrescribimos el archivo con los datos actualizados
                        writer = new BufferedWriter(new FileWriter("marcas_vehiculos.txt", false));
                        for (String updatedLine : lines) {
                            writer.write(updatedLine);
                            writer.newLine();
                        }
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Marca actualizada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Marca no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Limpiar los campos de texto
                tModel.setText("");
                tYear.setText("");
                tFounder.setText("");
            }
        });

        panel.add(title);
        panel.add(lModel);
        panel.add(tModel);
        panel.add(lYear);
        panel.add(tYear);
        panel.add(lFounder);
        panel.add(tFounder);
        panel.add(save);

        return panel;
    }

private JPanel delete() {
    Border tMargin = new EmptyBorder(30, 0, 0, 0);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JLabel title = new JLabel("Eliminar");
    title.setBorder(new CompoundBorder(title.getBorder(), tMargin));

    JLabel lModel = new JLabel("Marca");
    JTextField tModel = new JTextField();
    lModel.setBorder(new CompoundBorder(lModel.getBorder(), tMargin));
    tModel.setPreferredSize(new Dimension(100, 20));

    // Botón para eliminar
    JButton delete = new JButton("Eliminar");

    // Acción al hacer clic en "Eliminar"
    delete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String marca = tModel.getText().trim();

            // Validar que el campo no esté vacío
            if (marca.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar una marca para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si la marca existe en la lista
            if (!marcas.contains(marca)) {
                JOptionPane.showMessageDialog(null, "La marca no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Eliminar la marca de la lista
            marcas.remove(marca);

            // Eliminar la marca del archivo .txt
            try {
                // Leer todo el contenido del archivo
                List<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader("marcas_vehiculos.txt"));
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    // Si la línea no contiene la marca a eliminar, la guardamos
                    if (!currentLine.contains("Marca: " + marca + ",")) {
                        lines.add(currentLine);
                    }
                }
                reader.close();

                // Reescribir el archivo con las líneas actualizadas (sin la marca eliminada)
                BufferedWriter writer = new BufferedWriter(new FileWriter("marcas_vehiculos.txt"));
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();

                JOptionPane.showMessageDialog(null, "Marca eliminada exitosamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Limpiar el campo de texto
            tModel.setText("");
        }
    });

    panel.add(title);
    panel.add(lModel);
    panel.add(tModel);
    panel.add(delete);

    return panel;
}

    private void saveToFile(String type, String year) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("marcas_vehiculos.txt", true))) {
            writer.write(type + "," + year);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFile(String type, String year) {
        try {
            ArrayList<String> updatedList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("marcas_vehiculos.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(type + ",")) {
                        updatedList.add(type + "," + year);
                    } else {
                        updatedList.add(line);
                    }
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("marcas_vehiculos.txt"))) {
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
            try (BufferedReader reader = new BufferedReader(new FileReader("marcas_vehiculos.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(type + ",")) {
                        updatedList.add(line);
                    }
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("marcas_vehiculos.txt"))) {
                for (String record : updatedList) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


private void loadMarcasFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("marcas_vehiculos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                marcas.add(parts[0]); // Agrega solo el tipo a la lista
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
