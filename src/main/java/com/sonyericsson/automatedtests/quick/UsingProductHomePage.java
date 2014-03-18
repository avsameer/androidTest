package com.sonyericsson.automatedtests.quick;

import java.sql.Timestamp;
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
import com.sonyericsson.automatedtests.page.ProductHomePage;

@GUITest
public class UsingProductHomePage extends EcomSetup{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static ProductHomePage page;

    @BeforeClass (groups="quicktest")
    @Parameters({ "browser", "locale", "page", "javascriptRequired" })
    public void UsingProductHomePage1(String browser, String locale, String page1, boolean javascriptRequired)
            throws Exception {
        System.out.println("==========Started testing of PHP page for "+locale+" locale========");
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        page = new ProductHomePage(browser, host + "/" + locale +"/", javascriptRequired);
        String url = "http://" + host + "/" + locale + "/" +page1 +"/";
        page.openUrl(url);
    }
    
    @Test(groups = "quicktest")
    public void testHeader() throws Exception {
        
        System.out.println("===Testing Header===");
        Assert.assertEquals(page.isHeaderPresent(host), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testHeader", alwaysRun=true)
    public void testAddRemoveCartWorking() throws Exception {
        System.out.println("===Testing Add to cart Working===");
        Assert.assertEquals(page.checkAddtoCartWorking(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testAddRemoveCartWorking", alwaysRun=true)
    public void testConfigbox() throws Exception {
        System.out.println("===Testing Configbox===");
        Assert.assertEquals(page.isConfigboxPresent(), true);
   	 
    }
    
 /*   @Test(groups = "quicktest", dependsOnMethods="testConfigbox", alwaysRun=true)
    public void testisRecsetPresent() throws Exception {
   	 System.out.println("===Testing Rec Sets===");
        Assert.assertEquals(page.isRecsetPresent(), true);
    }*/
    
    @Test(groups = "quicktest", dependsOnMethods="testConfigbox", alwaysRun=true)
    public void testInfobox() throws Exception {
        System.out.println("===Testing Infobox===");
        Assert.assertEquals(page.isInfoboxPresent(), true);
   
    }
    
    @Test(groups = "quicktest", dependsOnMethods={"testInfobox"}, alwaysRun=true)
    public void testNewFooter() throws Exception
    {
        System.out.println("===Testing New Footer===");
    	Assert.assertEquals(page.isNewFooterPresent(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testNewFooter", alwaysRun=true)
    public void testFooter() throws Exception {
        System.out.println("===Testing Footer===");
        Assert.assertEquals(page.isFooterPresent(), true);
   	 
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testFooter", alwaysRun=true)
    public void testSocialBar() throws Exception {
        System.out.println("===Testing Social Bar===");
        Assert.assertEquals(page.isSocialBarAvailable(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testSocialBar", alwaysRun=true)
    public void testSmallBanner() throws Exception
    {
        System.out.println("===Testing Small Banner===");
        Assert.assertEquals(page.isSmallBannersAvailable(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testSmallBanner", alwaysRun=true)
    @Parameters({"locale"})
    public void testNotifyMe(String locale) throws Exception
    {
        System.out.println("===Testing NotifyMe===");
        Assert.assertEquals(page.checkNotifyMe(host,locale), true);
    }
    
   
    
    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        System.out.println("===Quiting===");
        page.quitDriver();
    }
}
