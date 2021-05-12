package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.remote.server.handler.GetPageSource;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Click;

public class Topic_07_Textbox_textarea {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");
	String newEmail = "testing" + randomnumber() + "@gmail.com";
	String CustomerID, Customermail, name ,gender, dateofbirth , addr, city,state,pin,mobile;
	String username , password, loginURL;	
	
	By usernameTextBox = By.name("uid");
	By PasswordTextBox = By.name("password");
	By loginbutton = By.name("btnLogin");
	By emailTextBox =  By.name("emailid");
	
	// Declare add new
	
	By Nametextbox = By.name("name");
	By genderradio = By.xpath("//input[@name='rad1' and @value='m']");
	By dobtextbox = By.id("dob");
	By Addresstextbox = By.name("addr");
	By Citytextbox = By.name("city");
	By Statetextbox = By.name("state");
	By PINtextbox = By.name("pinno");
	By MobileNumbertextbox = By.name("telephoneno");
	By emailtextbox = By.name("emailid");
	By passtextbox = By.name("password");
	By submitbutton = By.name("sub");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.guru99.com/v4");
		
		Customermail = "Customer" + randomnumber() + "@gmail.com";
		name = "John C";
		dateofbirth = "1956-01-01";
		addr = "12 NY";
		city = "Hawaii";
		state = "NY";
		pin = "000000";
		mobile = "1234567890";
		gender = "male";
	}

	@Test
	public void TC_01_register() {
		// Create account for demosite
		loginURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(emailTextBox).clear();
		driver.findElement(emailTextBox).sendKeys(newEmail);
		driver.findElement(loginbutton).click();
		
		username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		System.out.println(username);
		System.out.println(password);
	}
	
	
	@Test
	public void TC_02_login() {
		// Login 
		driver.get(loginURL);
		
		driver.findElement(usernameTextBox).clear();
		driver.findElement(usernameTextBox).sendKeys(username);
		driver.findElement(PasswordTextBox).clear();
		driver.findElement(PasswordTextBox).sendKeys(password);
		
		driver.findElement(loginbutton).click();
		
		String loginsucess =  "Welcome To Manager's Page of Guru99 Bank";
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), loginsucess);
	}
	
	@Test
	public void TC_03_newcustomer() {
		// Login 
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(Nametextbox).sendKeys(name);
		driver.findElement(genderradio).click();
		driver.findElement(dobtextbox).sendKeys(dateofbirth);
		driver.findElement(Addresstextbox).sendKeys(addr);
		driver.findElement(Citytextbox).sendKeys(city);
		driver.findElement(Statetextbox).sendKeys(state);
		driver.findElement(PINtextbox).sendKeys(pin);
		driver.findElement(MobileNumbertextbox).sendKeys(mobile);
		driver.findElement(emailtextbox).sendKeys(Customermail);
		driver.findElement(passtextbox).sendKeys("123456");
		
		driver.findElement(submitbutton).click();
		
		
		Sleeper.sleepTightInSeconds(20);
		
		//verify info
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")), dateofbirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")), addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")), Customermail);
		
	}
	
	@Test
	public void TC_04_editcustomer() {
		// Login 
		driver.findElement(By.xpath("//a[text()='New Customer']"));
	}

	
	public void Inputvalue (By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	
	public void ClickElement(By by) {
		driver.findElement(by).click();
	}
	
	public int randomnumber() {
		Random rand = new Random();
		return rand.nextInt();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
