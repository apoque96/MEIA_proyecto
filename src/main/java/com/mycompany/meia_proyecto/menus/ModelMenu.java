package com.mycompany.meia_proyecto.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.mycompany.meia_proyecto.MainFrame;

public class ModelMenu extends Menu {

	public ModelMenu(MainFrame parent) {
		super(parent);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.add(column("Grabar"));
		this.add(column("Actualizar"));
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

		// Empty for button top margin
		JLabel empty = new JLabel("");
		empty.setBorder(new CompoundBorder(empty.getBorder(), tMargin));
		JButton save = new JButton("Guardar");

		panel.add(title);
		panel.add(lModel);
		panel.add(tModel);
		panel.add(lYear);
		panel.add(tYear);
		panel.add(lFounder);
		panel.add(tFounder);
		panel.add(empty);
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

		// Empty for button top margin
		JLabel empty = new JLabel("");
		empty.setBorder(new CompoundBorder(empty.getBorder(), new EmptyBorder(162, 0, 0, 0)));
		JButton save = new JButton("Guardar");

		panel.add(title);
		panel.add(lModel);
		panel.add(tModel);
		panel.add(empty);
		panel.add(save);

		return panel;
	}
}
