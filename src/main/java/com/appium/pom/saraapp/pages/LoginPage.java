package com.appium.pom.saraapp.pages;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;




import org.openqa.selenium.support.PageFactory;

import com.appium.pom.saraapp.pages.base.BasePage;
import com.appium.pom.saraapp.util.SAConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;






public class LoginPage extends BasePage {
	WebDriver driver;
	
	@FindBy(xpath=SAConstants.LoginText)
	public AndroidElement LoginText;
	
	@FindBy(id=SAConstants.UserNameTextField)
	public AndroidElement UserNameTextField;
	
	@FindBy(id=SAConstants.PasswordTextField)
	public AndroidElement PasswordTextField;
	
	@FindBy(id=SAConstants.LoginButton)
	public AndroidElement LoginButton;
	
	
	
	
	
	 public LoginPage(AndroidDriver<AndroidElement> aDriver, ExtentTest test){
		super(aDriver,test);
	 }
	 
	
	public Object LoginToApp(String UserName, String Password){
		test.log(LogStatus.INFO, "Logging in to the appliction");
		UserNameTextField.sendKeys(UserName);
		PasswordTextField.sendKeys(Password);
		//aDriver.pressKeyCode(4); //This is to press the back button in appium
		LoginButton.click();
		test.log(LogStatus.INFO, "Logged in to the application successfully");
		
		if(aDriver.findElements(By.xpath(SAConstants.ItemsInWork)).size()!=0){
			ItemsinWorkPage itemsInworkpage = new ItemsinWorkPage(aDriver, test);
			PageFactory.initElements(new AppiumFieldDecorator(aDriver,15,TimeUnit.SECONDS),itemsInworkpage);
			return itemsInworkpage;
			
		}
		
		return null;
	}
	

}
