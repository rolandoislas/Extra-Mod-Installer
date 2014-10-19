package com.rolandoislas.extramodinstaller.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import com.google.gson.JsonObject;
import com.rolandoislas.extramodinstaller.util.Config;

public class Download {
	
	private File installDir;
	private String fileName;
	private boolean isMod;
	private JsonObject config = new Config().getConfig();

	public Download(File installDir, String fileName, boolean isMod) {
		this.installDir = installDir;
		this.fileName = fileName;
		this.isMod = isMod;
	}

	public void get() throws IOException {
		// Set root for config or mod
		String root;
		String specDir;
		if(isMod) {
			root = config.get("modRootURL").getAsString();
			specDir = "mods";
		} else {
			root = config.get("configRootURL").getAsString();
			specDir = "config";
		}
		File file = new File(installDir + "/" + specDir + "/" + fileName);
		if (file != null) {
			// Create directory structure if it does not exist.
			File folder = file.getParentFile();
			if (!folder.exists()) {
				folder.mkdirs();
			}
			InputStream in = null;
			OutputStream out = null;
			try {
				URL url = new URL(root + "/" + fileName);
				in = url.openStream();
				out = new FileOutputStream(file);
				// Begin transfer
				transfer(in, out);
			} catch (IOException e) {
				throw e;
			} finally {
				// Close streams
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) { /* ignore */ }
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) { /* ignore */ }
				}
			}
		}
	}

	private void transfer(InputStream in, OutputStream out) throws IOException{
		byte[] buffer = new byte[2048];
		int bytesRead;
		while ((bytesRead = in.read(buffer)) > 0) {
			out.write(buffer, 0, bytesRead);
		}
	}

}

