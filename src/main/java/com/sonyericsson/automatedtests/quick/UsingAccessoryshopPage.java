package com.sonyericsson.automatedtests.quick;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.fest.swing.annotation.GUITest;
import org.junit.Rule;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.EcomSetup;
import com.sonyericsson.automatedtests.page.AccessoryshopPage;

@GUITest
public class UsingAccessoryshopPage extends EcomSetup{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static AccessoryshopPage page;
    

    
    @BeforeClass (groups = "quicktest")
    @Parameters({ "browser","locale","page", "javascriptRequired" })
    
    public void UsingAccessoryshopPage1(String browser, String locale,String page1, boolean javascriptRequired)
            throws Exception {
        System.out.println("==========Started testing of Accessory shop page for "+locale+" locale========");
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        page = new AccessoryshopPage(browser, host, javascriptRequired);
        String url = "http://" + host + "/" + locale + "/" + page1 + "/";
        System.out.println(url);
        page.openUrl(url);
    }
  
    
    @Test(groups = "quicktest")
    public void testHeader() throws Exception {
        System.out.println("===Testing Header===");
        Assert.assertEquals(page.isHeaderPresent(), true);
    }
    
    
    @Test(groups = "quicktest", dependsOnMethods="testHeader", alwaysRun=true)
    public void testAccessories() throws Exception {
        System.out.println("===Testing Accessories===");
        Assert.assertEquals(page.isAccessoriesPresent(), true);
    }
    
    
    @Test(groups = "quicktest",dependsOnMethods="testAccessories", alwaysRun=true)
    public void testFooter() throws Exception {
        System.out.println("===Testing Footer===");
        Assert.assertEquals(page.isFooterPresent(), true);
    }

    @Test(groups = "quicktest",dependsOnMethods="testFooter", alwaysRun=true)
    public void testNewFooter() throws Exception
    {
        System.out.println("===Testing New footer===");
    	Assert.assertEquals(page.isNewFooterPresent(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods={"testNewFooter"},alwaysRun=true)
    // @Test(groups = "quicktest")
    public void testFilters() throws Exception {
        System.out.println("===Testing Filters===");
        Assert.assertEquals(page.IsFilterPresent(), true);
    }
    
    @Test(groups = "quicktest",dependsOnMethods={"testFilters"},alwaysRun=true)
    public void testSmallBanner() throws Exception
    {
        System.out.println("===Testing Small Banner===");
    	Assert.assertEquals(page.isSmallBannersAvailable(), true);
    }
    
    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        System.out.println("===Quiting===");
        page.quitDriver();
    }
}
