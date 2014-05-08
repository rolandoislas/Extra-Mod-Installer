package com.rolandoislas.extramodinstaller.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSON {
	
	private JsonParser jsonParser = new JsonParser();
	
	public JSON() {
		
	}
	
	public JsonObject getList() {
		String requestString = com.rolandoislas.extramodinstaller.Main.LIST_URL;
		URL requestURL = null;
		try {
			requestURL = new URL(requestString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		Scanner scanner = null;
		try {
			scanner = new Scanner(requestURL.openStream());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String response = scanner.useDelimiter("\\Z").next();
		JsonObject json = jsonParser.parse(response).getAsJsonObject();
		scanner.close();
		return json;
	}
	
}
