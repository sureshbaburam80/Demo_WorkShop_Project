package com.WebShop.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuildYourOwnComputerPagePOM {
	public WebDriver driver;

	@FindBy(id = "product_attribute_74_5_26_82") // Select Processor Radio button
	WebElement processorOption;

	@FindBy(id = "product_attribute_74_6_27_85") // Select RAM (8GB - radio button)
	WebElement ramOption;

	@FindBy(id = "product_attribute_74_3_28_87") // Select HDD (400 GB - radio button)
	WebElement hddOption;

	@FindBy(id = "product_attribute_74_8_29_90") // Select softwareOption
	WebElement softwareOption;

	@FindBy(id = "addtocart_74_EnteredQuantity") // Select softwareOption
	WebElement Qty;

	public void setQuantity(int quantity) {
		// Clear any existing text in the field
		Qty.clear();
		// Convert the integer to a string and send it to the quantity input field
		Qty.sendKeys(String.valueOf(quantity));
	}

	@FindBy(id = "add-to-cart-button-74") // Add to cart button
	WebElement addToCartButton;

	public BuildYourOwnComputerPagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method to select processor
	public void selectProcessor() {
		if (!processorOption.isSelected()) {
			processorOption.click();
		}
	}

	// Method to select RAM
	public void selectRAM() {
		if (!ramOption.isSelected()) {
			ramOption.click();
		}
	}

	// Method to select HDD
	public void selectHDD() {
		if (!hddOption.isSelected()) {
			hddOption.click();
		}
	}

	// Method to select software
	public void selectSoftware() {
		if (!softwareOption.isSelected()) {
			softwareOption.click();
		}
	}

	// Method to add the computer to the cart
	public void addToCart() {
		addToCartButton.click();
	}

}
