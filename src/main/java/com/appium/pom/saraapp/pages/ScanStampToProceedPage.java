package com.appium.pom.saraapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import com.appium.pom.saraapp.pages.base.BasePage;
import com.appium.pom.saraapp.util.SAConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ScanStampToProceedPage extends BasePage{

	public ScanStampToProceedPage(AndroidDriver<AndroidElement> aDriver,ExtentTest test) {
		super(aDriver, test);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath=SAConstants.ScanStamp)
	public AndroidElement ScanStamp;
	
	public void clickScanStamp(){
		test.log(LogStatus.INFO, "Got into the click SCANSTAMP button");
		boolean Result=isElementPresent(SAConstants.ScanStamp);
		if(Result==true){
			reportPass("The elemnt is found successfully");
			aDriver.findElement(By.xpath(SAConstants.ScanStamp)).click();
			reportPass("Clicked the Scan Stamp button successfully");
		}else{
			reportFail("The element is not found on the page");
		}
	}

}
