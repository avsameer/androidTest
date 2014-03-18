package com.sonyericsson.automatedtests.quick;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.fest.swing.annotation.GUITest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.EcomSetup;
import com.sonyericsson.automatedtests.page.JSONPage;
import com.sonyericsson.automatedtests.page.PhoneshopPage;

@GUITest
public class UsingJSONPage extends EcomSetup{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static JSONPage page;

    @BeforeClass(groups = "quicktest")
    @Parameters({ "browser", "locale" , "javascriptRequired" })
    public void UsingJSONPage1(String browser, String locale, boolean javascriptRequired) throws Exception {
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        page = new JSONPage(browser, host, javascriptRequired);
    }
    @Test(groups = "quicktest")
     @Parameters("locale")
    public void testStoreInfoJSON(String locale) throws Exception {
       Assert.assertEquals(page.checkStoreInfoJSON(host,locale), true);
    }
    
    @Test(groups = "quicktest")
    @Parameters({"locale"})
    public void testSalesItemsJSON(String locale) throws Exception
    {
    	Assert.assertEquals(page.checkSalesItemsJSON(host,locale), true);
    }
    
    @Test(groups = "quicktest")
    @Parameters({"locale"})
    public void testPriceListingJSON(String locale) throws Exception {
        Assert.assertEquals(page.checkPriceListingJSON(host,locale), true);
    }
    
  
    @Test(groups = "quicktest")
    @Parameters({"locale"})
    public void testSalesItemsJSONnew(String locale) throws Exception
    {
    	Assert.assertEquals(page.checkSalesItemsJSONnew(host,locale), true);
    }
    
    @Test(groups = "quicktest")
    @Parameters({"locale"})
    public void testPriceListingJSONnew(String locale) throws Exception {
        Assert.assertEquals(page.checkPriceListingJSONnew(host,locale), true);
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        page.quitDriver();
    }
}
