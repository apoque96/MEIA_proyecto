package com.mycompany.meia_proyecto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.mycompany.meia_proyecto.classes.FileManager;
import com.mycompany.meia_proyecto.menus.MainMenu;

public class Sidebar extends JPanel {
	public Sidebar(MainFrame parent, int type) {
		if (type == 0)
			initialSidebar();
		else
			returnSidebar(parent);
	}
	
	private void initialSidebar() {
		this.setOpaque(false);

		this.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.setOpaque(false);
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		JPanel bottom = new JPanel();
		bottom.setOpaque(false);
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

		Border margin = new EmptyBorder(30, 0, 10, 0);
		JLabel title = new JLabel("Venta Automotriz");
		title.setFont(new Font("Courier New", Font.BOLD, 18));
		title.setBorder(new CompoundBorder(title.getBorder(), margin));
		JLabel company = new JLabel("Dereck&Friends.co");
		company.setFont(new Font("Georgia", Font.BOLD, 14));
		company.setBorder(new CompoundBorder(company.getBorder(), margin));

		Border namesMargin = new EmptyBorder(10, 0, 10, 0);
		JLabel dereck = new JLabel("Dereck 1177223");
		dereck.setFont(new Font("Georgia", Font.PLAIN, 12));
		dereck.setBorder(new CompoundBorder(dereck.getBorder(), namesMargin));
		JLabel jose = new JLabel("Jose 1143323");
		jose.setFont(new Font("Georgia", Font.PLAIN, 12));
		jose.setBorder(new CompoundBorder(jose.getBorder(), namesMargin));
		JLabel paula = new JLabel("Paula 1006323");
		paula.setFont(new Font("Georgia", Font.PLAIN, 12));
		paula.setBorder(new CompoundBorder(paula.getBorder(), namesMargin));

		top.add(title);
		top.add(company);
		top.add(dereck);
		top.add(jose);
		top.add(paula);

		this.add(top, BorderLayout.NORTH);


		margin = new EmptyBorder(100, 0, 100, 0);
		JButton ExitButton = new JButton("Salir");
        ExitButton.setPreferredSize(new Dimension(90, 40));
        ExitButton.addActionListener(e -> System.exit(0));
		ExitButton.setBorder(new CompoundBorder(ExitButton.getBorder(), margin));
		ExitButton.setVerticalAlignment(SwingConstants.CENTER);
        bottom.add(ExitButton);

		JButton ReportButton = new JButton("Reporte");
		ReportButton.setVerticalAlignment(SwingConstants.CENTER);
		ReportButton.setPreferredSize(new Dimension(90, 40));
		ReportButton.addActionListener(e -> FileManager.exportToExcel());
		ReportButton.setBorder(new CompoundBorder(ReportButton.getBorder(), margin));
		bottom.add(ReportButton);

		this.add(bottom, BorderLayout.SOUTH);
	}
	
	private void returnSidebar(MainFrame parent) {
		this.setOpaque(false);

		this.setLayout(new BorderLayout());
		
		JButton ExitButton = new JButton("Regresar");
        ExitButton.setPreferredSize(new Dimension(10, 40));
		ExitButton.addActionListener(e -> parent.setMenu(new MainMenu(parent), 0));
        this.add(ExitButton, BorderLayout.SOUTH);
	}
}
