package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;

public class Topic_20_Popup_Frame_Window {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand;
	String email;
	Select select;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "hotgirl" + rand.nextInt(9999) + "@gmail.com";
		
	}

	@Test
	public void TC_01() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.cssSelector("div#modal-login-v1[style] div.modal-content");
		
		// click vào button login 
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		// verify pop-up không hiện thị 
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		
		
		// verify pop-up hiện thị
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("div#modal-login-v1[style] input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("div#modal-login-v1[style] input#password-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("div#modal-login-v1[style] button.btn-login-v1")).click();
		sleep(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1[style] div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		// close pop-up
		driver.findElement(By.cssSelector("div#modal-login-v1[style] button.close")).click();
		sleep(3);
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
	}

	@Test
	public void TC_02_Fixed_Not_In_Dom_Tiki() {
		driver.get("https://tiki.vn/");
		sleep(5);

		// khai báo 1 kiểu dữ liệu bằng By thì chương trình chưa có đi tìm đến element đó : biến By lưu 1 biến và chưa thực thi lệnh
		// khai báo biến theo kiểu WebElement thì khi khai báo chương trình sẽ thực thi lệnh để tìm đến element đó 
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		// verify khi chưa hiện thị login pop-up không có trong DOM/HTML sử dụng .findElements => chọn hàm .size để verify
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// click cho pop up bật lên 
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleep(3);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("0987654321");
		sleep(3);
		
		// close pop up
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleep(3);
		
		// verify khi chưa hiện thị login pop-up không có trong DOM/HTML sử dụng .findElements số nhiều => chọn hàm .size để verify
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}

	@Test
	public void TC_03_Fixed_Not_In_Dom_Tiki() {
		driver.get("https://tiki.vn/");
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleep(3);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleep(3);
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleep(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());

	}
	
	@Test
	public void TC_04_Fixed_Not_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		sleep(3);
		By createAccountPopup = By.xpath("//button[@name='websubmit']");
		String name = "fc";
		String mobilePhone = "0987654321";
		String pass = "auto123456";
		
		// verify 1 pop up không có trong DOM/HtML sẽ sử dụng hàm findElements số nhiều có thêm s và sử dụng hàm size với 0 là không hiện thị
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleep(3);
		Assert.assertTrue(driver.findElement(createAccountPopup).isDisplayed());
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 1);
		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys(mobilePhone);
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys(pass);
		
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("14");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("May");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1996");
		
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleep(3);
		
		driver.findElement(By.xpath("//div[text()='Sign Up']//parent::div/preceding-sibling::img")).click();	
		// verify 1 pop up không có trong DOM/HtML sẽ sử dụng hàm findElements số nhiều có thêm s và sử dụng hàm size với 0 là không hiện thị
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
	}
	
	// yêu cầu :
	// Ramdom popup nên nó có thể hiện thị 1 cách ngẫu nhiên hoặc không hiện thị
	// Nếu như nó hiện thị thì mình cần thao tác lên popup -> đóng nó đi để qua step tiếp theo
	// khi mà đóng nó lại thì có thể refresh tran nó hiện lên lại / hoặc là không
	// Nếu như nó không hiện thị thì qua step tiếp theo luôn
	@Test
	public void TC_05_Ramdom_In_Dom_java_code_geeks() {
		driver.get("https://www.javacodegeeks.com/");
		sleep(10);
		By Popup = By.cssSelector("div.lepopup-popup-fh-container>div:not([style^='display:none']),div.lepopup-form-inner>div.lepopup-element-2");
		
		if(driver.findElement(Popup).isDisplayed()) {
		driver.findElement(By.cssSelector("input.lepopup-ta-left")).sendKeys(email);
		driver.findElement(By.xpath("//span[text()='OK' or text()='Get the Books']")).click();
		sleep(10);
		Assert.assertEquals(driver.findElement(By.cssSelector("h4[style^='text-align']")).getText(), "Thank you!");
		sleep(10);
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleep(10);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());
		sleep(10);
		}else {
			driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
			driver.findElement(By.cssSelector("button#search-submit")).click();
			sleep(10);
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());
			sleep(10);
		}
		
	}
	
	@Test
	public void TC_06_IFRAME_kyna_english() {
		driver.get("https://skills.kynaenglish.vn/");
		sleep(4);
		// verify facebook iframe hiện thị 
		// facebook IFrame là 1 element của kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
		// cần switch vào đúng cái thẻ iframe chưa các element đó 
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		
		String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(facebookLike);
		Assert.assertEquals(facebookLike, "165K likes");
		
		// Switch về main page 
		driver.switchTo().defaultContent();
		// từ main page switch tới Frame/Iframe mới 
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		// click vào chat để show lên Chat Support
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleep(3);
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Jonh Wich");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		sleep(4);
		String text = "Ngã ở đâu, đứng dậy ở đó";
		driver.findElement(By.cssSelector("textarea.input_textarea")).sendKeys(text);
		
		// switch ra ngoài main page để thực hiện handle tiếp các element khác
		driver.switchTo().defaultContent();
		
		String office = "excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(office);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleep(3);
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("ul#w0"));
		
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
		
		String veryfyExcel = driver.findElement(By.cssSelector("div.col-lg-9 span.menu-heading")).getText();
		System.out.println(veryfyExcel);
		Assert.assertEquals(veryfyExcel,"9 Kết quả tìm kiếm từ khóa" + " " + "'excel'");
		
	}
	
	@Test
	public void TC_07_hdfc_bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		sleep(5);
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("john2022");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleep(5);
		driver.switchTo().defaultContent();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
		driver.findElement(By.cssSelector("input#keyboard")).sendKeys("john20232022");
	}
	
	@Test
	public void TC_08_Window() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Windows/ Tab có 2 hàm lấy ra ID của Window/tab đó 
		// sẽ lấy ra cái ID của Tab/window mà nó đang đứng (active)
		 String parentPageWindowID = driver.getWindowHandle();
		 System.out.println("parent Page = " + parentPageWindowID);
		 
		 // click vào google link để bật ra tab mới
		 driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		 sleep(3);
		 
		 Set<String> allWindowIDs = driver.getWindowHandles();
		 System.out.println(allWindowIDs);
		 
		 // lấy hết tất cả các Id ra sau đó dùng vòng lặp duyệt qua và kiểm tra 
		 for (String id : allWindowIDs) {
			// nếu như ID nào khác với id trang chính thì chuyển qua (switch) window/tab đó
			if (!id.equals(parentPageWindowID)) {
				driver.switchTo().window(id);
			}
		}
		 
		 driver.findElement(By.cssSelector("textarea#APjFqb")).sendKeys("happy");
		 sleep(3);
		 
		 // chuyển qua lại trang chính sử dụng lại vòng lặp for.
		 
		 String googleWindowID = driver.getWindowHandle();
		 
		 Set<String> AllWindowIDs = driver.getWindowHandles();
		 
		 for (String id : AllWindowIDs) {
			 if (!id.equals(googleWindowID)) {
				 driver.switchTo().window(id);
				 sleep(3);
			 }	
		}
		 Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		 
		 
		 
		 // case 1 : chi có duy nhất 2 window và chỉ có 2 tab trường hợp này gặp nhiều
		 
		 // case 2 : sẽ có nhiều hơn 2 window và 2 tab trường hợp này ít gặp
		 }
	@Test
	public void TC_09_Short_code_Switch_Window_tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// ghi ra ID của trang chính
		String parentPageWindowID = driver.getWindowHandle();
		// click vào google link để bật ra tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		// switch qua window/tab mới click
		
		switchToWindowbyId(parentPageWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		// ghi ra ID của trang chính
		String googleID = driver.getWindowHandle();
		// switch ngược lại window/tab chính 
		switchToWindowbyId(googleID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}
	// hàm này dùng được duy nhất cho 2 id window/tab
	public void switchToWindowbyId(String IDmain ) {

		 
		 Set<String> AllWindowIDs = driver.getWindowHandles();
		 
		 for (String id : AllWindowIDs) {
			 if (!id.equals(IDmain)) {
				 driver.switchTo().window(id);
				 sleep(3);
			 }	
		}
	}
	
	@Test
	public void TC_10_Switch_By_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleep(3);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleep(3);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleep(3);
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleep(3);
		//switch đến window/tab google.com
		// sử dụng code document.title trên web để lấy title của trừng window/tab web
		switchToWindowByPageTitle("Google");
		driver.findElement(By.cssSelector("textarea#APjFqb")).sendKeys("happy");
		sleep(3);
		//switch to facebook.com
		switchToWindowByPageTitle("Facebook – log in or sign up");
		driver.findElement(By.cssSelector("input#email")).sendKeys("lobely12@gmail.com");
		sleep(3);
		// switch to tiki
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.cssSelector("input.dIEamy")).sendKeys("máy giặt");
		sleep(3);
		
		
	}
	public void switchToWindowByPageTitle(String PageTitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String ID : allWindowIds) {
			// switch từng ID trước
			driver.switchTo().window(ID);
			// lấy ra title page vừa mới switch
			String actualPageTitle = driver.getTitle();
			
			if(actualPageTitle.equals(PageTitle)) {
				break;
			}
			
		}
	}
	public void sleep (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
			}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}










