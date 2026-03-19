package com.WebShop.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePOM {
	public WebDriver driver;

	@FindBy(name = "Email")
	WebElement emailField;

	@FindBy(name = "Password")
	WebElement passwordField;

	@FindBy(xpath = "//input[@value='Log in']")
	WebElement loginButton;

	@FindBy(css = ".message-error")
	WebElement loginError;

	@FindBy(linkText = "Build your own expensive computer")
	WebElement expensiveComputerLink;

	// WebElement expensiveComputerLink = driver.findElement(By.linkText("Build your
	// own expensive computer"));

	// Constructor
	public LoginPagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method to set email
	public void setEmail(String email) {
		emailField.sendKeys(email);
	}

	// Method to set password
	public void setPassword(String password) {
		passwordField.sendKeys(password);
	}

	// Method to click on the login button
	public void clickLoginButton() {
		loginButton.click();
	}

	// Method to click the link button
	public void clickLinkButton() {
		expensiveComputerLink.click();
	}

	// Method to perform login
	public void login(String email, String password) {
		setEmail(email);
		setPassword(password);
		clickLoginButton();
	}

	// Method to get the login error message
	public String getLoginErrorMessage() {
		return loginError.getText();
	}
}