package com.gbis.Bank.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Stream;

public class HelperClass {

	public static String getFileProperties(String property) throws IOException {
		File configDir = new File(System.getProperty("catalina.base"), "conf");
		File configFile = new File(configDir, "catalina.properties");
		InputStream stream = new FileInputStream(configFile);
		Properties props = new Properties();
		props.load(stream);
		return props.getProperty(property);
	}

	public String getProperty(String sPath, String urlName) throws IOException {
		InputStream stream = new FileInputStream(new File(sPath));
		Properties props = new Properties();
		props.load(stream);
		return props.getProperty(urlName);
	}

	public String emptyIfNull(String input) {
		if (input == null || input.isEmpty()) {
			return "";
		}
		return input;
	}

	public static String findFilePath(String sourceDirectory, String fileName) {
		String sFilePath = "";
		File folder = new File(sourceDirectory);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (sFilePath.isEmpty()) {
				if (file.isFile()) {
					if (file.getName().toLowerCase().equals(fileName.toLowerCase())) {
						sFilePath = file.getAbsolutePath();
						break;
					}
				} else if (file.isDirectory()) {
					if (file.getName().toLowerCase().equals(fileName.toLowerCase())) {
						sFilePath = file.getAbsolutePath();
						break;
					}
					sFilePath = findFilePath(file.getAbsolutePath(), fileName);
				}
			} else {
				return sFilePath;
			}
		}

		return sFilePath;
	}

	public String getfileBody(String path) throws IOException {
		String sFileBody = "";
		File fRequestBodyFile = new File(path);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(fRequestBodyFile));
		String st;
		while ((st = br.readLine()) != null) {
			sFileBody = sFileBody + st;
		}
		return sFileBody;
	}

	public String getPropertyValue(String fileName, String propertyName) throws IOException {
		File folder = new File(getFileProperties("shared.loader"));
		String sFilePath = findFilePath(folder.getAbsolutePath(), fileName);
		return emptyIfNull(getProperty(sFilePath, propertyName));
	}

	public static String readFile(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}

	public static void writeInFile(String path, String text)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.println(text);
		writer.close();
	}

	public static Properties getProps(String filePropertiesPath) throws IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(new File(filePropertiesPath)));
		return props;
	}

	public static String getFilePath(String fileName) throws IOException {
		return findFilePath(getFileProperties("shared.loader"), fileName);
	}

	public static String displayNumberInLegalFormat(String input) {
		if (input != null) {
			StringBuilder builder = new StringBuilder();
			String[] array = new String[2];
			String numString;
			if (input.contains(".")) {
				array = input.split("\\.");
				numString = array[0];
			} else {
				array[0] = input;
				array[1] = "0";
				numString = array[0];
			}

			String newString = "";
			for (int i = 0; i < numString.length(); i++) {
				if ((numString.length() - i - 1) % 3 == 0) {
					newString += Character.toString(numString.charAt(i)) + ",";
				} else {
					newString += Character.toString(numString.charAt(i));
				}
			}

			newString += array[1];
			builder.append(newString);

			int positionOfLastComa = builder.lastIndexOf(",");
			builder.replace(positionOfLastComa, positionOfLastComa + 1, ".");
			return builder.toString();
		}
		return null;
	}

	public static String costumID() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String createdName = dateFormat.format(date);
		return createdName;
	}

}
