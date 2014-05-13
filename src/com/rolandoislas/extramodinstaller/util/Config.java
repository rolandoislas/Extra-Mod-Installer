package com.rolandoislas.extramodinstaller.util;

import java.io.InputStream;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Config {

	private InputStream input;
	private JsonParser jsonParser = new JsonParser();

	public Config() {
		input = Config.class.getClassLoader().getResourceAsStream("config/config.json");
	}
	
	public JsonObject getConfig() {
		Scanner scanner = new Scanner(input);
		String config = scanner.useDelimiter("\\Z").next();
		JsonObject json = jsonParser.parse(config).getAsJsonObject();
		scanner.close();
		return json;
	}
	
}
