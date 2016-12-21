package com.appium.pom.saraapp.pages;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.appium.pom.saraapp.pages.base.BasePage;
import com.appium.pom.saraapp.util.ExtentManager;
import com.appium.pom.saraapp.util.SAConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ItemsinWorkPage extends BasePage{

	public ItemsinWorkPage(AndroidDriver<AndroidElement> aDriver,ExtentTest test) {
		super(aDriver, test);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath=SAConstants.Add_New_Job)
	public AndroidElement AddNewJobButton;
	
	@FindBy(xpath=SAConstants.Upload_Jobs)
	public AndroidElement Upload_Jobs;
	
	@FindBy(xpath=SAConstants.ItemsInWork)
	public AndroidElement ItemsInWork;
	
	public int checkNumberOfbuttons(int expected){
		test.log(LogStatus.INFO, "Checking if desired number of buttons are present in the page is equal to "+expected);
		int numofbuttons = aDriver.findElements(By.className("android.widget.Button")).size();
		
		if(expected == numofbuttons){
			reportPass("The required number of buttons are present in the page");
		}else{
			reportFail("The desired number of buttons are not present in the page");
		}
		return numofbuttons;
	}
	
	public boolean checkisEnabled(String Locator){
		test.log(LogStatus.INFO, "Checking if the element is enabled "+Locator);
		boolean res=aDriver.findElement(By.xpath(Locator)).isEnabled();
		if(res==true){
			test.log(LogStatus.INFO, "The element is enabled");
			
		}else{
			test.log(LogStatus.INFO, "The element is not enabled");
		}
		return res;
	}
	
	public Object ClickAddNewJob(String Locator){
		test.log(LogStatus.INFO, "Clicking on the Add New Job button");
		aDriver.findElement(By.xpath(Locator)).click();
		test.log(LogStatus.INFO, "Clicked on the button successfully");
		if(aDriver.findElements(By.xpath(SAConstants.Scan_Stamp_To_Proceed)).size()!=0){
			ScanStampToProceedPage scanstampproceedpage=new ScanStampToProceedPage(aDriver, test);
			PageFactory.initElements(new AppiumFieldDecorator(aDriver,15,TimeUnit.SECONDS),scanstampproceedpage);
			return scanstampproceedpage;
		}
		return null;
	}
	
	

}