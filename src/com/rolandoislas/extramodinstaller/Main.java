package com.rolandoislas.extramodinstaller;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;

import com.rolandoislas.extramodinstaller.GUI.Confirm;
import com.rolandoislas.extramodinstaller.GUI.DirectoryFound;
import com.rolandoislas.extramodinstaller.GUI.DirectoryNotFound;
import com.rolandoislas.extramodinstaller.GUI.Install;
import com.rolandoislas.extramodinstaller.util.OS;
import com.rolandoislas.extramodinstaller.util.StateBasedApplication;

public class Main extends StateBasedApplication{

	public static final String APP_NAME = "Extra Mod Installer";
	private static final double VERSION = 0.3;
	private static final int WIDTH = 450;
	private static final int HEIGHT = 350;
	
	private OS OS = new OS();
	public static int screenWidth;
	public static int screenHeight;
	public static File defaultDir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		super(APP_NAME + " " + VERSION);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		setPosition(screenWidth / 2 - WIDTH / 2, screenHeight / 2 - HEIGHT / 2);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		initialize();
		defaultDir = OS.getDir();
		addStates();
		promptInstallLocation();
	}

	private void addStates() {
		addState(new DirectoryFound());
		addState(new DirectoryNotFound());
		addState(new Confirm());
		addState(new Install());
	}

	public void promptInstallLocation() {
		if(defaultDirectoryExists()) {
			setState(0);
		} else {
			setState(1);
		}
	}

	public static boolean defaultDirectoryExists() {
		File file = defaultDir;
		return file.exists();
	}

	
}
