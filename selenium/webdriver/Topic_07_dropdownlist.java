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

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import jdk.nashorn.internal.runtime.ListAdapter;


public class Topic_07_dropdownlist {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");
	String firstname, lastname, email;
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
		

		

		firstname = "testing";
		lastname ="automation";
		email = "automationtesting@gmail.com";
		
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
		
		DayDropDown.selectByVisibleText("1");
		MonthDropDown.selectByVisibleText("May");
		YearDropDown.selectByVisibleText("1980");

	}
	
	public void inputValue (By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}