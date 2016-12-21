package com.appium.pom.saraapp.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import com.appium.pom.saraapp.util.ExtentManager;
import com.appium.pom.saraapp.util.SAConstants;
import com.appium.pom.saraapp.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	public WebDriver driver;
	public AndroidDriver<AndroidElement> aDriver;
	public Xls_Reader xls = new Xls_Reader(SAConstants.XLS_PATH);
		
	
	
	public void launchApp() throws InterruptedException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		File app = new File(SAConstants.APK_PATH);
		capabilities.setCapability("automationName", SAConstants.AutomationName);
		capabilities.setCapability("platformName", SAConstants.PlatformName);
		capabilities.setCapability("platformVersion", SAConstants.PlatformVersion);
		capabilities.setCapability("deviceName", SAConstants.DeviceName);
		capabilities.setCapability("app", app.getAbsolutePath());
		

		try {
			test.log(LogStatus.INFO, "Launching app ");
			driver = new AndroidDriver<AndroidElement>(new URL(SAConstants.HUB_URL), capabilities);
		   aDriver = (AndroidDriver<AndroidElement>)driver;
		} catch (MalformedURLException e) {
			test.log(LogStatus.FAIL, "Application did not launch "+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Application did not launch"+ e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
	}
	
	
	@AfterMethod
	public void quit(){
		if(rep!=null){
			rep.endTest(test);
			rep.flush();
		}
		if(driver!=null)
			driver.quit();
		
	}
	

}
