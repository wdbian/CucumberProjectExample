package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigUtil;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger logger;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Long.parseLong(ConfigUtil.getProperty("explicitWaitTimeout")));
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(this.getClass());
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWebDriverWait() {
		return this.wait;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setWebDriverWait(int waitSecond) {
		wait = new WebDriverWait(driver, waitSecond);
	}
	
	public void navigateTo(String url) {
		driver.navigate().to(url);
		waitForPageLoaded();
	}
	
	public void navigateBack() {
		driver.navigate().back();
		waitForPageLoaded();
	}
	
	public void clickElement(WebElement element) {
		waitForElementToBeClickable(element);
		highlightElement(element);
		element.click();
	}
	
	public void clickElementByJavaScript(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		if (! isElementDisplayed(element)) {
			waitForElementDisplay(element);
		}
		jse.executeScript("arguments[0].click;", element);
	}
	
	public void inputText(WebElement element, String text) {
		if (! isElementDisplayed(element)) {
			waitForElementDisplay(element);
		}
		highlightElement(element);
		element.clear();
		element.sendKeys(text);
	}
	
	public void inputTextByJavaScript(WebElement element, String text) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		if (! isElementDisplayed(element)) {
			waitForElementDisplay(element);
		}
		jse.executeScript("arguments[0].value=arguments[1];", element, text);
	}
	
	public void waitForPageLoaded() {
		if (! isPageLoaded()) {
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					// TODO Auto-generated method stub
					return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
				}
			});
		}
	}
	
	public void waitForPageLoaded(int seconds) {
		if (! isPageLoaded()) {
			wait = new WebDriverWait(driver, seconds);
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					// TODO Auto-generated method stub
					return  ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
				}	
			});
		}
	}
	
	public void waitForElementPropertyToBe(String elementXPath, String propertyName, String propertyValue) {
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath(elementXPath)).getAttribute(propertyName).equals(propertyValue);
			}
		});
	}
	
	public void waitForElementDisplay(WebElement element) throws TimeoutException {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementsDisplay(List<WebElement> elementList) throws TimeoutException {
		wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
	}
	
	public void waitForElementDisappear(WebElement element) throws TimeoutException {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementsDisappear(List<WebElement> elementList) throws TimeoutException {
		wait.until(ExpectedConditions.invisibilityOfAllElements(elementList));
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForSelectOptionsCountToEqual(WebElement element, int optionCount) {
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				Select s = new Select(element);
				return (s.getOptions().size() == optionCount);
			}	
		});
	}
	
	public void clickWebElement(WebElement element) {
		if (! isElementDisplayed(element)) {
			waitForElementDisplay(element);
		}
		element.click();
	}
	
	public void highlightElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 2px solid red;');", element);
	}
	
	public boolean isPageLoaded() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		return jse.executeScript("return document.readyState").equals("complete");
	}
	
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}
	
	public boolean areElementsDisplayed(List<WebElement> elementList)
	{
		if(elementList.size() == 0)
		{
			return false;
		}else
		{
			return true;
		}	
	}
	
	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}
	
	public boolean isElementAttributeEqualToValue(WebElement element, String elementAttribute, String value) {
		return element.getAttribute(elementAttribute).equalsIgnoreCase(value) ? true : false;
	}
	
	public void enterTabKey(WebElement element) {
		waitForElementDisplay(element);
		element.sendKeys(Keys.TAB);
	}
	
	public void pressEnterKey(WebElement element) {
		waitForElementDisplay(element);
		Actions actions = new Actions(driver);
		actions.sendKeys(element,Keys.ENTER).perform();
	}
	
	public boolean isAlertPresent() {
		boolean result = false;
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			result = true;
		} catch(TimeoutException e) {
			result = false;
		}
		return result;
	}
	
	public void acceptAlertIfPopOutWithinSeconds(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			acceptAlert();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}		
	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();	
		driver.switchTo().defaultContent();
	}
	
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
		driver.switchTo().defaultContent();
	}
	
	public void inputAlert(String str) {
		Alert alrt = driver.switchTo().alert();
		alrt.sendKeys(str);
		alrt.accept();
		driver.switchTo().defaultContent();
	}
	
	public void switchToIFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	public void switchBackFromIFrame() {
		driver.switchTo().defaultContent();
	}
	
	public void scrollToWebElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollDownToWindowBottom() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void openNewBrowserTapToUrlByJavascript(String url) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open(arguments[0], '_blank);", url);
	}
	
	public void openNewBlankBrowserTab() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open('about:blank', '_blank')");
	}
	
	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}
	
	public void returnToSpecificWindowHandle(String originalWindowHandle) {
		Set<String> windowHandles = driver.getWindowHandles();
		try {
			for (String s : windowHandles) {
				if (s.equals(originalWindowHandle)) {
					driver.switchTo().window(originalWindowHandle);
				}
			}
		} catch (NoSuchWindowException e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public void setLocalStorage(String key, String value) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.localStorage.setItem(arguments[0], arguments[1])", key, value);
	}
	
	public String getLocalStorage(String key) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		return (String) jse.executeScript("window.localStorage.getItem(arguments[0])", key);
	}
	
	public void hoverOnElement(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).build().perform();	
	}
	
	public String getTextOfWebElement(WebElement element) {
		return element.getText();
	}
	
	public String getTextOfWebElementAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}
	
	public String getTextOfWebElemetAttribute(String xpath, String attribute) {
		return driver.findElement(By.xpath(xpath)).getAttribute(attribute);
	}
	
	public String selectFromDropDownListByIndex(WebElement element, int selectedIndex) {
		String selectedOptionText = null;
		Select s = new Select(element);
		s.selectByIndex(selectedIndex);
		List<WebElement> optionList = s.getOptions();
		for (int i = 0; i < optionList.size(); i++) {
			if (i == selectedIndex) {
				selectedOptionText = optionList.get(i).getText();
				break;
			}
		}
		return selectedOptionText;
	}
	
	public String selectFromDropDownListByVisibleText(WebElement element, String selectedOptionText) {
		Select s = new Select(element);
		s.selectByVisibleText(selectedOptionText);
		return selectedOptionText;
	}
	
	public String selectFromDropDownListByRandomIndexAndExcludeCertainOption(WebElement element, String excludedOptionText) {
		Select s = new Select(element);
		int listSize = s.getOptions().size();
		int selectedIndex = (int) Math.floor(Math.random() * listSize);
		while (s.getOptions().get(selectedIndex).getText().equals(excludedOptionText)) {
			selectedIndex = (int) Math.floor(Math.random() * listSize);
		}
		s.selectByIndex(selectedIndex);
		return s.getOptions().get(selectedIndex).getText();
	}
	
	public <T> List<T> sortAscended(List<T> lst) {
		Collections.sort(lst, new Comparator<T>() {

			public int compare(T o1, T o2) {
				int result = 0;
				if ((o1 instanceof Integer) && (o2 instanceof Integer)) {
					if ((Integer)o1 > (Integer)o2) {
						result = 1; 
					}
					if ((Integer)o1 < (Integer)o2) {
						result = -1;
					}
					result = 0;
				}
				if ((o1 instanceof Float) && (o2 instanceof Float)) {
					if ((Float)o1 > (Float)o2) {
						result = 1;
					}
					if ((Float)o1 < (Float)o2) {
						result = -1;
					}
					result = 0;
				}
				if ((o1 instanceof String) && (o2 instanceof String)) {
					String s1 = (String)o1;
					String s2 = (String)o2;
					result = s1.compareTo(s2);
				}
				return result;
			}
			
		});
		return lst;		
	}
	
	public <T> List<T> sortDescended(List<T> lst) {
		Collections.sort(lst, new Comparator<T>() {

			public int compare(T o1, T o2) {
				int result = 0;
				if ((o1 instanceof Integer) && (o2 instanceof Integer)) {
					if ((Integer)o1 > (Integer)o2) {
						result = -1; 
					}
					if ((Integer)o1 < (Integer)o2) {
						result = 1;
					}
					result = 0;
				}
				if ((o1 instanceof Float) && (o2 instanceof Float)) {
					if ((Float)o1 > (Float)o2) {
						result = -1;
					}
					if ((Float)o1 < (Float)o2) {
						result = 1;
					}
					result = 0;
				}
				if ((o1 instanceof String) && (o2 instanceof String)) {
					String s1 = (String)o1;
					String s2 = (String)o2;
					if (s1.compareTo(s2) == 1) {
						result = -1;
					}
					if (s1.compareTo(s2) == -1) {
						result = 1;
					}
					result = 0;
				}
				return result;
			}
			
		});
		return lst;
	}
	
	public List<Date> sortDateAscended(List<Date> lst) {
		Collections.sort(lst, new Comparator<Date>() {
			public int compare(Date o1, Date o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}	
		});
		return lst;	
	}
	
	public List<Date> sortDateDescended(List<Date> lst) {
		Collections.sort(lst, new Comparator<Date>() {

			public int compare(Date o1, Date o2) {
				// TODO Auto-generated method stub
				int result = 0;
				if (o1.before(o2)) {
					result = 1;
				}
				if (o1.after(o2)) {
					result = -1;
				}
				return result;
			}
		});
		return lst;
	}
	
	public Date parseStringToDate(String dateText) {
		String datePattern = "YYYY-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		Date date = null;
		try {
			date = sdf.parse(dateText);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
}
