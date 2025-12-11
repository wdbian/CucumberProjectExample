package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPhysicalCard extends LandingBasePage{
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//button[@aria-label='Add to cart']")
	private WebElement addToCartButton;
	
	public OrderPhysicalCard(WebDriver driver) {
		super(driver);
	}

	public boolean isAddToCartButtonDisplayed() {
		waitForPageLoaded();
		return isElementExists(addToCartButton);
	}
}
