package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;

/*
 * Not using Listeners in my Framework 
 */

public class ListenerImpl implements ITestListener{
	public ExtentReports report;
	public ExtentTest test;
	ITestResult result;
	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		System.out.println("starting: "+ result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ReportLogger(Status.PASS,"Details of Failure" ,ScreenShotCapture());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		this.result=result;
		try {
			ReportLogger(Status.FAIL,"Details of Failure" ,ScreenShotCapture());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String ScreenShotCapture()
	{
		TakesScreenshot t=(TakesScreenshot)BaseTest.driver;
		File src=t.getScreenshotAs(OutputType.FILE); 
		File dest=new File("./Extent Report/screenshot/"+result.getMethod().getMethodName()+".PNG");
		try {
			FileUtils.copyFile(src,dest);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest.getAbsolutePath();
	}
	
	public void ReportLogger(Status status, String details, String screenshotPath) throws Throwable
	{
		test.log(status, details,MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlReporter=new ExtentSparkReporter("./Extent Report/"+context.getName()+".html"); 
//		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Yono Lite Regression Run");
		htmlReporter.config().setReportName("Automation Execution report");
		
		report=new ExtentReports();
		report.attachReporter(htmlReporter);
		report.setSystemInfo("os",System.getProperty("os.name"));
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
