package com.WebShop.Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.WebShop.PageObject.BuildYourOwnComputerPagePOM;
import com.WebShop.PageObject.LoginPagePOM;
import com.WebShop.PageObject.NotificationPOM;
import com.WebShop.PageObject.ShoppingCartPOM;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LoginTest {

	public WebDriver driver;
	private String loginUrl;
	private String email;
	private String passwd;
	private LoginPagePOM loginPage;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest

	public void setUp() throws IOException {
		final Properties prop = new Properties();
		try (FileInputStream fi = new FileInputStream(
				"C:\\Users\\user\\eclipse-workspace1\\Demo_WorkShop_Project\\CONFIG.PROPERTIES")) {
			prop.load(fi);
		}

		// Setup Extent Reports
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("User Timezone", System.getProperty("user.timezone"));
		extent.setSystemInfo("Browser", "Chrome 95.0"); // Custom info like browser version
		driver = new ChromeDriver();
		loginUrl = (prop.getProperty("login.url"));
		driver.get(loginUrl);
		loginPage = new LoginPagePOM(driver);
		System.out.println(prop.getProperty("login.url"));
		driver.manage().window().maximize();
		// Start a test case
		test = extent.createTest("Demo Web Shop Test");
	}

	@Test(description = "Login process")
	public void myTestMethod() throws IOException, InterruptedException {

		// test logic
		LoginPagePOM login = new LoginPagePOM(driver);
		// BuildYourOwnComputerPagePOM newcomputerpage=new
		// BuildYourOwnComputerPagePOM(driver);
		final Properties prop = new Properties();
		FileInputStream fi = new FileInputStream(
				"C:\\Users\\user\\eclipse-workspace1\\Demo_WorkShop_Project\\CONFIG.PROPERTIES");
		prop.load(fi);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Start a test case
		test = extent.createTest("Login validation Process");
		try {
			email = (prop.getProperty("emailField"));
			login.setEmail(email);
			passwd = (prop.getProperty("passwordField"));
			login.setPassword(passwd);
			System.out.println(prop.getProperty("emailField"));

			System.out.println(prop.getProperty("passwordField"));
			login.clickLoginButton();
			// Start a test case
			test.pass("Login successful");
		} catch (Exception e) {
			test.fail("Login failed: " + e.getMessage());
		}
	}

	@Test(priority = 2)
	public void printmenuitemcounts() throws IOException, InterruptedException {

		LoginPagePOM login = new LoginPagePOM(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		login.clickLinkButton();
		test = extent.createTest("Print each menu & Total count menu item's");
		// --------Locate the menu items from the webpage
		List<WebElement> menuItems = driver.findElements(By.cssSelector(".top-menu > li"));
		// ---------Print each menu item's text from the webpage
		for (WebElement item : menuItems) {
			System.out.println(item.getText());
			test.pass(item.getText());
		}
		// --------Print the total count of menu items
		System.out.println("Total number of menu items: " + menuItems.size());
		test.pass("Printed Total number of menu items: " + menuItems.size());

	}

	@Test(priority = 4)
	public void Imagescreenshot() throws IOException, InterruptedException
	{
		test = extent.createTest("ImageScreenshot Print Process");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("screenshots/demo_webshop_home.png");
		try {
			FileUtils.copyFile(screenshot, destinationFile);
			System.out.println("Screenshot saved successfully!");
			test.pass("Screenshot captured: " + destinationFile.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Error while saving screenshot: " + e.getMessage());
			test.fail("Error while capturing screenshot: " + e.getMessage());
		}
	}

	@Test(priority = 3)
	public void BuildYourOwnComputer() throws IOException, InterruptedException {
		BuildYourOwnComputerPagePOM newcomputerpage = new BuildYourOwnComputerPagePOM(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		test = extent.createTest("BuildYourOwnComputer Process");
		newcomputerpage.selectProcessor();
		Thread.sleep(2000);
		newcomputerpage.selectRAM();
		Thread.sleep(2000);
		newcomputerpage.selectHDD();
		Thread.sleep(2000);
		newcomputerpage.selectSoftware();
		Thread.sleep(2000);
		newcomputerpage.setQuantity(2);
		Thread.sleep(2000);
		newcomputerpage.addToCart();
		test.pass("Processor,RAM,HDD,Software,Qty details updated");
		test.pass("BuildYourOwnComputer Process done");
	}

	@Test(priority = 5)
	public void Notification() throws IOException, InterruptedException {
		NotificationPOM notificationclose = new NotificationPOM(driver);
		test = extent.createTest("Notification Pop-Up");
		try {
			notificationclose.clickNotificationCloseButton();
			test.pass("Notification Pop-Up Closed");
		} catch (Exception e) {
			System.out.println("Close button not found or not clickable: " + e.getMessage());
			test.fail("Close button not found or not clickable: " + e.getMessage());
		}

	}

	@Test(priority = 6)
	public void ShoppingCart() throws IOException, InterruptedException {
		ShoppingCartPOM shoppingCart = new ShoppingCartPOM(driver);
		test = extent.createTest("MouseHover the Shopping Cart Link, Click Cart button");
		shoppingCart.hoverOverShoppingCart();
		Thread.sleep(2000);
		shoppingCart.goToCartButton();
		test.pass("MouseHovered the Shopping Cart Link, Click done on Cart button");
		test = extent.createTest("Country,Zipcode, AgreeTerms options to enable");
		shoppingCart.selectCountry();
		shoppingCart.zipCodeField(600119);
		shoppingCart.agreetermsCheckbox();
		test.pass("Country,Zipcode, AgreeTerms options are enabled");
		Thread.sleep(2000);
		test = extent.createTest("Order checkout process");
		shoppingCart.CheckoutButton();
		WebElement ContinueButton1 = driver.findElement(By.xpath("//input[@onclick='Billing.save()']"));
		ContinueButton1.click();
		test.pass("Billing Address updated");
		Thread.sleep(2000);
		WebElement ContinueButton2 = driver.findElement(By.xpath("//input[@onclick='Shipping.save()']"));
		ContinueButton2.click();
		test.pass("Shipping Address updated");
		Thread.sleep(2000);
		WebElement ContinueButton3 = driver.findElement(By.xpath("//input[@onclick='ShippingMethod.save()']"));
		ContinueButton3.click();
		test.pass("Shipping Method details updated");
		Thread.sleep(4000);
		WebElement cashonDeliveryCheckbox = driver.findElement(By.xpath("//input[@value='Payments.CashOnDelivery']")); // Adjust
																														// needed
		if (!cashonDeliveryCheckbox.isSelected()) {
			cashonDeliveryCheckbox.click();
		}
		test.pass("Payment Method updated");
		Thread.sleep(2000);
		WebElement ContinueButton4 = driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']"));
		ContinueButton4.click();
		Thread.sleep(2000);
		WebElement ContinueButton5 = driver.findElement(By.xpath("//input[@onclick='PaymentInfo.save()']"));
		ContinueButton5.click();
		Thread.sleep(2000);
		WebElement ContinueButton6 = driver.findElement(By.xpath("//input[@onclick='ConfirmOrder.save()']"));
		ContinueButton6.click();
		Thread.sleep(2000);
		WebElement ContinueButton7 = driver.findElement(By.xpath("//input[@onclick=\"setLocation('/')\"]"));
		ContinueButton7.click();
		Thread.sleep(2000);
		test.pass("New Order checkout process done");
	}

	@AfterTest
	public void tearDown() {
		driver.close(); 
		extent.flush();
	}
}
