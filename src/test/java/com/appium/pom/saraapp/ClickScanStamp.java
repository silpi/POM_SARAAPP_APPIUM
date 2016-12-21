package com.appium.pom.saraapp;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appium.pom.saraapp.base.BaseTest;
import com.appium.pom.saraapp.pages.ItemsinWorkPage;
import com.appium.pom.saraapp.pages.LoginPage;
import com.appium.pom.saraapp.pages.ScanStampToProceedPage;
import com.appium.pom.saraapp.util.DataUtil;
import com.appium.pom.saraapp.util.SAConstants;
import com.relevantcodes.extentreports.LogStatus;

public class ClickScanStamp extends BaseTest {

	String testName="ClickScanStamp";
	@Test(dataProvider="getData")
	public void ClickScanStamp(Hashtable<String,String> data) throws InterruptedException{
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
		PageFactory.initElements(new AppiumFieldDecorator(driver,15,TimeUnit.SECONDS),loginpage);
		
		String Username=data.get("UserName");
		String Password=data.get("Password");
	    Object resultpage=loginpage.LoginToApp(Username,Password);
        test.log(LogStatus.PASS, "Reached ItemsinWorkPage");
        
		ItemsinWorkPage itemsInworkpage = (ItemsinWorkPage)resultpage;
		
		Object respage=itemsInworkpage.ClickAddNewJob(SAConstants.Add_New_Job);
        test.log(LogStatus.PASS, " Reached Scan a Stamp To Proceed page");
		
        ScanStampToProceedPage scanstamppreceedpage = (ScanStampToProceedPage)respage;
       
        scanstamppreceedpage.clickScanStamp();
        test.log(LogStatus.PASS, "Test completed successfully");
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName, xls);
	}
	
}