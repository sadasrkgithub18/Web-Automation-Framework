package com.nagarro.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.nagarro.tests.BaseEngine;


public interface ScreenShotUtility 
{
	public static String screenShot()  
    {
    	String loc = BaseEngine.getCurDir()+"\\ScreenShots\\"+BaseEngine.getTcName()+".png";
    	TakesScreenshot takesscreenshot = (TakesScreenshot) BaseEngine.getDriver(); // Using WebDriver driver
    	File file = takesscreenshot.getScreenshotAs(OutputType.FILE);
		try 
		{
			FileUtils.copyFile(file, new File(BaseEngine.getCurDir()+"\\ScreenShots\\"+BaseEngine.getTcName()+".png"));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loc;
    }
	
	
	
	
	public static String screenShot(String ScreenShots) throws IOException 
    {
    	String loc = BaseEngine.getCurDir()+"\\ScreenShots\\"+BaseEngine.getTcName()+".png";
    	TakesScreenshot takesscreenshot = (TakesScreenshot) BaseEngine.getDriver(); // Using WebDriver driver   
    	File file = takesscreenshot.getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(file, new File(BaseEngine.getCurDir()+"\\ScreenShots\\"+BaseEngine.getTcName()+".png"));
		return loc;
    }
	
	
}
