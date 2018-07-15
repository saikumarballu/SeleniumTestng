package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath="//input[@type='text' and @class='_1frb']")
	WebElement searchBox;
	
	@FindBy(xpath="//button[@type='submit' and @data-testid='facebar_search_button']")
	WebElement searchSubmit;
	
	public HomePage(){
		PageFactory.initElements(driver, this);		
	}
	
	public void searchFriend(String frndName){
		searchBox.sendKeys(frndName);
		searchSubmit.click();
	}
	
	
}
