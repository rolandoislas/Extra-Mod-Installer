package com.rolandoislas.extramodinstaller.util;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;

import com.rolandoislas.extramodinstaller.Main;

public class StateBasedApplication {
	
	private HashMap<Integer, ApplicationState> stateMap;
	private static JFrame frame;
	private Dimension windowPos;
	private int windowWidth;
	private int windowHeight;
	private String windowTitle;
	private int activeState;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Main window = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StateBasedApplication(String title) {
		stateMap = new HashMap<Integer, ApplicationState>();
		windowTitle = title;
	}
	
	public void setState(int stateID) {
		frame.setContentPane(stateMap.get(stateID).initialize(this, frame));
		frame.invalidate();
		frame.validate();
		activeState = stateID;
	}
	
	public void addState(ApplicationState frame) {
		int stateID = frame.getID();
		stateMap.put(stateID, frame);
	}
	
	public int getState() {
		return activeState;
	}
	
	public void initState(ApplicationState frame) {
		addState(frame);
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(windowPos.width, windowPos.height, windowWidth, windowHeight);
		frame.setResizable(false);
		frame.setTitle(windowTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setPosition(int x, int y) {
		windowPos = new Dimension(x, y);
	}
	
	public void setWidth(int x) {
		windowWidth = x;
	}
	
	public void setHeight(int y) {
		windowHeight = y;
	}
	
}
