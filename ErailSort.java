package testCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailSort {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://erail.in/");

		driver.findElementByXPath("//input[@id='chkSelectDateOnly']").click();

		WebElement From = driver.findElementByXPath("//input[@id='txtStationFrom']");
		From.clear();
		From.sendKeys("Chennai Egmore", Keys.DOWN, Keys.ENTER);
		Thread.sleep(2000);

		WebElement To = driver.findElementByXPath("//input[@id='txtStationTo']");
		To.clear();
		To.sendKeys("mdu", Keys.ENTER);
		Thread.sleep(2000);

		List<WebElement> trainNames = driver
				.findElementsByXPath("//table[@class='DataTable TrainList TrainListHeader']//tr/td[2]");
		;

		List<String> strNames = new ArrayList<String>();

		for (WebElement trains : trainNames) {
			String text = trains.getText();
			strNames.add(text);
			// System.out.println(text);

		}

		Collections.sort(strNames);

		for (String eachName : strNames) {
			System.out.println(eachName);
		}

	}

}
