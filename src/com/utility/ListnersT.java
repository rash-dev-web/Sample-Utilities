package com.utility;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.log.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;

import com.google.inject.Inject;

public class ListnersT  implements ITestListener
{
	//@Inject
	public WebDriver driver;
	public String testname;
	String s;
	
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		logs("test" + arg0.getName() + " passed");
		logs(arg0.getName());
	}
	
	@BeforeSuite
	@Override
	public void onStart(ITestContext arg0) {
		logs("Test case" + arg0.getName() + " passed");
		logs(arg0.getName());
		/*System.out.println("test case name" +arg0.getName());
		 s = arg0.getName();
		Logg.setRunTimeLogFile(s);*/
		org.apache.log4j.Logger l = Logg.createLogger();
		testname=arg0.getName();
		System.out.println("onstart listener" + testname);
		arg0.setAttribute("WebDriver",this.driver);
		logs(arg0.getName());
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) 
{
		// TODO Auto-generated method stub
		
		logs("test" + arg0.getName() + " failure");
		if(ITestResult.FAILURE==arg0.getStatus()){

			try{
				System.out.println("test case failure");
				Object cc = arg0.getInstance();
			driver = ((Util)cc).getDriver();

		TakesScreenshot as = (TakesScreenshot)driver;
		File f = as.getScreenshotAs(OutputType.FILE);
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmmss");
		LocalDateTime currentTime = LocalDateTime.now();
		String dateTime = dtFormatter.format(currentTime);
	//	long millis=System.currentTimeMillis();
		String testname=arg0.getName();
		String screencapture_failure= testname+"_"+dateTime ;
		File des= new File(System.getProperty("user.dir")+"/ScreenShotsFailure/"+screencapture_failure+".png");
		
			FileUtils.copyFile(f, des);
		} catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	
			}
		System.out.println("screen capteure for failure");
}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		logs("test" + arg0.getName() + " skipped");
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method 
		//
		System.out.println("test case name" +arg0.getName());
		s = arg0.getName();
		Logg.setRunTimeLogFile(s);
		logs("test Started");
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		logs("test" + arg0.getName() + " passed");
		logs(arg0.getTestClass());
	}
	
	private void logs(IClass iClass)
	{
		System.out.println(iClass.getTestName() + " test case name");
		//System.out.println(iClass);
		System.out.println(iClass.getName() + " Class Name ");
	}
	private void logs(String mName)
	{
		System.out.println(mName);
	}
	
}
