package extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	public static void startTest(String name) {
		ExtentReports extent = ExtentManager.getReporter();
		test.set(extent.createTest(name));
	}
	
	public static ExtentTest getTest() {
		return test.get();
	}
	
	public static void endTest() {
		test.remove();
	}
}
