package auto;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class amazon {

	public static void main(String[] args) throws Throwable {
		
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Karthik\\eclipse-workspace\\SeleniumGt\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		
//Launching browser
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		
		
//Amazon Sign-in
		WebElement Loginbtn = driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"));
		Loginbtn.click();
		WebElement email = driver.findElement(By.xpath("//input[@id='ap_email']"));
		email.sendKeys("goshyam547@gmail.com");
		WebElement cont = driver.findElement(By.xpath("//input[@id='continue']"));
		cont.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement pass = driver.findElement(By.id("ap_password"));
		pass.sendKeys("parikshit");
		WebElement signin = driver.findElement(By.id("signInSubmit"));
		signin.click();
		
		
//Entering product in the Search bar
		WebElement Search = driver.findElement(By.id("twotabsearchtextbox"));
		Search.sendKeys("Dell inspiron 15" + Keys.ENTER);
		//// By.xpath("//span[contains(text(),'Dell Inspiron 15 (3501) 15.6\" (39.62cms)FHD Displ')]"));
		WebElement clickonprod = driver.findElement(By.xpath("//span[contains(text(),'Dell Inspiron 3501 15-inch FHD')]"));
		clickonprod.click();
		
		
//Switching to child window and adding product to the cart
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
		addtocart.click();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
//Closing the install charge pop-up
		WebElement close = driver.findElement(By.xpath("//button[@class=' a-button-close a-declarative']"));
		Actions ac = new Actions(driver);
		ac.moveToElement(close).click().build().perform();
		
		
//Proceed to Checkout
		WebElement check = driver.findElement(By.xpath("//a[@id='hlb-ptc-btn-native']"));

		ac.moveToElement(check).click().build().perform();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
//Selecting the delivery addresss
		WebElement deliveryBtn = driver.findElement(By.xpath("//span[@class='a-button-inner'][1]"));

		ac.moveToElement(deliveryBtn).click().build().perform();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
//Adding quantity
		ac.moveToElement(driver.findElement(By.xpath("//span[@class='a-button a-button-dropdown a-button-span12 ']")))
				.click().build().perform();
		ac.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'2')]"))).click().build().perform();
		
		
//Final Validation of  product and price
		WebElement FinalCheck = driver.findElement(By.xpath("//p[contains(text(),'Dell Inspiron')]"));
		String Productname = FinalCheck.getText();
		WebElement finalprice = driver
				.findElement(By.xpath("//p[@class='a-text-bold a-color-price a-size-base a-spacing-micro']"));
		String price = finalprice.getText();
		if (Productname.contains("Dell")) {
			System.out.println("YOUR LAPTOP HAS BEEN ADDED TO CART AND IS FOR DELIVERY:" + Productname);
			System.out.println("THE PRICE OF YOUR PRODUCT IS:" + price);

			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			File destination = new File(
					"C:\\Users\\Karthik\\eclipse-workspace\\SeleniumGt\\Screenshot\\Item_purchased.jpeg");

			FileUtils.copyFile(source, destination);
		}

	
		// List<WebElement> AllFrames = driver.findElements(By.tagName("iframe"));
		// int framecount = AllFrames.size();
		// System.out.println("The number fof frames are:" + framecount);
		// driver.switchTo().frame(framecount);
	
		// WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.visibilityOf(Product));
		// System.out.println(Product.getText());

		// WebElement fram = driver.findElement(By.xpath("//div[@id='a-page']"));
		// driver.switchTo().frame(fram);
		// WebElement cart =
		// driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='dp']/div[@id='dp-container']/div[@id='ppd']/div[@id='rightCol']/div[@id='attachAccessoryModal_feature_div']/div[@id='attach-dss-placeholder']/div[@id='attach-desktop-sideSheet']/div[@id='attach-accessory-pane']/div[1]/div[1]/div[3]/div[1]/div[2]/div[3]/form[1]/span[1]/span[1]/input[1]"));
		// cart.click();
		// WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.visibilityOf(addtocart));
		// boolean Clickable = addtocart.isEnabled();
		// System.out.println("Element Enabled:" + Clickable);
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// TakesScreenshot ts = (TakesScreenshot)driver;
		// File source = ts.getScreenshotAs(OutputType.FILE);
		// File dest = new File("C:\\purchased.png");
		// FileUtils.copyDirectory(source, dest);

	}

}
