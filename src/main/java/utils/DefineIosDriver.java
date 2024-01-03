package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.BaseTest;
import io.appium.java_client.ios.IOSDriver;

public  class DefineIosDriver {
		
		public static IOSDriver driver = BaseTest.driver;
	

	public static void minimizeApplication() {

		System.out.println("Application running in background");
		driver.runAppInBackground(Duration.ofSeconds(-1));
//		try {
//			driver.runAppInBackground(Duration.ofSeconds(-1));
//			driver.findElement(By.id("Safari"));
//		}catch (Exception e) {
//			try {
//				driver.runAppInBackground(Duration.ofSeconds(-1));
//				driver.findElement(By.id("Safari"));
//			}catch (Exception e2) {
//				driver.runAppInBackground(Duration.ofSeconds(-1));
//			}
//			
//		}

	
	}
	
	public void takeScreenShot( String methName) {
	
		
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the  screenshot to desired location using copyFile method
			File file = new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/"+ methName);
			if (file.exists()) { 	
				System.out.println("File exists");
			}else 
			{
				System.out.println("File not exists");
				file.mkdir();        	
			}

	
			FileUtils.copyFile(src, new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/"+".png"));

		}

		catch (IOException e){ System.out.println(e.getMessage()); }


	}
	
	public void quitApp() {
		driver.quit();
	}
	
	public static void lauchapp() throws Exception {
		driver.activateApp(PropertyUtils.getTestConfiguration("bundleId"));
	}
//	
//	public void launchYonoLiteapp() {
//		driver.activateApp("com.sbi.sbfreedomplus.ios");
//	}
//	
}
