package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import manager.DriverManager;
import pages.DashboardPage;
import pages.OrderPhysicalCard;

public class OrderCardSteps {
	WebDriver driver;
	DashboardPage dashboardPage;
	OrderPhysicalCard orderPhysicalCard;
	
	@And("user clicks order physical card button from dashboard page")
	public void user_clicks_order_physical_card_button() {
		driver = DriverManager.getWebDriver();
		dashboardPage = new DashboardPage(driver);
		orderPhysicalCard  = dashboardPage.clickOrderPhysicalCardButton();	
	}
	
	@Then("validate add to cart button is displayed")
	public void validate_order_physical_card_page_is_displayed() {
		boolean isAddToCartButtonDisplayed = orderPhysicalCard.isAddToCartButtonDisplayed();
		Assert.assertTrue(isAddToCartButtonDisplayed, "Add to cart button is not displayed correctly.");
	}

}
