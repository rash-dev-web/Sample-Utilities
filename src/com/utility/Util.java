package com.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.excelutils.ReadingExcel;
import com.google.common.base.Function;
import com.otherutil.DataSet;


public class Util {
	
protected   WebDriver driver;
	//public WebDriverEventListener e;
private  static WebDriverWait wait;



	public void SelectBrowser(String browsername)
	{
		if(browsername.equalsIgnoreCase("ie") || browsername.equalsIgnoreCase("internet explorer"))
			
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/DriverFile/IEDriverServer.exe");
			//System.setProperty("webdriver.ie.driver","D:\\Ak105497\\ATT\\D2E_Tools\\All_D2E_setup\\LIVE\\iAF-Selenium-v3\\webdrivers\\IEDriverServer.exe");
			/*DesiredCapabilities d = DesiredCapabilities.internetExplorer();
			d.setBrowserName(browsername);
			d.setPlatform(Platform.WINDOWS);
			d.setCapability(InternetExplorerDriver., value);*/
			
		    driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		else if(browsername.equalsIgnoreCase("firefox") || browsername.equalsIgnoreCase("ff"))
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
	else
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/DriverFile/IEDriverServer.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			
		}
	//	return driver;
	}
	
	
   public  void screencapture(String testname) throws IOException
   {
	  try
	   {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmmss");
		LocalDateTime currentTime = LocalDateTime.now();
		String dateTime = dtFormatter.format(currentTime);
		String screencapture= testname+"_"+dateTime;
		System.out.println(screencapture);
		File des= new File(System.getProperty("user.dir")+"/ScreenShot/"+screencapture+".png");
		FileUtils.copyFile(source,des);
	   }
	  catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   
   }
   
   public void windowAlertHandle(){
	   try {
		Robot r =new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   }
    public static   WebDriverWait waitForElement(WebDriver driver)
    {
    	try
    	{
    		wait = new WebDriverWait(driver,180);
    	
    	}
    	catch(NoSuchElementException e)
    	{
    		e.printStackTrace();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return wait;
    }
    
   public  void waits(long millisec)
   {
    	try
    	{
    	Thread.sleep(millisec);
    	}
    	catch(InterruptedException e)
    	{
    		e.printStackTrace();
    	}
   }
    public void launchUrl(String url)
    {
    	driver.get(url);
    	//e.afterNavigateTo(url, driver);
    }
//    
  
    public void modelDialogue()
   {
    	Actions ac = new Actions(driver);
   }
   
   public WebDriver switchToFrame(WebDriver driver,String name){
	   try
	   {
		   
			driver.switchTo().frame(name);
	  
	   }
	   catch(NoSuchFrameException e){
		   e.printStackTrace();
	   }
	   catch(Exception w){
		  w.printStackTrace(); 
	   }
	return driver;
   }
//    
   public void switchToWindow(WebDriver driver,String name){
	   try
	   {
		   
			driver.switchTo().window(name);
	  
	   }
	   catch(NoSuchFrameException e){
		   e.printStackTrace();
	   }
	   catch(Exception w){
		  w.printStackTrace(); 
	   }
   }

    public Alert switchToAlert() 
	{ 
    	Alert al = null;
	    try 
	    { 
	       al  =  driver.switchTo().alert(); 
	        
	    }   // try 
	    catch (Exception Ex) 
	    { 
	       Ex.printStackTrace();
	    }   // catch
	    return al; 
	}   // isAlertPrese
//    public static Object[][] getExcelData(String sheetname)
//	{
//		Object[][] data = getData(sheetname);
//		
//		return data;
//		
//	}

	public WebDriver getDriver()
	{
		// TODO Auto-generated method stub
		return driver;
	}

	public int getFrame(By name)
	{
		int i;
		java.util.List<WebElement> al = driver.findElements(By.tagName("frame"));
		 System.out.println(al.size());
		 for( i=0;i<al.size();i++){
			 driver.switchTo().frame(i);
			List<WebElement> bl = driver.findElements(name);
			for(WebElement sl : bl){
			 if(bl.size()>0){
			break;	 
		}
	}
}
		return i;
	}
	
	
	public String getMainWindowHandle(WebDriver driver)
	{
		return driver.getWindowHandle();
	}


	public  String getCurrentWindowTitle() 
	{
		String windowTitle = driver.getTitle();
		return windowTitle;
	}
	

	public  String  closeAllOtherWindows(WebDriver driver)
	{
		String currentWindowHandle = getMainWindowHandle(driver);
		System.out.println("current window id:"+currentWindowHandle);
		Set<String> allWindowHandles = getMultipleWindow(driver);
		System.out.println("the total window size"+ allWindowHandles.size());
		for (String s : allWindowHandles) {
			//String s= allWindowHandles;
			if (!(currentWindowHandle.equals(s))) {
				driver.switchTo().window(s);
				System.out.println("switch to child window" + s);
				/*String url = driver.getCurrentUrl();
				System.out.println("the url of switch window" + url);
				driver.navigate().to(url);
				waits(10000);
				System.out.println("the url is navigating");*/
				driver.close();
				System.out.println(s + "child window close");
			}
			System.out.println("the id of window"+ s);
		}
	/*	;
		driver.switchTo().window(openWindowHandle);
		
		System.out.println("switch to parent window"+openWindowHandle);*/
		/*if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;*/
		System.out.println("id of current window: " +currentWindowHandle);
		waits(3000);
		switchToWindow(driver, currentWindowHandle);
		switchToFrame(driver,"VISIFRAME");
		
		return currentWindowHandle;
	}
	
	
	
	public  Set<String> getMultipleWindow(WebDriver driver)
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
		
		System.out.println("the total window size"+allWindowHandles.size());
		
		return allWindowHandles;
		
	}
	
	
	public void fullPageScreenShot(String testname){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,0)");
		Boolean b = (Boolean)js.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight");
		Long ch = (Long)js.executeScript("return document.documentElement.clientHeight");
		System.out.println(ch);
		Long sh = (Long)js.executeScript("return document.documentElement.scrollHeight");
		System.out.println(sh);
		int i=0;
		if(b.booleanValue())
		{
			
			while(sh.intValue()>0){
				try 
				{
					screencapture(testname);
					js.executeScript("window.scrollTo(0,"+ch*i+")");
					
				} catch (IOException e) 
				
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				i++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sh=sh-ch;
				//break;
			}
			
			
		
		
		}
		else{
			try {
				screencapture(testname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	
	public void fWait(WebDriver driver,final WebElement e,final String inp)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				

			       .withTimeout(60,TimeUnit.SECONDS)

			       .pollingEvery(2,TimeUnit.SECONDS)

			       .ignoring(NoSuchElementException.class);

		
		
			   WebElement foo = wait.until(new Function<WebDriver, WebElement>() {

			     public WebElement apply(WebDriver arg0) {
			    	 
			    	 WebElement ele = e;
			    	 if (ele.getAttribute("innerHTML").equalsIgnoreCase(inp)) 
	                 {

	                     System.out.println("Value is >>> " + ele.getAttribute("innerHTML"));

	                     return ele;

	                }
			    	 else {
			    	        System.out.println("Value is >>> " + ele.getAttribute("innerHTML"));

			    	           return null;

			    	       }

			    	               }

			    	          });		   
			       //return driver.findElement(By.id("foo"));
		}
	
	
	
	//dynamic handling of data
	public static String[] datepickformat(String dates){  ///date should be in "04/26/2017"; format
		
		
		//String dates = "04/26/2017";
		
		@SuppressWarnings("deprecation")
		Date d = new Date(dates);
		SimpleDateFormat sd = new SimpleDateFormat("MMMM/dd/YYYY");
		String datesd = sd.format(d);
		System.out.println(datesd);
		String[] splits = datesd.split("/");
		return splits;		
	}
	
	public static void dynamicCalender(WebDriver driver,String dates){
	 String[] seldate =datepickformat(dates);
	 String months= seldate[0];
	 String year= seldate[2];
	 String day= seldate[1];
	
	Outer:
		while(true){
			String month=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[1]")).getText();
			String years=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[2]")).getText();
			System.out.println(month);
			System.out.println(years);
			
			if(months.equals(month) && year.equals(years)){
				
				WebElement wel = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody"));
				List<WebElement> s= wel.findElements(By.tagName("tr"));
				
				Iterator<WebElement> it = s.iterator();
				while(it.hasNext())
				{
					List<WebElement> sr= wel.findElements(By.tagName("td"));
					
					Iterator<WebElement> iw = sr.iterator();
					
					while(iw.hasNext())
					{
						String cvalue= iw.next().getText();
				//		iw.next().
					
						//System.out.println(cvalue);
						
						if(cvalue.equals(day))
						{
							iw.next().click();
							break Outer;
						}
						
					}
					//
					
				}
				
				
		}
			else
			{
				driver.findElement(By.xpath("//a[@class='ui-datepicker-next ui-corner-all' and @title='Next']")).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 

			}
			
		}
	
	
	
			
	}
	
	public static String verifyPageHeader(WebElement ele,WebDriver driver){
		waitForElement(driver).until(ExpectedConditions.visibilityOf(ele));
		String str= ele.getText();
		return str;
	}

	}
	
