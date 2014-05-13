package com.rolandoislas.extramodinstaller.GUI;

import java.awt.Container;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.rolandoislas.extramodinstaller.Main;
import com.rolandoislas.extramodinstaller.util.ApplicationState;
import com.rolandoislas.extramodinstaller.util.ModList;
import com.rolandoislas.extramodinstaller.util.StateBasedApplication;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Install extends JPanel implements ApplicationState{

	private static final long serialVersionUID = 8274652567240002797L;
	private static final int ID = 3;
	private File installDir;
	private ModList modList;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 * @param installDir 
	 * @param ml 
	 * @param frame 
	 */
	public Install(File installDir, ModList modList) {
		this.installDir = installDir;
		this.modList = modList;
		createComponents();
	}
	
	public Install() {
	}

	private void createComponents() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel label = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, label, 47, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, label, -10, SpringLayout.EAST, this);
		label.setText(Main.APP_NAME);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label.setBackground(Color.WHITE);
		add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 206, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public Container initialize(final StateBasedApplication sba, JFrame frame) {
		com.rolandoislas.extramodinstaller.util.Install installUtil = new com.rolandoislas.extramodinstaller.util.Install(installDir, modList, textArea);
		installUtil.execute();
		return this;
	}

}
