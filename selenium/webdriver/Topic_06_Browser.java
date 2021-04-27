package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.GetPageSource;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Browser {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_verify_Url() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String curURL = driver.getCurrentUrl();
		Assert.assertEquals(curURL, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
		String registerURL = driver.getCurrentUrl();
		Assert.assertEquals(registerURL, "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_verify_title() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String curURL = driver.getTitle();
		Assert.assertEquals(curURL, "Customer Login");
		
		driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
		String registerURL = driver.getTitle();
		Assert.assertEquals(registerURL, "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigate_funtion() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String curURL = driver.getCurrentUrl();
		Assert.assertEquals(curURL, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
		String registerURL = driver.getCurrentUrl();
		Assert.assertEquals(registerURL, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		// click back
		driver.navigate().back();
		String backURL = driver.getCurrentUrl();
		Assert.assertEquals(curURL, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//forward
		driver.navigate().forward();
		String forwardregisterURL = driver.getCurrentUrl();
		Assert.assertEquals(forwardregisterURL, "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_04_get_page_source_code() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String sourcecode = driver.getPageSource();
		sourcecode.contains("Login or Create an Account");
		Assert.assertEquals(sourcecode.contains("Login or Create an Account"), true);

		driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
		String Createsourcecode = driver.getPageSource();
		Assert.assertEquals(Createsourcecode.contains("Create an Account"), true);
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
