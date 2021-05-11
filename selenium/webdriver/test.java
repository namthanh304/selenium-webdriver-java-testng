package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
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
	String projectpath = System.getProperty("user.dir");
	String usernamelogin = "mngr233468";
	String pass = "tYqAhaq";
	By username = By.name("uid");
	By Password = By.name("password");
	By loginbutton = By.name("btnLogin");
	


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	@Test
	public void TC_01_textbox_textarea() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(usernamelogin);
		driver.findElement(Password).clear();
		driver.findElement(username).sendKeys(pass);
		
		driver.findElement(loginbutton).click();

		
		
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
