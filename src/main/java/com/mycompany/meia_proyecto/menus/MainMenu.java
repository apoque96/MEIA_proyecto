package com.mycompany.meia_proyecto.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JPanel {

	public MainMenu() {
        //this.setOpaque(false);
        
        this.setLayout(new GridLayout());
        
        Border lineBorder = BorderFactory.createLineBorder(Color.black, 5);
        this.setBorder(lineBorder);
        
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        container.setOpaque(false);
        
        //Creates buttons
        int width = 90;
        int height = 40;
        
        JButton ModelButton = new JButton("Modelo");
        ModelButton.setPreferredSize(new Dimension(width, height));
        
        JButton TypeButton = new JButton("Tipo");
        TypeButton.setPreferredSize(new Dimension(width, height));
        
        JButton LineButton = new JButton("Línea");
        LineButton.setPreferredSize(new Dimension(width, height));
        
        JButton VehicleButton = new JButton("Vehículo");
        VehicleButton.setPreferredSize(new Dimension(width, height));
        
        container.add(ModelButton);
        container.add(TypeButton);
        container.add(LineButton);
        container.add(VehicleButton);
        
        this.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent e) {
        		
        	}
        });
        
        this.add(container);
	}
}
