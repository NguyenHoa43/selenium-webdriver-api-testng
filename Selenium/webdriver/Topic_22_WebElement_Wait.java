package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_WebElement_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		//explicitWait = new WebDriverWait(driver, 50);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_ExplicitWait_Displayed_element_Wait() {
		driver.get("https://www.facebook.com/");
		// điều kiện 1 : sử dụng Wait để xác định Element có trên giao diện (UI) và có trong HTML
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name='login']")));
		

	}

	//@Test
	public void TC_02_ExplicitWait_Undisplay_element_In_Dom() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// điều kiện 2: sử dụng Wait để xác định Element không có trên UI nhưng lại có trong HTML/DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='firstname']")));
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("FC");
	}

	//@Test
	public void TC_03_ExplicitWait_Undisplay_element_Not_In_Dom() {
		driver.get("https://www.facebook.com/");
		
		// điều kiện 3: sử dụng Wait để xác định Element không có trên UI và không có trong HTML/DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='firstname']")));
	
	}
	
	//@Test
	public void TC_04_ExplicitWait_Present() {
		driver.get("https://www.facebook.com/");
		
		// điều kiện bắt buộc của present là Element phải có trong HTML/DOM
		// điều kiện 1 : element hiện thị trong HTL/DOM và hiện thị trên UI 
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='login']")));
		// điều kiện 2 : element hiện thị trong HTL/DOM và không hiện thị trên UI 
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='firstname']")));
		
	}
	
	//@Test
	public void TC_05_ExplicitWait_Staleness() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// tại thời điểm này element đang xuất hiện input[name='firstname']
		WebElement confirmEmailTextbox = driver.findElement(By.cssSelector("input[name='firstname']"));
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		 
		// wait cho confirm email không có  trong HTML/DOM
		// cách 1 : sử dụng invisible
		explicitWait.until(ExpectedConditions.invisibilityOf(confirmEmailTextbox));
		// cách 2 : staleness
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmailTextbox));
		
	}
	
	//@Test
	public void TC_06_ImplicitWait_findelement() {
		
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// Tìm thấy duy nhất 1 element / node.
		// tìm thấy và thao tác trực tiếp lên element/ node đó. ide sẽ không chờ hết timeout được set trước đó và dừng luôn.
		driver.findElement(By.cssSelector("input#email"));
		// Tìm thấy nhiều hơn 1 element/ node.
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("alo@gmail.com");
		// Không tìm thấy element/ node nào.
		// hết thời gian đã cài đặt sẽ bị đánh fail và các step tiếp theo sẽ không được thực hiện nữa.
		driver.findElement(By.cssSelector("input[type='check']"));
		
	}
	
	//@Test
	public void TC_07_ImplicitWait_findElements() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// Tìm thấy duy nhất 1 element / node.
		// tìm thấy và lưu nó vào list = 1 element. ide sẽ không chờ hết timeout được set trước đó và dừng luôn.
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("list number of element = " + elements.size());
		// Tìm thấy nhiều hơn 1 element/ node.
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("list number of element = " + elements.size());
		
		elements = driver.findElements(By.cssSelector("input"));
		System.out.println("list number of element = " + elements.size());
		
		WebElement elementAction = driver.findElement(By.cssSelector("input[type='email']"));
		elementAction.sendKeys("Alo@gmail.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Không tìm thấy element/ node nào.
		// sẽ không đánh fail test case và thực hiện các step tiếp theo
		// trả về 1 list rỗng = 0
		elements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("list number of element = " + elements.size());
	}
	
	//@Test
	public void TC_08_ImplicitWait_Enough_time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),  "Hello World!");
	}
	
	//@Test
	public void TC_09_ImplicitWait_Not_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),  "Hello World!");
	}
	//@Test
	public void TC_10_ImplicitWait_More_time() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// get text và verify 
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		Assert.assertEquals(text,  "Hello World!");
	}

	
	@Test
	public void TC_11_Hard_Wait_enough() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleep(5);
		//click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		sleep(5);
	}
	
	@Test
	public void TC_12_Hard_Wait_Not_enough() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleep(3);
		//click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		sleep(3);
	}
	
	@Test
	public void TC_13_Hard_Wait_More() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleep(7);
		//click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		sleep(7);
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