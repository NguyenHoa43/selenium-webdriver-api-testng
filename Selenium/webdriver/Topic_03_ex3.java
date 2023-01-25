package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_ex3 {
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
    public void TC_01_() throws InterruptedException {
    	driver.get("http://live.techpanda.org/index.php/customer/account/login/");
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hoanguyen09@gmail.com");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("hoa123");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
    	driver.findElement(By.xpath("//li[@class='error-msg']//span"));
    }
    @Test
    public void TC_02_() {
    	driver.get("http://live.techpanda.org/index.php/customer/account/login/");
    	driver.findElement(By.xpath("//a[@class='button']")).click();
    }
    
    @Test
    public void TC_03_() throws InterruptedException {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//input[@id='search']")).sendKeys("anh dep trai");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//button[@class='button search-button']")).click();
    }
    
    @Test
    public void TC_04_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
    }
    
    @Test
    public void TC_05_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='Orders and Returns']")).click();
    }
    
    @Test
    public void TC_06_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='Site Map']")).click();
    }
    
    @Test
    public void TC_07_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='Search Terms']")).click();
    }
    
    @Test
    public void TC_08_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='Advanced Search']")).click();
    }
    
    @Test
    public void TC_09_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='About Us']")).click();
    }
    
    @Test
    public void TC_10_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='Contact Us']")).click();
    	
    }
    
    @Test
    public void TC_11_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='Customer Service']")).click();
    }
    
    @Test
    public void TC_12_() {
    	driver.get("http://live.techpanda.org/index.php/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='Privacy Policy']")).click();
    }
	//@AfterClass
	//public void afterClass() {
	//	driver.quit();
	}
