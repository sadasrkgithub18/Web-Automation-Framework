package com.nagarro.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.nagarro.page.BasePage;
import com.nagarro.page.Page;
import com.nagarro.supporters.ExcelReader;
import com.nagarro.supporters.PropertiesReader;
import com.nagarro.util.DriverPaths;
import com.nagarro.util.FilePaths;
import com.nagarro.util.ScreenShotUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseEngine 
{

	private static WebDriver driver;
    private static String curDir;
    private static String tcName;
    private static int count = 1;
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static ExcelReader excelReader;
    private static int rowCount = 1;
    private static Page page;
    private static PropertiesReader prOr;
    
    @Parameters("brow")
	@BeforeSuite
	public void openBrowser(@org.testng.annotations.Optional("chrome")String browser)
	{
		curDir = System.getProperty("user.dir");
		
	if(driver == null)
	{	
		if(browser.equalsIgnoreCase("ie"))
		{
	      System.setProperty(DriverPaths.ieKey, DriverPaths.ieValue);
	      //System.setProperty("webdriver.ie.driver","C:\\Users\\K SADA SIVA REDDY\\eclipse-workspaceE\\FrameworksSelenium\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			enterURL();
		}
		
		else if(browser.equalsIgnoreCase("chrome"))
		{
			
			System.setProperty(DriverPaths.chromeKey, DriverPaths.chromeValue);
		//	System.setProperty("webdriver.chrome.driver","C:\\Users\\K SADA SIVA REDDY\\eclipse-workspaceE\\FrameworksSelenium\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver(); 
			enterURL();
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
		 	System.setProperty(DriverPaths.firefoxKey, DriverPaths.firefoxValue);		
		//	System.setProperty("webdriver.gecko.driver","C:\\Users\\K SADA SIVA REDDY\\eclipse-workspaceE\\FrameworksSelenium\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();	
			enterURL();
		}
	 }
		
   }
    
       
       public static void enterURL()
	   {
    	   try 
    	   {
			prOr = new PropertiesReader(FilePaths.configPath);
	       } 
    	   catch (IOException e) 
    	   {
			e.printStackTrace();
		   }
    	  
		   getDriver().get(prOr.getPropertyValue("controlGroup_url"));
		   page = new BasePage(getDriver());
		   init();
		   
	   }
    
    private static void init()
	{
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(18, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
			
	}
	
    @AfterSuite
	public void closeBroswer() 
	{
		Optional<WebDriver> optional = Optional.ofNullable(driver);
		if(optional.isPresent())
		{
			driver.quit();
			driver = null;
		}
		
		else
		{
		
			System.out.println("Driver is pointing to NULL");
		}
	}
	
    
    @BeforeMethod
	public void beforeTestcaseExecution(Method method) throws EncryptedDocumentException, InvalidFormatException, IOException  
	{
	   
	   tcName = method.getName();
	   System.out.println("Currenty execuitng TC is:"+ tcName);
	   excelReader = new ExcelReader(FilePaths.excelPath);
	   excelReader.writeData("Frame",count,0,tcName);
	   count++;
	   extentTest = extentReports.startTest(tcName);
	}
	
    
    @AfterMethod
	public void afterTestCase(ITestResult result) throws IOException, EncryptedDocumentException, InvalidFormatException
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			System.out.println(result.getName()+":Test Case got passed successfully");
			excelReader = new ExcelReader(FilePaths.excelPath);
			excelReader.writeDataTwo("Frame",rowCount,1,"PASS");
			extentTest.log(LogStatus.PASS, "TC is passed:"+result.getName());
			rowCount++;
	    }
		
		else if(result.getStatus() == ITestResult.FAILURE )
		{
			// String imagePath = ScreenShotUtility.screenShot();
			System.out.println(getTcName()+": TC got failed and taking screen shot");
			excelReader = new ExcelReader(FilePaths.excelPath);
			excelReader.writeDataTwo("Frame",rowCount,1,"FAIL");
			rowCount++;
			extentTest.log(LogStatus.FAIL,"TC is failed:"+result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(ScreenShotUtility.screenShot()));
			
		}
			
		else if(result.getStatus() == ITestResult.SKIP )
		{
			String imagePath = ScreenShotUtility.screenShot();
			System.out.println(getTcName()+": TC got skipped and taking screen shot");
			excelReader = new ExcelReader(FilePaths.excelPath);
			excelReader.writeDataTwo("Frame",rowCount,1,"SKIP");
			rowCount++;
			extentTest.log(LogStatus.SKIP, "TC is Skipped:"+result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(imagePath));			
		}
		
		extentReports.endTest(getExtentTest());
	}
	
    
    @BeforeTest
	public void initTCReport()
	{
		extentReports = new ExtentReports(curDir+"\\Reports\\Extent.html");
	}
	
	@AfterTest
	public void generateReports()
	{
		extentReports.flush();
		extentReports.close();
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	public static String getCurDir()
	{
		return curDir;
	}
	
	public static String getTcName()
	{
		return tcName;
	}
	
	public static ExtentTest getExtentTest()
	{
		return extentTest;
	}
	
	public static Page getpage()
	{
		return page;
	}

}
