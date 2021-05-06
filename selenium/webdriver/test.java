package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.GetPageSource;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Click;

public class test {
	WebDriver driver;
	WebElement element;
	String projectpath = System.getProperty("user.dir");
	By AgeUnder18Radio = By.id("under_18");
	By languageJavaCheckbox = By.id("java");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	@Test
	public void TC_05_Register_Function() {
		driver.get("https://login.mailchimp.com/signup/");
		By Email = By.id("email");
		By username = By.id("new_username");
		By password = By.id("new_password");
		
		Inputvalue(Email, "Testing@gmail.com");
		Inputvalue(username, "Nam Nguyen");
		Inputvalue(password, "1");
		IsElementCompted(By.xpath("//li[contains(@class,'number-char')]"));
		Inputvalue(password, "a");
		IsElementCompted(By.xpath("//li[contains(@class,'lowercase-char')]"));
		Inputvalue(password, "A");
		IsElementCompted(By.xpath("//li[contains(@class,'uppercase-char')]"));
		Inputvalue(password, "@");
		IsElementCompted(By.xpath("//li[contains(@class,'special-char')]"));
		Inputvalue(password, "12345678");
		IsElementCompted(By.xpath("//li[contains(@class,'8-char')]"));
		Inputvalue(password, "1");
		
	}
	
	
	
	public boolean IsElementCompted(By by) {
		if (driver.findElement(by).getAttribute("class").contains("completed")) {
			System.out.println(by + " Is satisfy");
			return true;
		} else {
			System.out.println(by + " Isnot satisfy");
			return false;
		}
	}
	
	
	public void Inputvalue (By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	
	public void ClickElement(By by) {
		driver.findElement(by).click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
