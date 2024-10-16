package com.mycompany.meia_proyecto;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JPanel;

public class Sidebar extends JPanel {
	public Sidebar() {
		this.setOpaque(false);

		this.setLayout(new GridBagLayout());
		Label title = new Label("Venta Automotriz");

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;

		this.add(title, gbc);
	}
}
