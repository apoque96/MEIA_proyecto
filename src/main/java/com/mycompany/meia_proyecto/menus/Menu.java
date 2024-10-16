package com.mycompany.meia_proyecto.menus;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Menu extends JPanel {
	protected JFrame parent;
	
	public Menu(JFrame parent) {
		this.parent = parent;
	}
}
