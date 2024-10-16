package com.mycompany.meia_proyecto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

import com.mycompany.meia_proyecto.menus.MainMenu;
import com.mycompany.meia_proyecto.menus.Menu;
import com.mycompany.meia_proyecto.menus.ModelMenu;

public class MainFrame extends JFrame {

	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setTitle("Manejo E Implementación de Archivos");
		this.setMinimumSize(new Dimension(800, 450));
		this.getContentPane().setBackground(Color.CYAN);

		this.setMenu(new MainMenu(this));

		this.setVisible(true);
	}
	
	public void setMenu(Menu menu) {
		this.getContentPane().removeAll();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 0.7;
		gbc.weighty = 1;

		menu.setPreferredSize(new Dimension(480, 400));
		menu.setMinimumSize(new Dimension(480, 400));
		Border lineBorder = BorderFactory.createLineBorder(Color.black, 5);
        menu.setBorder(lineBorder);
		this.add(menu, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.3;

		Sidebar side = new Sidebar();
		side.setPreferredSize(new Dimension(200, 400));
		side.setMinimumSize(new Dimension(200, 400));
		this.add(side, gbc);
		
		this.validate();
	}
}
