package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String authenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";
	String authenChrome = projectPath + "\\autoIT\\authen_chrome.exe";
	String username, password;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		username = "admin";
		password = "admin";
		
	}

	@Test
	public void TC_01_accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleep(6);
		// switch qua :/ Alert / Iframe (frame) / windows
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		// accept alert là click vào button ok của alert
		//alert.accept();
		//cancel alert 
		//
		//gettext ra verify title cua alert
		//alert.getText();
		// verify title alert
		
		// nhap lieu vao alert
		// accept alert
		
		
		
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleep(6);
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		//cancel popup alert
		alert.dismiss();
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
		
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleep(6);
		//switch qua alert
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		//verify alert dung nhu mong doi
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		// define biến name để sử dụng cho các hàm sau 
		String courseName = "Fullstack Selenium Java";
		// điền thông tin vào một alert prompt
		alert.sendKeys(courseName);
		
		// click vào button ok có trên alert
		alert.accept();
		//verify kết quả trả về khi đã thực hiện các step xong
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courseName);
		
		
	}

	@Test
	public void TC_04_Authentication_Alert() {
		// trick su dung tai khoan dang nhap vao chinh duong link
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(), "Congratulations! You must have the proper credentials.");
	}
	
	@Test
	public void TC_05_Authetication_Alert() {
		// sử dụng hàm khai báo passUser để thực hiện việc verify alert
		driver.get(passUser("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(), "Congratulations! You must have the proper credentials.");
	}
	
	@Test
	public void TC_06_Authetication_Alert_autoIT() throws IOException {
		if(driver.toString().contains("firefox")) {
			// thực thi 1 file exe trong code java
			Runtime.getRuntime().exec(new String[] {
					authenFirefox,username,password
			});
		}else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {
					authenChrome, username, password
			});
		}
		
		
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		sleep(4);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(), "Congratulations! You must have the proper credentials.");
	}
	
	public String passUser (String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
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