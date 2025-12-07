package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Access To Page:
 * Landing Page (click 'SIGN UP/SIGN IN' button) -> Login Page (input email & password) -> Dashboard Page
 */
public class DashboardPage extends BasePage{
	WebDriver driver;
	
	@FindBy(xpath="//li[@class='myProductsOverview-cardListItem']/div")
	private List<WebElement> availableProducts;
	
	@FindBy(xpath="//li[@class='myProductsOverview-cardListItem']/div[contains(@class, 'myProductsOverview')]//*[@class='myProductsOverview-cardBalance']")
	private WebElement myProductCardBalance;
	
	@FindBy(xpath="//a[./parent::*[@class='myProductsOverview-textWrapper']]")
	private WebElement customerName;
	
	public DashboardPage(WebDriver driver){
		super(driver);
	}
	
	public int getAvailableProductsCount(){
		return availableProducts.size();
	}
	
	public String getCustomerName(){
		return customerName.getText().trim();
	}

}
