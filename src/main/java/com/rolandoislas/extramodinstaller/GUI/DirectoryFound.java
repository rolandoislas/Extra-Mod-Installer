package com.rolandoislas.extramodinstaller.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.rolandoislas.extramodinstaller.Main;
import com.rolandoislas.extramodinstaller.util.ApplicationState;
import com.rolandoislas.extramodinstaller.util.Directory;
import com.rolandoislas.extramodinstaller.util.Popup;
import com.rolandoislas.extramodinstaller.util.StateBasedApplication;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

public class DirectoryFound extends JPanel implements ApplicationState {

	private static final long serialVersionUID = -1016786632123113099L;
	private StateBasedApplication sba;
	private static final int ID = 0;

	/**
	 * Create the panel.
	 * @param frame 
	 */
	public DirectoryFound() {
		createComponents();
	}

	private void createComponents() {
		setBackground(UIManager.getColor("Panel.background"));
		SpringLayout sl_panel = new SpringLayout();
		setLayout(sl_panel);
		
		JLabel textRowOne = new JLabel();
		sl_panel.putConstraint(SpringLayout.WEST, textRowOne, 10, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, textRowOne, -127, SpringLayout.SOUTH, this);
		sl_panel.putConstraint(SpringLayout.EAST, textRowOne, -10, SpringLayout.EAST, this);
		textRowOne.setBackground(Color.WHITE);
		textRowOne.setText("Default install directory found.");
		textRowOne.setHorizontalAlignment(SwingConstants.CENTER);
		add(textRowOne);
		
		JTextArea textRowTwo = new JTextArea();
		textRowTwo.setLineWrap(true);
		textRowTwo.setOpaque(false);
		textRowTwo.setFocusable(false);
		textRowTwo.setEditable(false);
		textRowTwo.setBorder(null);
		sl_panel.putConstraint(SpringLayout.NORTH, textRowTwo, 6, SpringLayout.SOUTH, textRowOne);
		sl_panel.putConstraint(SpringLayout.WEST, textRowTwo, 0, SpringLayout.WEST, textRowOne);
		sl_panel.putConstraint(SpringLayout.EAST, textRowTwo, -10, SpringLayout.EAST, this);
		textRowTwo.setBackground(UIManager.getColor("Panel.background"));
		textRowTwo.setText("Use \"" + Main.defaultDir + "\" as install directory?");
		add(textRowTwo);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleClick("yes");
			}
		});
		sl_panel.putConstraint(SpringLayout.WEST, btnYes, 102, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnYes, -40, SpringLayout.SOUTH, this);
		sl_panel.putConstraint(SpringLayout.EAST, btnYes, -276, SpringLayout.EAST, this);
		add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleClick("no");
			}
		});
		sl_panel.putConstraint(SpringLayout.WEST, btnNo, 102, SpringLayout.EAST, btnYes);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNo, -40, SpringLayout.SOUTH, this);
		sl_panel.putConstraint(SpringLayout.EAST, btnNo, -102, SpringLayout.EAST, this);
		add(btnNo);
		
		JLabel titleText = new JLabel();
		sl_panel.putConstraint(SpringLayout.WEST, titleText, 10, SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.EAST, titleText, -10, SpringLayout.EAST, this);
		titleText.setBackground(Color.WHITE);
		titleText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleText.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel.putConstraint(SpringLayout.NORTH, titleText, 46, SpringLayout.NORTH, this);
		titleText.setText("Extra Mod Installer");
		add(titleText);
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public Container initialize(StateBasedApplication sba, JFrame frame) {
		this.sba = sba;
		return this;
	}
	
	protected void handleClick(String selection) {
		if(selection.equals("yes")) {
			Directory dir = new Directory(Main.defaultDir);
			if(dir.isValid()) {
				sba.setState(2);
			} else {
				new Popup("This is not a valid installation directory.", "Error");
			}
		} else if(selection.equals("no")) {
			sba.initState(new DirectoryNotFound(true));
			sba.setState(1);
		}
	}

}
