package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_ex4_5 {
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		driver.findElement(By.xpath("//label[@id='txtFirstname-error']"));
		driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
		driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
		driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
		driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
		driver.findElement(By.xpath("//label[@id='txtPhone-error']"));

	}
	
	@Test
	public void TC_02() throws InterruptedException {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("nguyen");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("123@345@123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@345@123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0961234578");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
		driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
	}

	@Test
	public void TC_03() throws InterruptedException{
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Hoa");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("jaxknam@gmail.net");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0961234578");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
	}
	
	@Test
	public void TC_04() throws InterruptedException{
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Hoa");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0961234578");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
		driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
	}
	
	@Test
	public void TC_05() throws InterruptedException{
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Hoa");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("huy123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0961234578");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
	}
	
	@Test
	public void TC_06() throws InterruptedException{
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Hoa");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("096123457");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
		
	}
	
	@Test
	public void TC_06_1() throws InterruptedException{
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Hoa");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("jaxknam@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("hoa123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("961234578");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
		
	}
	//@AfterClass
	//public void afterClass() {
	//	driver.quit();
}
