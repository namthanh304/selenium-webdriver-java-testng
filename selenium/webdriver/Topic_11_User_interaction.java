package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.remote.server.handler.GetPageSource;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Click;

import sun.security.ec.ECOperations;

public class Topic_11_User_interaction {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");
	WebDriverWait explicitiwait;
	JavascriptExecutor jsexcutor;
	Alert alert;
	Actions action;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		explicitiwait = new WebDriverWait(driver, 15);
		
		jsexcutor = (JavascriptExecutor) driver;
		
		action = new Actions(driver);
		

	}
	
	public void TC_01_HoverElement() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");

	}

	public void TC_02_HoverToElement() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		Sleeper.sleepTightInSeconds(2);
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
	}
	
	
	public void TC_04_Click_and_hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> items = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("Number of items : " + items.size());
		
		action.clickAndHold(items.get(0)).moveToElement(items.get(3)).release().perform();
		Sleeper.sleepTightInSeconds(3);
		
		Assert.assertEquals(driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(), 4);
	}
	
	@Test
	public void TC_05_Click_and_hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> items = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("Number of items : " + items.size());
		
		action.keyDown(Keys.CONTROL).perform();
		action.click(items.get(0)).click(items.get(4)).click(items.get(9)).click(items.get(10)).perform();
		action.keyUp(Keys.CONTROL).perform();
		
		action.release();
		
		Assert.assertEquals(driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(), 4);
	}
	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
