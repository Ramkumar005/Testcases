package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RedBus {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.redbus.in/");

		driver.findElementByXPath("//input[@id='src']").sendKeys("c");
		Thread.sleep(3000);
		driver.findElementByXPath("//input[@id='src']").sendKeys(Keys.DOWN, Keys.ENTER);

		driver.findElementByXPath("//input[@id='dest']").sendKeys("b");
		Thread.sleep(3000);
		driver.findElementByXPath("//input[@id='dest']").sendKeys(Keys.DOWN, Keys.ENTER);

		driver.findElementByXPath("//input[@id='onward_cal']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//td[@class='next']/button").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//table[@class='rb-monthTable first last']//tr/td[text()='1']").click();

		driver.findElementByXPath("//button[@id='search_btn']").click();

		driver.findElementByXPath("//div[@class='close']/i").click();

		String results1 = driver.findElementByXPath("//span[@class='f-bold busFound']").getText();
		System.out.println("Total Buses " + results1);

		WebElement sleeper = driver.findElementByXPath("(//input[@id='bt_SLEEPER']/following-sibling::label)[1]");
		sleeper.click();

		String results2 = driver.findElementByXPath("//span[@class='f-bold busFound']").getText();
		System.out.println("Sleeper Buses " + results2);

		WebElement AC = driver.findElementByXPath("(//input[@id='bt_AC']/following-sibling::label)[1]");
		AC.click();

		String results3 = driver.findElementByXPath("//span[@class='f-bold busFound']").getText();
		System.out.println("Sleeper With AC Bus " + results3);

		List<WebElement> travelsFound = driver.findElementsByXPath("//div[@class='fare d-block']");
		System.out.println(travelsFound.size());

		List<Integer> maxValue = new ArrayList<Integer>();

		/*
		 * for (WebElement travelFare : travelsFound) {
		 * 
		 * String fare1 = travelFare.getText(); int fareValue = Integer.parseInt(fare1);
		 * maxValue.add(fareValue); System.out.println(fare); }
		 */

		for (int i = 0; i <= travelsFound.size() - 1; i++) {

			String fare = travelsFound.get(i).getText();
			String busFare = fare.replaceAll("[^0-9]", "");
			int travelFare = Integer.parseInt(busFare);
			maxValue.add(travelFare);
			// System.out.println(travelFare);
		}

		Collections.sort(maxValue);
		Integer maxFare = Collections.max(maxValue);
		System.out.println("Maximum Fare: " + maxFare);

		System.out.println("Costliest bus name:" + driver.findElementByXPath(
				"//*[contains(text(), " + maxFare + ")]/preceding::div[@class=\"travels lh-24 f-bold d-color\"][1]")
				.getText());
	}

}
