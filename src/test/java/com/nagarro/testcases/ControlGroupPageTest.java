package com.nagarro.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.tests.BaseEngine;
import com.nagarro.webpages.ControlgroupPage;

public class ControlGroupPageTest extends BaseEngine
{

	@Test(priority = 1)
	public void verifyControlGroupPageTitleTest()
	{
		String titleA = getpage().getInstance(ControlgroupPage.class).getControlGroupPageTitle();
		System.out.println("Control Group Page title is: "+titleA);
		Assert.assertEquals(titleA, "Controlgroup | jQuery UI");
	}
	
	@Test(priority = 2)
	public void carSelectMethod() throws InterruptedException
	{
		getpage().getInstance(ControlgroupPage.class).carSelectsMethod();
	}
}
