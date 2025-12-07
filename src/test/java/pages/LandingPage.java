package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import extent.ExtentTestManager;

public class LandingPage extends BasePage {
	WebDriver driver;
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
