package com.bhim.npci.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * @author piyus
 * This class is used to perform property file related operations
 */
public class FileUtility {
	
	/**
	 * @author piyus
	 * This methos is used to read data from property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream(PathConstants.commonDataFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}
}
