package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Upload_File2_Edgedriver {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String hinhOne = "1.jpg";
	String hinhTwo = "2.jpg";
	String hinhThree = "7.jpg";
	
	String hinhOnePath = projectPath + File.separator + "UploadFiles" + File.separator + hinhOne;
	String hinhTwoPath = projectPath + File.separator + "UploadFiles" + File.separator + hinhTwo;
	String hinhThreePath = projectPath + File.separator + "UploadFiles" + File.separator + hinhThree;
	

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

	//@Test
	public void TC_01_UpLoad_Sigle_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		
		uploadFile.sendKeys(hinhOnePath);
		sleep(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinhOne + "']")).isDisplayed());
		
		driver.findElement(By.cssSelector("table button.start")).click();
		sleep(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinhOne + "']")).isDisplayed());
	}

	//@Test
	public void TC_02_Upload_Sigle_File_sesion2() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		
		driver.findElement(uploadFileBy).sendKeys(hinhOnePath);
		sleep(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinhOne + "']")).isDisplayed());
		
		driver.findElement(uploadFileBy).sendKeys(hinhTwoPath);
		sleep(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinhTwo + "']")).isDisplayed());
		
		driver.findElement(uploadFileBy).sendKeys(hinhThreePath);
		sleep(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinhThree + "']")).isDisplayed());
		
		List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));
		
		for (WebElement start : startButton) {
			start.click();
			sleep(3);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinhOne + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinhTwo + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinhThree + "']")).isDisplayed());
	}

	@Test
	public void TC_03_Upload_Multiple() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		
		driver.findElement(uploadFileBy).sendKeys(hinhOnePath + "\n" + hinhTwoPath + "\n" + hinhThreePath);
		sleep(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinhOne + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinhTwo + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinhThree + "']")).isDisplayed());
		
		List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));
		
		for (WebElement start : startButton) {
			start.click();
			sleep(3);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinhOne + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinhTwo + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinhThree + "']")).isDisplayed());
	}
	
	
	public void sleep (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e ) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}