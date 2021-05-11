package com.nagarro.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page
{

	
	public BasePage(WebDriver driver) 
	{
		super(driver);
	}
	
	@Override
	public String getPageTitle() 
	{
		return driver.getTitle();
	}

	@Override
	public boolean getPageHeader(By locator) 
	{
		return getElement(locator).isDisplayed();
	}

	@Override
	public WebElement getElement(By locator) 
	{
		WebElement element = null;
		try 
		{
		  waitForElementPresent(locator);
		  element = driver.findElement(locator);
		  return element;
		}
		catch(Exception e)
		{
			System.out.println("Some error occured while locating element: "+locator.toString());
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public void waitForElementPresent(By locator) 
	{
		try 
		{
		  	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch(Exception e)
		{
			System.out.println("Some error occured while waiting for the element "+locator.toString());
		}
	}

	@Override
	public void waitForPageTitle(String title) 
	{
		try 
		{
		  	wait.until(ExpectedConditions.titleContains(title));
		}
		catch(Exception e)
		{
			System.out.println("Some error occured while waiting for the element "+title);
		}
	}

	public void alertHandlingAccept()
	{
		driver.switchTo().alert().accept();
	}
	
	public void alertHandlingReject()
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void alertHandlinggetText()
	{
		driver.switchTo().alert().getText();
	}
	
	public void frameHandling(WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void windowHandling(String name)
	{
		driver.switchTo().window(name);
	}
	
	public void verticalScroll()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0, 350)");
	}
	
	public void staticWait() throws InterruptedException
	{
		Thread.sleep(2000);
	}
}
