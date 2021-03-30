package testCases;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailUnique {

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
		To.sendKeys("mdu",Keys.ENTER);
		Thread.sleep(2000);

		List<WebElement> trainNames = driver
				.findElementsByXPath("//table[@class='DataTable TrainList TrainListHeader']//tr/td[2]");

		int sizeList = trainNames.size();
		System.out.println(" List size:"+sizeList );
		
		for (WebElement list : trainNames) {
			System.out.println("List Train Size " + list.getText());
		}

		System.out.println(" Set ***********");
		Set<WebElement> hSet = new HashSet<WebElement>(trainNames);
		System.out.println("Set Size: " +hSet.size());
		for (WebElement set : hSet) {
			System.out.println(set.getText());
			//System.out.println(set.getSize());
			
		}
		
		
		
		/*
		 * for (WebElement set : trainNames) System.out.println(set.getText());
		 * 
		 */
	}

}
