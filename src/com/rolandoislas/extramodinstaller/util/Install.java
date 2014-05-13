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

	private void doInstall() throws Exception {
		addToConsole("Starting install.\n");
		installFiles("mods");
		installFiles("config");
		addToConsole("\nFinished.\n");
	}

	private void addToConsole(String string) {
		textArea.append(string);
		int pos = textArea.getText().length() - 1;
		textArea.setCaretPosition(pos);
	}

	private void installFiles(String fileType) throws Exception {
		addToConsole("\nDownloading " + fileType + ".\n\n");
		Map<Integer, String> map;
		if(fileType.equals("mods")) {
			map = modList.getModListMap();
		} else {
			map = modList.getConfigListMap();
		}
		for(int i = 0; i < map.size(); i++) {
			addToConsole("-Downloading " + map.get(i) + "\n");
			Download dl = new Download(installDir, map.get(i), fileType.equals("mods"));
			do {
				try {
					dl.get();
				} catch (Exception e) {
					addToConsole("--Failed to download " + map.get(i) + ".");
					throw e;
				}
			} while (dl.isDownloading());
			addToConsole("--Successfully downloaded " + map.get(i) + ".");
		}
	}

	@Override
	protected Void doInBackground() throws Exception {
		doInstall();
		return null;
	}

}
