package com.mycompany.meia_proyecto.menus;

import java.awt.GridLayout;

import javax.swing.JLabel;

import com.mycompany.meia_proyecto.MainFrame;

public class VehicleMenu extends Menu {
	
	public VehicleMenu(MainFrame parent) {
		super(parent);
        this.setLayout(new GridLayout());
        
        this.add(new JLabel("Vehiculo"));
	}
}
