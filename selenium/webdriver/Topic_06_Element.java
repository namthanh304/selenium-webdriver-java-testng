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

public class Topic_06_Element {
	WebDriver driver;
	WebElement element;
	String projectpath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_verify_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//verify Email is display
		WebElement Emailtextbox = driver.findElement(By.id("mail"));
		Assert.assertTrue(Emailtextbox.isDisplayed());
		//verify under_18 is display
		WebElement Under18checkbox = driver.findElement(By.id("under_18"));
		Assert.assertTrue(Under18checkbox.isDisplayed());
		//verify Education is display
		WebElement Educationtxt = driver.findElement(By.id("edu"));
		Assert.assertTrue(Educationtxt.isDisplayed());
		//verify user5 is not display
		WebElement user5 = driver.findElement(By.xpath("//*[text()='Name: User5']"));
		Assert.assertFalse(user5.isDisplayed());
		LogConsole("Name user5",user5.isDisplayed());
		
		if (Emailtextbox.isDisplayed()) {
			Emailtextbox.sendKeys("Automation Testing");
			LogConsole("Emailtextbox" , Emailtextbox.isDisplayed());
		}
		
		if (Under18checkbox.isDisplayed()) {
			Under18checkbox.click();
			LogConsole("Under18checkbox" ,Under18checkbox.isDisplayed());
		}
		
		if (Educationtxt.isDisplayed()) {
			Educationtxt.sendKeys("Automation Testing");
			LogConsole("Education textarea",Educationtxt.isDisplayed());
		}

	}
	
	
	public void LogConsole(String string ,boolean Boolean){
		if (Boolean) {
			System.out.println(string + ": " + "Element is display");
		}
		else
			System.out.println(string + ": " + "Element isnot display");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
