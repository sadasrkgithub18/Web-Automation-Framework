package com.nagarro.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.nagarro.util.ScreenShotUtility;


public class TestListenerTC implements ITestListener
{

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("onTestStart method");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("onTestSuccess metohd");
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) 
	{
		System.out.println("TC got failed:"+arg0.getName());
		try 
		{
			ScreenShotUtility.screenShot(arg0.getMethod().getMethodName());
			//ScreenShotUtility.screenShot(arg0.getName());
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

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub
		
	}

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

}
