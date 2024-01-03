package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	public static Properties propertyLoader(String filePath) {
		Properties properties = new Properties();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Properties file not found at: " + filePath);
		}
		try {
			properties.load(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load the Properties file: " + filePath);
		}
		return properties;
	}

	public static String getTestData(String Key) throws IOException {
		FileReader reader=new FileReader("./src/test/resources/testdata.properties");  
	    Properties p=new Properties();  
	    p.load(reader);  
		String value=p.getProperty(Key);
		return value;
	}
	
	
	public static String getCordinate(String Key) throws IOException {
		FileReader reader=new FileReader("./src/test/resources/coordinates.properties");  
	    Properties p=new Properties();  
	    p.load(reader);  
		String value=p.getProperty(Key);
		return value;
	}
	
	
	public static String getTestConfiguration(String Key) throws IOException {
		FileReader reader=new FileReader("./src/main/resources/config.properties");  
		Properties p=new Properties();  
		p.load(reader);  
		String value=p.getProperty(Key);
		return value;
	}
	
	public void writePropertyData(String key,String value, String key1,String value1) throws IOException {
		Properties p=new Properties();
		p.setProperty(key, value);
		p.setProperty(key1, value1);
		File file = new File("./src/test/resources/coordinates.properties");
        FileOutputStream fOut = new FileOutputStream(file);
		p.store(fOut, null);
        fOut.close();
	}
}