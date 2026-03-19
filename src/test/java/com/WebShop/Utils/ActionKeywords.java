package com.WebShop.Utils;

import org.openqa.selenium.WebDriver;

import com.WebShop.PageObject.LoginPagePOM;

public class ActionKeywords {
	
	static WebDriver driver;
	//static LoginPagePOM login = new LoginPagePOM(driver);
	static LoginPagePOM login = new LoginPagePOM(driver);
	public static void validusername_pwd() {
		//LoginPagePOM login = new LoginPagePOM(driver);
		login.setEmail("sureshbabu7780@gmail.com");
		login.setPassword("Ayyammal@1980");
		
		
	}
	public static void login() {
		login.clickLoginButton();
	}
	
}
