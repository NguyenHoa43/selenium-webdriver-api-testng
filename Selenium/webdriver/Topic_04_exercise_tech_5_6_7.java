package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_exercise_tech_5_6_7 {
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
	public void TC_01() {
		driver.get("https://automationfc.github.io/basic-form/");
		System.out.println(driver.findElement(By.xpath("//h5[@id='nested']")).getText());

	}
	
	@Test
	public void TC_02_text() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@id='advice-required-entry-email' and text()='This is a required field.']")).getText());
	}

	@Test
	public void TC_03_contains() {
		driver.get("https://automationfc.github.io/basic-form/");
		System.out.println(driver.findElement(By.xpath("//h5[contains(text(),'Michael Jackson')]")).getText());
		System.out.println(driver.findElement(By.xpath("//h5[contains(text(),'Michael Jackson') and @id='one']")).getText());
	}
	public void TC_03_1_contains() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		System.out.println(driver.findElement(By.xpath("//div[contains(text(),'This is root of mobile')]")).getText());
	}
	
	@Test
	public void TC_04_contains() {
		driver.get("https://automationfc.github.io/basic-form/");
		System.out.println(driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson')]")).getText());
		System.out.println(driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson') and @id='one']")).getText());
	}
	
	@Test
	public void TC_05_and() {
		driver.get("https://automationfc.github.io/basic-form/");
		System.out.println(driver.findElement(By.xpath("//h5[@id='one' and contains(.,'Michael Jackson')]")).getText());
	}
	
	@Test
	public void TC_06_concat() {
		driver.get("https://automationfc.github.io/basic-form/");
		System.out.println(driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]")).getText());
		System.out.println(driver.findElement(By.xpath("//p[text()=concat('Mail Personal or Business Check, Cashier',\"'s Check or money order to:\")]")).getText());
	}
	
	
	@Test
	public void TC_07_inside_parent_position() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		driver.findElement(By.xpath("//li[position()=1]")).click();
		driver.findElement(By.xpath("//li[position()=2]")).click();
		driver.findElement(By.xpath("//li[position()=3]")).click();
		driver.findElement(By.xpath("//li[position()=4]")).click();
		driver.findElement(By.xpath("//li[position()=5]")).click();
		driver.findElement(By.xpath("//li[position()=6]")).click();
		driver.findElement(By.xpath("//li[position()=7]")).click();
		driver.findElement(By.xpath("//li[position()=8]")).click();
		driver.findElement(By.xpath("//li[position()=9]")).click();
		
	}
	
	@Test
	public void TC_08_outside_parent() {
		driver.get("https://www.thegioididong.com/laptop-ldp");
		driver.findElement(By.xpath("(//a[@class='box-quicklink__item bd-radius quicklink-logo'])[1]"));
		driver.findElement(By.xpath("(//a[@class='box-quicklink__item bd-radius quicklink-logo'])[2]"));
		driver.findElement(By.xpath("(//a[@class='box-quicklink__item bd-radius quicklink-logo'])[3]"));
		driver.findElement(By.xpath("(//a[@class='box-quicklink__item bd-radius quicklink-logo'])[4]"));
		driver.findElement(By.xpath("(//a[@class='box-quicklink__item bd-radius quicklink-logo'])[5]"));
	}
	//@AfterClass
	//public void afterClass() {
	//	driver.quit();
}
