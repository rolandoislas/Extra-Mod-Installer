package com.rolandoislas.extramodinstaller.util;

import java.io.File;

import com.google.gson.JsonObject;

public class OS {
	
	private static String OSS;
	private static String homeDir;
	private static JsonObject config;
	private JsonObject defaultInstallLocation;
	
	public OS() {
		OSS = System.getProperty("os.name").toLowerCase();
		homeDir = System.getProperty("user.home");
		config = new Config().getConfig();
		defaultInstallLocation = config.get("defaultInstallLocation").getAsJsonObject();
	}
	
	public String getSys() {
		if(isWindows()) {
			return "win";
		} else if(isMac()) {
			return "mac";
		} else if(isUnix()) {
			return "nix";
		}
		return null;
	}

	private boolean isUnix() {
		return (OSS.indexOf("nix") >= 0 || OSS.indexOf("nux") >= 0 || OSS.indexOf("aix") > 0 );
	}

	private boolean isMac() {
		return (OSS.indexOf("mac") >= 0);
	}

	private boolean isWindows() {
		return (OSS.indexOf("win") >= 0);
	}

	public File getDir() {
		String sys = getSys();
		if(sys.equalsIgnoreCase("win")) {
			return getOSDir("win");
		} else if(sys.equalsIgnoreCase("mac")) {
			return getOSDir("mac");
		} else if(sys.equalsIgnoreCase("nix")) {
			return getOSDir("nix");
		}
		return null;
	}

	private File getOSDir(String OS) {
		JsonObject OSDefaultDir = defaultInstallLocation.get(OS).getAsJsonObject();
		String directory = OSDefaultDir.get("directory").getAsString();
		if(OSDefaultDir.get("useHomeDirectory").getAsBoolean()) {
			return new File(homeDir + "/" + directory);
		}
		return new File(directory);
	}
	
}
