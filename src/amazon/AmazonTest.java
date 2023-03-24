package amazon;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

import java.util.List;

public class AmazonTest {

	public static void main(String[] args) throws InterruptedException {
		
		String shoes ="Sparx Shoes";
		String Size =" 6 UK ";

		// Set the path to the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\Downloads\\chromedriver\\chromedriver.exe");
		
		//removing the forbidden 403 network connectivity error
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);

		// Launch the Chrome browser
		WebDriver driver = new ChromeDriver(options);
		System.out.println("***Chrome Browser is initiated***");


		driver.manage().window().maximize();
		// Navigate to https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		System.out.println("***Amazon Webpage is opened***");
		

		//find the search box and find the element locator
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(shoes);
		System.out.println("***send shoes for the search box***");

		driver.findElement(By.id("twotabsearchtextbox")).submit();
		System.out.println("***searching for shoes***");
		  WebElement minPrice = driver.findElement(By.xpath("//input[@name='low-price']"));
	        minPrice.sendKeys("500");
	        WebElement maxPrice = driver.findElement(By.xpath("//input[@name='high-price']"));
	        maxPrice.sendKeys("1500");
	        WebElement goButton = driver.findElement(By.xpath("//input[@class='a-button-input' and @type='submit']"));
	        goButton.click();
		
		//creating a list of web elements and selecting the 1st one by index
		List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class,'tall-aspect')]"));
		items.get(0).click();
		System.out.println("***listing items***");
		System.out.println("***selected first element by index***");
  
		
		//using window handles to switch from current tab to second tab
		String currentHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();

		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {    //it should not be equal to the current tab 
				driver.switchTo().window(actual);             //switched driver to the next tab
			}
		}
		System.out.println("***switched tab***");
		

		//to add size of shows scrolldown to window by using java script
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,500)");
		System.out.println("***scrolled down ***");
		
		
		
		//selecting the size of shoes from the filter
		Select select = new Select(driver.findElement(By.name("dropdown_selected_size_name")));
		select.selectByVisibleText(Size); 
		
		String[] verifyingshoes = driver.findElement(By.xpath("//span[@id='productTitle']")).getText().split(" ");
		String[] usergivenshoes=shoes.split(" ");
		for (String actual : verifyingshoes) {
			if(actual.equalsIgnoreCase(usergivenshoes[0])) {
				System.out.println("***verified that selected shoes is the same***");

				
				driver.findElement(By.id("submit.add-to-cart")).click();
				System.out.println("***add to cart button is clicked***");

				//compete the process by clicking on proceed to retail checkout button
				driver.findElement(By.name("proceedToRetailCheckout")).click();
				System.out.println("***add item checked button is clicked***");
				
		        //giving the login credentials 
				driver.findElement(By.id("ap_email")).sendKeys("6307718162");
				driver.findElement(By.id("continue")).click();
				driver.findElement(By.id("ap_password")).sendKeys("Wipro");
				driver.findElement(By.id("signInSubmit")).click();
				System.out.println("***Login completed successfully***");
				Thread.sleep(2);

				//clicking on adding a new address button
				driver.findElement(By.id("add-new-address-popover-link")).click();
		 
				System.out.println("***New addresss button is clicking and personal details pop up opens***");
				
				//filing all the personal details
				WebElement fullname = driver.findElement(By.id("address-ui-widgets-enterAddressFullName"));
				fullname.sendKeys("Shubham");

				WebElement mobilenumber = driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber"));
				mobilenumber.sendKeys("6307718162");

				WebElement pincode = driver.findElement(By.id("address-ui-widgets-enterAddressPostalCode"));
				pincode.sendKeys("208017");

				WebElement flatno = driver.findElement(By.id("address-ui-widgets-enterAddressLine1"));
				flatno.sendKeys("i-39,AwasVikas, Keshavpuram ,Kalyanpur , Kanpur");
				
				WebElement street = driver.findElement(By.id("address-ui-widgets-enterAddressLine2"));
				street.sendKeys("keshavgarh");

				WebElement landmark = driver.findElement(By.id("address-ui-widgets-landmark"));
				landmark.sendKeys("IT park");


				System.out.println("***Personal Detials and address filled succesfully***");
				Thread.sleep(2);
				WebElement usethisaddress = driver
						.findElement(By.xpath("//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']"));
				usethisaddress.click();
				break;
				
			}			
		}
}
}
