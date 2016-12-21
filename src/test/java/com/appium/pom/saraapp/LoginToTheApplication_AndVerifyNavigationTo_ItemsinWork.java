package com.appium.pom.saraapp;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appium.pom.saraapp.base.BaseTest;
import com.appium.pom.saraapp.pages.ItemsinWorkPage;
import com.appium.pom.saraapp.pages.LoginPage;
import com.appium.pom.saraapp.util.DataUtil;
import com.appium.pom.saraapp.util.ExtentManager;
import com.appium.pom.saraapp.util.SAConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginToTheApplication_AndVerifyNavigationTo_ItemsinWork extends BaseTest{
	
	String testName="LoginToTheApplication_AndVerifyNavigationTo_ItemsinWork";
	 
	@Test(dataProvider="getData")
	public void LoginToTheApplication_AndVerifyNavigationTo_ItemsinWork(Hashtable<String,String> data) throws InterruptedException{
	test = rep.startTest("LoginToTheApplication_AndPerformSomeTask");
	test.log(LogStatus.INFO, "Starting the test");
	
	
	//This is to check the runmode of the testcase
	if(!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")){
		test.log(LogStatus.SKIP, "Skipping the test as the runmode is set to No");
	    throw new SkipException("Skipping the test as the runmode of the test is set to No");
     }
	launchApp();
	
	    aDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "App Lunched successfully");
		
		LoginPage loginpage=new LoginPage(aDriver,test);
		PageFactory.initElements(new AppiumFieldDecorator(driver,15,TimeUnit.SECONDS),loginpage); //This line will initialize all the elements of LoginPage class
		
		if(!loginpage.isElementPresent(SAConstants.LoginText)){
			loginpage.reportFail("Not able to find the Login Page.");
		}
		String Username=data.get("UserName");
		String Password=data.get("Password");
	    Object resultpage=loginpage.LoginToApp(Username,Password);
		
	    if(resultpage==null){
	    	loginpage.reportFail("Not able to login successfully.");
	    }
	    
	    if(!(resultpage instanceof ItemsinWorkPage)){
	    	loginpage.reportFail("User not navigated to the ItemsinWorkPage.");
	    }
	    
		test.log(LogStatus.PASS, "Reached ItemsinWorkPage");
		
		ItemsinWorkPage itemsInworkpage = (ItemsinWorkPage)resultpage;
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName, xls);
	}
	
	

}
