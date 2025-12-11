package driverGenerator;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
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
			ChromeOptions chromeOption = new ChromeOptions();
			driver = new RemoteWebDriver(new URL(ConfigUtil.getProperty("remoteWebDriverUrl")), chromeOption);
		} else {
			EdgeOptions edgeOption = new EdgeOptions();
			driver = new RemoteWebDriver(new URL(ConfigUtil.getProperty("remoteWebDriverUrl")), edgeOption);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigUtil.getProperty("implicitWaitTimeout"))));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigUtil.getProperty("pageLoadWaitTimeout"))));
		DriverManager.saveWebDriver(driver);
	}
}
