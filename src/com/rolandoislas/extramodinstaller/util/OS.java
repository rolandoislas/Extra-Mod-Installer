package com.rolandoislas.extramodinstaller.util;

public class OS {
	
	private static String OSS = System.getProperty("os.name").toLowerCase();
	
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

	public String getDir() {
		String homeDir = System.getProperty("user.home");
		String technicRoot = "/modpacks/attack-of-the-bteam";
		String sys = getSys();
		if(sys.equalsIgnoreCase("win")) {
			return homeDir + "\\AppData\\Roaming\\.technic" + technicRoot.replace("/", "\\");
		} else if(sys.equalsIgnoreCase("mac")) {
			return homeDir + "/Library/Application Support/technic" + technicRoot;
		} else if(sys.equalsIgnoreCase("nix")) {
			return homeDir + "/.technic" + technicRoot;
		}
		return null;
	}
	
}
