package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	String shtName="friends";
	HomePageTest(){
		super();
	}
	
	Logger log = Logger.getLogger(HomePageTest.class);
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();	
		loginPage=new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		
	}
	
	
	@DataProvider
	public Object[][] getFbTestData(){
		Object data[][]=TestUtil.getTestData(shtName);
		return data;
	}
	
	@Test(dataProvider="getFbTestData")
	public void verifySearchFriends(String frndName){
		log.info("Searching for Frind: "+ frndName);
		homePage.searchFriend(frndName);
	}
	
	@AfterMethod
	public void tearDown(){
		log.info("Closing the Browser");
		driver.quit();
	}
	

}
