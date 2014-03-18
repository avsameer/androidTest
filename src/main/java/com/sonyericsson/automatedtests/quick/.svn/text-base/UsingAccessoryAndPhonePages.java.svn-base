package com.sonyericsson.automatedtests.quick;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Rule;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.EcomSetup;
import com.sonyericsson.automatedtests.page.AccessoryshopPage;
import com.sonyericsson.automatedtests.page.CheckAllProducts;


public class UsingAccessoryAndPhonePages extends EcomSetup{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static CheckAllProducts page;
    

    
    @BeforeClass (groups = "quicktest")
    @Parameters({ "browser", "locale", "javascriptRequired" })
    public void UsingAccessoryAndPhonePages1(String browser, String locale, boolean javascriptRequired)
            throws Exception {
        System.out.println("==========Started testing of AccessoryAndPhone for "+locale+" locale========");
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        page = new CheckAllProducts(browser, host, javascriptRequired);
        String url = "http://" + host + "/" + locale + "/";
        System.out.println(url);
        page.openUrl(url);

    }
  
    
    @Test
    @Parameters("locale")
    public void checkPhonePage(String locale) throws Exception {
        System.out.println("===Storing Data===");
        page.storeDropDownsInList();
        page.storeProductsFromPages(host,"/" + locale + "/");
        System.out.println("===Testing Phone Page===");
    	 Assert.assertEquals(page.checkPhonesPage(), true);
    }
    
    @Test(dependsOnMethods="checkPhonePage", alwaysRun=true)
    public void checkAccessoryPage() throws Exception {
        System.out.println("===Testing Accessory Page===");
    	 Assert.assertEquals(page.checkAccessoriesPage(), true);
    }
    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        System.out.println("===Quiting===");
        page.quitDriver();
    }
}
