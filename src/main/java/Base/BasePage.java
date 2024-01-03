package Base;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.ReporterClass;
import utils.Utilitycommon;
import utils.VerificationUtils;
import utils.WebDriverUtility;

public class BasePage extends BaseTest implements ReporterClass {

	public WebDriverUtility wLib = new WebDriverUtility(BaseTest.driver);
	public Utilitycommon util=new Utilitycommon();
//	public static AndroidDriver<AndroidElement> driver = BaseTest.driver;
	/**
	 * @param element
	 * @param elementName
	 */
	public void clickScreenshot(WebElement element, String elementName) {
		try {
			wLib.waitForElemnetToBeVisible( element);
			element.click();
			ReporterClass.ReportLoggerScreenshot(info, elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
//			ReportLoggerScreenshot(info, elementName + " unable to click");
			LogManager.getLogger().info(elementName + " unable to click");
//			returnExceptionMessage(driver,e);
			Assert.fail();
		}
	}

	/**
	 * click on element and take screenshot with message written in attribute "
	 * 'label'+ is clicked " and Print message in testng report and in console
	 * <b>Strore logs</b>
	 * 
	 * @param element
	 */
	public void clickScreenshot(WebElement element) {
//		System.out.println("Trying clicking element "+element);
		wLib.waitForElemnetToBeVisible(element);
//		String elementName = element.getAttribute("text");
		String elementName = element.getText();
		try {
			element.click();
			ReporterClass.ReportLoggerScreenshot(info, elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			ReporterClass.ReportLoggerScreenshot(info, elementName + " unable to click");
			LogManager.getLogger().info(elementName + " unable to click");
			Assert.fail();
		}

	}
	


	/**
	 * No Screenshot Click log the event on console and log on file and extent
	 * Report
	 */
	public void click(WebElement element) {
		wLib.waitForElemnetToBeVisible(element);
		String elementName = element.getAttribute("label");
		if(elementName==null) {
			elementName=element.getAttribute("name");
		}
		try {
			element.click();
			BaseTest.test.info(elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
			long heapFreeSize = Runtime.getRuntime().freeMemory();
			System.out.println(formatSize(heapFreeSize));
			//report.flush();
		} catch (Exception e) {
			ReporterClass.ReportLoggerScreenshot(info, elementName + " unable to click");
			BaseTest.test.info(elementName + " unable to click");
			LogManager.getLogger().info(elementName + " unable to click");
			//Assert.fail();
		}

	}
	public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
    }
	public void clickE(WebElement element, String elementName) {
		wLib.waitForElemnetToBeVisible( element);
		
		try {
			element.click();
			BaseTest.test.info(elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			BaseTest.test.fail(elementName + " unable to click");
			LogManager.getLogger().info(elementName + " unable to click");
			Assert.fail();
		}

	}
	/**
	 * No Screenshot Click log the event on console and log on file and extent
	 * Report
	 */
	public void click(WebElement element, String elementName) {
		try {
			wLib.waitForElemnetToBeVisible(element);
			element.click();
			BaseTest.test.info(elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			BaseTest.test.info(elementName + " unable to click");
			LogManager.getLogger().info(elementName + " unable to click");
			Assert.fail();
		}
	}

	public void sendKeys(WebElement element, String txt) {
		wLib.waitForElemnetToBeVisible( element);
		String name = element.getAttribute("name");
		if(name==null) {
			name=element.getAttribute("label");
		}
		try {
			
			element.sendKeys(txt);
			BaseTest.test.info("text " + txt + " in " + name + " text box is sent");
			LogManager.getLogger().info("text " + txt + " in " + name + " text box is sent");
			//ReporterClass.ReportLoggerScreenshot(info, "text " + txt + " in " + name + " text box is sent");
			
		} catch (Exception e) {
			ReporterClass.ReportLoggerScreenshot(fail, "text " + txt + " in " + name + " text box is not sent");
			BaseTest.test.info("send Keys " + txt + " in " + name + " is failed");
			LogManager.getLogger().info("send Keys " + txt + " in " + name + " is failed");
			Assert.fail();
		}
	}

	public void sendKeys(WebElement element, String txt, String fieldName) {
		wLib.waitForElemnetToBeVisible( element);
		String name = element.getAttribute("name");
		if(name==null) {
			name=element.getAttribute("label");
		}
		try {
			element.sendKeys(txt);
			BaseTest.test.info(txt + " is sent to " + fieldName + " text field ");
			//LogManager.getLogger().info(txt + " is sent to " + fieldName + " text field ");
			//ReporterClass.ReportLoggerScreenshot(info, "text " + txt + " is sent to " + fieldName + " text field ");
		} catch (Exception e) {
			ReporterClass.ReportLoggerScreenshot(fail, "text " + txt + " in " + name + " text box is not sent");
			BaseTest.test.info("send Keys " + txt + " in " + fieldName + " is failed");
			//LogManager.getLogger().info("send Keys " + txt + " in " + fieldName + " is failed");
			Assert.fail();
		}
	}

	/**
	 * pending
	 */
	public void sendKeysSlowly(WebElement element, String txt, String fieldName) {
		wLib.waitForElemnetToBeVisible( element);
		element.sendKeys(txt.substring(0, 1));
		for (int i = 1; i < txt.length(); i++) {
//			BaseTest.driver.findElement(By.xpath(
//					"//XCUIElementTypeOther[@name=\"Re-enter Account Number *\"]/preceding-sibling:: XCUIElementTypeTextField")
//					.sendKeys(txt.substring(i, i + 1));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		ReporterClass.ReportLoggerScreenshot(fail, "text " + txt + " in text box is not sent");
		BaseTest.test.info("Filling " + txt + " in " + fieldName);
		Reporter.log("Filling " + txt + " in " + fieldName, true);
		Assert.fail();
	}
//	public boolean isDisplayed(WebElement element) {
//		try {
//			new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
//			return element.isDisplayed();
//		} catch (NoSuchElementException | TimeoutException exception) {
//			BaseTest.test.fail("Element is not present");
//			ReporterClass.ReportLoggerScreenshot(fail, "text is not display");
//			return false;
//		}
//	}


	public static boolean isDisplayed(WebElement element) {
		String name = element.getAttribute("label");
		if(name==null) {
			name=element.getAttribute("name");
		}
		try {
			new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (NoSuchElementException | TimeoutException exception) {
			BaseTest.test.fail("Element is not present");
			ReporterClass.ReportLoggerScreenshot(fail, "Element  in " + name + "  is not Display");
			return false;
		}
	}
	
	public void isDisplayedonscreen(WebElement element) {
		String elementName = element.getAttribute("label");
		if(elementName==null) {
			elementName=element.getAttribute("name");
		}
		try {
			new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
			BaseTest.test.pass(elementName + " is  present");
			LogManager.getLogger().info(elementName + " is  present");
		} catch (NoSuchElementException | TimeoutException exception) {
			BaseTest.test.fail(elementName + " is  present");
			ReporterClass.ReportLoggerScreenshot(fail, "Element  in " + elementName + "  is not Display");
			Assert.fail();
			
		}
	}
	public boolean isnotDisplayed(WebElement element) {
		String elementName = element.getAttribute("label");
		if(elementName==null) {
			elementName=element.getAttribute("name");
		}
		try {
			new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (NoSuchElementException | TimeoutException exception) {
			BaseTest.test.pass("Element is not present");
			ReporterClass.ReportLoggerScreenshot(fail, "Element  in " + elementName + "  is  Display");
			return false;
		}
	}
	public void isnotDisplayedonscreen(WebElement element, String elementName) {
		
			try {
				new WebDriverWait(BaseTest.driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOf(element));
				BaseTest.test.pass(elementName + " is  not present");
				LogManager.getLogger().info(elementName + " is not present");
			} catch (Exception e) {
				ReporterClass.ReportLoggerScreenshot(fail, "Element  in " + elementName + "  is  Display");
				Assert.fail();
			}
		
	}
	
		public void clickAfterScrolling(WebElement element, int scrollNumber, String elementName) throws InterruptedException {
//			AndroidDriver driver = BaseTest.driver;
			for(int i=1;i<scrollNumber;i++) {
				try {
					element.isDisplayed();
					System.out.println("Element is not present");
					ReporterClass.ReportLoggerScreenshot(info, elementName + " is clicked");
					LogManager.getLogger().info(elementName + " is clicked");
					break;
				}
				catch (Exception e) {
					Thread.sleep(1000);
					util.swipe(300, 1400, 300, 300);
					System.out.println("swipe is performed");
					LogManager.getLogger().info(elementName + " unable to click");
//					Assert.fail();
				}
				
				
			}
			element.click();
	}
		
//		public void scrollandClick(AndroidElement element, String direction){
//			
//			Direction direct=null;
//			
//			if (direction=="up")
//				{
//					direct=Direction.UP;
//					System.out.println("Scrolling up");
//				}
//			else {
//				direct=Direction.DOWN;
//				System.out.println("Scrolling down");
//
//			}
//				
//			swipe(element, direct);
//			element.click();
//		}
		public void scrollToelement(WebElement element) {
		    try {
				TouchAction action = new TouchAction(driver);
				action.press(PointOption.point(element.getLocation().getX(), element.getLocation().getY()))
						.moveTo(PointOption.point(element.getLocation().getX(), element.getLocation().getY())).release()
						.perform();
			} catch (Exception e) {
				ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
				//Assert.fail();
			}
		}
		public void Scrolltoelement(WebElement element, int scrollNumber, String elementName) throws InterruptedException {
try {
	//			AndroidDriver driver = BaseTest.driver;
	for (int i = 1; i < scrollNumber; i++) {
		try {
			element.isDisplayed();
			System.out.println("Element is not present");
			ReporterClass.ReportLoggerScreenshot(info, elementName + " is Scroll");
			LogManager.getLogger().info(elementName + " is clicked");
			break;
		} catch (Exception e) {
			util.swipe(170, 550, 165, 208);
			LogManager.getLogger().info(elementName + " unable to Scroll");
			//					Assert.fail();
		}
	} 
} catch (Exception e) {
	ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
	//Assert.fail();
}
	}
		
		
		
public void clickonWebElement(WebElement element1)
		{
	try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element1));
			 element1.click();
		}catch(Exception ex){
			ex.printStackTrace();
			ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
			//Assert.fail();
		}
		}



		
public void switchToNativeview() {
		    Set<String> str = driver.getContextHandles();
		    for (String newsrt : str) {
		      if (newsrt.contains("NATIVE")) {
		        driver.context(newsrt);
		        System.out.println(newsrt);
		      }
		   
		    } 
		  }

public void switchToWeb() throws InterruptedException {
			Thread.sleep(4000);
		    Set<String> str = driver.getContextHandles();
		    for (String newsrt : str) {
		      if (newsrt.contains("chrome")) {
		        driver.context(newsrt);
		      }
		      if (newsrt.contains("CHROMIUM")) {
			        driver.context(newsrt);
			      }
		      System.out.println(newsrt);
		      System.out.println("Switch to Web");
		     
		    } 
		  }



//public void scrollWithUiAutomator(String exactText) {
//	((AndroidDriver)driver).
//	findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +".scrollIntoView(new UiSelector().text(\""+exactText+"\"))");
//}



@SuppressWarnings("deprecation")
public void swipe(int X1, int Y1, int X2, int Y2) {
	try {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		new TouchAction(driver).press(PointOption.point(X1, Y1))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(X2, Y2)).release()
				.perform();
		System.out.println("Swipe is performed");
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
		Assert.fail();
	}
}


//public void scrollintoview(WebElement driver, String scrollToText) {
//    Dimension size = driver.manage().window().getSize();
//    int startX = size.width / 2;
//    int startY = (int) (size.height * 0.5);
//    int endY = (int) (size.height * 0.1);
//
//    while (driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + scrollToText + "\")")).size() == 0) {
//        TouchAction touchAction = new TouchAction(driver);
//        touchAction.press(PointOption.point(startX, startY))
//                   .waitAction()
//                   .moveTo(PointOption.point(startX, endY))
//                   .release()
//                   .perform();
//    }
//    ReporterClass.ReportLoggerScreenshot(info, scrollToText + " is Scroll");
//	LogManager.getLogger().info(scrollToText + " is Scroll");
//
//}
public void scrolluptillelement(WebElement driver, String scrollToText) {
    try {
		Dimension size = ((WebDriver) driver).manage().window().getSize();
		int startX = size.width / 2;
		int startY = (int) (size.height * 0.1);
		int endY = (int) (size.height * 0.5);
		while (driver
				.findElements(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + scrollToText + "\")"))
				.size() == 0) {
			TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
			touchAction.press(PointOption.point(startX, startY)).waitAction().moveTo(PointOption.point(startX, endY))
					.release().perform();
		}
		ReporterClass.ReportLoggerScreenshot(info, scrollToText + " is Scroll");
		LogManager.getLogger().info(scrollToText + " is Scroll");
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
		Assert.fail();
	}

}

public void clickToLeftOfText(String targetText) {
	try {
		WebElement element = (WebElement) driver.findElement(By.xpath("//*[contains(@text, '" + targetText + "')]"));
		int targetX = element.getLocation().getX();
		int targetY = element.getLocation().getY() + element.getSize().getHeight() / 2;
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(targetX - 50, targetY)).perform();
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
		Assert.fail();
	}
}
public void clickontext(String text, String elementName) {
	try {
		WebElement element = (WebElement) driver.findElement(By.xpath("//*[@text='" + text + "']"));
		wLib.waitForElemnetToBeVisible(element);
		element.click();
		BaseTest.test.info(elementName + " is clicked");
		LogManager.getLogger().info(elementName + " is clicked");
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
		Assert.fail();
	}
}
public void clickwaitios(WebElement element,String elementName) {
	try {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element)).click();
		BaseTest.test.info(elementName + " is clicked");
		LogManager.getLogger().info(elementName + " is clicked");
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
		Assert.fail();
	}

}
public void clickvisble(WebElement element,String elementName) {
	try {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element)).click();
		BaseTest.test.info(elementName + " is clicked");
		LogManager.getLogger().info(elementName + " is clicked");
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "click on element");
		Assert.fail();
	}

}
public void clickcontainsname(String txt) {
	try {
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name, '" + txt + "')]")).click();
		BaseTest.test.info(txt + " is clicked");
		LogManager.getLogger().info(txt + " is clicked");
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "Element is  not clikable");
		Assert.fail();
	}
}
public static void source() {
	String pageSource = driver.getPageSource();
	String filePath = "/Users/3di/Downloads/yono/Drivers/app_source.xml"; // Specify the file path where you want to save the XML
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	    writer.write(pageSource);
	    System.out.println("App source saved as XML file: " + filePath);
	} catch (IOException e) {
	    e.printStackTrace();
	}
}
public void waitelementtobeclickable(WebElement element,String elementName) {
	try {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
		BaseTest.test.info(elementName + " is Displayed");
		LogManager.getLogger().info(elementName + " is Displayed");
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "Element is  not visible");
		
	}

}
//while (!textField().getText().isEmpty()) {
//	   TouchAction touchAction = new TouchAction(driver);
//	   touchAction.longPress(textField());
//	   driver.getKeyboard().sendKeys(Keys.DELETE);
//	}
public void taponelement(WebElement element) {
	String elementName = element.getAttribute("label");
	if(elementName==null) {
		elementName=element.getAttribute("name");
	}
	try {
		wLib.waitForElemnetToBeVisible(element);
		
	Point elementLocation = element.getLocation();
		int x = elementLocation.getX();
		int y = elementLocation.getY();
	   TouchAction action=new TouchAction(driver);
	   action.tap(PointOption.point(x, y)).release().perform();
	   BaseTest.test.info(elementName + " is clicked");
		LogManager.getLogger().info(elementName + " is clicked");
	}catch (Exception e) {
		// TODO: handle exception
		ReporterClass.ReportLoggerScreenshot(info, elementName + " unable to click");
		BaseTest.test.info(elementName + " unable to click");
		LogManager.getLogger().info(elementName + " unable to click");
	}
	
	
}

public void scrollup(WebElement element) {
	try {
    Dimension windowSize = driver.manage().window().getSize();
    int X1 = windowSize.width / 2;
    int Y1 = windowSize.height / 2;
    int y2 = windowSize.height * 4 / 5; // Adjust as needed

    while (!isElementVisible(element)) {
    	System.out.println("Element Scrolled");
    	new TouchAction(BaseTest.driver).press(PointOption.point(X1, Y1)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).
		moveTo(PointOption.point(X1, y2)).release().perform();
    }}catch (Exception e) {
    	ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
		Assert.fail();
	}
	
}

//public void scrollDown(WebElement element) {
//    int X1 = driver.manage().window().getSize().getWidth() / 2;
//    int Y1 = driver.manage().window().getSize().getHeight() * 4 / 5;
//    int y2 = driver.manage().window().getSize().getHeight() / 5;
//    while (!isElementVisible(element)) {
//    new TouchAction(BaseTest.driver).press(PointOption.point(X1, Y1)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).
//	moveTo(PointOption.point(X1, y2)).release().perform();
//}
//}
public void scrollDown(WebElement element) {
    try {
		int X1 = driver.manage().window().getSize().getWidth() / 2;
		int Y1 = driver.manage().window().getSize().getHeight() * 4 / 5;
		int y2 = driver.manage().window().getSize().getHeight() / 5;
		int maxAttempts = 10;
		int attempt = 1;
		while (attempt <= maxAttempts && !isElementVisible(element)) {
			new TouchAction(BaseTest.driver).press(PointOption.point(X1, Y1))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(X1, y2))
					.release().perform();

			attempt++;
		} 
	} catch (Exception e) {
		ReporterClass.ReportLoggerScreenshot(fail, "Element is  not scroll");
		Assert.fail();
	}
}
public void SlideOptions(WebElement incident) throws InterruptedException {
	//open app 
	
	
	Dimension elementSize = incident.getSize();
	Point elementLocation = incident.getLocation();
	int startX = elementLocation.getX() + elementSize.getWidth() - 10; // Adjust the value as needed
	int startY = elementLocation.getY() + elementSize.getHeight() / 2;

	int endX = elementLocation.getX() + 10; // Adjust the value as needed
	int endY = startY;
	new TouchAction(BaseTest.driver).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).
	moveTo(PointOption.point(endX, endY)).release().perform();
	//click(delete);
}

private boolean isElementVisible(WebElement element) {
    try {
    	
        return element.isDisplayed();
    } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
        return false;
    }
}
public static Point2D getCoords(BufferedImage baseImg, String targetImgPath, double minSimilarityValue)
{
  org.sikuli.basics.Settings.MinSimilarity = minSimilarityValue;
  
  Finder f = new Finder(baseImg);
  Point2D coords = new Point2D.Double(-1.0D, -1.0D);
  
  f.find(targetImgPath);
  if (f.hasNext())
  {
    Match m = f.next();
    coords.setLocation(m.getTarget().getX(), m.getTarget().getY());
  }
  org.sikuli.basics.Settings.MinSimilarity = 0.8D;
  return coords;
}

public static BufferedImage takeScreenshot()
{
  File scrFile = (File)driver.getScreenshotAs(OutputType.FILE);
  BufferedImage bufferedImage = null;
  try
  {
    bufferedImage = ImageIO.read(scrFile);
  }
  catch (IOException e)
  {
    e.printStackTrace();
  }
  return bufferedImage;
}

public boolean clickByImage(String targetImgPath)
{
  long startTime = System.currentTimeMillis();
  long elapsedTime = 0L;
  boolean flag = false;
  while (elapsedTime < 90000L)
  {
    Point2D coords = getCoords(takeScreenshot(), targetImgPath, 0.6D);
    if ((coords.getX() >= 0.0D) && (coords.getY() >= 0.0D))
    {
    	TouchAction touchAction = new TouchAction(driver);
    	touchAction.tap(PointOption.point((int)coords.getX(), (int)coords.getY())).release().perform();
     
      flag = true;
      break;
    }
    elapsedTime = new Date().getTime() - startTime;
  }
  return flag;
}

public static boolean AssertOnImage(String targetImgPath)
{
  long startTime = System.currentTimeMillis();
  long elapsedTime = 0L;
  boolean flag = false;
  while (elapsedTime < 30000L)
  {
    Point2D coords = getCoords(takeScreenshot(), targetImgPath, 0.7D);
    if ((coords.getX() >= 0.0D) && (coords.getY() >= 0.0D))
    {
      flag = true;
      break;
    }
    elapsedTime = new Date().getTime() - startTime;
  }
  return flag;
}

public static void getScreenShot()
{
  try
  {
    TakesScreenshot scrShot = driver;
    File SrcFile = (File)scrShot.getScreenshotAs(OutputType.FILE);
    File DestFile = new File("d:\\ERROR.png");
    FileUtils.copyFile(SrcFile, DestFile);
  }
  catch (IOException localIOException) {}
}
public static void sendKeysOneByOne(WebElement element, String text) {
    char[] chars = text.toCharArray();
    for (char c : chars) {
        String singleChar = String.valueOf(c);
        element.sendKeys(singleChar);
        System.out.println(singleChar);
    }
}
public static String extractConfirmationNumber(String text) {
    String pattern = "(\\w+-\\d+)";
    java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
    java.util.regex.Matcher matcher = regex.matcher(text);
    
    if (matcher.find()) {
        return matcher.group(1);
    } else {
        return "";
    }
}
public static String present(WebElement element) {

	String present = Boolean.toString(isDisplayed(element));
	return present;
}
public void clicktext(String AidGiven) {
	WebElement Aidselect = (WebElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@label='"+AidGiven+"']"));
	click(Aidselect);
}
public void clickontextcontains(String element) {
	WebElement elementele = (WebElement) driver.findElement(By.xpath("//*[contains(@name,'"+ element +"')]"));
	click(elementele);
}

public void dataclickbutton(String AidGiven) {
	WebElement Aidselect = (WebElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@label='"+AidGiven+"']"));
	click(Aidselect);
}
public void swipemodule() {
	new TouchAction(BaseTest.driver)
    .press(PointOption.point(352, 122))
    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
    .moveTo(PointOption.point(271, 122))
    .release()
    .perform();
	
}
public void backbutton() {
	driver.navigate().back();
}
public static String valuepresent(String elementtxt) {
	
	WebElement element = (WebElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@label='"+elementtxt+"']"));
	

		String present = Boolean.toString(isDisplayed(element));
		return present;
	
}
@FindBy(xpath = "//XCUIElementTypeNavigationBar[@name = \"KahunaBaseApp.IncidentsDashboardVC\"]/XCUIElementTypeButton[1]")
private WebElement searchbutton;
@FindBy(xpath = "//XCUIElementTypeSearchField")
private WebElement  searchbar;
@FindBy(xpath = "//XCUIElementTypeButton[@name=\"Search\"]")
private WebElement Searchbtn;


public void searchbarincident(String txt) throws Exception {
	click(searchbutton);

	sendKeys(searchbar, txt);
	click(Searchbtn);
}
public void checkgivendata(String datatxt) {
	WebElement Checkelel = (WebElement) driver.findElement(By.xpath("//*[contains(@value,'"+datatxt+"')]"));
	VerificationUtils.validate("true", present(Checkelel), datatxt +"  is display");
	
}
}
