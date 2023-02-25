package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	Random rand;
	Actions action;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, passWords, fullName;
	String employeeID, passpostNumber, commentInput, companyName, dayBirth, mounthBirth, yearBirth;
	String countryName, provinceName, addressName, cityName, postalCode, phoneNumber;


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
		
		
		emailAddress = "elonmsut" + rand.nextInt(9999) + "@gmail.com";
		firstName = "elon";
		lastName = "must";
		fullName = firstName + " " + lastName;
		passWords = "Hoa123!@#";
		employeeID = String.valueOf(rand.nextInt(999));
		passpostNumber = "40517-402-967202";
		commentInput = "Ván ô tim là loại ván công nghiệp được ứng dụng";
		companyName = "Love Company";
		dayBirth = "5";
		mounthBirth = "January";
		yearBirth = "1997";
		countryName = "United States";
		provinceName = "Arizona";
		addressName = "7350 W (West) CAMINO SAN XAVIER, GLENDALE, AZ 85308-0350";
		postalCode = "85308";
		phoneNumber = "(623) 561-1000";
		cityName = "Glendale";
		
		
		
	}

	@Test
	public void TC_01_Register_New_Account() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		sleepInSecond(4);
		
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		// gọi hàm select ở trong test case để thực thi thẻ có Dropdown list
		new Select(driver.findElement(By.xpath("//label[text()='Date of birth:']/parent::div//select[@name='DateOfBirthDay']"))).selectByVisibleText(dayBirth);
		new Select(driver.findElement(By.xpath("//label[text()='Date of birth:']/parent::div//select[@name='DateOfBirthMonth']"))).selectByVisibleText(mounthBirth);
		new Select(driver.findElement(By.xpath("//label[text()='Date of birth:']/parent::div//select[@name='DateOfBirthYear']"))).selectByVisibleText(yearBirth);
		
		Assert.assertFalse(new Select(driver.findElement(By.xpath("//label[text()='Date of birth:']/parent::div//select[@name='DateOfBirthDay']"))).isMultiple());
		Assert.assertFalse(new Select(driver.findElement(By.xpath("//label[text()='Date of birth:']/parent::div//select[@name='DateOfBirthMonth']"))).isMultiple());
		Assert.assertFalse(new Select(driver.findElement(By.xpath("//label[text()='Date of birth:']/parent::div//select[@name='DateOfBirthYear']"))).isMultiple());
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(passWords);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(passWords);
		
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(4);
		//verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		sleepInSecond(4);
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(passWords);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		sleepInSecond(4);
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		sleepInSecond(4);
		//verify
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),dayBirth);
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(),mounthBirth);
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(),yearBirth);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);
	}
	
	@Test
	public void TC_02_Add_Address() {
		driver.findElement(By.xpath("//div[@class='listbox']//a[text()='Addresses']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector(".add-address-button")).click();
		sleepInSecond(3);
		
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Address_Company")).sendKeys(companyName);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);
		new Select(driver.findElement(By.xpath("//label[text()='State / province:']/following::select"))).selectByVisibleText(provinceName);
		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(),firstName + " " + lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.email")).getText(),"Email:" + " " + emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.phone")).getText(),"Phone number:" + " " + phoneNumber);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(),companyName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(),addressName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(),countryName);
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