package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01() {
		driver.get("https://api.orangehrm.com/");
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader div.container1")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
		
		
	}

	//@Test
	public void TC_02_Page_Ready() {
		driver.get("https://admin-demo.nopcommerce.com");
		
		
		driver.findElement(By.cssSelector("input.email")).clear();
		driver.findElement(By.cssSelector("input.email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input.password")).clear();
		driver.findElement(By.cssSelector("input.password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button.button-1")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertEquals(driver.getTitle(),"Your store. Login");
	}

	@Test
	public void TC_03_Ready_Wait() {
		driver.get("https://blog.testproject.io/");
		action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		String Text = "Selenium";
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(Text);
		action.doubleClick(driver.findElement(By.cssSelector("section#search-2 span.glass"))).perform();
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[@class='main-heading' and text()='Search Results']")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postArticles = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement articles : postArticles) {
			Assert.assertTrue(articles.getText().contains(Text));
		}
	}
	
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
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