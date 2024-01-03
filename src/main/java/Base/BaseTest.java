package Base;



import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import org.zeroturnaround.zip.ZipUtil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.collect.ImmutableMap;


import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.ConstantsConfig;
import utils.DataUtil;
import utils.ExcelReader;
import utils.FileUtility;
import utils.PropertyUtils;
import utils.Utilitycommon;

public class BaseTest {
	
	
	 public String invalidUsername = fLib.getPropertyData("invalidUsername") ;
     public String invalidPassword = fLib.getPropertyData("invalidPassword");
     public String validUsername = fLib.getPropertyData("validUsername");
     public String validPassword = fLib.getPropertyData("validPassword");
     public String emailid = fLib.getPropertyData("emailid");
	public static FileUtility fLib = new FileUtility();
	public static String profilePassword = fLib.getPropertyData("profilePassword");
	public static String username = fLib.getPropertyData("username");
	public static String password = fLib.getPropertyData("password");
	public ExcelReader xls = new ExcelReader(ConstantsConfig.DATA_XLS_PATH);
	public SoftAssert softAssert = new SoftAssert();
	public static AppiumDriverLocalService service;
	public static IOSDriver driver;
	public static IOSDriver safdriver;
	public static AndroidDriver isdriver;
	public Utilitycommon util = new Utilitycommon();
	public static String otp=Utilitycommon.Otp1;
	public String currentRunningMethod;
	public static String linkurl;
	public ExtentReports report;
	public static ExtentTest test;
	public ExtentSparkReporter spark;
	public static String className;
	public static String testCaseName;
	
	static int count_passedTCs;
	static int count_skippedTCs;
	static int count_failedTCs;
	static int count_totalTCs;
	
	@BeforeSuite
	public void beforeSuite() {
		////before class 
	}
	@AfterSuite
	public void Kill() throws Exception {
//		driver.terminateApp(PropertyUtils.getTestConfiguration("bundleId"));
//		driver.quit();
		//zipReport1();
		//String Reportpath=System.getProperty("user.dir") + "/ExtentReports.zip";
		//EmailSendUtils.sendEmail(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs, Reportpath);
	}
	public void zipReport1() {
		String reportsLocation =    System.getProperty("user.dir") + "/Extent Report/" +dateForReport()+"/"+ className ;
		ZipUtil.pack(new File(reportsLocation), new File("ExtentReports.zip"));
	}
//	@BeforeClass
//	public void configBc(ITestContext context) throws Throwable {
//		LogManager.getLogger().info("Runnig Before Class");
//		className = this.getClass().getSimpleName();
//		
//	}
	
	@BeforeClass
	public void configBc(ITestContext context) throws Throwable {
		
		testCaseName = this.getClass().getSimpleName();
		LogManager.getLogger().info("Runnig Before Class");
		className = this.getClass().getSimpleName();
		System.out.println(className);
		spark = new ExtentSparkReporter("./Extent Report/" +dateForReport()+"/"+ className + "/" + className+ dateTime() +".html");
		spark.config().setDocumentTitle("Ohio NFSR");
		spark.config().setReportName(className + " Automation Execution report");
		report = new ExtentReports();
		report.attachReporter(spark);
		//report.setSystemInfo("os", System.getProperty("os.name"));
		report.setSystemInfo("Platform Name", PropertyUtils.getTestConfiguration("platformNameios"));
		report.setSystemInfo("Device Version", PropertyUtils.getTestConfiguration("platformVersionios"));
		report.setSystemInfo("Device Name", PropertyUtils.getTestConfiguration("deviceNameios"));

		ioscapabilities();
		driver.activateApp(PropertyUtils.getTestConfiguration("bundleId"));
		driver.terminateApp(PropertyUtils.getTestConfiguration("bundleId"));
		driver.activateApp(PropertyUtils.getTestConfiguration("bundleId"));
		FileUtility.printSessionID(driver.getSessionId());
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 2);
		
	}
	@BeforeMethod
	public void configBm(ITestResult result) throws Throwable {
		
		count_totalTCs = count_totalTCs + 1;
		test = report.createTest(result.getMethod().getMethodName());
		System.out.println("starting: " + result.getMethod().getMethodName());
		LogManager.getLogger().info("starting: " + result.getMethod().getMethodName());
	}
	
//	@BeforeMethod
//	public void configBm(ITestResult result) throws Throwable {
//		
//		
//		currentRunningMethod = result.getMethod().getMethodName();
//		spark = new ExtentSparkReporter("./Extent Report/" +dateForReport()+"/"+ className + "/" + currentRunningMethod+ dateTime() +".html");
//		
//		spark.config().setDocumentTitle("Ohio Report");
//		spark.config().setReportName(className + " Automation Execution report");
//		
//		report = new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("os", System.getProperty("os.name"));
//		
//		test = report.createTest(result.getMethod().getMethodName());
////		test.assignAuthor("Biswajit");
//		System.out.println("starting: " + result.getMethod().getMethodName());
//		LogManager.getLogger().info("starting: " + result.getMethod().getMethodName());
//		
//		ioscapabilities();
//		driver.activateApp(PropertyUtils.getTestConfiguration("bundleId"));
//		driver.terminateApp(PropertyUtils.getTestConfiguration("bundleId"));
//		driver.activateApp(PropertyUtils.getTestConfiguration("bundleId"));
//		FileUtility.printSessionID(driver.getSessionId());
//		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
////		driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 2);
////		Login();
//	}
	
//	@AfterMethod
//	public void configAm(ITestResult result) throws Exception {
//		
//		
//		if (result.getStatus() == ITestResult.FAILURE) {
//			try {
//				test.log(Status.FAIL, result.getMethod().getMethodName(), MediaEntityBuilder
//						.createScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64))
//						.build());
//			} catch (WebDriverException e) {
////				test.log(Status.FAIL, result.getMethod().getMethodName(), MediaEntityBuilder
////						.createScreenCaptureFromBase64String(((TakesScreenshot) safdriver).getScreenshotAs(OutputType.BASE64))
////						.build());
//			}
//			
//		}
//
//		if (result.getStatus() == ITestResult.SKIP) {
//			test.log(Status.SKIP, result.getMethod().getMethodName());
//		}
//
//		if (result.getStatus() == ITestResult.SUCCESS) {
//			Thread.sleep(1000);
////			test.log(Status.PASS, "Test Case Passed", MediaEntityBuilder
////					.createScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64))
////					.build());
//		}
//
//		softAssert.assertAll();
//		LogManager.getLogger().info(
//				"---------------------------Execution of the Method is Finished--------------------------------------\n \n");
//		
//		report.flush();
////		BasePage.source();
////		driver.terminateApp(PropertyUtils.getTestConfiguration("bundleId"));
//		driver.quit();
//		System.out.println("quitting driver");
//	}
	
	@AfterMethod(alwaysRun = true)
	public void configAm(ITestResult result) throws Exception {
		
		
		if (result.getStatus() == ITestResult.FAILURE) {
			count_failedTCs = count_failedTCs + 1;
			
			test.log(Status.FAIL, result.getMethod().getMethodName(), MediaEntityBuilder
					.createScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64))
					.build());
			report.flush();
			org.testng.Assert.fail("Test Case Fail");
			System.out.println("Test Case Fail");
		}

		if (result.getStatus() == ITestResult.SKIP) {
			count_skippedTCs = count_skippedTCs + 1;
			
			test.log(Status.SKIP, result.getMethod().getMethodName());
		}

		if (result.getStatus() == ITestResult.SUCCESS) {
			count_passedTCs = count_passedTCs + 1;
			
			test.log(Status.PASS, "Test Case Passed", MediaEntityBuilder
					.createScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64))
					.build());
		}

		softAssert.assertAll();
		LogManager.getLogger().info("---------------------------Execution of the Method is Finished---------------------------\n \n");
		LogManager.getLogger().info("---------------------------report Flush Started---------------------------\n \n");
		report.flush();
		LogManager.getLogger().info("---------------------------report Flush Finished---------------------------\n \n");
		
		//driver.quit();
//		BasePage.source();
		Thread.sleep(4000);
		System.out.println("quitting driver");
	}
	public void zipReport() {
		String reportsLocation =    System.getProperty("user.dir") + "/Extent Report/" +dateForReport()+"/"+ className ;
		ZipUtil.pack(new File(reportsLocation), new File("ExtentReports.zip"));
	}

	public static String getScreenshot(WebDriver driver) throws IOException {
		String dateName = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Extent Report/" + className + "/Screenshot/" + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	public static IOSDriver ioscapabilities() throws Exception{
		DesiredCapabilities ioscap = new DesiredCapabilities();
		ioscap.setCapability("platformName", PropertyUtils.getTestConfiguration("platformNameios"));
		ioscap.setCapability("platformVersion", PropertyUtils.getTestConfiguration("platformVersionios"));
		ioscap.setCapability("deviceName", PropertyUtils.getTestConfiguration("deviceNameios"));
		ioscap.setCapability("udid", PropertyUtils.getTestConfiguration("udidios"));
		ioscap.setCapability("automationName", PropertyUtils.getTestConfiguration("automationNameios"));
		ioscap.setCapability("bundleId", PropertyUtils.getTestConfiguration("bundleId"));
		ioscap.setCapability("newCommandTimeout", 60);
		ioscap.setCapability("noReset", true);
		ioscap.setCapability("safariIgnoreFraudWarning", true);
		ioscap.setCapability("wdaLocalPort", 8104);
		ioscap.setCapability("webkitDebugProxyPort", 11072);
		URL url = new URL("http://0.0.0.0:4723/");
		driver = new IOSDriver(url,ioscap);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}
//	public static void  activateurl(String Eemail) throws IOException, Exception {
//		threedisystememailid threedisystememailid = new threedisystememailid();
//		//System.out.println(Eemail);
//		threedisystememailid.geturls(Eemail);
//		linkurl = threedisystememailid.verificationurl;
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("platformName", PropertyUtils.getTestConfiguration("platformNameios"));
//		caps.setCapability("platformVersion", PropertyUtils.getTestConfiguration("platformVersionios"));
//		caps.setCapability("deviceName", PropertyUtils.getTestConfiguration("deviceNameios"));
//		caps.setCapability("udid", PropertyUtils.getTestConfiguration("udidios"));
//		
//		caps.setCapability("browserName", "safari");//
//		caps.setCapability("nativeWebTap",true);
//		caps.setCapability("automationName", PropertyUtils.getTestConfiguration("automationNameios"));
//		safdriver = new IOSDriver(new URL("http://127.0.0.1:4723/"), caps);
//		safdriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		safdriver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
//		safdriver.get(linkurl);
//		Thread.sleep(5000l);
//		WebDriverWait wait = new WebDriverWait(safdriver, Duration.ofSeconds(60));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Login']")));
//		safdriver.quit();
//			
//		}
		
	
	public static IOSDriver  safariurl() throws IOException, Exception {
		//threedisystememailid threedisystememailid = new threedisystememailid();
		//System.out.println(Eemail);
		//threedisystememailid.geturls(Eemail);
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", PropertyUtils.getTestConfiguration("platformNameios"));
		caps.setCapability("platformVersion", PropertyUtils.getTestConfiguration("platformVersionios"));
		caps.setCapability("deviceName", PropertyUtils.getTestConfiguration("deviceNameios"));
		caps.setCapability("udid", PropertyUtils.getTestConfiguration("udidios"));	
		caps.setCapability("automationName", "XCUITest");
		//caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, PropertyUtils.getTestConfiguration("automationNameios"));
		caps.setCapability("browserName", "Safari");
		caps.setCapability("safariInitialUrl", "https://www.google.com");
		//
		safdriver = new IOSDriver(new URL("http://127.0.0.1:4723/"), caps);
//		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, PropertyUtils.getTestConfiguration("automationNameios"));
//		safdriver = new IOSDriver(new URL("http://127.0.0.1:4723/"), caps);
		safdriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		safdriver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		safdriver.get("https://ohiosfm-uatidm.3diengage.com/Account/Login?ReturnUrl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dwordpress-client%26scope%3Dopenid%2520email%2520profile%26redirect_uri%3Dhttps%253A%252F%252Fohiosfm.3diengage.com%26response_type%3Dcode%26state%3DX6FwqzWmQ7rbVcdvG94B");
		Thread.sleep(5000l);
		
		//safdriver.quit();
			return safdriver;
		}
	public static AndroidDriver capabilities() throws IOException, InterruptedException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", PropertyUtils.getTestConfiguration("platformName"));
		cap.setCapability("platformVersion", PropertyUtils.getTestConfiguration("platformVersion"));
		cap.setCapability("deviceName", PropertyUtils.getTestConfiguration("deviceName"));
		cap.setCapability("udid", PropertyUtils.getTestConfiguration("udid"));
		cap.setCapability("automationName", PropertyUtils.getTestConfiguration("automationName"));
		cap.setCapability("appPackage", PropertyUtils.getTestConfiguration("appPackage"));
		cap.setCapability("appActivity", PropertyUtils.getTestConfiguration("appActivity"));
		cap.setCapability(className, false);
		cap.setCapability("noReset", "false");
		cap.setCapability("appTopLevelWindows", "handle");
		cap.setCapability("chromedriverExecutable",System.getProperty("user.dir")+"//Drivers//chromedriver");
	    cap.setCapability("applicationCacheEnabled", false);
//		cap.setCapability("newCommandTimeout", 90000);
		cap.setCapability("noReset", true); 
		cap.setCapability("clearSystemFiles", true);
		cap.setCapability("waitForIdleTimeout", 10);
		cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));  
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		isdriver = new AndroidDriver(url, cap);
		isdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return isdriver;
	}

	public static void getScreenshot(String s) throws IOException {
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir") + "\\" + s + ".png"));
	}

//	public static void Login() throws IOException {
//		String username = PropertyUtils.getTestData("username");
//		String password = PropertyUtils.getTestData("password");
//		SBIYonoLoginPage loginPage = new SBIYonoLoginPage();
//		loginPage.clickOnLoginBtn();
//		loginPage.clickOnUserID();
//		loginPage.EnterCredentials(username, password);
//		loginPage.clickOnLogin();
//	}

	public static void LogOut() {
//		SBIYonoLogoutPage logoutPage = new SBIYonoLogoutPage();
//		logoutPage.clickOnLogout();
		
	}


	private String dateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_dd-MM-yyyy_HHmmss");  
	    LocalDateTime now = LocalDateTime.now();  
	    return dtf.format(now);
	}
	
	public String dateForReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	    LocalDateTime now = LocalDateTime.now();  
	    return dtf.format(now);
	}
	
	public void fetchOTP() throws InterruptedException, IOException {
		
		Thread.sleep(3000);
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformName", PropertyUtils.getTestConfiguration("platformName"));
			cap.setCapability("platformVersion", PropertyUtils.getTestConfiguration("platformVersion"));
			cap.setCapability("deviceName", PropertyUtils.getTestConfiguration("deviceName"));
			cap.setCapability("udid", PropertyUtils.getTestConfiguration("udid"));
			cap.setCapability("browserName", "Chrome");
			cap.setCapability("chromedriverExecutable","C:\\Users\\Administrator\\Desktop\\Raj\\ChromeDriver\\chromedriver.exe");
			URL url;
			url = new URL("http://0.0.0.0:4723/wd/hub");
			isdriver = new AndroidDriver(url, cap);
			isdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//			driver.activateApp("com.android.chrome");
			Thread.sleep(2000);
			isdriver.get("https://muat.intouch.onlinesbi.com/mfp/api/adapters/UtilsAdapter/getOtp/UAT/7400426831");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		


	}
	
	
	@DataProvider
	public Object[][] getData() throws Exception{
		return DataUtil.getData(xls, testCaseName);
	}
	@DataProvider
	public Object[][] getIncidentData() throws Exception{
		return DataUtil.getIncidentData(xls, testCaseName);
	}
	
}
