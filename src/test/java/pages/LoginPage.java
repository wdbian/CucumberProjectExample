package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='Email']")
	public WebElement emailField;
	
	@FindBy(xpath="//input[@id='Password']")
	public WebElement passwordField;
	
	@FindBy(xpath="//input[@class='button-1 login-button' and @type='submit']")
	public WebElement loginBtn;
	
	public void inputEmail(String email)
	{
		emailField.clear();
		emailField.sendKeys(email);
	}
	
	public void inputPassword(String pwd)
	{
		passwordField.clear();
		passwordField.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		this.loginBtn.click();
	}
}
