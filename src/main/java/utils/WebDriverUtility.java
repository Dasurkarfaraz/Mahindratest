package utils;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;

public class WebDriverUtility {
	public static WebDriverWait wait;
	
	public WebDriverUtility(IOSDriver driver ) {
//		this.wait = wait;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(120));
	}
		
	/**
	 * 
	 * @param driver
	 * @param time
	 */
	public void implicitWait(WebDriver driver,int time) {
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 * @param time
	 */
	public void explicitWait(WebElement element,int waitTime) {
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 * @param time
	 */
public void waitForElemnetToBeinVisible(WebElement element,int waitTime) {
		
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	/**
	 * check for visibility of element
	 * @param driver
	 * @param elemnet
	 */
	public void  waitForElemnetToBeVisible( WebElement elemnet){
//		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(elemnet));
	}
	
//	public void  waitForElemnetToBeVisible(AndroidElement elemnet){
////		WebDriverWait wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOf(elemnet));
////		driver.findElementByAndroidUIAutomator(locatorString);
//	}
//	public  void  waitForElemnetToBeVisible(IOSElement elemnet){
////		WebDriverWait wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOf(elemnet));
//		
//		LogManager.getLogger().info("Waiting For element To be Clickable");
////		driver.findElementByAndroidUIAutomator(locatorString);
//	}
	
	/**
	 * check for elment is visible and clickable
	 * @param driver
	 * @param elemnet
	 */
	public void  waitForElemnetToBeClickable( WebElement elemnet,String elementtext){
//		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(elemnet));
		LogManager.getLogger().info("Waiting For "+elementtext +" To be Clickable");
	}
	
	/**
	    * This method wait for the element to be clicked , its custom wait created to avoid elemenInterAceptable Exception
	    * @param element
	    * @throws throwable
	    * 
	    */
	   
	   public void waitAndClick(WebElement element) throws InterruptedException
	   {
		   int count = 0;
	       while(count<20) {
		    	   try {
		    	       element.click();
		    	       break;
		    	   }catch(Throwable e){
		    		   Thread.sleep(1000);
		    		   count++;
		    	   }
	       }
	   }
	   /**
	     * 
	     * @param driver
	     * @param screenshotName
	     * @throws Throwable
	     */
	    public void takeScreenshot(WebDriver driver, String screenshotName) throws Throwable {
	    	TakesScreenshot ts=(TakesScreenshot)driver;
	    	File src=ts.getScreenshotAs(OutputType.FILE);
	    	File dest=new File("./screenshot/"+screenshotName+".PNG");
	    	FileUtils.copyFile(src, dest);
	    }
	    /**
		    * This method used for clicking on element using java script
		    * @param driver
		    * @param element
		    */
		   public void jsClick(WebDriver driver, WebElement element) {
			   JavascriptExecutor executor = (JavascriptExecutor)driver;
			   executor.executeScript("arguments[0].click();", element);
		   }
		   /**
			 * 
			 * @param driver
			 * @param element
			 */
			   public void tapInMiddle(PerformsTouchActions driver ,WebElement element) {
				   Point location = element.getLocation();
				   int x=location.getX();
				   int y=location.getY();
				   Dimension size = element.getSize();
				   int height = size.getHeight();
				   int width = size.getWidth();
				   TouchAction action=new TouchAction(driver);
				   action.tap(PointOption.point(x+width/2, y-height/2)).release().perform();
			   }
			   
		   
}
