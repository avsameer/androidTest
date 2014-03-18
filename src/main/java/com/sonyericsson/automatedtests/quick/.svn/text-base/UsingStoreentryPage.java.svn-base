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
import com.sonyericsson.automatedtests.page.StoreentryPage;

@GUITest
public class UsingStoreentryPage extends EcomSetup {
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static StoreentryPage page;

    @BeforeClass (groups="quicktest")
    @Parameters({ "browser", "locale", "javascriptRequired" })
    public void UsingStoreentryPage1(String browser, String locale, boolean javascriptRequired)
            throws Exception {
        System.out.println("==========Started testing of store entry page for "+locale+" locale========");
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        page = new StoreentryPage(browser, host, javascriptRequired);
        String url = "http://" + host + "/" + locale + "/";
        page.openUrl(url);
    }
    
    @Test(groups = "quicktest")
    public void testHeader() throws Exception {
        System.out.println("===Testing Header===");
        Assert.assertEquals(page.isHeaderPresent(), true);
    }
   
    @Test(groups = "quicktest", dependsOnMethods="testHeader", alwaysRun=true)
    public void testTopPaymentBanner() throws Exception {
        System.out.println("===Testing Top Payment Banner===");
        Assert.assertEquals(page.isTopPaymentBannerPresent(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testTopPaymentBanner", alwaysRun=true)
    public void testTopBanner() throws Exception {
        System.out.println("===Testing Top Banner===");
        Assert.assertEquals(page.isTopBannerPresent(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testTopBanner", alwaysRun=true)
    public void testPopularProducts() throws Exception {
        System.out.println("===Testing Popular Products===");
        Assert.assertEquals(page.isPopularProductsPresent(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testPopularProducts", alwaysRun=true)
    public void testQuickSelection() throws Exception {
        System.out.println("===Testing Quick Selectors===");
        Assert.assertEquals(page.isQuickSelectionPresent(), true);
    }
    
    @Test(groups = "quicktest", dependsOnMethods="testQuickSelection", alwaysRun=true)
    public void testNewFooter() throws Exception {
        System.out.println("===Testing New Footer===");
        Assert.assertEquals(page.isNewFooterPresent(), true);
    }
    
    @Test(groups = "quicktest",dependsOnMethods="testNewFooter", alwaysRun=true)
    public void testFooter() throws Exception {
        System.out.println("===Testing Footer===");
        Assert.assertEquals(page.isFooterPresent(), true);
    }
   
    @Test(groups = "quicktest",dependsOnMethods="testFooter", alwaysRun=true)
    public void testSocialBar() throws Exception {
        System.out.println("===Testing Social Bar===");
        Assert.assertEquals(page.isSocialBarAvailable(), true);
    }
    @Test(groups = "quicktest",dependsOnMethods="testSocialBar", alwaysRun=true)
    public void testSmallBanner() throws Exception
    {
        System.out.println("===Testing Small Banner===");
    	Assert.assertEquals(page.isSmallBannersAvailable(), true);
    }
    
    @Test(groups = "quicktest",dependsOnMethods="testSmallBanner", alwaysRun=true)
     @Parameters({"locale" })
    public void testPhoneQuickSelector(String locale) throws Exception
    {
        System.out.println("===Testing Phone Quick Selector===");
    	Assert.assertEquals(page.checkPhoneQuickSelection(locale), true);
    }
    
    @Test(groups = "quicktest",dependsOnMethods="testPhoneQuickSelector", alwaysRun=true)
    @Parameters({"locale" })
    public void testAccessoryQuickSelector(String locale) throws Exception
    {
        System.out.println("===Testing Accessory Quick Selector===");
        String url = "http://" + host + "/" + locale + "/";
    	Assert.assertEquals(page.checkAccessoryQuickSelection(url,locale), true);
    }
    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        System.out.println("===Quiting===");
        page.quitDriver();
    }
}
