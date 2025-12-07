package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{
	WebDriver driver;
	
	@FindBy(xpath="//li[@class='myProductsOverview-cardListItem']/div")
	List<WebElement> availableProducts;
	
	@FindBy(xpath="//li[@class='myProductsOverview-cardListItem']/div[contains(@class, 'myProductsOverview')]//*[@class='myProductsOverview-cardBalance']")
	WebElement myProductCardBalance;
	
	public DashboardPage(WebDriver driver){
		super(driver);
	}
	
	public int getAvailableProductsCount(){
		return availableProducts.size();
	}

}
