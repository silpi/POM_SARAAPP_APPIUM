package com.appium.pom.saraapp.pages.base;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.appium.pom.saraapp.util.SAConstants;
 import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;



public class BasePage {
        public AndroidDriver<AndroidElement> aDriver;
		public ExtentTest test;
		

		public BasePage(AndroidDriver<AndroidElement> aDriver,ExtentTest test) {
		
			this.aDriver = aDriver;
			this.test=test;
		}
		
		
		
		public boolean isElementPresent(String locator){
			test.log(LogStatus.INFO, "Finding presence of element "+locator);
			int s=aDriver.findElements(By.xpath(locator)).size();
			if(s==0)
				return false;
			else
				return true;
		}
		
		public void verifyText(String Locator,String expectedText){
			test.log(LogStatus.INFO, "Checking text of the element "+expectedText);
			//int size=aDriver.findElements(By.name(expectedText)).size();
			AndroidElement ae = aDriver.findElement(By.xpath(Locator));
			String Text=ae.getText();
			if(Text.equals(expectedText)){
				reportPass("The expected text is matching with the actual text "+Text);
			
			}else{
				reportFail("The expected text is not matching with the actual text "+Text);
				
			}
			
			
		}
		
		/*****************************Reporting**************************/
		
		public void reportPass(String passMsg){
			 test.log(LogStatus.PASS, passMsg);
		}
		
		public void reportFail(String failureMsg){
			 takeScreenshot();
			 test.log(LogStatus.FAIL, failureMsg);
			 Assert.fail(failureMsg);
		}
		
		public void takeScreenshot(){
			// decide the file name
			Date d = new Date();
			String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
			String path=SAConstants.SCREENSHOT_PATH+screenshotFile;
			// take screenshot
			File scrFile = ((TakesScreenshot)aDriver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//add screenshot to report
			test.log(LogStatus.INFO,"Snapshot below: ("+screenshotFile+")"+
					 test.addScreenCapture(path));
		}

}
