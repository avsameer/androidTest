package com.sonyericsson.automatedtests.page;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.CwsSeleniumBaseTest;

public class PhoneshopPage extends CwsAbstractBaseTest {
    String host = "";
    public WebDriver driver;
    public String parent;
    public Set<String> handlers;
    public PhoneshopPage(String browser, String targetHost, boolean javascriptRequired) throws Exception {
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
    public boolean isPhonesAvailable() throws Exception {
    	scrollPage();
    	CwsSeleniumBaseTest.phoneHash.clear();
    	if(isPhonesPresent())
    	{
    	    populateHashMap();
    		return true;
    	}
    	else
    	{
    	    populateHashMap();
    		return false;
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
