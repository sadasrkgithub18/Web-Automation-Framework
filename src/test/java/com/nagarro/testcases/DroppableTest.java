package com.nagarro.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.tests.BaseEngine;
import com.nagarro.webpages.DroppablePage;

public class DroppableTest extends BaseEngine
{
 	
    @Test(priority = 1)
	public void verifyDroppablePageTitleTest()
	{
		String titleA = getpage().getInstance(DroppablePage.class).getDroppablePageTitle();
		System.out.println("Droppable Page title is: "+titleA);
		Assert.assertEquals(titleA, "Droppable | jQuery UI");
	}
    
    
    @Test(priority = 2)
	public void verifyDroppableTest()
	{
		   
		getpage().getInstance(DroppablePage.class).dragAndDropTheBox(getDriver());
		
		String scMsg = getpage().getInstance(DroppablePage.class).getStatusMsg();
		
		System.out.println("Actual Msg we got is:" + scMsg);
		
		Assert.assertEquals(scMsg, "Dropped!");
	}
	
    
    
}
