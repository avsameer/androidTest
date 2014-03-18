package com.sonyericsson.automatedtests.quick;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fest.swing.annotation.GUITest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.EcomSetup;
import com.sonyericsson.automatedtests.page.FilteredCategoryPage;

@GUITest
public class UsingFilteredCategoryPage extends EcomSetup{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static FilteredCategoryPage page;
    private String targethost;
    public Object[][] listOfQuickLinks;

  /*  @DataProvider(name = "test1")
    public Object[][] createData1() {
     return new Object[][] {
       { "newarrivals/", targethost },
       { "bestsellers/", targethost},
       { "xperianxt/", targethost},
     };
    }*/
    
    @DataProvider(name = "test1")
    public Object[][] createData1() {
     return listOfQuickLinks;
    }
     
    @Parameters({ "browser","locale", "javascriptRequired" })
    @Test(groups = "quicktest")
    public void initPage(String browser, String locale, boolean javascriptRequired)
            throws Exception {
        System.out.println("==========Started testing of Filtered Category Page for "+locale+" locale========");
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        targethost=host +"/"+ locale +"/";
        page = new FilteredCategoryPage(browser, targethost, javascriptRequired);
        page.openUrl("http://"+targethost);
        listOfQuickLinks = page.prepareList();
       // String url = "http://" + targetHost + baseUri;
       // page.openUrl(url);
    }
    
    @Test(groups = "quicktest",dependsOnMethods={"initPage"},dataProvider="test1")
    public void testQuickLinks(String url, String str)   throws Exception {
        System.out.println("===Testing Page : "+url+"===");
        //String url = "http://" + targethost + pg;
        page.openUrl(url);
        Reporter.log("=========================TESTING HEADER START=======================");    
        Assert.assertEquals(testHeader(), true);
        Reporter.log("=========================TESTING HEADER COMPLETE=======================");
        
        Reporter.log("=========================TESTING FOOTER START=======================");
        Assert.assertEquals(testFooter(), true);
        Reporter.log("=========================TESTING FOOTER COMPLETE=======================");
        
        Reporter.log("=========================TESTING NEW FOOTER START=======================");
        Assert.assertEquals(testNewFooter(), true);
        Reporter.log("=========================TESTING NEW FOOTER COMPLETE=======================");
        
        if(!page.isPHPpage())
        {
        	Reporter.log("=========================TESTING PRODUCTS START=======================");
        	 Assert.assertEquals(testPhones(), true);
        	Reporter.log("=========================TESTING PRODUCTS COMPLETE=======================");
        }
    }
    
    
  /*  @Parameters({ "browser", "host", "baseUri", "javascriptRequired" })
    public UsingFilteredCategoryPage(String browser, String targetHost, String baseUri, boolean javascriptRequired)
            throws Exception {
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        page = new FilteredCategoryPage(browser, targetHost, javascriptRequired);
        String url = "http://" + targetHost + baseUri;
        page.openUrl(url);
    }*/
  //  @Test(groups = "quicktest",dependsOnMethods={"mytest1"})
    public boolean testHeader() throws Exception {
    	try
    	{
    	    System.out.println("===Testing Header===");
    		Assert.assertEquals(page.isHeaderPresent(), true);
    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    //  @Test(groups = "quicktest",dependsOnMethods={"mytest1"})
    public boolean testFooter() throws Exception {
    	try
    	{
    	    System.out.println("===Testing Footer===");
    		Assert.assertEquals(page.isFooterPresent(), true);
    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    //  @Test(groups = "quicktest", dependsOnMethods={"mytest1"})
    public boolean testPhones() throws Exception {
    	try
    	{
    	    System.out.println("===Testing Phones===");
    		Assert.assertEquals(page.isProductsPresent(), true);
    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    public boolean testNewFooter() throws Exception {
    	try
    	{
    	    System.out.println("===Testing New Footer===");
    		Assert.assertEquals(page.isNewFooterPresent(), true);
    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        System.out.println("===Quiting===");
        page.quitDriver();
    }
}
