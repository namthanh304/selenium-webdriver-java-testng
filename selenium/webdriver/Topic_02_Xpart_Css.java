package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_02_Xpart_Css {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Empty_Email_And_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Empty Email
		driver.findElement(By.id("email")).sendKeys("");
		//Empty Password
		driver.findElement(By.name("login[password]")).sendKeys("");
		//Click Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Verify expected text with requirement
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");

	}
	
	@Test
	public void TC_02_Invalid_Email_Address() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("123123123@123123");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// verify error message
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Invalid_Password() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// verify error message
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Incorrect_Password() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Incorrect_Email() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("virus@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	
	public static class GlobalClass {
	     public static String tmp_email = "";
	     public static String firstname = "nam";
	     public static String lastname = "nguyen";
	}
	
	@Test
	// TC_06_create_new_acc
	public void TC_06_Create_new_acc() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account' and @class='button']")).click();
		driver.findElement(By.id("firstname")).sendKeys(GlobalClass.firstname);
		driver.findElement(By.id("lastname")).sendKeys(GlobalClass.lastname);
		
		// create random email
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random email = new Random();
		int length = 7;
	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = email.nextInt(alphabet.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	     
	    }
	    String randomString = sb.toString();
	    GlobalClass.tmp_email = randomString + "@gmail.com";
	    //input email
	    driver.findElement(By.id("email_address")).sendKeys(GlobalClass.tmp_email);
	    //input pass
	    driver.findElement(By.id("password")).sendKeys("123456");
	    //input confirmpass
	    driver.findElement(By.id("confirmation")).sendKeys("123456");
	    // Click register
		driver.findElement(By.xpath("//button[@class='button' and @title='Register']//span[text()='Register']")).click();

		
		// verify message
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		// verify fistname and lastname
		//Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p/child::text()[1]")).getText(), fullname);
		// verify Email
		//Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p/child::text()[2]")).getText(), tmp_email);
		String text = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().replace("\nChange Password", "");
		Assert.assertEquals(text, GlobalClass.firstname + " " + GlobalClass.lastname + "\n" + GlobalClass.tmp_email);
		//Click logout
		driver.findElement(By.xpath("//header[@id='header']//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//ul/li[last()]")).click();
		//verify home page
		String homepagemsg = "Default welcome msg!";
		homepagemsg = homepagemsg.toUpperCase();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='welcome-msg']")).getText(), homepagemsg);
	}
	

	@Test
	public void TC_07_Login_validate_with_emal() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(GlobalClass.tmp_email);
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Verify page after login
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), ("My Dashboard").toUpperCase());
		// verify name and email
		String text = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().replace("\nChange Password", "");
		Assert.assertEquals(text, GlobalClass.firstname + " " + GlobalClass.lastname + "\n" + GlobalClass.tmp_email);

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
