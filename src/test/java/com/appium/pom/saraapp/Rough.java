package com.appium.pom.saraapp;

import com.appium.pom.saraapp.util.SAConstants;
import com.appium.pom.saraapp.util.Xls_Reader;



public class Rough {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testName="LoginToTheApplication_AndVerifyNavigationTo_ItemsinWork";
		Xls_Reader xls = new Xls_Reader(SAConstants.XLS_PATH);
		int rows = xls.getRowCount("TestCases");
		System.out.println(rows);
		for(int rNum=2;rNum<=rows;rNum++){
			String tcid = xls.getCellData("TestCases", "TCID", rNum);
			System.out.println(tcid);
			if(tcid.equals(testName)){
				String runMode=xls.getCellData("TestCases", "Runmode", rNum);
				System.out.println(runMode);
				
			}
		}
		System.out.println("hello");
		
	}
		

	}


