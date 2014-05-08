package com.rolandoislas.extramodinstaller.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.rolandoislas.extramodinstaller.net.JSON;


public class ModList {
	
	private JSON json = new JSON();
	private JsonObject allList;
	
	public ModList() {
		allList = json.getList();
	}

	public Map<Integer, String> getModListMap() {
		HashMap<Integer, String> list = new HashMap<Integer, String>();
		for(int i = 0; i < allList.get("mod").getAsJsonArray().size(); i++) {
			String fileName = allList.get("mod").getAsJsonArray().get(i).getAsJsonObject().get("fileName").toString();
			fileName = fileName.substring(1, fileName.length() - 1);
			list.put(i, fileName);
		}
		return list;
	}

	public Map<Integer, String> getConfigListMap() {
		HashMap<Integer, String> list = new HashMap<Integer, String>();
		for(int i = 0; i < allList.get("config").getAsJsonArray().size(); i++) {
			String fileName = allList.get("config").getAsJsonArray().get(i).getAsJsonObject().get("fileName").toString();
			fileName = fileName.substring(1, fileName.length() - 1);
			String installDir = allList.get("config").getAsJsonArray().get(i).getAsJsonObject().get("install").toString();
			installDir = installDir.substring(1, installDir.length() - 1);
			String fullInstallLocation;
			if(installDir.length() > 0) {
				fullInstallLocation = installDir + "/" + fileName;
			} else {
				fullInstallLocation = fileName;
			}
			list.put(i, fullInstallLocation );
		}
		return list;
	}
	
	
}
