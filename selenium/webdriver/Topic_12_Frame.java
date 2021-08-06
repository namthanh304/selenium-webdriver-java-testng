package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_12_Frame {
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
	
	@Test
	public void TC_01_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("hello");
		
		driver.findElement(By.xpath("//table[@class='lForm']//img[@alt='continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='input_password']")).isDisplayed());
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("footer");
		
		driver.findElement(By.xpath("//form[@name='frmFooter']//a[text()='Terms and Conditions']")).click();
		
		
		
	}


	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}