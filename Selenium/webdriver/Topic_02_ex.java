package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_ex {
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
	public void TC_01_ValidateCurrentUrl() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		// Click vao My account link ở duoi Footer
		driver.findElement(By.xpath("//input[@id='email']")).click();
				
		// cơ chế của Slenium luôn thao tác với element đầu tiên

	}
	@Test
	public void TC_02_() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}

    @Test
    public void TC_03_() throws InterruptedException {
        System.out.println("8");
    	driver.get("http://live.techpanda.org/index.php/customer/account/login/");
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hoanguyen09@gmail.com");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//input[@id='email']")).clear();
    	//driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("hoa123");
    	//driver.findElement(By.xpath("//li[@class='error-msg']//span"));
    }
    @Test
    public void TC_04_() throws InterruptedException {
    	driver.get("http://live.techpanda.org/index.php/customer/account/login/");
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hoanguyen09@gmail.com");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("hoa123");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
    	driver.findElement(By.xpath("//li[@class='error-msg']//span"));
    }
	//@AfterClass
	//public void afterClass() {
	//	driver.quit();
	}
