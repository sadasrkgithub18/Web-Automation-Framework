package com.nagarro.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.nagarro.page.BasePage;

public class ControlgroupPage extends BasePage
{

	public ControlgroupPage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	// Page Locators
	
	private By carType = By.id("car-type");

	private By triangle = By.xpath("//span[@id='car-type-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']");

	private By suv = By.xpath("//div[@id='ui-id-4']");
	
	private By automaticRadioBtton = By.xpath("//label[@for='transmission-automatic']//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank']");
	
	private By insuranceCheckbox = By.xpath("//label[@for='insurance']//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank']");
	
	private By noOfCars = By.xpath("//input[@id='horizontal-spinner']");
	
	private By dropdownIconTwo = By.xpath("//span[@id='ui-id-8-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']");

	private By truck = By.xpath("//div[@id='ui-id-14']");
	
	private By standardRadioButton = By.xpath("//label[@for='transmission-standard-v']//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank']");
	
	private By insurnaceTwoCheckBox = By.xpath("//label[@for='insurance-v']//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank']");
	
	private By noOfCarsTwo = By.xpath("//input[@id='vertical-spinner']");
	
	private By bookNowButton = By.xpath("//button[@id='book']");
	
	
	private By frameName  = By.xpath("//iframe[@class='demo-frame']");

	
	public WebElement getCarType() 
	{
		return getElement(carType);
	}

	public WebElement getFrameName() 
	{
		return getElement(frameName);
	}
	

	
	public WebElement getTriangle() 
	{
		return getElement(triangle);
	}

	public WebElement getSuv() 
	{
		return getElement(suv);
	}

	public WebElement getAutomaticRadioBtton() 
	{
		return getElement(automaticRadioBtton);
	}

	public WebElement getInsuranceCheckbox() 
	{
		return getElement(insuranceCheckbox);
	}
	
	

	public WebElement getNoOfCars() 
	{
		return getElement(noOfCars);
	}

	public WebElement getDropdownIconTwo() 
	{
		return getElement(dropdownIconTwo);
	}

	public WebElement getTruck() 
	{
		return getElement(truck);
	}

	public WebElement getStandardRadioButton() 
	{
		return getElement(standardRadioButton);
	}

	public WebElement getInsurnaceTwoCheckBox() 
	{
		return getElement(insurnaceTwoCheckBox);
	}

	public WebElement getNoOfCarsTwo() 
	{
		return getElement(noOfCarsTwo);
	}

	public WebElement getBookNowButton() 
	{
		return getElement(bookNowButton);
	}

	// page actions
	public String getControlGroupPageTitle()
	{
		return getPageTitle();	
	}
	
	public void carSelectsMethod() throws InterruptedException
	{
		
		verticalScroll();
		
		frameHandling(getFrameName());
		
		getTriangle().click();
		
		
		getSuv().click();
		
			
		getAutomaticRadioBtton().click();
		
		
		getInsuranceCheckbox().click();
		
		
		getNoOfCars().sendKeys("2");
		
		
		getDropdownIconTwo().click();
		
		
		getTruck().click();
		
		
		getStandardRadioButton().click();
		
		
		getInsurnaceTwoCheckBox().click();
		
		
		getNoOfCarsTwo().sendKeys("1");
		
		
		getBookNowButton().click();
		
		staticWait();
	}
}
