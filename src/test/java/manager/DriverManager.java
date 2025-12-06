package manager;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();
	
	public static WebDriver getWebDriver() {
		return threadLocalWebDriver.get();
	}
	
	public static void saveWebDriver(WebDriver driver) {
		threadLocalWebDriver.set(driver);
	}
	
	public static void quitWebDriver() {
		threadLocalWebDriver.get().quit();
	}
}
