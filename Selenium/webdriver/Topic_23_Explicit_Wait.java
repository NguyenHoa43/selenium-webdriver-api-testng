package webdriver;

import java.io.File;
import java.sql.Date;
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

public class Topic_23_Explicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	String picterOne = "1.jpg";
	String picterTwo = "2.jpg";
	String picterThree = "3.jpg";
	
	String PicterOnePath = projectPath + File.separator + "UploadFiles" + File.separator + picterOne ;
	String PicterTwoPath = projectPath + File.separator + "UploadFiles" + File.separator + picterTwo ;
	String PicterThreePath = projectPath + File.separator + "UploadFiles" + File.separator + picterThree;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 50);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01() {
		driver.get("https://www.facebook.com/");
		// chờ cho 1 alert xuất hiện trong HTML/DOM
		// chờ và switch vào alert được luôn không cần hàm switchto()
		explicitWait.until(ExpectedConditions.alertIsPresent());
		//chờ cho 1 attribute có value -  dùng hàm attribute
		explicitWait.until(ExpectedConditions.attributeContains(By.id("email"), "placeholder", "Email address"));
		explicitWait.until(ExpectedConditions.attributeToBe(By.id("email"), "placeholder", "Email address or phone number"));
		// chờ cho 1 element có thể được click hay không : botton/ checkbox/ radio/ link/ image.
		// dùng trước hàm click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='login']")));
		// chờ cho 1 element đã được chọn hay chưa : checkbox/ radio
		// dùng trước khi apply isSelected()
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.elementToBeSelected(By.xpath("//input[@class='_8esa' and @value='1']")));
		
		// chờ cho frame xuất hiện và switch vào frame đó
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
		// chờ cho 1 element không còn hiện diện nữa
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
		// chờ cho nhiều element không còn hiện diện nữa
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));
		// var Arguments
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));
		
		// chờ cho các element nó có tổng số lượng là 3
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 3));
		// chờ các element có số lượng ít hơn 3
		explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 3));
		// chờ các element có số lượng lớn hơn 3
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 3));
		
		// thao tác và chờ có bao nhiêu cửa sổ và tab xuất hiện.
		explicitWait.until(ExpectedConditions.numberOfWindowsToBe(4));
		// chờ cho các element xuất hiện trong HTML/DOM( không cần quan tâm có hiện lên UI hay không)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));
		// chờ cho các element xuất hiện trong HTML/DOM( không cần quan tâm có hiện lên UI hay không)
		// sử dụng cho phần dropdown Item 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
		// 
		explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector(""), By.cssSelector("")));
		
		explicitWait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.cssSelector(""), By.cssSelector("")));
		
		
		// chờ cho 1 element không còn trong HTML nữa.
		explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));
		
		// chờ cho xuất hiện element xuất hiện text đó - trước hàm gettext
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "aloalo"));
		
		explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(""), "aloalo"));
		
		// chờ cho xuất hiện title - contains là lấy tương đối
		explicitWait.until(ExpectedConditions.titleContains("aloalo"));
		// chờ cho xuất hiện title - titleIS là lấy tuyệt đối
		explicitWait.until(ExpectedConditions.titleIs("aloalo-1234"));
		// chờ cho url xuất hiện - hàm contains lấy tương đối
		explicitWait.until(ExpectedConditions.urlContains("facebook.com"));
		// chờ cho url xuất hiện - hàm urltobe lấy tuyệt đối
		explicitWait.until(ExpectedConditions.urlToBe("https://www.facebook.com/"));
		
		// chờ cho 1 element hiện thị 
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
		
		// chờ cho nhiều element hiện thị.
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("")));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("")));
		
		// chờ cho nhiều element hiện thị.- gồm 2 element cha và con 
		explicitWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(By.cssSelector("cha"), By.cssSelector("con")));
		explicitWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(driver.findElement(By.cssSelector("cha")), By.cssSelector("con")));
	}

	@Test
	public void TC_02_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// Wait và click vào start button
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#start button"))).click();
		
		String textHello = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).getText();
		System.out.println(textHello);
		Assert.assertEquals(textHello,  "Hello World!");
	}

	@Test
	public void TC_03_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		// wait cho loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		System.out.println("result =" + " " + helloText);
		Assert.assertEquals(helloText,  "Hello World!");
	}
	
	@Test
	public void TC_04_ExplicitWait_telerik() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// chờ cho panel date hiện lên
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));
		// chờ và verify text
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display.")));
		// chờ và click vào button date 11
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']/parent::td"))).click();
		// chờ icon loading biến mất
		boolean buttonWait = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv")));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:not([style='display:none;'])>div.raDiv")));
		// verify loading icon biến mất
		Assert.assertTrue(buttonWait);
		
		boolean textDate = explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),  "Thursday, May 11, 2023"));
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Thursday, May 11, 2023")));
		Assert.assertTrue(textDate);
	}

	@Test
	public void TC_05_ExplicitWait_gofile() {
		driver.get("https://gofile.io/?t=uploadFiles");
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-lg"))).click();
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));
		WebElement ClickUpload = driver.findElement(By.cssSelector("input#filesUploadInput"));
		ClickUpload.sendKeys(PicterOnePath + "\n" + PicterTwoPath + "\n" + PicterThreePath);
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.progress-bar"))));
		
		// cách 1 verify bằng cách khai báo biến Wait
		boolean textSuccess = explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.mainUploadSuccess div.border-success"), "Your files have been successfully uploaded"));
		Assert.assertTrue(textSuccess);
		// cách 2 verify bằng cách trực tiếp
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.mainUploadSuccess div.border-success"), "Your files have been successfully uploaded")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();
		
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='" + picterOne + "']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='" + picterTwo + "']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='" + picterThree + "']"))).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}