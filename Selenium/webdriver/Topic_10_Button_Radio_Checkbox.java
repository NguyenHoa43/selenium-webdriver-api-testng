package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand;
	String id, passWords;

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
		id = "tomcruin12@gmail.com";
		passWords = "9876541123";
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		// define biến để sử dụng nhiều lần 
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		// verify login button is disabled
		
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println(loginButtonBackground);
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys(id);
		driver.findElement(By.cssSelector("input#login_password")).sendKeys(passWords);
		sleep(4);
		// verify login button enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println(loginButtonBackground);
		
		Color loginButtonBackgroundColour = Color.fromString(loginButtonBackground);
		Assert.assertEquals(loginButtonBackgroundColour.asRgb(), "rgb(201, 33, 39)");
		
		
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		
	}
	
	public void sleep (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}