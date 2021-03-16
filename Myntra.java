package testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://www.myntra.com/");

		WebElement women = driver.findElementByXPath("(//a[text()='Women'])[1]");

		Actions builder = new Actions(driver);
		builder.moveToElement(women).perform();

		driver.findElementByXPath("//a[text()='Jackets & Coats']").click();

		String total = driver.findElementByXPath("//span[@class='title-count']").getText();
		// System.out.println(total);
		String totalCount = total.replaceAll("\\D", "");
		int totalnum = Integer.parseInt(totalCount);
		System.out.println(totalnum);

		String jackets = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String coats = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();

		String replaceJackets = jackets.replaceAll("\\D", "");
		String replaceCoats = coats.replaceAll("\\D", "");

		int jacketsCount = Integer.parseInt(replaceJackets);
		int coatsCount = Integer.parseInt(replaceCoats);

		int sum = jacketsCount + coatsCount;

		if (totalnum == sum) {
			
			System.out.println("Total count and sum of jackets&coats are same");
		}
		else {
			System.out.println("Total count and sum of jackets&coats are not same");
		}
	
		WebElement chkbox_coats = driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]");
		chkbox_coats.click();
		
		driver.findElementByXPath("//div[@class='brand-more']").click();
		
		driver.findElementByXPath("//input[@placeholder='Search brand']").sendKeys("MANGO");
		
		driver.findElementByXPath("//span[@class='FilterDirectory-count']/following-sibling::div[1]").click();
		
		driver.findElementByXPath("//span[contains(@class,'myntraweb-sprite FilterDirectory-close')]").click();
		
		List<WebElement> list = driver.findElementsByXPath("//h3[text()='MANGO']");
		for (WebElement AllList : list) {
			String allList = AllList.getText();
			if (allList.contains("MANGO")) {
				System.out.println("Hurrey");
			}
			System.out.println(allList);	
		}
			WebElement sortby = driver.findElementByXPath("//span[contains(@class,'myntraweb-sprite sort-downArrow')]");
			builder.moveToElement(sortby).perform();
			driver.findElementByXPath("//label[text()='Better Discount']").click();
			
			List<WebElement> items = driver.findElementsByXPath("(//span[@class='product-discountedPrice'])[1]");
			String price = items.get(0).getText();
			System.out.println(price);
			
			WebElement first_item = driver.findElementByXPath("(//div[@class='product-productMetaInfo'])[1]");
			builder.moveToElement(first_item).perform();
			
			Thread.sleep(2000);
			driver.findElementByXPath("(//span[@class='product-wishlistFlex product-actionsButton product-wishlist '])[1]").click();
			
			driver.close();
				
		}
			
	}
	
	


