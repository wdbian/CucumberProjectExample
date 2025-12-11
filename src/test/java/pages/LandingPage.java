package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import extent.ExtentTestManager;

/**
 * Access To Page:
 * input landing page URL
 */
public class LandingPage extends LandingBasePage {
	WebDriver driver;
	WebDriverWait wait;
	LoginPage loginPage;
	
	@FindBy(xpath="//a[text()='Sign In / Sign Up']")
	private WebElement signInSignUpLink;
	
		
	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage clickSignInSignUpLink() {
		clickElement(signInSignUpLink);
		logger.info("Clicked on 'Sign In / Sign Up' link on Landing Page");
		ExtentTestManager.getTest().info("Clicked on 'Sign In / Sign Up' link on Landing Page");
		loginPage = new LoginPage(driver);
		return loginPage;
	}
}
