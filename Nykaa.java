package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://www.nykaa.com/");

		WebElement brands = driver.findElementByXPath("//a[text()='brands']");
		WebElement popular = driver.findElementByXPath("//a[text()='Popular']");

		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		builder.moveToElement(popular).perform();

		driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']")
		.click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> newWindow = new ArrayList<String>(windowHandles);

		driver.switchTo().window(newWindow.get(1));

		String title = driver.getTitle();
		System.out.println(title);

		driver.findElementByXPath("//span[@class='pull-left']").click();

		driver.findElementByXPath("(//div[@class='control__indicator radio'])[4]").click();

		Thread.sleep(5000);
		WebElement category = driver.findElementByXPath("//div[text()='Category']");
		category.click();

		// driver.executeScript("arguments[0].scrollIntoView()", category);

		// builder.moveToElement(category).click(category).perform();

		WebElement Hair = driver.findElementByXPath("//span[text()='Hair']");
		Hair.click();

		WebElement HairCare = driver.findElementByXPath("//span[text()='Hair Care']");
		HairCare.click();

		WebElement shampoo = driver.findElementByXPath("//span[text()='Shampoo']");
		shampoo.click();

		WebElement filter = driver.findElementByXPath("//li[text()='Shampoo']");


		if (filter.getText().replaceAll("close","").equals("Shampoo")) {
			System.out.println("Filter Applied");

		}else {
			System.out.println("Filter not Applied");
		}

		WebElement colorproshampoo = driver.findElementByXPath("(//div[@class='m-content__product-list__title']//h2)[3]");
		colorproshampoo.click();

		Set<String> winSet1 = driver.getWindowHandles();
		List<String> winList1 = new ArrayList<String>(winSet1);
		driver.switchTo().window(winList1.get(2));

		String mrp = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		System.out.println("MRP of the produce: "+mrp.replaceAll("\\D", ""));

		WebElement Bag = driver.findElementByXPath("//button[text()='ADD TO BAG']");
		Bag.click();

		driver.findElementByClassName("AddBagIcon").click();

		Thread.sleep(3000);
		String mrp1 = driver.findElementByXPath("//div[text()='220']").getText();
		String GrandTotal = mrp1.replaceAll("\\D", "");
		int GT = Integer.parseInt(GrandTotal);

		Thread.sleep(3000);
		WebElement proceed = driver.findElementByXPath("//span[text()='Proceed']");
		proceed.click();

		WebElement Guest = driver.findElementByXPath("(//button[@type='button'])[2]");
		Guest.click();
		
		String GTotal = driver.findElementByXPath("(//div[@class='value']//span)[2]").getText();
		String GrandTotal2 = GTotal.replaceAll("\\D", "");
		int GT2 = Integer.parseInt(GrandTotal2);
		
		
		if (GT == GT2) {
			System.out.println("Grand Total are same"+ GT2);
		}else {
			System.out.println("Grand Total are not same");
		}

		driver.quit();


	}
}
