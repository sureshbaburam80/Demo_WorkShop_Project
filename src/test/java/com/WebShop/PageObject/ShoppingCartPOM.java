package com.WebShop.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class ShoppingCartPOM {
	private WebDriver driver;

	// Define the WebElement using Page Factory
	@FindBy(xpath = "//span[text()='Shopping cart']")
	private WebElement shoppingCartLink;

	@FindBy(css = "input[value='Go to cart']")
	private WebElement goToCartButton;

	@FindBy(id = "CountryId")
	private WebElement countryDropdown;

	@FindBy(id = "ZipPostalCode")
	private WebElement zipCodeField;

	public void zipCodeField(int zipcode) {
		zipCodeField.clear();
		zipCodeField.sendKeys(String.valueOf(zipcode));
	}

	@FindBy(id = "termsofservice")
	private WebElement termsCheckbox;

	public void agreetermsCheckbox() {
		if (!termsCheckbox.isSelected()) {
			termsCheckbox.click();
		}
	}

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	public void CheckoutButton() {
		checkoutButton.click();
	}

	public ShoppingCartPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize the elements
	}

	// Method to scroll to the shopping cart link and hover over it
	public void hoverOverShoppingCart() {
		// Scroll into view
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", shoppingCartLink);
		// Initialize Actions class for mouse hover
		Actions actions = new Actions(driver);
		actions.moveToElement(shoppingCartLink).perform();

	}

	public void goToCartButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait
		wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
		goToCartButton.click();

	}

	// Method to select a country from the dropdown
	public void selectCountry() {
		Select select = new Select(countryDropdown);
		select.selectByVisibleText("India");
	}

}
