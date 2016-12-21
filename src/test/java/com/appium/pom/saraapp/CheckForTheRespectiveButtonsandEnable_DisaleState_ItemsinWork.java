package com.appium.pom.saraapp;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.appium.pom.saraapp.base.BaseTest;
import com.appium.pom.saraapp.pages.ItemsinWorkPage;
import com.appium.pom.saraapp.pages.LoginPage;
import com.appium.pom.saraapp.pages.ScanStampToProceedPage;
import com.appium.pom.saraapp.pages.base.BasePage;
import com.appium.pom.saraapp.util.DataUtil;
import com.appium.pom.saraapp.util.SAConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CheckForTheRespectiveButtonsandEnable_DisaleState_ItemsinWork extends BaseTest{
	
	String testName="CheckForTheRespectiveButtonsandEnable_DisaleState_ItemsinWork";
	

	@Test(dataProvider="getData")
	public void CheckForTheRespectiveButtonsandEnable_DisaleState_ItemsinWork(Hashtable<String,String> data) throws InterruptedException{
		test=rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test "+testName);
		
		if(!DataUtil.isExecutable(xls, testName)){
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
		
		test.log(LogStatus.INFO, "Check if 2 buttons are present in the ItemsINWork Page");
		itemsInworkpage.checkNumberOfbuttons(2);
		
		test.log(LogStatus.INFO, "Check if the required button text are as expected");
		itemsInworkpage.verifyText(SAConstants.Add_New_Job, "Add New Job");
		itemsInworkpage.verifyText(SAConstants.Upload_Jobs, "Upload Jobs");
		
		test.log(LogStatus.INFO, "Check if UploadJobs button is disabled when accessing the test 1st time");
		Assert.assertFalse(itemsInworkpage.checkisEnabled(SAConstants.Upload_Jobs));
		
		
		test.log(LogStatus.INFO, "Check if AddNewJob button is enabled when accessing the test 1st time");
		Assert.assertTrue(itemsInworkpage.checkisEnabled(SAConstants.Add_New_Job));
		
		test.log(LogStatus.INFO, "Clicking on AddNewJobs button");
		Object respage=itemsInworkpage.ClickAddNewJob(SAConstants.Add_New_Job);
		if(respage==null){
			loginpage.reportFail("Not able navigate to Scan a Stamp To Proceed page");
		}
		
		if(!(respage instanceof ScanStampToProceedPage)){
	    	loginpage.reportFail("User not navigated to Scan a Stamp To Proceed page");
	    }
		
        test.log(LogStatus.PASS, " Reached Scan a Stamp To Proceed page");
		
        ScanStampToProceedPage scanstamppreceedpage = new ScanStampToProceedPage(aDriver, test);
		
		
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName, xls);
	}

}
