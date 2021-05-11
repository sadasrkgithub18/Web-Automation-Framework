package com.nagarro.webpages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.nagarro.page.BasePage;

public class SelectablePage extends BasePage 
{

	public SelectablePage(WebDriver driver) 
	{
		super(driver);
	}

	
	// Page Locators
	
	private By item1 = By.xpath("//li[contains(text(),'Item 1')]");
	
	private By item3 = By.xpath("//li[contains(text(),'Item 3')]");

	private By item7 = By.xpath("//li[contains(text(),'Item 7')]");

	private By frameName  = By.xpath("//iframe[@class='demo-frame']");
	
	
	public WebElement getItem1() 
	{
		return getElement(item1);
	}

	public WebElement getItem3() 
	{
		return getElement(item3);
	}

	public WebElement getItem7() 
	{
		return getElement(item7);
	}

	public WebElement getFrameName() 
	{
		return getElement(frameName);
	}

	
	
	// page actions
	public String getSelectablePageTitle()
	{
			return getPageTitle();	
	}
		
	
	public List<String> selectTheItems() throws AWTException, InterruptedException
	{
		
		frameHandling(getFrameName());
		
		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		
		Thread.sleep(3000);
		
		getItem1().click();
		
		String item1Txt = getItem1().getText();
		
		getItem3().click();
		
		String item3Txt = getItem3().getText();
		
		
		getItem7().click();
		
		String item7Txt = getItem7().getText();
		
		List<String> list = new ArrayList<String>();
		
		list.add(item1Txt);
		list.add(item3Txt);
		list.add(item7Txt);
		
		return list;
	}
	
}
