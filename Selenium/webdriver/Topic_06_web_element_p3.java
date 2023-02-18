package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_web_element_p3 {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, passWords, fullName;



	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		rand = new Random();

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
		firstName = "automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		passWords = "12345678";
		
	}

	@Test
	public void TC_01_Login_empty_email_pass() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
		
	}
	
	@Test
	public void TC_02_login_invalid_email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12343423@12312.123123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
		
	}
	
	@Test
	public void TC_03_login_passwods_under_6_charracter() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nguyenhoa12@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
		
	}
	
	@Test
	public void TC_04_Incorrect_email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']")).getText(), "Invalid login or password.");
		
		
	}
	@Test
	public void TC_05_Create_New_Accout() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(passWords);
		driver.findElement(By.id("confirmation")).sendKeys(passWords);
		
		driver.findElement(By.cssSelector("input[id='is_subscribed']")).click();
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
		
		String contactInfomationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInfomationText);
		
		Assert.assertTrue(contactInfomationText.contains(fullName));
		Assert.assertTrue(contactInfomationText.contains(emailAddress));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	
	@Test
	public void TC_06_Login_Valid_Info() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(passWords);
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		
		String contactInfomationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInfomationText);
		
		Assert.assertTrue(contactInfomationText.contains(fullName));
		Assert.assertTrue(contactInfomationText.contains(emailAddress));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	
	
	public void sleepInSecond(long timeInSecond) {
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