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
	public void TC_01_verify_Element_isDisplay() {
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
		LogConsole_IsDisplay("Name user5",user5.isDisplayed());
		
		if (Emailtextbox.isDisplayed()) {
			Emailtextbox.sendKeys("Automation Testing");
			LogConsole_IsDisplay("Emailtextbox" , Emailtextbox.isDisplayed());
		}
		
		if (Under18checkbox.isDisplayed()) {
			Under18checkbox.click();
			LogConsole_IsDisplay("Under18checkbox" ,Under18checkbox.isDisplayed());
		}
		
		if (Educationtxt.isDisplayed()) {
			Educationtxt.sendKeys("Automation Testing");
			LogConsole_IsDisplay("Education textarea",Educationtxt.isDisplayed());
		}

	}
	
	@Test
	public void TC_02_verify_Element_IsEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//verify Email is Enabled
		WebElement Emailtextbox = driver.findElement(By.id("mail"));
		Assert.assertTrue(Emailtextbox.isEnabled());
		LogConsole_IsEnable("Emailtextbox",Emailtextbox.isEnabled());
		
		//verify under_18 is Enabled
		WebElement Under18checkbox = driver.findElement(By.id("under_18"));
		Assert.assertTrue(Under18checkbox.isEnabled());
		LogConsole_IsEnable("Under18checkbox",Under18checkbox.isEnabled());
		
		//verify Education is Enabled
		WebElement Educationtxt = driver.findElement(By.id("edu"));
		Assert.assertTrue(Educationtxt.isEnabled());
		LogConsole_IsEnable("Educationtxt",Educationtxt.isEnabled());
		
		//verify Job1 is Enabled
		WebElement job1 = driver.findElement(By.id("job1"));
		Assert.assertTrue(job1.isEnabled());
		LogConsole_IsEnable("job1",job1.isEnabled());

		//verify Job2 is Enabled
		WebElement job2 = driver.findElement(By.id("job2"));
		Assert.assertTrue(job2.isEnabled());
		LogConsole_IsEnable("job2",job2.isEnabled());
		
		//verify interest_development is Enabled
		WebElement interest_development = driver.findElement(By.id("development"));
		Assert.assertTrue(interest_development.isEnabled());
		LogConsole_IsEnable("interest_development",interest_development.isEnabled());
		
		//verify slider_1 is Enabled
		WebElement slider_1 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		Assert.assertTrue(slider_1.isEnabled());
		LogConsole_IsEnable("slider_1",slider_1.isEnabled());
		
		//verify password is Disable
		WebElement password = driver.findElement(By.xpath("//legend[text()='Your basic info']/parent::div/input[@id='password']"));
		Assert.assertFalse(password.isEnabled());
		LogConsole_IsEnable("password",password.isEnabled());

		//verify AgeRadio is Disable
		WebElement AgeRadio = driver.findElement(By.xpath("//label[text()='Radio button is disabled']//preceding-sibling::input[@id='radio-disabled']"));
		Assert.assertFalse(AgeRadio.isEnabled());
		LogConsole_IsEnable("AgeRadio",AgeRadio.isEnabled());

		
		//verify textarea_bio is Disable
		WebElement textarea_bio = driver.findElement(By.xpath("//textarea[@id='bio']"));
		Assert.assertFalse(textarea_bio.isEnabled());
		LogConsole_IsEnable("textarea_bio",textarea_bio.isEnabled());
		
		//verify job3 is Disable
		WebElement job3 = driver.findElement(By.id("job3"));
		Assert.assertFalse(job3.isEnabled());
		LogConsole_IsEnable("job3",job3.isEnabled());
		
		//verify Checkbox is Disable
		WebElement Checkbox = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		Assert.assertFalse(Checkbox.isEnabled());
		LogConsole_IsEnable("Checkbox",Checkbox.isEnabled());
		
		//verify slider_2 is Disable
		WebElement slider_2 = driver.findElement(By.id("slider-2"));
		Assert.assertFalse(slider_2.isEnabled());
		LogConsole_IsEnable("slider_2",slider_2.isEnabled());
	}
	
	public void LogConsole_IsDisplay(String string ,boolean Boolean){
		if (Boolean) {
			System.out.println(string + ": " + "Element is display");
		}
		else
			System.out.println(string + ": " + "Element isnot display");
	}
	
	public void LogConsole_IsEnable(String string ,boolean Boolean){
		if (Boolean) {
			System.out.println(string + ": " + "Element is Enable");
		}
		else
			System.out.println(string + ": " + "Element isnot Enable");
	}
	
	public void LogConsole_IsSelected(String string ,boolean Boolean){
		if (Boolean) {
			System.out.println(string + ": " + "Element is selected");
		}
		else
			System.out.println(string + ": " + "Element isnot selected");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
