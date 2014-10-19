package com.rolandoislas.extramodinstaller.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ATLauncher {
	
	private static final String INSTANCE_JSON = "instance.json";
	private File installDir;
	private JsonObject instanceJson;
	private String instanceJsonFullPath;
	private ModList modList = new ModList();
	private JsonParser jsonParser;

	public ATLauncher(File installDir) throws FileNotFoundException {
		this.installDir = installDir;
		getInstanceJSON();
	}
	
	private void getInstanceJSON() throws FileNotFoundException {
		instanceJsonFullPath = installDir + "/" + INSTANCE_JSON;
		File instanceJsonFile = new File(instanceJsonFullPath);
		Scanner scanner = new Scanner(instanceJsonFile);
		String config = scanner.useDelimiter("\\Z").next();
		jsonParser = new JsonParser();
		JsonObject json = jsonParser.parse(config).getAsJsonObject();
		scanner.close();
		instanceJson = json;
	}

	public void updateModList() throws FileNotFoundException {
		String instance = addModsToList();
		writeInstance(instance);
	}

	private void writeInstance(String instance) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(instanceJsonFullPath);
		out.print(instance);
		out.close();
	}

	private String addModsToList() {
		Map<Integer, String> mods = modList.getModListMap();
		JsonObject instance = this.instanceJson;
		for(int i = 0; i < mods.size(); i++) {
			if(!isInList(mods.get(i))) {
				String json = "{\"name\":\"" + mods.get(i) + "\",\"version\":\"Custom\",\"optional\":true,\"file\":\"" + mods.get(i) + "\",\"type\":\"mods\",\"disabled\":false}";
				JsonElement modElement = jsonParser.parse(json);
				instance.get("mods").getAsJsonArray().add(modElement);
			}
		}
		return instance.toString();
	}

	private boolean isInList(String fileName) {
		JsonArray instanceModList = instanceJson.get("mods").getAsJsonArray();
		for(int i = 0; i < instanceModList.size(); i++) {
			if(fileName.equalsIgnoreCase(instanceModList.get(i).getAsJsonObject().get("file").getAsString())) {
				return true;
			}
		}
		return false;
	}

}
