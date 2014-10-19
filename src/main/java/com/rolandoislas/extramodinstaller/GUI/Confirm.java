package com.rolandoislas.extramodinstaller.GUI;

import java.awt.Container;
import java.io.File;
import java.util.Map;

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
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Confirm extends JPanel implements ApplicationState {

	private static final long serialVersionUID = -3897602267297936019L;
	private static final int ID = 2;
	private File installDir;
	private String installTextString;
	private ModList ml;
	private StateBasedApplication sba;

	/**
	 * Create the panel.
	 * @param directory 
	 */
	public Confirm(File directory) {
		installDir = directory;
	}
	
	private void generateInstallTextString() {
		Map<Integer, String> modList = ml.getModListMap();
		Map<Integer, String> configList = ml.getConfigListMap();
		Map<Integer, String> modListRemove = ml.getModListMap("remove");
		Map<Integer, String> configListRemove = ml.getConfigListMap("remove");
		installTextString = "Directory selected for installation:\n" + installDir + "\n";
		installTextString += "\nWARNING: Any existing files will be overwritten.\n";
		installTextString += "\nThe following mods will be installed to your mods folder:\n\n";
		char bullet = '\u2022';
		String preFix = "     " + Character.toString(bullet) + " ";
		for(int i = 0; i < modList.size(); i++) {
			installTextString += preFix + modList.get(i) + "\n";
		}
		installTextString += "\n\nThe following configs will be installed to your config folder:\n\n";
		for(int i = 0; i < configList.size(); i++) {
			installTextString += preFix + configList.get(i) + "\n";
		}
		installTextString += "\n\nThe following mods will be deleted from your mods folder:\n\n";
		for(int i = 0; i < modListRemove.size(); i++) {
			installTextString += preFix + modListRemove.get(i) + "\n";
		}
		installTextString += "\n\nThe following configs will be deleted from your config folder:\n\n";
		for(int i = 0; i < configListRemove.size(); i++) {
			installTextString += preFix + configListRemove.get(i) + "\n";
		}
	}

	private void createComponents() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel label = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, label, 46, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, label, -10, SpringLayout.EAST, this);
		label.setText(Main.APP_NAME);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label.setBackground(Color.WHITE);
		add(label);
		
		JTextArea installText = new JTextArea();
		installText.setSize(new Dimension(430, 50));
		installText.setLineWrap(true);
		installText.setEditable(false);
		installText.setText(installTextString);
		springLayout.putConstraint(SpringLayout.WEST, installText, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, installText, -23, SpringLayout.EAST, this);
		installText.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, installText, 6, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.SOUTH, installText, -56, SpringLayout.SOUTH, this);
		add(installText);
		
		final JScrollPane scrollPane = new JScrollPane(installText);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -56, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -5, SpringLayout.EAST, this);
		add(scrollPane);
		
		SwingUtilities.invokeLater(new Runnable()
		{
		    public void run()
		    {
		        scrollPane.getViewport().setViewPosition( new Point(0, 0) );
		    }
		});
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleButton();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -43, SpringLayout.EAST, this);
		add(btnNewButton);
	}

	protected void handleButton() {
		sba.initState(new Install(installDir, ml));
		sba.setState(3);
	}

	public Confirm() {
		this(Main.defaultDir);
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public Container initialize(StateBasedApplication sba, JFrame frame) {
		this.sba = sba;
		ml = new ModList();
		generateInstallTextString();
		createComponents();
		return this;
	}
}
