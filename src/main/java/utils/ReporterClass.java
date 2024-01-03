package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Base.BaseTest;

public interface ReporterClass {
	
	public Status info = Status.INFO;
	public Status fail = Status.FAIL;
	public Status pass = Status.PASS;
	
	
	/**
	 * Attach Screenshot to reporter with message and status
	 * @param status
	 * @param message
	 */
	
	public static void ReportLoggerScreenshot(Status status, String message){
		try {
//			BaseAnnotationClass.test.log(status, message,MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			BaseTest.test.log(status,message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot) BaseTest.driver)
							.getScreenshotAs(OutputType.BASE64)).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getScreenshot() throws IOException{
		String dateName = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) BaseTest.driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Extent Report/"+BaseTest.className+"/Screenshot/" + dateName +".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	/**
	 * Modify After adding Config.properties
	 * @param message
	 * @param isScreeshotNeeded
	 */
	public static void pass(String message, boolean isScreeshotNeeded) {
			if (isScreeshotNeeded) {
				BaseTest.test.pass(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64)).build());
		} else {
			BaseTest.test.pass(message);
		}
	}
	
	
	public static void fail(String message, boolean isScreeshotNeeded) {
		
		if (isScreeshotNeeded) {
			BaseTest.test.fail(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64)).build());
		} else {
			BaseTest.test.fail(message);
		}
	}
	
	
}
