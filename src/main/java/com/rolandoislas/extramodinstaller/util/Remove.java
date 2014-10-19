package com.rolandoislas.extramodinstaller.util;

import java.io.File;
import java.io.IOException;

public class Remove {

	private File installDir;
	private String fileName;
	private boolean isMod;

	public Remove(File installDir, String fileName, boolean isMod) {
		this.installDir = installDir;
		this.fileName = fileName;
		this.isMod = isMod;
	}

	public void doRemove() throws IOException {
		String specDir;
		if(isMod) {
			specDir = "mods";
		} else {
			specDir = "config";
		}
		File file = new File(installDir + "/" + specDir + "/" + fileName);
		if(file != null) {
			file.delete();
		}
	}

}
