package com.rolandoislas.extramodinstaller.util;

import java.io.File;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import com.rolandoislas.extramodinstaller.net.Download;

public class Install extends SwingWorker<Void,Void> {

	private ModList modList;
	private File installDir;
	private JTextArea textArea;

	public Install(File installDir, ModList modList, JTextArea textArea) {
		this.installDir = installDir;
		this.modList = modList;
		this.textArea = textArea;
	}

	private void doInstall() {
		addToConsole("Starting install.\n");
		installMods();
		installConfigs();
		addToConsole("\nFinished.\n");
	}

	private void addToConsole(String string) {
		textArea.append(string);
		int pos = textArea.getText().length() - 1;
		textArea.setCaretPosition(pos);
	}

	private void installConfigs() {
		addToConsole("\nDownloading config.\n\n");
		Map<Integer, String> map = modList.getConfigListMap();
		for(int i = 0; i < map.size(); i++) {
			String fileName = map.get(i);
			fileName = fileName.substring(1, fileName.length() - 1);
			addToConsole("-Downloading " + map.get(i) + "\n");
			Download dl = new Download(installDir, map.get(i), false);
			do {
				dl.get();
			} while (dl.isDownloading());
		}
	}

	private void installMods() {
			addToConsole("\nDownloading mods.\n\n");
		Map<Integer, String> map = modList.getModListMap();
		for(int i = 0; i < map.size(); i++) {
			String fileName = map.get(i);
			fileName = fileName.substring(1, fileName.length() - 1);
			addToConsole("-Downloading " + map.get(i) + "\n");
			Download dl = new Download(installDir, map.get(i), true);
			do {
				dl.get();
			} while (dl.isDownloading());
		}
	}

	@Override
	protected Void doInBackground() throws Exception {
		doInstall();
		return null;
	}

}
