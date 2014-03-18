package com.sonyericsson.automatedtests.page;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.xalan.trace.SelectionEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.WebClient;
import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.CwsSeleniumBaseTest;

public class AccessoryshopPage extends CwsAbstractBaseTest {
    String host = "";
    public WebDriver driver;
    public String parent;
    public Set<String> handlers;
    public AccessoryshopPage(String browser, String targetHost, boolean javascriptRequired) throws Exception {
        init(browser, targetHost, javascriptRequired);
    }
    public void quitDriver() {
        Reporter.log("Calling Quit Driver");
        destroy();
    }
    public boolean isHeaderPresent() throws Exception {
        return this.testQuickHeaderNavLinksGeneric();
    }
    public boolean isFooterPresent() throws Exception {
        return this.testQuickFooterNavLinksGeneric();
    }
    public boolean IsFilterPresent() throws Exception
    {
    	try
    	{
    	    getDriver().navigate().to(getDriver().getCurrentUrl());
            Thread.sleep(5000);
            Assert.assertTrue(!getText(By.xpath("//div[@class='filter-block']/p")).isEmpty(),"Heading for phone filter is not present");
            Reporter.log("Heading for phone filter present");
            Assert.assertTrue(isElementPresent(By.id("filter-cat-accessoryphone_cDD")),"Dropdown for phone filter not present");//
            Reporter.log("Accessory for dropdown present");
            
            
            Reporter.log("Testing Accessory for filter....");
            getDriver().findElement(By.cssSelector("div#filter-cat-accessoryphone_cDD")).click();
            for(int a=0;a<10;a++)
            {
                try
                {
                    //if(!!getDriver().findElement(By.xpath("//div[@id='customDDOptions']/a[5]")).isDisplayed())
                    if(!getDriver().findElement(By.cssSelector("div#customDDOptions > a + a")).isDisplayed())
                    {
                        Thread.sleep(1000);
                        getDriver().findElement(By.xpath("//div[@id='filter-cat-accessoryphone_cDD']/span[@class='ddButton']")).click();
                    }
                    else
                        break;
                }
                catch(Exception ex)
                {
                    getDriver().findElement(By.cssSelector("div#filter-cat-accessoryphone_cDD > span.ddButton")).click();
                    //getDriver().findElement(By.xpath("//div[@id='filter-cat-accessoryphone_cDD']/span[@class='ddButton']")).click();
                }
            }
            getDriver().findElement(By.xpath("//div[@id='customDDOptions']/a[5]")).click();
            Reporter.log("Option from Accessory for filter selected");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.sales-item-row")),"Items not present");
            getDriver().findElement(By.xpath("//div[@id='filter-cat-accessoryphone_cDD']/span[@class='ddButton']")).click();
            for(int count=0;count<=5;count++)
            {
                if(isElementDisplayed(By.xpath("//div[@id='customDDOptions']/a[1]")))
                {
                    break;
                }
                else
                {
                    Thread.sleep(1000);
                    getDriver().findElement(By.xpath("//div[@id='filter-cat-accessoryphone_cDD']/span[@class='ddButton']")).click(); 
                }
                    
            }
            getDriver().findElement(By.xpath("//div[@id='customDDOptions']/a[1]")).click();
            Reporter.log("All from Accessory for filter selected");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.sales-item-row")),"Items not present");


            Assert.assertTrue(!getText(By.xpath("//div[@class='filter-block'][2]/p")).isEmpty(),"Heading for extra filter not present");
            Assert.assertTrue(isElementPresent(By.id("filter-cat-accessorytype_cDD")),"Dropdown for extra filter not present");//
            Reporter.log("Accessory type dropdown present");
            Reporter.log("Testing Accessory type filter....");
            getDriver().findElement(By.xpath("//div[@id='filter-cat-accessorytype_cDD']/span[@class='ddButton']")).click();
            for(int count=0;count<=5;count++)
            {
                if(isElementDisplayed(By.xpath("//div[@id='customDDOptions']/a[5]")))
                {
                    break;
                }
                else
                {   
                    Thread.sleep(1000);
                    getDriver().findElement(By.xpath("//div[@id='filter-cat-accessorytype_cDD']/span[@class='ddButton']")).click(); 
                }
            }
            getDriver().findElement(By.xpath("//div[@id='customDDOptions']/a[5]")).click();
            Reporter.log("Option from Accessory type filter selected");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.sales-item-row")),"Items not present");
            getDriver().findElement(By.xpath("//div[@id='filter-cat-accessorytype_cDD']/span[@class='ddButton']")).click();
            for(int count=0;count<=5;count++)
            {
                if(isElementDisplayed(By.xpath("//div[@id='customDDOptions']/a[1]")))
                {
                    break;
                }
                else
                {
                    Thread.sleep(1000);
                    getDriver().findElement(By.xpath("//div[@id='filter-cat-accessorytype_cDD']/span[@class='ddButton']")).click(); 
                }
                    
            }
            getDriver().findElement(By.xpath("//div[@id='customDDOptions']/a[1]")).click();
            Reporter.log("All from Accessory type filter selected");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.sales-item-row")),"Items not present");
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}

    	return true;
    }
    public boolean isAccessoriesPresent() throws Exception {
    getDriver().navigate().refresh();
      scrollPage();
      CwsSeleniumBaseTest.accessoryHash.clear();
      if(isPhonesPresent()) // how ever here it is not phones but accessories
      {
          populateHashMap();
        return true;
      }
      else
      {
          populateHashMap();
    	return  false;
      }
    }
    public boolean isSmallBannersAvailable() throws Exception
    {
    	boolean flag=false;
    	if(isSmallBannerPresent())
    		flag=true;
    	return flag;
    }
}
