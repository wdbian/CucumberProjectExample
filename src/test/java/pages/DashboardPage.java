package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import extent.ExtentTestManager;

/**
 * Access To Page:
 * Landing Page (click 'SIGN UP/SIGN IN' button) -> Login Page (input email & password) -> Dashboard Page
 */
public class DashboardPage extends BasePage{
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//li[@class='myProductsOverview-cardListItem']/div")
	private List<WebElement> availableProducts;
	
	@FindBy(xpath="//li[@class='myProductsOverview-cardListItem']/div[contains(@class, 'myProductsOverview')]//*[@class='myProductsOverview-cardBalance']")
	private WebElement myProductCardBalance;
	
	@FindBy(xpath="//a[./parent::*[@class='myProductsOverview-textWrapper']]")
	private WebElement customerName;
	
	@FindBy(xpath="//button[text()='Order a physical card']")
	private WebElement orderPhysicalCardButton;
	
	public DashboardPage(WebDriver driver){
		super(driver);
	}
	
	public int getAvailableProductsCount(){
		waitForElementsDisplay(availableProducts);
		return availableProducts.size();
	}
	
	public String getCustomerName(){
		waitForElementDisplay(customerName);
		return customerName.getText().trim();
	}
	
	public OrderPhysicalCard clickOrderPhysicalCardButton(){
		waitForPageLoaded();
		String currentWindowHandle = getCurrentWindowHandle();
		int currentNumberOfWindows = getNumberOfOpenWindows();
		clickElement(orderPhysicalCardButton);
		logger.info("Clicked on 'ORDER A PHYSICAL CARD' button on Dashboard Page");
		ExtentTestManager.getTest().info("Clicked on 'ORDER A PHYSICAL CARD' button on Dashboard Page");
		waitForNewWebPageDisplay(currentNumberOfWindows);
		switchToWindowNotSpecified(currentWindowHandle);
		return new OrderPhysicalCard(driver);
	}

}
