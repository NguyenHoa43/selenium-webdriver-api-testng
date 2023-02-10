package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_CSS {
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
	public void TC_01_sub_child() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account'] ")).click();
	}

	@Test
	public void TC_02_direct_child() {
		driver.get("http://live.techpanda.org/");
		System.out.println(driver.findElement(By.cssSelector("div>h2")).getText());
	}
	
	@Test
	public void TC_03_ID() throws InterruptedException {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("input[id='newsletter']")).sendKeys("hello");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_03_1_ID() throws InterruptedException {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("input#search")).sendKeys("hello");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_03_2_ID() throws InterruptedException {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("#search")).sendKeys("hello");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_04_class() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("button[class='button search-button']")).click();
	}
	
	@Test
	public void TC_04_1_class() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("button.button.search-button")).click();
	}
	
	@Test
	public void TC_04_2_class() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector(".search-button")).click();
	}
	
	@Test
	public void TC_04_3_class() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector(".button.search-button")).click();
	}
	// @AfterClass
	// public void afterClass() {
	// driver.quit();
}
