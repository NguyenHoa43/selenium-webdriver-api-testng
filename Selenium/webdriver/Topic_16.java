package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16 {
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
	public void TC_01() {
		driver.get("https://www.thegioididong.com/dtdd");
		System.out.println(driver.findElement(By.xpath("(//h3[contains(text(),'iPhone 11')]/preceding-sibling::p/following-sibling::div[@class='item-rating']/descendant::i)[1]")).getText());

	}
	
	@Test
	public void TC_02_text() {
		driver.get("https://www.thegioididong.com/dtdd");
		driver.findElement(By.xpath("//strong[text()='11.490.000₫']/preceding-sibling::div[@class='box-p']/preceding-sibling::h3"));
	}

	@Test
	public void TC_03() {
		driver.get("https://www.thegioididong.com/dtdd");
		driver.findElement(By.xpath("//strong[text()='11.490.000₫']/preceding-sibling::div[@class='prods-group']//li")).click();
	}
	//@AfterClass
	//public void afterClass() {
	//	driver.quit();
}
