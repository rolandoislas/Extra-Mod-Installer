package com.rolandoislas.extramodinstaller.util;

import java.io.File;

public class Directory extends File {
	
	private static final long serialVersionUID = 564621869300918391L;
	private String fileString;

	public Directory(String fileString) {
		super(fileString);
		this.fileString = fileString;
	}

	public Directory(File file) {
		this(file.toString());
	}

	public boolean isValid() {
		File modDir = new File(fileString + "/mods");
		File configDir = new File(fileString + "/config");
		if(!modDir.exists() || !configDir.exists()) {
			return false;
		}
		return true;
	}
	
}
