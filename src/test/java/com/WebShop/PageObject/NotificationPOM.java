package com.WebShop.PageObject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationPOM {

	private WebDriver driver;
	// Define the WebElement using @FindBy
	@FindBy(xpath = "//span[@title='Close']")
	private WebElement closeButton;

	@FindBy(xpath = "//span[text()='Shopping cart']")
	private WebElement shoppingCartLink;

	// Constructor
	public NotificationPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method to click the close button with explicit wait
	public void clickNotificationCloseButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait
		wait.until(ExpectedConditions.elementToBeClickable(closeButton));
		closeButton.click();

	}

	public void clickshoppingCartLinkButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
		shoppingCartLink.click();

	}
}
