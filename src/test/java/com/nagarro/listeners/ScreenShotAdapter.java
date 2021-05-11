package com.nagarro.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.nagarro.util.ScreenShotUtility;


public class ScreenShotAdapter extends TestListenerAdapter  
{	
	@Override
	public void onStart(ITestContext context) 
	{
		System.out.println("Suite execution started");
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		System.out.println("Suite execution completed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult arg0) 
	{
		System.out.println("TC got failed:"+arg0.getName());
		try 
		{
			ScreenShotUtility.screenShot(arg0.getName());
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) 
	{
		System.out.println("TC got skipped:"+arg0.getName());
		try 
		{
			ScreenShotUtility.screenShot(arg0.getName());
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
