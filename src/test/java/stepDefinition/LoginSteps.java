package stepDefinition;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.DriverManager;
import pages.DashboardPage;
import pages.LandingPage;
import pages.LoginPage;
import utility.ConfigUtil;

public class LoginSteps {
	WebDriver driver;
	LandingPage landingPage;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	
	@Given("user opens landing page")
	public void user_opens_landing_page() {
		driver = DriverManager.getWebDriver();
		driver.get(ConfigUtil.getProperty("homePageUrl"));
	}
	
	@When("user navigates to login page")
	public void user_navigates_to_login_page() {
		landingPage = new LandingPage(driver);
		loginPage = landingPage.clickSignInSignUpLink();
	}
	
	@When("user enters {string} in Email input")
	public void user_enters_email(String email) {
		loginPage.inputEmail(email);
	}
	
	@And("user enters {string} in Password input")
	public void user_enters_password(String password) {
		loginPage.inputPassword(password);
	}
	
	@And("user clicks SignIn button")
	public void user_clicks_on_signIn_button() {
		dashboardPage = loginPage.clickSignInButton();
	}
	
	@Then("validate user product size is correct")
	public void validate_user_product_size_is_correct() {
		int productCount = dashboardPage.getAvailableProductsCount();
		assertTrue(productCount == 1);
	}
	
	@Then("validate user name is displayed correctly")
	public void validate_user_name_is_displayed_correctly() {
		
		
	}
	
}
