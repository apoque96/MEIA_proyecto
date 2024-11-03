package com.mycompany.meia_proyecto.menus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
import com.mycompany.meia_proyecto.classes.FileManager;
import com.mycompany.meia_proyecto.classes.Model;

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
                Model model = new Model(marca, year, fundador);

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
                    FileManager.saveToFile("marcas_vehiculos.txt", model);
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
                Model model = new Model(marca, year, fundador);

                // Validar que los campos no estén vacíos
                if (marca.isEmpty() || year.isEmpty() || fundador.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    FileManager.updateToFile("marcas_vehiculos.txt", model);
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
                FileManager.deleteFromFile("marcas_vehiculos.txt", marca);
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
