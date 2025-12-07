package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import extent.ExtentTestManager;

/**
 * Access To Page:
 * Landing Page (click "SIGN UP/SIGN IN" button) -> Login Page
 */
public class LoginPage extends BasePage{
	WebDriver driver;
	DashboardPage dashboardPage;
	
	@FindBy(xpath="//input[@id='signInName' and ./preceding-sibling::*[contains(text(), 'Email Address')]]")
	private WebElement emailInputField;
	
	@FindBy(xpath="//input[@id='password' and ./preceding-sibling::*//*[text()='Password']]")
	private WebElement passwordInputField;
	
	@FindBy(xpath="//button[@type='submit' and text()='SIGN IN']")
	private WebElement signInBtn;
	
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	public void inputEmail(String email){
		inputText(emailInputField, email);
		logger.info("input '" + email + "' to Email inputbox on Login Page");
		ExtentTestManager.getTest().info("input '" + email + "' to Email inputbox on Login Page");
	}
	
	public void inputPassword(String pwd){
		inputText(passwordInputField, pwd);
		logger.info("input '" + pwd + "' to Password inputbox on Login Page");
		ExtentTestManager.getTest().info("input '" + pwd + "' to Password inputbox on Login Page");
	}
	
	public DashboardPage clickSignInButton(){
		clickElement(signInBtn);
		logger.info("click on 'SIGN IN' button on Login Page");
		ExtentTestManager.getTest().info("click on 'SIGN IN' button on Login Page");
		dashboardPage = new DashboardPage(driver);
		return dashboardPage;
	}
}
