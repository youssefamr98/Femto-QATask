
package Default;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Test {
	public static WebDriver driver;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://weathershopper.pythonanywhere.com/");
		WebElement myElement = driver.findElement(By.cssSelector("#weather"));
		String extractedNumberString = myElement.getText().replaceAll("[^\\d]", "");
		int extractedTemperature = Integer.parseInt(extractedNumberString);
		System.out.println("Current Temperature: " + extractedTemperature);

		// Shop for Moisturiser
		if (extractedTemperature < 19) {
			WebElement moisturizerButton = driver
					.findElement(By.xpath("//button[normalize-space()='Buy moisturizers']"));
			moisturizerButton.click();
			List<WebElement> products = driver.findElements(By.xpath("//div[@class='text-center col-4']"));
			int Prodcount = 0;
			for (@SuppressWarnings("unused") WebElement prod : products) {
				Prodcount++;
			}
			System.out.println("Total Products:" + Prodcount);

			List<WebElement> AloeProducts = new ArrayList<WebElement>();
			List<WebElement> AlmondProducts = new ArrayList<WebElement>();
			int AloeCount = 0;
			int Almondcount = 0;
			for (WebElement product : products) {
				if (product.getText().contains("Aloe")) {
					AloeProducts.add(product);
					AloeCount++;

				} else {
					AlmondProducts.add(product);
					Almondcount++;
				}

			}
			System.out.println("AloeProduct: " + AloeCount);
			System.out.println("AlmondProducts: " + Almondcount);
			buyAloe(AloeProducts);
			buyAlmond(AlmondProducts);
			navigateToCartPage();
		}
		if (extractedTemperature > 34) {
			WebElement sunScreensButton = driver.findElement(By.xpath("//button[normalize-space()='Buy sunscreens']"));
			sunScreensButton.click();
			List<WebElement> products = driver.findElements(By.xpath("//div[@class='text-center col-4']"));
			int Prodcount = 0;
			for (@SuppressWarnings("unused") WebElement prod : products) {
				Prodcount++;
			}
			System.out.println("Total Products:" + Prodcount);
			List<WebElement> SPF50 = new ArrayList<WebElement>();
			List<WebElement> SPF30 = new ArrayList<WebElement>();
			int SPF50Count = 0;
			int SPF30count = 0;
			for (WebElement product : products) {
				if (product.getText().contains("SPF-50")) {
					SPF50.add(product);
					SPF50Count++;

				}
				if (product.getText().contains("SPF-30"))

					SPF30.add(product);
				SPF30count++;
			}

			System.out.println("SPF50:" + SPF50Count);
			System.out.println("SPF30:" + SPF30count);
			buySPF50(SPF50);
			buySPF30(SPF30);
			navigateToCartPage();

		}

	}

	public static void buyAloe(List<WebElement> Aloe) {
		WebElement leastExpensiveAloe = null;
		double aloePrice = 0.0;
		double leastAloeExpensivePrice = Double.MAX_VALUE;
		for (WebElement AloeElement : Aloe) {
			aloePrice = Double.parseDouble(AloeElement.getText().replaceAll("[^\\d.]", ""));
			if (aloePrice < 1) {
				aloePrice = aloePrice * 1000;
			}
			// Compare prices and update leastExpensive variables
			if (aloePrice < leastAloeExpensivePrice) {
				leastAloeExpensivePrice = aloePrice;
				leastExpensiveAloe = AloeElement;
			}
		}
		if (leastExpensiveAloe != null) {
			leastExpensiveAloe.findElement(By.cssSelector("[onclick*=\'addToCart\']")).click();
		}
		System.out.println("Success Aloe Add To Cart");

	}

	public static void buyAlmond(List<WebElement> Almond) {

		WebElement leastExpensiveAlmond = null;
		double almondPrice = 0.0;
		double leastAlmondExpensivePrice = Double.MAX_VALUE;
		for (WebElement AlmondElement : Almond) {
			almondPrice = Double.parseDouble(AlmondElement.getText().replaceAll("[^\\d.]", ""));
			if (almondPrice < 1) {
				almondPrice = almondPrice * 1000;
			}
			// Compare prices and update leastExpensive variables
			if (almondPrice < leastAlmondExpensivePrice) {
				leastAlmondExpensivePrice = almondPrice;
				leastExpensiveAlmond = AlmondElement;
			}
		}

		if (leastExpensiveAlmond != null) {
			leastExpensiveAlmond.findElement(By.cssSelector("[onclick*=\'addToCart\']")).click();
		}
		System.out.println("Success Almond Add To Cart");

	}

	public static void buySPF50(List<WebElement> spf50) {
		WebElement leastExpensiveSPF50 = null;
		double SPF50Price = 0.0;
		double leastSPF50ExpensivePrice = Double.MAX_VALUE;
		for (WebElement SPF50Element : spf50) {
			SPF50Price = Double.parseDouble(SPF50Element.getText().replaceAll("[^\\d.]", ""));
			if (SPF50Price < 1) {
				SPF50Price = SPF50Price * 1000;
			}
			// Compare prices and update leastExpensive variables
			if (SPF50Price < leastSPF50ExpensivePrice) {
				leastSPF50ExpensivePrice = SPF50Price;
				leastExpensiveSPF50 = SPF50Element;
			}
		}

		if (leastExpensiveSPF50 != null) {
			leastExpensiveSPF50.findElement(By.cssSelector("[onclick*=\'addToCart\']")).click();
		}
		System.out.println("Success SPF-50 Add To Cart");

	}

	public static void buySPF30(List<WebElement> spf30) {
		WebElement leastExpensiveSPF30 = null;
		double SPF30Price = 0.0;
		double leastSPF30ExpensivePrice = Double.MAX_VALUE;
		for (WebElement SPF30Element : spf30) {
			SPF30Price = Double.parseDouble(SPF30Element.getText().replaceAll("[^\\d.]", ""));
			if (SPF30Price < 1) {
				SPF30Price = SPF30Price * 1000;
			}
			// Compare prices and update leastExpensive variables
			if (SPF30Price < leastSPF30ExpensivePrice) {
				leastSPF30ExpensivePrice = SPF30Price;
				leastExpensiveSPF30 = SPF30Element;
			}
		}

		if (leastExpensiveSPF30 != null) {
			leastExpensiveSPF30.findElement(By.cssSelector("[onclick*=\'addToCart\']")).click();
		}
		System.out.println("Success SPF-30 Add To Cart");

	}

	public static void verifyCheckOutpage() throws InterruptedException {
		List<WebElement> checkOutProducts = driver.findElements(By.xpath("//tbody/tr"));
		int productCounts = 0;
		for (@SuppressWarnings("unused") WebElement product : checkOutProducts) {
			productCounts++;
		}
		int expectedCartCount = 2;
		System.out.println("Number of products in the Cart=" + productCounts);
		org.testng.Assert.assertEquals(productCounts, expectedCartCount);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = "C:\\Users\\13694\\Pictures\\Selenium\\Selenium.png";
		try {
			FileHandler.copy(screenshotFile, new File(screenshotPath));
			// System.out.println("Screenshot saved: " + screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		navigateToPaymentPage();

	}

	public static void navigateToCartPage() throws InterruptedException {
		driver.findElement(By.xpath("//button[@class='thin-text nav-link']")).click();
		verifyCheckOutpage();
	}

	public static void navigateToPaymentPage() throws InterruptedException {
		driver.findElement(By.xpath("//span[normalize-space()='Pay with Card']")).click();
		driver.switchTo().frame("stripe_checkout_app");
		driver.findElement(By.id("email")).sendKeys("youssef.amr@gmail.com");
		WebElement cardNumberField = driver.findElement(By.id("card_number"));
		cardNumberField.clear();
		cardNumberField.sendKeys("4242");
		cardNumberField.sendKeys("4242");
		cardNumberField.sendKeys("4242");
		cardNumberField.sendKeys("4242");
		Thread.sleep(1000);
		WebElement dateField = driver.findElement(By.id("cc-exp"));
		dateField.clear();
		dateField.sendKeys("09");
		dateField.sendKeys("2025");
		WebElement CVC = driver.findElement(By.id("cc-csc"));
		CVC.sendKeys("123");
		driver.findElement(By.xpath("//input[@x-autocompletetype='postal-code']")).sendKeys("12345");
		driver.findElement(By.className("iconTick")).click();
		driver.switchTo().defaultContent();
		navigateToSuccessMsg();
	}

	public static void navigateToSuccessMsg() {
		String success = driver.findElement(By.xpath("//h2[contains(text(), 'PAYMENT SUCCESS')]")).getText();
		Assert.assertEquals(success, "PAYMENT SUCCESS");
		System.out.println(success);
		driver.close();
	}

}
