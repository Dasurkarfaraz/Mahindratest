package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Utilitycommon implements ReporterClass{

	Boolean status = true;
	public static String Otp1;

	public void takeScreenShot(WebDriver driver, String methName) {

		String destDir = "screenshots";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		new File(destDir).mkdirs();
		String destFile = methName + dateFormat.format(new Date()) + ".png";

		try {

			FileUtils.copyFile(scrFile, new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeScreenShot1(WebDriver driver, String methName) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			File file = new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/" + methName);
			if (file.exists()) {

			} else {
				file.mkdir();
			}
			
			File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/" + methName);
			
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void doubleTap(WebDriver driver, Object X, Object Y) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
		}
		Map<String, Object> args = new HashMap<>();

		args.put("x", X);
		args.put("y", Y);
		((RemoteWebDriver) driver).executeScript("mobile: doubleTap", args);
		System.out.println("Tap");
	}
	
	
//	public void scrollWithUiAutomator(String attributeName,String attributeValue) {
//		BaseTest.driver.getContext();
//		((AndroidDriver)BaseTest.driver).
//		findElementByAndroidUIAutomator("new UIScrollable(new UiSelector())."
//				+ "scrollIntoView("+attributeName+"(\""+attributeValue+"\"))");
//		
//	}

	
//	public void scrollWithUiAutomator(String exactText) {
//		((AndroidDriver)BaseTest.driver).
//		findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +".scrollIntoView(new UiSelector().text(\""+exactText+"\"))");
//	}
	
	public void swipe(int X1, int Y1, int X2, int Y2) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
//		new TouchAction(BaseTest.driver).press(PointOption.point(X1, Y1))
//		 .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
//		 .moveTo(PointOption.point(X2, Y2))
//		 .release().perform();
		new TouchAction(BaseTest.driver).press(PointOption.point(X1, Y1)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).
		moveTo(PointOption.point(X2, Y2)).release().perform();
		System.out.println("Swipe is performed");
	}

	public void tap(PerformsTouchActions driver, int xOffset, int yOffset) {

		new TouchAction(driver).press(PointOption.point(xOffset, yOffset)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).release().perform();
		LogManager.getLogger().info("Tap is Performed at location "+xOffset+" and "+yOffset);
	}
	
	public void tap(int xOffset, int yOffset,String Button) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
		new TouchAction(BaseTest.driver).press(PointOption.point(xOffset, yOffset)).release().perform();
		LogManager.getLogger().info(Button+" is clicked");
	}
	
	public void tap(int xOffset, int yOffset,String Button,int waitTimeInSecond) {
		try {
			Thread.sleep(waitTimeInSecond*1000);
		} catch (InterruptedException e) {}
		new TouchAction(BaseTest.driver).press(PointOption.point(xOffset, yOffset)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
		LogManager.getLogger().info(Button+" is clicked");
		ReporterClass.ReportLoggerScreenshot(ReporterClass.pass,Button+" is clicked");
	}
	
	public void tap(int xOffset, int yOffset,int waitTimeInSecond) {
		try {
			Thread.sleep(waitTimeInSecond*1000);
		} catch (InterruptedException e) {}
		new TouchAction(BaseTest.driver).press(PointOption.point(xOffset, yOffset)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
		LogManager.getLogger().info("Tap is Performed at location "+xOffset+" and "+yOffset);
	}


	public void returnBackToTheSbiYonoLiteApplication(WebDriver driver) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(AppiumBy.accessibilityId("Yono Lite SBI")).click();

		
	}
	
	public void activateMessageApp() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BaseTest.driver.activateApp("com.samsung.android.messaging");
		
	}
	
	public void activateSbiYonoLiteApplication() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BaseTest.driver.activateApp("com.sbi.sbunified");
		
	}


	public String ReadOtpPage2(WebDriver driver) {
		driver.findElement(AppiumBy.accessibilityId("Messages")).click();

//		new WebDriverWait(driver, 180).until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.iOSClassChain("**XCUIElementTypeCell[1]"));
		driver.findElement(AppiumBy.iOSClassChain("**XCUIElementTypeCell[1]")).click();
		String read = driver.findElement(By.xpath("(//XCUIElementTypeCell)[last()]")).getText();
		System.out.println(read);
		final Pattern p = Pattern.compile("[0-9]{8}");
		System.out.println("pattern is: " + p);
		final Matcher m = p.matcher(read);
		System.out.println("matcher is: " + m);
		if (m.find()) {
			Otp1 = m.group();

		}
		return Otp1;
	}

	public String ReadOtpPage3(AppiumDriver driver) {
		try {
			Thread.sleep(3000);
		}catch (Exception e) {}
		try {
			((InteractsWithApps) driver).activateApp(PropertyUtils.getTestConfiguration("msgPackage"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		}catch (Exception e) {}
//		new WebDriverWait(driver, 20).until(
//				ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.xpath("//android.widget.LinearLayout")));
//		new WebDriverWait(driver, 20).until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.xpath("//android.view.ViewGroup"));
		String read = null;
		try {
//			driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.samsung.android.messaging:id/card_view_list_item']")).click();
			driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id='com.google.android.apps.messaging:id/swipeableContainer']")).click();
			try {
				Thread.sleep(3000);
			}catch (Exception e) {}
			read = driver.findElement(By.xpath("(//*[contains(@text,'OTP')])[last()]")).getText();
			 driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
		} catch (Exception e) {
		}
		System.out.println(read);
		final Pattern p = Pattern.compile("[0-9]{8}");
		System.out.println("pattern is: " + p);
		final Matcher m = p.matcher(read);
		System.out.println("matcher is: " + m);
		if (m.find()) {
			Otp1 = m.group();
			System.out.println("Read line 316 - "+Otp1);
		}
//		driver.terminateApp("com.google.android.apps.messaging");
		return Otp1;
	}

	public void ReadTransactionSuccessfulMessage(WebDriver driver) {
		// driver=(IOSDriver);

		((IOSDriver) driver).runAppInBackground(Duration.ofSeconds(-1));
//
//		new WebDriverWait(driver, 20)
//				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.AccessibilityId("Messages")));
//		driver.findElement(MobileBy.AccessibilityId("Messages")).click();
//
//		new WebDriverWait(driver, 120).until(ExpectedConditions
//				.presenceOfAllElementsLocatedBy(By.xpath("//XCUIElementTypeStaticText[contains(@name,'Txn done')]")));

		((IOSDriver) driver).runAppInBackground(Duration.ofSeconds(-1));
		driver.findElement(AppiumBy.accessibilityId("SBI Anywhere Personal")).click();
		System.out.println(2);
	}


	
	public void tapByCoordinates(WebDriver driver, int X1, int Y1, int X2, int Y2) {

		// HashMap scrollObject = new HashMap<>();
		// scrollObject.put("predicateString", "type=='XCUIElementTypeOther' and
		// name=='AD-SBGMBS, Txn done for self trf frm 00000030002121395 to XXXXX1464
		// for Amt Rs.100.00 on 21-Aug-2019.TID: UI00019065.If not done,fwd this SMS to
		// 9223008333or call1800111109 to block ID, 2:27 PM'");
		// ((JavascriptExecutor) driver).executeScript("mobile: scroll", scrollObject);
		// WebElement
		// ele=driver.findElement(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeOther'
		// and name=='AD-SBGMBS, Txn done for self trf frm 00000030002121395 to
		// XXXXX1464 for Amt Rs.100.00 on 21-Aug-2019.TID: UI00019065.If not done,fwd
		// this SMS to 9223008333or call1800111109 to block ID, 2:27 PM'"));
		// TouchActions action = new TouchActions(driver);
		// action.scroll(ele, 10, 100);
		// action.perform();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		Dimension d = driver.manage().window().getSize();
		X1 = (int) (d.getWidth() * 0.5D);
		Y1 = (int) (d.getHeight() * 0.6D);

		X2 = (int) (d.getWidth() * 0.5D);
		Y2 = (int) (d.getHeight() * 0.2D);
		Map<String, Object> args = new HashMap<>();
		args.put("duration", 3);
		args.put("fromX", X1);
		args.put("fromY", Y1);
		args.put("toX", X2);
		args.put("toY", Y2);
		((JavascriptExecutor) driver).executeScript("mobile: dragFromToForDuration", args);

	}

	// public void gestSwipeVerticalPercentage(WebDriver driver) throws Exception {
	//// new TouchAction(driver).press(0, 200)
	//// .waitAction(2000)
	//// .moveTo(0, 300).release().perform();
	// Thread.sleep(3000);
	// new TouchAction ((PerformsTouchActions) driver)
	// .press(PointOption.point(0, 500))
	// .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
	// .moveTo(PointOption.point(0, 200))
	// .release().perform();
	//
	//
	//
	//
	// }
	
	
	public void swipe1(WebDriver driver) {
		try {
			Thread.sleep(3000);
		}catch (Exception e) {}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);

	}
/**
 * Crashing
 * @param driver
 * @param elementName
 */
	public void swipe2(WebDriver driver,String elementName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<Object, Object> scrollObject = new HashMap<>();
		scrollObject.put("direction", "down");
//		scrollObject.put("velocity", 250);
		scrollObject.put("name", elementName);
		js.executeScript("mobile: scroll", scrollObject);
	
	}
	public void isClickable() {
		

	}
}
