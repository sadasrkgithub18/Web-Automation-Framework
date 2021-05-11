package com.nagarro.tests;

import com.nagarro.page.BasePage;
import com.nagarro.page.Page;

public class ActionEngine extends BaseEngine
{
	public static Page page;
	
	   public static void enterURL(String url)
	   {
		   getDriver().get(url);
		   page = new BasePage(getDriver());
	   }
}
