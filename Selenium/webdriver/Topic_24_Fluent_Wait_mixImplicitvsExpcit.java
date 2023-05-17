package webdriver;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_24_Fluent_Wait_mixImplicitvsExpcit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	long allTime = 150;
	long pollingTime = 1000;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Element_Found() {
		
		// Element có xuất hiện và không cần chờ hết timeout mặc dù set 2 loại wait thì không ảnh hưởng
		// implicit wait chỉ apply cho findElement
		// explicitWait chỉ apply cho các điều kiện của các Element
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
		
		System.out.println("kết quả time bắt đầu chạy = " + timeDate());
		try {
			driver.findElement(By.cssSelector("input#email"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết quả time sau khi chạy = " + timeDate());
		}
		
		
		System.out.println("kết quả time bắt đầu chạy = " + timeDate());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết quả time sau khi chạy = " + timeDate());
		}
		
		
	}
	//@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
		
		System.out.println("kết quả time bắt đầu chạy = " + timeDate());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết quả time sau khi chạy = " + timeDate());
		}
		
		
		
		
	}

	//@Test
	public void TC_03_Element_Not_Found() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		System.out.println("kết quả time bắt đầu chạy = " + timeDate());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết quả time sau khi chạy = " + timeDate());
		}
		
		
		System.out.println("kết quả time bắt đầu chạy = " + timeDate());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết quả time sau khi chạy = " + timeDate());
		}
		
		
		
		
		
	}
	//@Test
	public void TC_04_Element_Not_Found() {
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("https://www.facebook.com/");
		
		System.out.println("kết quả time bắt đầu chạy = " + timeDate());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết quả time sau khi chạy = " + timeDate());
		}
		
	}
	
	//@Test
	public void TC_Fluent_Wait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// khai báo FluentWait
		fluentDriver = new FluentWait<WebDriver>(driver);
		// set thời gian và tần số tìm kiếm
		fluentDriver.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(333)).ignoring(NoSuchElementException.class);
		// Apply điều kiện
		fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#start>button"));
			}
		});
		
		// có thể sử dụng để khai báo 1 biến 
		WebElement startbutton = fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#start>button"));
			}
		});
		
		// sử dụng biến mới được khai báo để thực hiện 1 action nào đó tùy theo kiểu dữ liệu.
		startbutton.click();
		
	}
	// viết 1 hàm fluent wait hoàn chỉnh rồi sử dụng cho test case sau 
	public WebElement findElement(String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		return fluentDriver.until(new Function<WebDriver, WebElement>(){
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
	}
	
	//@Test
	public void TC_Fluent_Wait_coston_short_1() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		System.out.println("kết quả time bắt đầu chạy = " + timeDate());
		findElement("//div[@id='start']/button").click();
		
		Assert.assertTrue(findElement("//div[@id='loading']/img").isDisplayed());
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
		String TextHello = findElement("//div[@id='finish']/h4").getText();
		System.out.println(TextHello);
		System.out.println("kết quả time sau khi chạy = " + timeDate());
	}
	@Test
	public void TC_Fluent_Wait_coston_short_2() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		System.out.println("kết quả time bắt đầu chạy = " + timeDate());
		WebElement countdown = findElement("//div[@id='javascript_countdown_time']");
		fluentElement = new FluentWait<WebElement>(countdown);
		fluentElement.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		fluentElement.until(new Function<WebElement, Boolean>(){
			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
	}
	
	
	public void sleep (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e ) {
			e.printStackTrace();
		}
	}
	
	// hàm đếm thời gian chạy của 1 test case 
	public String timeDate() {
		Date date = new Date();
		return date.toString();
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}