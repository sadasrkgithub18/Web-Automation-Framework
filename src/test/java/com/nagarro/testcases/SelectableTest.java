package com.nagarro.testcases;

import java.awt.AWTException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.tests.BaseEngine;
import com.nagarro.webpages.SelectablePage;

public class SelectableTest extends BaseEngine
{
	@Test(priority = 1)
	public void verifySelectablePageTitleTest()
	{
		String titleA = getpage().getInstance(SelectablePage.class).getSelectablePageTitle();
		System.out.println("Selectable Page title is: "+titleA);
		Assert.assertEquals(titleA, "Selectable | jQuery UI");
	}
	
	
	@Test(priority = 2)
	public void verifySelectableTest() throws AWTException, InterruptedException
	{
		   
		List<String> list = getpage().getInstance(SelectablePage.class).selectTheItems();
		
		System.out.println(list);
		
		Assert.assertEquals(list.get(0),"Item 1");
		Assert.assertEquals(list.get(1),"Item 3");
		Assert.assertEquals(list.get(2),"Item 7");
		
	}
	
}
