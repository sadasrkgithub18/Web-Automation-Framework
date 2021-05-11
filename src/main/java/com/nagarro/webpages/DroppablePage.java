package com.nagarro.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.nagarro.page.BasePage;

public class DroppablePage extends BasePage
{

	public DroppablePage(WebDriver driver) 
	{
		super(driver);
	}

	// page locators
	
	private By dragMeToTarget = By.xpath("//div[@id='draggable']");
	
	private By dropHere = By.xpath("//div[@id='droppable']");
	
	private By successMsg = By.xpath("//p[normalize-space()='Dropped!']");
	
	private By frameName  = By.xpath("//iframe[@class='demo-frame']");
	
	private By droppable = By.xpath("//a[normalize-space()='Droppable']");
	
	
	
	private By demos = By.xpath("//a[normalize-space()='Demos']");

	private By download = By.xpath("//a[normalize-space()='Download']");
	
	private By apiDocmentation = By.xpath("//a[normalize-space()='API Documentation']");
	
	private By themes = By.xpath("//a[normalize-space()='Themes']");
	
	
	
	// getters
	public WebElement getDroppable() 
	{
		return getElement(droppable);
	}

	
	public WebElement getDragMeToTarget() 
	{
		return getElement(dragMeToTarget);
	}

	public WebElement getDropHere() 
	{
		return getElement(dropHere);
	}

	public WebElement getSuccessMsg() 
	{
		return getElement(successMsg);
	}

	
	public WebElement getDemos() 
	{
		return getElement(demos);
	}

	public WebElement getDownload() 
	{
		return getElement(download);
	}

	public WebElement getApiDocmentation() 
	{
		return getElement(apiDocmentation);
	}

	public WebElement getThemes() 
	{
		return getElement(themes);
	}

	
	public WebElement getFrameName() 
	{
		return getElement(frameName);
	}


	// page actions
	public String getDroppablePageTitle()
	{
		return getPageTitle();	
	}
	
	

	public void dragAndDropTheBox(WebDriver driver)
	{
		Actions actions = new Actions(driver);
				
		frameHandling(getFrameName());
		
		actions.clickAndHold(getDragMeToTarget())
		       .moveToElement(getDropHere())
		       .release()
		       .build()
		       .perform();
	}
	
	
	public String getStatusMsg()
	{
	  return getSuccessMsg().getText();	
	}
	

}
