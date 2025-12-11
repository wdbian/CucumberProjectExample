package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import extent.ExtentTestManager;

public class LandingBasePage extends BasePage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//div[@class='contentPageLayout-header']//a[@aria-label='My Cards']")
	private WebElement myCardsLink;
	
	@FindBy(xpath="//div[@class='contentPageLayout-header']//a[@aria-label='Learn']")
	private WebElement learnLink;
	
	@FindBy(xpath="//div[@class='contentPageLayout-header']//a[@aria-label='Ways to Pay']")
	private WebElement waysToPayLink;
	
	@FindBy(xpath="//div[@class='contentPageLayout-header']//a[@aria-label='My Profile']")
	private WebElement myProfileLink;
	
	@FindBy(xpath="//li[@class='user-icon']//ul[@class='dropdown-menu']//a[@role='button' and text()='Sign Out']")
	private WebElement signOutButton;
	
	public LandingBasePage(WebDriver driver) {
		super(driver);
	}
	
	public void clickMyCardsLink() {
		clickElement(myCardsLink);
		logger.info("Click on 'My Cards' link of the top content layout header on the page");
		ExtentTestManager.getTest().info("Click on 'My Cards' link of the top content layout header on the page");
	}
	
	public void clickLearnLink() {
		clickElement(learnLink);
		logger.info("Click on 'Learn' link of the top content layout header on the page");
		ExtentTestManager.getTest().info("Click on 'Learn' link of the top content layout header on the page");
	}
	
	public void signOut() {
		clickElement(myProfileLink);
		logger.info("Click on 'My Profile' link of the top content layout header on the page");
		ExtentTestManager.getTest().info("Click on 'My Profile' link of the top content layout header on the page");
		waitForElementToBeClickable(signOutButton);
		clickElement(signOutButton);
		logger.info("Click on 'Sign Out' button from the dropdown menu");
		ExtentTestManager.getTest().info("Click on 'Sign Out' button from the dropdown menu");
		
	}

}
