package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Action_part_II2_chromedriver {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// code đang chứa 12 item trong list => cần lấy list trước khi thực hiện các bước sau
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// click vào số 1 trong list sẽ chọn index băt đầu lá 0 -> di chuyển chuột tới số cần di chuyển (target) 8 -1 => 7 -> sau đó nhã chuột sẽ chọn 
		// hàm release và thực thi câu lệnh là hàm perform
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(7)).release().perform();
		sleep(2);
		
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		// verify chọn và giữ 1 đối tượng sẽ sử dụng hàm size và số lượng đối tượng được chọn và giữ để xác nhận 
		Assert.assertEquals(listSelectedNumber.size(), 8);
	}

	@Test
	public void TC_02_Click_And_Hold_Ramdom() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// define biến key để chạy được trên windows và mac
		Keys key = null;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// nhấn Ctrl xuống - do khai báo biến keys trên nên biến trong keyDown sẽ là key 
		action.keyDown(key).perform();
		//nhấn Ctrl xuống - khi chưa khai báo biến keys action.keyDown(Keys.CONTROL).perform();
		// chọn các số ramdom
		action.click(listNumber.get(0)).click(listNumber.get(4)).click(listNumber.get(7)).click(listNumber.get(8)).click(listNumber.get(11)).perform();
		sleep(4);
		
		// tạo danh sách các thành phần được chọn để xác thực các thành phần được chọn đúng hay sai
		List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		// xác thực ( verify ) các thành phần được chọn
		Assert.assertEquals(listNumberSelected.size(), 5);
		
	}

	@Test
	public void TC_03_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//scroll đến element cần thực thi action 
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleep(3);
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleep(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
		
		
	}
	
	@Test
	public void TC_04_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		// sử dụng contextclick là hàm nhấn chuột phải
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleep(3);
		
		// verify hiện thị của icon quit khi chuột phải vào thành phần chứa icon đó
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		// sử dụng hàm movetoElement để holver chuột vào vị trí cần giữ chuột 
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleep(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
		driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();
		sleep(3);
		
		driver.switchTo().alert().accept();
		sleep(3);
		
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
	}
	
	@Test
	public void TC_05_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		// khai báo 2 thẻ để có thể truyền vào hàm action
		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
		// sử dụng 2 thẻ vừa khai bào cho vào hàm drag and drop để thực thi hành động 
		action.dragAndDrop(smallCircle, bigCircle).perform();
		
		//verify text khi đã kéo thả 
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		// verify background color
		// khai báo biến background color của đối tượng cần verify trước và sau đó in bảng màu đó ra rồi mới verify màu của đối tượng được chọn
		String bigCircleRGB = bigCircle.getCssValue("background-color");
		System.out.println(bigCircleRGB);
		
		String bigCircleRGBA = Color.fromString(bigCircleRGB).asRgba();
		System.out.println(bigCircleRGBA);
		
		Assert.assertEquals(bigCircleRGBA,"rgba(3, 169, 244, 1)");
		
		// verify background color bàng mã màu hex -> chuyển đổi mã màu RGB sang mã màu hex mình mới verify bằng cách khai báo và chuyển đổi sang hex
		String bigCircleHexa = Color.fromString(bigCircleRGB).asHex();
		System.out.println(bigCircleHexa);
		
		Assert.assertEquals(bigCircleHexa.toUpperCase(), "#03A9F4");
		
	}
	
	public void sleep (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();	
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}