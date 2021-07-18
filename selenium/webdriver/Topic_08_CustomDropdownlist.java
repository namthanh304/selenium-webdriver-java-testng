package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.remote.server.handler.GetPageSource;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Click;

public class Topic_08_CustomDropdownlist {
	WebDriver driver;
	String projectpath = System.getProperty("user.dir");
	WebDriverWait explicitiwait;
	JavascriptExecutor jsexcutor;
	String[] threemonth = {"May","July","April"};
	
	String[] Overthreemonth = {"May","July","April","June","October"};
	
	String [] twelvemothns = {"January","February","March","April","May","June","July","August","September","October","November","December"};



	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectpath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		explicitiwait = new WebDriverWait(driver, 15);
		
		jsexcutor = (JavascriptExecutor) driver;
		
		String[] threemonth = {"May","July","April"};
		
		String[] Overthreemonth = {"May","July","April","June","October"};
		

	}
	
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		SelectItem("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']//div", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		SelectItem("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']//div", "9");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='9']")).isDisplayed());
		
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		SelectItem("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']//div", "8");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='8']")).isDisplayed());
	}
	
	
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		SelectItem("//div[@role='listbox']//i[@class='dropdown icon']", "//div[@role='listbox']//div[@role='option']/span", "Matt");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']//div[@class='divider text' and text()='Matt']")).isDisplayed());
		
		SelectItem("//div[@role='listbox']//i[@class='dropdown icon']", "//div[@role='listbox']//div[@role='option']/span", "Justen Kitsune");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']//div[@class='divider text' and text()='Justen Kitsune']")).isDisplayed());
		
		SelectItem("//div[@role='listbox']//i[@class='dropdown icon']", "//div[@role='listbox']//div[@role='option']/span", "Stevie Feliciano");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']//div[@class='divider text' and text()='Stevie Feliciano']")).isDisplayed());
	}
	
	public void TC_03_Vuejs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		SelectItem("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		SelectItem("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "First Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		SelectItem("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
	}
	
	@Test
	public void TC_04_multipleSelect() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		selectmultipleitem("(//div[@class='ms-parent multiple-select'])[1]/button", "(//div[@class='ms-parent multiple-select'])[1]//ul/li", threemonth);
		verifyItemSelected(threemonth);
		
		driver.navigate().refresh();
		
		selectmultipleitem("(//div[@class='ms-parent multiple-select'])[1]/button", "(//div[@class='ms-parent multiple-select'])[1]//ul/li", Overthreemonth);
		verifyItemSelected(Overthreemonth);
		
		driver.navigate().refresh();
		
		selectmultipleitem("(//div[@class='ms-parent multiple-select'])[1]/button", "(//div[@class='ms-parent multiple-select'])[1]//ul/li", twelvemothns);
		verifyItemSelected(twelvemothns);
	}
	
	
	public void SelectItem(String Dropdownicon, String menulist, String expectedItem) {
		//Click to Explan dropdownlist and wait
		driver.findElement(By.xpath(Dropdownicon)).click();
		Sleeper.sleepTightInSeconds(1);
		// wait all item visible and set on list
		List<WebElement> allitem = explicitiwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(menulist)));
		
		for (WebElement item : allitem) {
			if (item.getText().trim().equals(expectedItem)) {
				if (!item.isDisplayed()) {
					jsexcutor.executeScript("arguments[0].scrollIntoView(true);", item);
				}
				item.click();
				break;
			}
		}		
	}
	
	public void selectmultipleitem(String dropdownlocator, String optionlist, String[] expecteditem){
		//click open option
		driver.findElement(By.xpath(dropdownlocator)).click();
		
		//wait all item display and get all item
		List<WebElement> allitem = explicitiwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(optionlist)));
		
		// loop for all item
		for (WebElement item : allitem) {
			for (String subitem : expecteditem) {
				if (subitem.equals(item.getText())) {
					jsexcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					Sleeper.sleepTightInSeconds(1);
					
					item.click();
					Sleeper.sleepTightInSeconds(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected : "+ itemSelected.size());
					if (itemSelected.size() == expecteditem.length) {
						break;
					}
				}
			}
		}
	
	}
	
	public boolean verifyItemSelected (String[] month){
		List<WebElement> ItemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItem = ItemSelected.size();
		
		String allItemSelected = driver.findElement(By.xpath("(//div[@class='ms-parent multiple-select'])[1]/button/span")).getText();
		System.out.println("Total Month are selected : "+ allItemSelected );
		
		if (numberItem <=3 && numberItem >0 ) {
			boolean status = true;
			for (String item : month) {
				if (allItemSelected.contains(item)) {
					status = true;
				}
				else {
						status = false;
					}
				}
			return status;
		}
		else if (numberItem > 3 && numberItem < 12) {
			return driver.findElement(By.xpath("(//div[@class='ms-parent multiple-select'])[1]/button/span[text()='"+ numberItem + " of 12 selected']")).isDisplayed();
		} else if (numberItem == 12) {
			return driver.findElement(By.xpath("(//div[@class='ms-parent multiple-select'])[1]/button/span[text()='All selected']")).isDisplayed();
		}
	else {
		return false;
	}
}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
