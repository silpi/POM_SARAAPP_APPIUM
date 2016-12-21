package com.appium.pom.saraapp;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.appium.pom.saraapp.base.BaseTest;
import com.appium.pom.saraapp.util.DataUtil;
import com.appium.pom.saraapp.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RecouncilationOfDamagedStamp extends BaseTest{
	String TestName= "RecouncilationOfDamagedStamp";
	
	@Test
	public void RecouncilationOfDamagedStamp(){
		test=rep.startTest("RecouncilationOfDamagedStamp");
		test.log(LogStatus.INFO, "Starting the test RecouncilationOfDamagedStamp");
		
		if(!DataUtil.isExecutable(xls, TestName)){
			test.log(LogStatus.SKIP, "Skipping the test as the runmode is set to No");
		    throw new SkipException("Skipping the test as the runmode of the test is set to No");
         }
	
	test.log(LogStatus.PASS, "Test Passed");
  }
	
}
