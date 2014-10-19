package com.rolandoislas.extramodinstaller.GUI;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Container;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import com.rolandoislas.extramodinstaller.util.ApplicationState;
import com.rolandoislas.extramodinstaller.util.Directory;
import com.rolandoislas.extramodinstaller.util.Popup;
import com.rolandoislas.extramodinstaller.util.StateBasedApplication;

import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class DirectoryNotFound extends JPanel implements ApplicationState{

	private static final long serialVersionUID = -8529796905674304622L;
	private static final int ID = 1;
	private boolean userNavigated;
	private JFrame frame;
	private StateBasedApplication sba;
	private static final int POPUP_WIDTH = 450;
	private static final int POPUP_HEIGHT = 100;

	/**
	 * Create the panel.
	 * @param frame 
	 * @param userNavigated 
	 */

	public DirectoryNotFound(boolean userNavigated) {
		this.userNavigated = userNavigated;
		createComponents();
	}
	
	public DirectoryNotFound() {
		this(false);
	}
	
	private void createComponents() {
		setBackground(UIManager.getColor("Panel.background"));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel label = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, label, 46, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, label, -10, SpringLayout.EAST, this);
		label.setText("Extra Mod Installer");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label.setBackground(Color.WHITE);
		add(label);
		
		JLabel lblDefaultInstallDirectory = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, lblDefaultInstallDirectory, 60, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.WEST, lblDefaultInstallDirectory, 0, SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.EAST, lblDefaultInstallDirectory, 0, SpringLayout.EAST, label);
		lblDefaultInstallDirectory.setText(getRowOneText());
		lblDefaultInstallDirectory.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefaultInstallDirectory.setBackground(Color.WHITE);
		add(lblDefaultInstallDirectory);
		
		JLabel lblWouldYouLike = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, lblWouldYouLike, 6, SpringLayout.SOUTH, lblDefaultInstallDirectory);
		springLayout.putConstraint(SpringLayout.WEST, lblWouldYouLike, 0, SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.EAST, lblWouldYouLike, 0, SpringLayout.EAST, label);
		lblWouldYouLike.setText("Select install location.");
		lblWouldYouLike.setHorizontalAlignment(SwingConstants.CENTER);
		lblWouldYouLike.setBackground(Color.WHITE);
		add(lblWouldYouLike);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBrowse();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnBrowse, 45, SpringLayout.SOUTH, lblWouldYouLike);
		springLayout.putConstraint(SpringLayout.EAST, btnBrowse, -191, SpringLayout.EAST, this);
		add(btnBrowse);
	}

	protected void doBrowse() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			Directory dir = new Directory(file);
			if(dir.isValid()) {
				sba.initState(new Confirm(dir));
				sba.setState(2);
			}
			else {
				new Popup("This is not a valid installation directory.", POPUP_WIDTH, POPUP_HEIGHT, "Error");
			}
		}
	}

	private String getRowOneText() {
		if(userNavigated) {
			return "Using custom install location!";
		}
		return "Default install directory not found.";
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public Container initialize(StateBasedApplication sba, JFrame frame) {
		this.sba = sba;
		this.frame = frame;
		return this;
	}
}
