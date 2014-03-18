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
import com.sonyericsson.automatedtests.page.PhoneshopPage;

@GUITest
public class UsingPhoneshopPage extends EcomSetup{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static PhoneshopPage page;

    @BeforeClass(groups = "quicktest")
    @Parameters({ "browser","locale" , "page" , "javascriptRequired" })
    public void UsingPhoneshopPage1(String browser, String locale, String page1, boolean javascriptRequired) throws Exception {
        System.out.println("==========Started testing of Phone shop page for "+locale+" locale========");
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        page = new PhoneshopPage(browser, host, javascriptRequired);
        String url = "http://" + host + "/" + locale + "/" + page1 +"/";
        page.openUrl(url);
    }
    @Test(groups = "quicktest")
    public void testHeader() throws Exception {
        System.out.println("===Testing Header===");
       Assert.assertEquals(page.isHeaderPresent(), true);
    }
    
    @Test(groups = "quicktest",dependsOnMethods="testHeader", alwaysRun=true)
    public void testSmallBanner() throws Exception
    {
        System.out.println("===Testing Small Banner===");
    	Assert.assertEquals(page.isSmallBannersAvailable(), true);
    }
    
    @Test(groups = "quicktest",dependsOnMethods="testSmallBanner", alwaysRun=true)
    public void testPhones() throws Exception {
        System.out.println("===Testing Phones===");
        Assert.assertEquals(page.isPhonesAvailable(), true);
    }
    
  
    @Test(groups = "quicktest",dependsOnMethods="testPhones", alwaysRun=true)
    public void testNewFooter() throws Exception
    {
        System.out.println("===Testing New footer===");
    	Assert.assertEquals(page.isNewFooterPresent(), true);
    }
    
    @Test(groups = "quicktest",dependsOnMethods="testNewFooter", alwaysRun=true)
    public void testFooter() throws Exception {
        System.out.println("===Testing Footer===");
        Assert.assertEquals(page.isFooterPresent(), true);
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        System.out.println("===Quiting===");
        page.quitDriver();
    }
}
