package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_web_element_p1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#password");
	By biographyTextarea = By.cssSelector("#bio");
	By jobRole3 = By.cssSelector("#job3");
	By checkBox = By.cssSelector("#check-disbaled");
	By developmentCheckbox = By.cssSelector("#development");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Selenium Webdriver");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("email textbox is not displayed");
		}
		
		if(driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium Webdriver");
			System.out.println("Education textarea is displayed");
		}else {
			System.out.println("Education textarea is not displayed");
		}
		
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age under 18 is displayed");
		}else {
			System.out.println("Age under 18 is not displayed");
		}
		
		if(driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Name user 5 is displayed");
		}else {
			System.out.println("Name user 5 is not displayed");
		}
	}
	
	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password text box is enable");
		}else {
			System.out.println("Password text box is disable");
		}
		
		if(driver.findElement(emailTextbox).isEnabled()) {
			driver.findElement(emailTextbox).sendKeys("Hoa handsome");
			System.out.println("Email textbox is enable");
		}else {
			System.out.println("Email textbox is disable");
		}
		
		if(driver.findElement(biographyTextarea).isEnabled()) {
			System.out.println("Biography is enable");
		}else {
			System.out.println("Biography is disable");
		}
		
		if(driver.findElement(jobRole3).isEnabled()) {
			System.out.println("Job role 3 is enable");
		}else {
			System.out.println("Job role 3 is disable");
		}
		
		if(driver.findElement(checkBox).isEnabled()) {
			System.out.println("Check Box is enable");
		}else {
			System.out.println("Check Box is disable");
		}
	}
	
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
		
	}
	
	@Test
	public void TC_04_Mailchimp() {
	driver.get("https://login.mailchimp.com/signup/");
	
	driver.findElement(By.id("email")).sendKeys("johwich@gmail.com");
	
	By passwordTextbox = By.id("new_password");
	
	
	driver.findElement(passwordTextbox).sendKeys("abc");
	sleepInSecond(3);
	
	// xac nhan lower case
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("ABC");
	sleepInSecond(3);
	
	// xac nhan upper case
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("123");
	sleepInSecond(3);
	
	// xac nhan number case
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("!@#");
	sleepInSecond(3);
	
	// xac nhan number case
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("ABCXYZGHM");
	sleepInSecond(3);
	
	// xac nhan number case
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("123abcABC!@#");
	sleepInSecond(3);
	
	// xac nhan number case
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
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