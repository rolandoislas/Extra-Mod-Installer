package com.rolandoislas.extramodinstaller.util;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.rolandoislas.extramodinstaller.Main;

import java.awt.Component;

public class Popup extends JDialog{
	
	private static final long serialVersionUID = 4458921046155210010L;
	private static final int POPUP_WIDTH = 450;
	private static final int POPUP_HEIGHT = 100;

	public Popup(String message, int width, int height, String title) {
		this.setTitle(title);
		setBounds(Main.screenWidth / 2 - width / 2, Main.screenHeight / 2 - height / 2, width, height);
		setResizable(false);
		JLabel label = new JLabel(message);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(label);
		setVisible(true);
	}

	public Popup(String message, String title) {
		this(message, POPUP_WIDTH, POPUP_HEIGHT, title);
	}
	
}
