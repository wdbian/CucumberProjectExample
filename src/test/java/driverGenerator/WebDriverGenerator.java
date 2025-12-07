package driverGenerator;

import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import enumeration.WebDriverType;
import enumeration.WebDriverVendor;
import io.github.bonigarcia.wdm.WebDriverManager;
import manager.DriverManager;
import utility.ConfigUtil;

/**
 * WebDriverGenerator is used to generate WebDriver instance based on the type and vendor
 */
public class WebDriverGenerator {
	private WebDriver driver;
	
	public WebDriverGenerator(WebDriverType type, WebDriverVendor vendor) throws MalformedURLException {
		DesiredCapabilities desiredCapability;
		if (type.getDescription().equalsIgnoreCase("local") && vendor.getDescription().equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(option);
		} else if (type.getDescription().equalsIgnoreCase("local") && vendor.getDescription().equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(option);
		} else if (type.getDescription().equalsIgnoreCase("remote") && vendor.getDescription().equalsIgnoreCase("chrome")) {
			desiredCapability = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL(ConfigUtil.getProperty("remoteWebDriverUrl")), desiredCapability);
		} else {
			desiredCapability = DesiredCapabilities.edge();
			driver = new RemoteWebDriver(new URL(ConfigUtil.getProperty("remoteWebDriverUrl")), desiredCapability);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigUtil.getProperty("implicitWaitTimeout")), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(ConfigUtil.getProperty("pageLoadWaitTimeout")), TimeUnit.SECONDS);
		DriverManager.saveWebDriver(driver);
	}
}
