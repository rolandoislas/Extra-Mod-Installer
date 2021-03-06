package com.rolandoislas.extramodinstaller.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import com.google.gson.JsonObject;
import com.rolandoislas.extramodinstaller.net.Download;

public class Install extends SwingWorker<Void,Void> {

	private ModList modList;
	private File installDir;
	private JTextArea textArea;
	private JsonObject config = new Config().getConfig();

	public Install(File installDir, ModList modList, JTextArea textArea) {
		this.installDir = installDir;
		this.modList = modList;
		this.textArea = textArea;
	}

	private void doInstall() throws Exception {
		addToConsole("Starting install.\n");
		installFiles("mods");
		installFiles("config");
		installFiles("mods", true);
		installFiles("config", true);
		configureLauncher();
		addToConsole("\nFinished.\n");
	}

	private void configureLauncher() {
		boolean isAtLauncher = config.get("launcher").getAsJsonObject().get("isAtLauncher").getAsBoolean();
		if(isAtLauncher == true) {
			addToConsole("Updating ATLauncher instance mod list.");
			try {
				new ATLauncher(installDir).updateModList();
			} catch (FileNotFoundException e) {
				addToConsole("Failed to update ATLauncher mod list.");
				e.printStackTrace();
			}
		}
	}

	private void addToConsole(String string) {
		textArea.append(string);
		int pos = textArea.getText().length() - 1;
		textArea.setCaretPosition(pos);
	}

	private void installFiles(String fileType, boolean doRemove) throws Exception {
		String[] processNames = getProcessNames(doRemove);
		addToConsole("\n" + processNames[1] + " " + fileType + ".\n\n");
		Map<Integer, String> map = getMap(fileType, doRemove);
		for(int i = 0; i < map.size(); i++) {
			addToConsole("-" + processNames[1] + " " + map.get(i) + "\n");
			Remove rm = new Remove(installDir, map.get(i), fileType.equals("mods"));
			Download dl = new Download(installDir, map.get(i), fileType.equals("mods"));
			try {
				if(doRemove) {
					rm.doRemove();
				} else {
					dl.get();
				}
			} catch (Exception e) {
				addToConsole("--Failed to " + processNames[0] + " " + map.get(i) + ".\n");
				throw e;
			}
			addToConsole("--Successfully " + processNames[2] + " " + map.get(i) + ".\n");
		}
	}

	private Map<Integer, String> getMap(String fileType, boolean doRemove) {
		Map<Integer, String> map;
		if(doRemove) {
			if(fileType.equals("mods")) {
				map = modList.getModListMap("remove");
			} else {
				map = modList.getConfigListMap("remove");
			}
		} else {
			if(fileType.equals("mods")) {
				map = modList.getModListMap();
			} else {
				map = modList.getConfigListMap();
			}
		}
		return map;
	}
	
	private String[] getProcessNames(boolean doRemove) {
		if(doRemove) {
			String[] processName = {"delete", "Deleting", "deleted"};
			return processName;
		} else {
			String[] processName = {"download", "Downloading", "downloaded"};
			return processName;
		}
	}

	private void installFiles(String fileType) throws Exception {
		installFiles(fileType, false);
	}

	@Override
	protected Void doInBackground() throws Exception {
		doInstall();
		return null;
	}

}
