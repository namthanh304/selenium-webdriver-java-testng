package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Javascript_excutor {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");
	WebDriverWait explicitiwait;
	JavascriptExecutor jsExecutor;
	Alert alert;
	Actions action;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		explicitiwait = new WebDriverWait(driver, 15);
		
		jsExecutor = (JavascriptExecutor) driver;
		
		action = new Actions(driver);
	}
	
	@Test
	public void TC_01_() {
			driver.get("https://sieuthimaymocthietbi.com/account/register");
			
			driver.findElement(By.xpath("//div[@class='form-signup clearfix']//button[@type='submit']")).click();
			
			String validatemsg;
			
			validatemsg =  (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement("//input[@id='lastName']"));
						
			Assert.assertEquals(validatemsg, "Please fill out this field.");
			
			driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("hello");
			
			driver.findElement(By.xpath("//div[@class='form-signup clearfix']//button[@type='submit']")).click();
			
			validatemsg =  (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement("//input[@id='firstName']"));
			
			Assert.assertEquals(validatemsg, "Please fill out this field.");
			
			driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("world");
			
			driver.findElement(By.xpath("//div[@class='form-signup clearfix']//button[@type='submit']")).click();
			
			validatemsg =  (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement("//input[@type='email']"));
			
			Assert.assertEquals(validatemsg, "Please fill out this field.");
			
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("a");
			
			driver.findElement(By.xpath("//div[@class='form-signup clearfix']//button[@type='submit']")).click();

			validatemsg =  (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement("//input[@type='email']"));
			
			Assert.assertEquals(validatemsg, "Please include an '@' in the email address. 'a' is missing an '@'.");
			
			driver.findElement(By.xpath("//input[@type='email']")).clear();
			
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("a@");
			
			driver.findElement(By.xpath("//div[@class='form-signup clearfix']//button[@type='submit']")).click();
			
			validatemsg =  (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement("//input[@type='email']"));
			
			Assert.assertEquals(validatemsg, "Please enter a part following '@'. 'a@' is incomplete.");
			
	}
	
	

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		Sleeper.sleepTightInSeconds(1);;
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}