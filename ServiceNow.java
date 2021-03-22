package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://dev68594.service-now.com/navpage.do");

		driver.switchTo().frame(0);
		driver.findElementById("user_name").sendKeys("admin");
		driver.findElementById("user_password").sendKeys("India@123");
		driver.findElementById("sysverb_login").click();

		WebElement filter = driver.findElementById("filter");
		filter.sendKeys("incident");

		WebElement all = driver.findElementByXPath("(//div[text()='All'])[2]");
		Thread.sleep(2000);
		all.click();

		Actions builder = new Actions(driver);

		WebElement iframe2 = driver.findElementByXPath("//iframe[@id='gsft_main']");

		Thread.sleep(2000);
		driver.switchTo().frame(iframe2);

		WebElement New = driver.findElementByXPath("//button[@id='sysverb_new']");

		Thread.sleep(5000);
		builder.moveToElement(New).build().perform();
		New.click();

		WebElement caller = driver.findElementById("sys_display.incident.caller_id");
		caller.sendKeys("Fred Luddy", Keys.ENTER);
		Thread.sleep(3000);
		WebElement shortdesc = driver.findElementById("incident.short_description");
		shortdesc.sendKeys("Hey ...Fred Luddy");
		Thread.sleep(3000);
		WebElement incident_num = driver.findElementByXPath("//input[@id='incident.number']");
		String text = incident_num.getAttribute("value");
		System.out.println(text);
		WebElement submit = driver.findElementByXPath("//button[@id='sysverb_insert']");
		submit.click();

		WebElement search = driver.findElementByXPath("(//input[@class='form-control'])[1]");
		search.sendKeys(text,Keys.ENTER);

		WebElement incident_num2 = driver.findElementByXPath("//a[@class='linked formlink']");
		String text2 = incident_num2.getText();

		if (text.equals(text2)) {
			System.out.println("incident is created successful");
		} else {
			System.out.println("incident is not created");
		}

	}

}
