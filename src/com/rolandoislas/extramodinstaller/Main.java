package com.rolandoislas.extramodinstaller;

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
	private static final double VERSION = 0.1;
	private static final int WIDTH = 450;
	private static final int HEIGHT = 350;
	public static final String LIST_URL = "http://minecraft.rolandoislas.com/inc/mod/list.json";
	public static final String MOD_ROOT_URL = "http://minecraft.rolandoislas.com/inc/mod/mod/";
	public static final String CONFIG_ROOT_URL = "http://minecraft.rolandoislas.com/inc/mod/config/";
	
	private OS OS = new OS();
	public static int screenWidth;
	public static int screenHeight;
	public static String defaultDir;

	

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
		File file = new File(defaultDir);
		return file.exists();
	}

	
}
