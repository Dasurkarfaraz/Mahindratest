package utils;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.remote.SessionId;

public class FileUtility {
	/**
	 * 
	 * @param key as url un pwd BROW
	 * @return value from property file url,un,pwd,BROW
	 * @throws IOException
	 */
	public String getPropertyData(String key) {
		Properties p=null;
		try {
			FileInputStream fis=new FileInputStream("./src/test/resources/testdata.properties");
			p=new Properties();
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = p.getProperty(key);
		return data;		
	}
	/**
	 * 
	 * @param key property file name
	 * @return value from property file 
	 * @throws IOException
	 */
	public String getPropertyData(String key,String propertyFileName) throws IOException {
		FileInputStream fis=new FileInputStream("./data/"+propertyFileName+".properties");
		Properties p=new Properties();
		p.load(fis);
		String data = p.getProperty(key);
		return data;		
	}
	
	public void writePropertyData(String key,int value, String propertyFileName) throws IOException {
		FileInputStream fis=new FileInputStream("./data/"+propertyFileName+".properties");
		Properties p=new Properties();
		p.load(fis);
		p.put(key, value);
	}
	/**
	 * store the session id in ./src/main/resources/SessionId.txt
	 * @param sessionId
	 */
	public static void printSessionID(SessionId sessionId) {
		try{    
	           FileWriter fw=new FileWriter("./src/main/resources/SessionId.txt");    
	           fw.write("sessionID");   
	           System.out.println(sessionId);
	           fw.close();    
	          }catch(Exception e){System.out.println(e);}    
	     }    
	
}
