package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){
		try{
			prop=new Properties();
			FileInputStream fs=new FileInputStream("S:\\workspace\\MavenProject\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(fs);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static void initialization(){
		String browser =prop.getProperty("browser");
		if(browser.equals("chrome")){
			ChromeOptions options=new ChromeOptions();
			Map<String, Object> prefs=new HashMap<String,Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			//1-Allow, 2-Block, 0-default
			options.setExperimentalOption("prefs",prefs);
			System.setProperty("webdriver.chrome.driver","S:\\Softwares\\selenium\\chromedriver.exe");
			driver = new ChromeDriver(options);			
		}
		else if(browser.equals("FF")){
			FirefoxProfile profile = new FirefoxProfile();
	        profile.setPreference("permissions.default.desktop-notification", 1);
	        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
	        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
	        System.setProperty("webdriver.gecko.driver","S:\\Softwares\\selenium\\geckodriver.exe");
	        driver = new FirefoxDriver(capabilities);			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	
}
