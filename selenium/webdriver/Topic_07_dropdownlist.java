package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_dropdownlist {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");
	String firstname, lastname, email , password, confirmpassword;
	Select select;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Empty_Email_And_Password() {
		driver.get("https://demo.nopcommerce.com/register");
		By Registermenu = By.className("ico-register");
		By FirstNameTexbox = By.id("FirstName");
		By LastNameTexbox = By.id("LastName");
		By MailTexbox = By.id("Email");
		By MaleGendercheckbox = By.id("gender-male");
		By PasswordTextbox = By.id("Password");
		By PasswordConfirmTextbox = By.id("ConfirmPassword");
		By RegisterTexbox = By.id("register-button");
		

		

		firstname = "testing";
		lastname ="automation";
		email = "automationtesting"+randomnumber()+"@gmail.com";
		password = "123456";
		
		driver.findElement(Registermenu).click();
		
		Select DayDropDown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Select MonthDropDown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Select YearDropDown = new Select(driver.findElement(By.name("DateOfBirthYear")));
		
		
		
		Assert.assertEquals(DayDropDown.getOptions().size(), 32);
		Assert.assertEquals(MonthDropDown.getOptions().size(), 13);
		Assert.assertEquals(YearDropDown.getOptions().size(), 112);
		
		driver.findElement(MaleGendercheckbox).click();
		driver.findElement(FirstNameTexbox).sendKeys(firstname);
		driver.findElement(LastNameTexbox).sendKeys(lastname);
		driver.findElement(MailTexbox).sendKeys(email);
		driver.findElement(PasswordTextbox).sendKeys(password);
		driver.findElement(PasswordConfirmTextbox).sendKeys(password);
		
		DayDropDown.selectByVisibleText("1");
		MonthDropDown.selectByVisibleText("May");
		YearDropDown.selectByVisibleText("1980");
		
		driver.findElement(RegisterTexbox).click();
		Sleeper.sleepTightInSeconds(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

		driver.findElement(By.xpath("//div[@class='header-links']//a[text()='My account']")).click();
		
		Sleeper.sleepTightInSeconds(3);
		
		DayDropDown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		MonthDropDown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		YearDropDown = new Select(driver.findElement(By.name("DateOfBirthYear")));
	
		Assert.assertEquals(DayDropDown.getFirstSelectedOption().getText(), "1");
		Assert.assertEquals(MonthDropDown.getFirstSelectedOption().getText(), "May");
		Assert.assertEquals(YearDropDown.getFirstSelectedOption().getText(), "1980");
		
		
		
	}
	
	public void inputValue (By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	
	public int randomnumber() {
		Random random = new Random();
		return random.nextInt();
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}