package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06 {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Verify_Url() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='My Account']/parent::li//ancestor::div[@class='footer-container']//following-sibling::li[@class='first']/child::a[@title='My Account']")).click();
        driver.getCurrentUrl();
        driver.findElement(By.xpath("//a[@class='button']")).click();
        driver.getCurrentUrl();
	}
	
	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='My Account']/parent::li//ancestor::div[@class='footer-container']//following-sibling::li[@class='first']/child::a[@title='My Account']")).click();
		driver.getTitle();
		driver.findElement(By.xpath("//a[@class='button']/child::span")).click();
		driver.getTitle();
	}
	
	@Test
	public void TC_03_Navigate_function() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='My Account']/parent::li//ancestor::div[@class='footer-container']//following-sibling::li[@class='first']/child::a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@class='button']/child::span")).click();
		driver.navigate().back();
		driver.getCurrentUrl();
		driver.navigate().forward();
		driver.getTitle();	
	}
	
	@Test
	public void TC_04_Get_page_source_code() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='My Account']/parent::li//ancestor::div[@class='footer-container']//following-sibling::li[@class='first']/child::a[@title='My Account']")).click();
		System.out.println(driver.findElement(By.xpath("//h1[contains(text(),'Login or Create an Account ')]")).getText());
		driver.findElement(By.xpath("//a[@class='button']/child::span")).click();
		System.out.println(driver.findElement(By.xpath("//h1[text()='Create an Account']")).getText());
	}
	//@AfterClass
	//public void afterClass() {
	//	driver.quit();
}
