package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	
	public static String  getProperty(String key) throws IOException {
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:\\Users\\piyus\\eclipse-workspace\\orangeHRM\\src\\main\\resources\\propertiesFiles\\app.properties");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
}
