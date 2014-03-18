package com.sonyericsson.automatedtests.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.selenesedriver.IsElementDisplayed;
import org.openqa.selenium.internal.selenesedriver.TakeScreenshot;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.testng.Assert;
import org.testng.Reporter;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;

public class CheckAllProducts extends CwsAbstractBaseTest {
    String host = "";
    private List<String> phonesDD =new ArrayList<String>();
    private List<String> accessoriesDD =new ArrayList<String>();
    private List<String> phonesPage = new ArrayList<String>();
    private List<String> accessoriesPage = new ArrayList<String>();
    public CheckAllProducts(String browser, String targetHost, boolean javascriptRequired) throws Exception {
        init(browser, targetHost, javascriptRequired);
    }
    public void quitDriver() {
        Reporter.log("Calling Quit Driver");
        destroy();
    }
    public void storeDropDownsInList() throws Exception
    {
    	try
    	{
    		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,325)", "");
    		Thread.sleep(5000);
    		waitForElementPresent(By.xpath("//div[@class='tab-pane box-shadow loaded']/div"));
    		Reporter.log("***********Fetching Phones from dropdown************");
    		click(By.xpath("//div[@class='tab-pane box-shadow loaded']/div/span[2]"));
    		Thread.sleep(1000);
    		int count = getElementCount(By.xpath("//div[@id='customDDOptions']/a"));
    		for(int i=2;i<=count;i++)
    		{
    			phonesDD.add(getText(By.xpath("//div[@id='customDDOptions']/a["+i+"]")));	
    		}
    		Reporter.log("***********Fetching Phones from dropdown Done************");
    		click(By.tagName("h2"));

    		Reporter.log("***********Fetching accessories from dropdown************");
    		click(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[2]/span[2]"));
    		Thread.sleep(1000);
    		int count1 = getElementCount(By.xpath("//div[@id='customDDOptions']/a"));
    		for(int i=2;i<=count1;i++)
    		{
    			accessoriesDD.add(getText(By.xpath("//div[@id='customDDOptions']/a["+i+"]")));
    		}
    		Reporter.log("***********Fetching Accessories from dropdown Done************");
    	}
    	catch(Exception ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    	}
    }
    
    public void storeProductsFromPages(String host,String baseuri) throws Exception
    {
    	try
    	{
    		getDriver().get("http://"+host+baseuri+"phones");
    		Reporter.log("***********Fetching Phones from page************");
    		phonesPage = populateList();
    		Reporter.log("***********Fetching Phones from page done************");
    		getDriver().get("http://"+host+baseuri+"accessories");
    		Reporter.log("***********Fetching accessories from page************");
    		accessoriesPage = populateList();
    		Reporter.log("***********Fetching accessories from page done************");
    	}
    	catch(Exception ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    	}

    }
    public List<String> populateList() throws Exception
    {
    	try
    	{
    		List<String> lst =  new ArrayList<String>();

    		int countR=getElementCount(By.xpath("//div[@class='sales-item-grid']/div"));
    		for(int i=1;i<=countR;i++)
    		{
    			int countC = getElementCount(By.xpath("//div[@class='sales-item-grid']/div["+i+"]/a"));
    			for(int j=1;j<=countC;j++)
    			{
    				lst.add(getText(By.xpath("//div[@class='sales-item-grid']/div["+i+"]/a["+j+"]/h3")));
    			}
    		}
    		return lst;
    	}
    	catch(Exception ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return null;
    	}
    }
    public boolean checkPhonesPage()
    {
    	try
    	{
    		Reporter.log("Number of items in phone dropdown list : "+phonesDD.size());
    		Reporter.log("Number of items in phones page : "+phonesPage.size());
    		List<String> tempList = new ArrayList<String>();
    		tempList.addAll(phonesDD);
    		boolean flag=true;
    		phonesDD.removeAll(phonesPage);
    		phonesPage.removeAll(tempList);
			if(phonesDD.size()>0)
			{
				Reporter.log("Phone DropDown has the following products which are not on page");
				for(int i=0;i<phonesDD.size();i++)
				{
					Reporter.log(phonesDD.get(i));
				}
				flag=false;
			}
			if(phonesPage.size()>0)
			{
				Reporter.log("Phone Page has the following products which are not on dropdown");
				for(int i=0;i<phonesPage.size();i++)
				{
					Reporter.log(phonesPage.get(i));
				}
				if(flag)
					flag=false;
			}
			if(!flag)
				return false;
    	}
    	catch(Exception ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    	Reporter.log("There is no uncommon item found between Phone Dropdown and Phones page");
    	return true;
    }
    
    public boolean checkAccessoriesPage()
    {
    	try
    	{
    		Reporter.log("Number of items in accessory dropdown list : "+accessoriesDD.size());
    		Reporter.log("Number of items in accessories page : "+accessoriesPage.size());
    		List<String> tempList = new ArrayList<String>();
    		tempList.addAll(accessoriesDD);
    		boolean flag=true;
    		accessoriesDD.removeAll(accessoriesPage);
    		accessoriesPage.removeAll(tempList);
			if(accessoriesDD.size()>0)
			{
				Reporter.log("Accessoories DropDown has the folling products which are not on page");
				for(int i=0;i<accessoriesDD.size();i++)
				{
					Reporter.log(accessoriesDD.get(i));
				}
				flag=false;
			}
			if(accessoriesPage.size()>0)
			{
				Reporter.log("Accessories Page has the folling products which are not on dropdown");
				for(int i=0;i<accessoriesPage.size();i++)
				{
					Reporter.log(accessoriesPage.get(i));
				}
				if(flag)
					flag=false;
			}
			if(!flag)
				return false;
    	}
    	catch(Exception ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    	Reporter.log("There is no uncommon item found between Accessory Dropdown and Accessories page");
    	return true;
    }
 
}