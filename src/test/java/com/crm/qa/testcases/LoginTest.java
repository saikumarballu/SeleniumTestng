package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;

public class LoginTest extends TestBase{
	
	LoginPage loginPage;
	public LoginTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();	
		loginPage=new LoginPage();
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitle(){
		String title=loginPage.validateLoginTitle();
		Assert.assertEquals(title, "Faebook – log in or sign up");
	}
	
	@Test(priority=2)
	public void verifyLoginPage(){
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}

}
