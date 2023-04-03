package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Action_part_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleep(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover_ThemeRoller() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	
	action.moveToElement(driver.findElement(By.xpath("//a[text()='ThemeRoller']"))).perform();
	sleep(3);
	
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "ThemeRoller: jQuery UI's theme builder application");
	}

	@Test
	public void TC_03_Hover_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Tooltips']"))).perform();
		sleep(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "That's what this widget is");
	}
	
	@Test
	public void TC_04_Hover_Web_Fahasa() {
		driver.get("https://www.fahasa.com/");
		sleep(10);
		
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleep(3);
		
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
		sleep(3);
		
		driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Nhân Vật - Bài Học Kinh Doanh']")).click();
		sleep(3);
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ves-breadcrumbs']//strong[text()='Nhân vật - Bài Học Kinh doanh']")).isDisplayed());
	}
	
	public void sleep (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}