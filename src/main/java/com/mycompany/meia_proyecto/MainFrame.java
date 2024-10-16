package com.mycompany.meia_proyecto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import com.mycompany.meia_proyecto.menus.MainMenu;

public class MainFrame extends JFrame {

	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
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
		side.setPreferredSize(new Dimension(160, 400));
		side.setMinimumSize(new Dimension(160, 400));
		this.add(side, gbc);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int width = getWidth();
				int heigth = getHeight();
				mMenu.setPreferredSize(new Dimension((int) (width * 0.6), (int) (heigth - 50)));
				side.setPreferredSize(new Dimension((int) (width * 0.2), (int) (heigth - 50)));
			}
		});

		this.setVisible(true);
	}
}
