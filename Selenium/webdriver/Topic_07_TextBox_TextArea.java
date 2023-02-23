package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox_TextArea {
	WebDriver driver;
	Random rand;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, passWords, fullName;
	String employeeID, passpostNumber, commentInput;
	


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		rand = new Random();
		
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
		firstName = "automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		passWords = "12345678";
		employeeID = String.valueOf(rand.nextInt(999));
		passpostNumber = "40517-402-967202";
		commentInput = "Ván ô tim là loại ván công nghiệp được ứng dụng";
		
	}

	@Test
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(4);
		
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		sleepInSecond(4);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		sleepInSecond(4);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeID);
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(4);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(7);
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div/child::button/i")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passpostNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(commentInput);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),passpostNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),commentInput);
		
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
	}
	
	@Test
	public void TC_02_Verify_Employee() {
		
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