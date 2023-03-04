package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Drop_down_list_customs {
	WebDriver driver;
	// wait tường minh 
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']", "Medium");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");
		selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']", "Fast");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']", "Slower");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']", "Slow");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
		selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']", "Faster");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
		
		}
	
		// 3.1- nếu nó nằm trong khoản nhìn thấy của user thì không cần scroll xuống 
		// 3.2- nếu nó không nằm trong khoản nhìn thấy của user thì cần scoll xuống đến item đó
		
		
	

	@Test
	public void TC_02_() {
		// 1- click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết ca item của dropdown
				driver.findElement(By.cssSelector("span#speed-button")).click();
				sleep(3);
				// 2- chờ cho tất cả các item được load ra thành công
				// Locator phải lấy để đại diện cho tất cả các item
				// lấy đến thẻ chứa text
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
				
				// đưa hết tất cả item vào trondropdown vào 1 list
				List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
				
				// 3- tìm item xem đúng cái cần hay không 
				for (WebElement tempItem : speedDropdownItems) {
					String itemText = tempItem.getText();
					System.out.println(itemText);
					// 4- kiểm tra cái text của item đó đúng với cái mình mong muốn
					if (itemText.equals("Medium")) {
						// 5- click vào item đó 
						tempItem.click();
						break;
					}
				}
	}

	@Test
	public void TC_03_() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropdown("span#salutation-button","ul#salutation-menu li.ui-menu-item", "Mr.");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mr.");
		
		selectItemInDropdown("span#salutation-button","ul#salutation-menu li.ui-menu-item", "Mrs.");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");
		
		selectItemInDropdown("span#salutation-button","ul#salutation-menu li.ui-menu-item", "Dr.");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
		
		selectItemInDropdown("span#salutation-button","ul#salutation-menu li.ui-menu-item", "Prof.");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");
		
		selectItemInDropdown("span#salutation-button","ul#salutation-menu li.ui-menu-item", "Other");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Other");
	}
	
	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropdown("div.btn-group", "ul.dropdown-menu>li>a", "First Option");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemInDropdown("div.btn-group", "ul.dropdown-menu>li>a", "Second Option");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemInDropdown("div.btn-group", "ul.dropdown-menu>li>a", "Second Option");
		sleep(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
	}
	
	@Test
	public void TC_05_Dropdown_List_example() {
		driver.get("http://opac.nlv.gov.vn/pages/opac/wpid-home.html");
		
		new Select(driver.findElement(By.cssSelector("select#cbSearchValueform-quick1"))).selectByVisibleText("Tìm nhanh");
		driver.findElement(By.cssSelector("div.col-sm-7 input#txtSearchValueform-quick1")).sendKeys("Dế Mèn Phiêu Lưu Ký");
		driver.findElement(By.cssSelector("button.awe-search")).click();
		sleep(3);
	}
	
	@Test
	public void TC_06_Dropdown_List_example() {
		driver.get("http://opac.nlv.gov.vn/pages/opac/wpid-home.html");
		
		new Select(driver.findElement(By.cssSelector("select#cbSearchValueform-quick1"))).selectByVisibleText("Nhan đề");
		driver.findElement(By.cssSelector("div.col-sm-7 input#txtSearchValueform-quick1")).sendKeys("truyện");
		driver.findElement(By.cssSelector("button.awe-search")).click();
		sleep(3);
	}
	// mục đích của hàm là dùng để tránh việc lặp lại code nhiều lần , khi cần viết case mới thì chỉ cần gọi hàm ra và dùng
	// hàm sẽ đi cùng với tham số
	// nếu mình truyền cứng giá trị vào trong hàm thì việc đó sẽ vô nghĩa 
	// nên mặc định (define ) để dùng đi dùng lại nhiều lần 
	// trong cùng 1 class có thể gọi 1 hàm này ra để sử dụng cho 1 hàm khác cũng được
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem ) {
		
		// 1- click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết ca item của dropdown
				driver.findElement(By.cssSelector(parentCss)).click();
				sleep(3);
				// 2- chờ cho tất cả các item được load ra thành công
				// Locator phải lấy để đại diện cho tất cả các item
				// lấy đến thẻ chứa text
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
				
				// đưa hết tất cả item vào trondropdown vào 1 list
				List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
				
				// 3- tìm item xem đúng cái cần hay không 
				for (WebElement tempItem : speedDropdownItems) {
					// 4- kiểm tra cái text của item đó đúng với cái mình mong muốn
					if (tempItem.getText().trim().equals(expectedTextItem)) {
						// 5- click vào item đó 
						tempItem.click();
						break;
					}
				}
	}
	
	public void enterAndselectItemInDropdown(String textboxCss, String allItemCss, String expectedTextItem ) {
		
		// 1- sendkey (nhập) 1 expected text vào - xổ ra các item matching 
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleep(2);
				
				// 2- chờ cho tất cả các item được load ra thành công
				// Locator phải lấy để đại diện cho tất cả các item
				// lấy đến thẻ chứa text
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
				
				// đưa hết tất cả item vào trondropdown vào 1 list
				List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
				
				// 3- tìm item xem đúng cái cần hay không 
				for (WebElement tempItem : speedDropdownItems) {
					// 4- kiểm tra cái text của item đó đúng với cái mình mong muốn
					if (tempItem.getText().trim().equals(expectedTextItem)) {
						sleep(3);
						// 5- click vào item đó 
						tempItem.click();
						break;
					}
				}
	}
	public void sleep(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}