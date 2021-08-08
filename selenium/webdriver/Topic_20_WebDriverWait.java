package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_WebDriverWait {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");
	WebDriverWait explicitiwait;
	JavascriptExecutor jsExecutor;



	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Mixwait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		By dateresult = By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitiwait = new WebDriverWait(driver, 10);
		
		explicitiwait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"))));
		scrollToElement("//span[@id='ctl00_ContentPlaceholder1_Label1']");
		
		String unpickdate = driver.findElement(dateresult).getText();
		Assert.assertEquals(unpickdate, "No Selected Dates to display.");
		
		explicitiwait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//td[@title='Sunday, August 01, 2021']/a"))));
		
		scrollToElement("//td[@title='Sunday, August 01, 2021']/a");
		//driver.findElement(By.xpath("//td[@title='Sunday, August 01, 2021']/a")).click();
		clickToElementByJS("//td[@title='Sunday, August 01, 2021']/a");
		
		explicitiwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("div[@class='raDiv']")));
		
		String pickeddate = driver.findElement(dateresult).getText();
		
		Assert.assertEquals(pickeddate, "Sunday, August 1, 2021");
		
		
	}
	
	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}