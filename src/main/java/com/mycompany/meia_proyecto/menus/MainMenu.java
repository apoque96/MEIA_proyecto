package com.mycompany.meia_proyecto.menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mycompany.meia_proyecto.MainFrame;

public class MainMenu extends Menu {

	public MainMenu(MainFrame parent) {
		super(parent);
		this.setLayout(new GridLayout());

		JPanel container = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(25, 25, 25, 25);

		int width = 90;
		int height = 40;

		// Creates labels
		JLabel title = new JLabel("Mantenimientos");
		title.setFont(new Font("Georgia", Font.BOLD, 14));
		title.setPreferredSize(new Dimension(130, height));
		JLabel options = new JLabel("Elige una opción");
		options.setFont(new Font("Georgia", Font.PLAIN, 12));

		// Creates buttons

		JButton ModelButton = new JButton("Modelo");
		ModelButton.setPreferredSize(new Dimension(width, height));
		ModelButton.addActionListener(e -> parent.setMenu(new ModelMenu(parent), 1));

		JButton TypeButton = new JButton("Tipo");
		TypeButton.setPreferredSize(new Dimension(width, height));
		TypeButton.addActionListener(e -> parent.setMenu(new TypeMenu(parent), 1));

		JButton LineButton = new JButton("Línea");
		LineButton.setPreferredSize(new Dimension(width, height));
		LineButton.addActionListener(e -> parent.setMenu(new LineMenu(parent), 1));

		JButton VehicleButton = new JButton("Vehículo");
		VehicleButton.setPreferredSize(new Dimension(width, height));
		VehicleButton.addActionListener(e -> parent.setMenu(new VehicleMenu(parent), 1));

		gbc.gridx = 1;
		gbc.gridy = 0;
		container.add(title, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		container.add(options, gbc);

		gbc.gridy = 2;
		container.add(ModelButton, gbc);

		gbc.gridx = 2;
		container.add(TypeButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		container.add(LineButton, gbc);

		gbc.gridx = 2;
		container.add(VehicleButton, gbc);

		this.add(container);
	}
}
