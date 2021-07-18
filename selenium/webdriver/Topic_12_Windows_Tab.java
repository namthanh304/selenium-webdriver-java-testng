package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;


public class Topic_12_Windows_Tab {
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
	public void TC_09_Windows() {
		driver.get("http://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		String Parentid= driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//ul//a[text()='Add to Compare']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Sony Xperia has been added to comparison list.");
	
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//ul//a[text()='Add to Compare']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//div[@class='block block-list block-compare']//span[text()='Compare']")).click();
		
		Sleeper.sleepTightInSeconds(1);
		
		SwitchToWindowsByTitle("Products Comparison List - Magento Commerce");
		
		CloseAllWindowsExeceptParent(Parentid);
		
		driver.findElement(By.xpath("//div[@class='block block-list block-compare']//a[text()='Clear All']")).click();
		alert = explicitiwait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The comparison list was cleared.");

	}

	
	public void SwitchToWindowsByTitle(String title) {
		 Set<String> allwindows = driver.getWindowHandles();
		 
		 for (String id : allwindows) {
			driver.switchTo().window(id);
			String Currenttitle = driver.getTitle();
			if (Currenttitle.equals(title)) {
				break;
			}
		}
		
	}
	
	public void CloseAllWindowsExeceptParent(String parent) {
		Set<String> allwindows = driver.getWindowHandles();
		for (String Currenttitle : allwindows) {
			if (!Currenttitle.equals(parent)) {
				driver.switchTo().window(Currenttitle);
				driver.close();
			}
			driver.switchTo().window(parent);
		}
		
	}

	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}