package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	//Page factory to be created
	@FindBy(id="email")
	WebElement username;
	@FindBy(id="pass")
	WebElement password;
	@FindBy(xpath="//input[@type='submit']")
	WebElement submit;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);		
	}
	//Actions will be starting from here
	public String validateLoginTitle(){
		return driver.getTitle();
	}
	
	public HomePage login(String uname,String paswd){
		username.sendKeys(uname);
		password.sendKeys(paswd);
		submit.click();
		
		return new HomePage();
	}
	

}
