package com.mycompany.meia_proyecto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import com.mycompany.meia_proyecto.menus.MainMenu;

public class MainFrame extends JFrame {

	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setTitle("Manejo E Implementación de Archivos");
		GridBagConstraints gbc = new GridBagConstraints();
		this.setMinimumSize(new Dimension(800, 450));
		this.getContentPane().setBackground(Color.CYAN);

		gbc.gridx = 0;
		gbc.weightx = 0.7;
		gbc.weighty = 1;

		MainMenu mMenu = new MainMenu();
		mMenu.setPreferredSize(new Dimension(480, 400));
		mMenu.setMinimumSize(new Dimension(480, 400));
		this.add(mMenu, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.3;

		Sidebar side = new Sidebar();
		side.setPreferredSize(new Dimension(200, 400));
		side.setMinimumSize(new Dimension(200, 400));
		this.add(side, gbc);

		this.setVisible(true);
	}
}
