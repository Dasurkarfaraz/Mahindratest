package utils;



import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.Reporter;

import Base.BaseTest;


public class VerificationUtils {

	public static void validate(Object expected, Object actual, String assertionFor) {

		try {
			logFile(assertionFor,actual, expected);
			Assert.assertEquals(actual, expected, assertionFor);
			ReporterClass.pass("Assertion for " + " - <b> <u>" + assertionFor
					+ "</u> </b>   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,
					false);
			LogManager.getLogger().info("Assertion for " + " - " + assertionFor
					+ "  |   Actual: " + actual + " and  Expected: " + expected,
					true);
		} catch (AssertionError assertionError) {
			ReporterClass.fail("Assertion for " + " - <b> <u>" + assertionFor
					+ "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,true);
			LogManager.getLogger().info("Assertion for " + " - " + assertionFor
					+ "   |   Actual: " + actual + " and  Expected: " + expected,true);
//			BaseTest.LogOut();	/* LogOut First then assertion fail because after that @AfterMethod running */
			//Assert.fail(assertionFor);
		}
	}
	/**
	 * No ScreenShot
	 * 
	 * @param expected
	 * @param actual
	 * @param assertionFor
	 */
	public static void validate2(Object expected, Object actual, String assertionFor) {
		
		try {
			logFile(assertionFor,actual, expected);
			Assert.assertEquals(actual, expected, assertionFor);
			BaseTest.test.pass("Assertion for " + " - <b> <u>" + assertionFor
					+ "</u> </b>   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
			LogManager.getLogger().info("Assertion for " + " - " + assertionFor
					+ "  |   Actual: " + actual + " and  Expected: " + expected,
					true);
		} catch (AssertionError assertionError) {
			BaseTest.test.fail("Assertion for " + " - <b> <u>" + assertionFor
					+ "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
			LogManager.getLogger().info("Assertion for " + " - " + assertionFor
					+ "   |   Actual: " + actual + " and  Expected: " + expected,true);
			BaseTest.LogOut();	/* LogOut First then assertion fail because after that @AfterMethod running */
			Assert.fail(assertionFor);
		}
	}
	
	private static void logFile(String assertionFor,Object actual, Object expected) {
		Reporter.log("Checking:"+assertionFor,true);
		Reporter.log("Expected: " + expected, true);
		Reporter.log("Actual: " + actual, true);
	}

	@SuppressWarnings("unused")
	private static void logFile(Object actual, Object expected) {
		Reporter.log("Actual: " + actual, true);
		Reporter.log("Expected: " + expected, true);
	}
	
	private static void logFile(String message) {
		Reporter.log(message, true);
	}

	/**
	 * In result you can pass element.isDisplayed() or any other boolean conditions
	 * @param result
	 * @param message
	 */
	public static void validateResponse(boolean result, String message) {
		try {
			logFile(message);
			Assert.assertTrue(result);
			ReporterClass.pass("<b><i>" + message + "</b></i>", true);
		} catch (AssertionError assertionError) {
			ReporterClass.fail("<b><i>" + message + "</b></i>",true);
			Assert.fail(message);
		}
	}

}
