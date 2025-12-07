package hook;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;

import dataDictionary.ThreadDataMap;
import driverGenerator.WebDriverGenerator;
import enumeration.WebDriverType;
import enumeration.WebDriverVendor;
import extent.ExtentTestManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import manager.DriverManager;
import utility.ConfigUtil;
import utility.WebUtil;

public class Hook {
	String scenarioName;
	WebDriver driver;
	ThreadDataMap threadDataMap;
	
	@Before
	public void beforeScenario(Scenario scenario) throws MalformedURLException {
		scenarioName = scenario.getName();
		
		/**
		 * create web driver instance based on property value
		 */
		String driverTypeString = ConfigUtil.getProperty("webDriverType");
		String driverVendorString = ConfigUtil.getProperty("webDriverVendor");
		WebDriverType driverType;
		WebDriverVendor driverVendor;
		if (driverTypeString.equalsIgnoreCase("local")) {
			driverType = WebDriverType.LOCAL;
		} else {
			driverType = WebDriverType.REMOTE;
		}
		switch (driverVendorString) {
			case "chrome": 
				driverVendor = WebDriverVendor.CHROME;
				break;
			case "edge": 
				driverVendor = WebDriverVendor.EDGE;
				break;
			default:
				driverVendor = WebDriverVendor.FIREFOX;
		}
		new WebDriverGenerator(driverType, driverVendor);
		driver = DriverManager.getWebDriver();
		
		/**
		 * create thread data map instance for each thread and it is used to store data in key-value pair
		 */
		threadDataMap = new ThreadDataMap();
		
		/**
		 * add each tag name of active running scenario to extent report
		 */
		ExtentTestManager.getTest().assignCategory(scenario.getSourceTagNames().toArray(new String[0]));
		
		/**
		 * ensure each thread will have a different 'testName' value, 
		 * so that each thread will have a unique routing log file associated with
		 */
		ThreadContext.put("testName", scenarioName + "_" + Thread.currentThread().getId()); 
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			driver = DriverManager.getWebDriver();
			ExtentTestManager.getTest().fail("<b>Scenario -" + scenarioName + ": </b>" + "<b style=\"color: red\">Failed</b>");
			try {
				ExtentTestManager.getTest().addScreenCaptureFromPath(WebUtil.getScreenshot(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			ExtentTestManager.getTest().pass("<b>Scenario -" + scenarioName + ": </b>" + "<b style=\"color: green\">Passed</b>");
		}
		ExtentTestManager.endTest();
		driver.quit();
		threadDataMap.removeDataMap();
	}
}
