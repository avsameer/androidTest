package com.sonyericsson.automatedtests.quick;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.fest.swing.annotation.GUITest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.EcomSetup;
import com.sonyericsson.automatedtests.page.EShopPage;
import com.sonyericsson.automatedtests.page.EShopPageNew;

@GUITest
@Test(sequential=true)
public class UsingEShopPageNew extends EcomSetup{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static EShopPageNew page;

    @BeforeClass(groups={"integrationtest","quick","onlyGB"})
    @Parameters({ "browser", "locale", "page", "javascriptRequired","auth" })
    public void UsingEShopPage1(String browser, String locale, String page1, boolean javascriptRequired, String auth)
            throws Exception {
        System.out.println("==========Started testing of Checkout for "+locale+" locale and "+page1+"========");
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        page = new EShopPageNew(browser, host, javascriptRequired);
        String url = "http://" + host + "/" + locale + "/" + page1 + "/";
        page.openUrl(url);
    }
    
//=========================================== STEP-1 ============================================================//    
    @Test(groups = { "integrationtest" }, sequential=true)
    @Parameters("auth")
    public void testDataTransferToCheckoutPage(String auth) throws Exception {
        System.out.println("===Testing Data Transfer To Checkout===");
        Assert.assertEquals(page.isDataTransferredToCheckoutPage(), true);
    }
    
   @Test(groups = "integrationtest", dependsOnMethods = { "testDataTransferToCheckoutPage" }, alwaysRun=true, sequential=true)
   @Parameters("auth")
    public void testHeader_Step1(String auth) throws Exception {
       System.out.println("===Testing Header Step-1===");
        Assert.assertEquals(page.checkHeader(), true);
     }
    
    @Test(groups = "integrationtest",dependsOnMethods="testHeader_Step1", alwaysRun=true)
    @Parameters("auth")
    public void testNewFooter_Step1(String auth) throws Exception
    {
        System.out.println("===Testing New Footer Step-1===");
        Assert.assertEquals(page.checkNewFooter(), true);
    }

    @Test(groups = "integrationtest", dependsOnMethods = { "testNewFooter_Step1" }, alwaysRun=true, sequential=true)
    @Parameters("auth")
    public void testFooter_Step1(String auth) throws Exception {
        System.out.println("===Testing Footer Step-1===");
        Assert.assertEquals(page.checkFooter(), true);
    }
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testFooter_Step1" }, alwaysRun=true,sequential=true)
    @Parameters("auth")
    public void testFunctionOfStep_1(String auth) throws Exception
    {
        System.out.println("===Testing Functionality of Step-1===");
    	Assert.assertEquals(page.isStep1Functional(), true);
    }
    
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testFunctionOfStep_1" }, alwaysRun=true,sequential=true)
    @Parameters({"locale", "auth"})
    public void testVoucherFeature(String locale, String auth) throws Exception
    {
        System.out.println("===Testing Voucher Feature===");
        if(locale.contains("gb"))
        {
            Assert.assertEquals(page.IsVoucherWorking(), true);
        }
        else
            Reporter.log("This test is executed for gb locale only");
    }

     @Test(groups = "integrationtest", dependsOnMethods = { "testVoucherFeature" }, alwaysRun=true,sequential=true)
     @Parameters("auth")
    public void testSanity_Step1(String auth) throws Exception {
         System.out.println("===Testing Sanity Step-1===");
        Assert.assertEquals(page.checkStep1Sanity(), true);
    }
    
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testSanity_Step1" }, alwaysRun=true, sequential=true)
    @Parameters({"auth","locale"})
    public void testDeliveryMethod(String auth,String locale) throws Exception {
        System.out.println("===Testing Delivery Method===");
        Assert.assertEquals(page.checkDeliveryMethod(locale), true);
    }
 
  //=========================================== STEP-1 ============================================================// 
  //=========================================== STEP-2 ============================================================// 
    
  
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testDeliveryMethod" }, alwaysRun=true, sequential=true)
    @Parameters("auth")
    public void testSanity_Step2(String auth) throws Exception {
        System.out.println("===Testing Sanity Step-2===");
        Assert.assertEquals(page.checkStep2Sanity(), true);
    }
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testSanity_Step2" }, alwaysRun=true, sequential=true)
    @Parameters("auth")
    public void testHeader_Step2(String auth) throws Exception {
        System.out.println("===Testing Header Step-2===");
        Assert.assertEquals(page.checkHeader(), true);
     }
    
   @Test(groups = "integrationtest",dependsOnMethods="testHeader_Step2", alwaysRun=true)
   @Parameters("auth")
    public void testNewFooter_Step2(String auth) throws Exception
    {
       System.out.println("===Testing New Footer Step-2===");
        Assert.assertEquals(page.checkNewFooter(), true);
    }

    @Test(groups = "integrationtest", dependsOnMethods = { "testNewFooter_Step2" }, alwaysRun=true, sequential=true)
    @Parameters("auth")
    public void testFooter_Step2(String auth) throws Exception {
        System.out.println("===Testing Footer Step-2===");
        Assert.assertEquals(page.checkFooter(), true);
    }
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testFooter_Step2" }, alwaysRun=true, sequential=true)
    @Parameters("auth")
    public void testFunctionOfStep_2(String auth) throws Exception {
        System.out.println("===Testing Function of Step-2===");
        Assert.assertEquals(page.isStep2Functional(auth, username, password), true);
    }
   
//=========================================== STEP-2 ============================================================// 
//=========================================== STEP-3 ============================================================// 
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testFunctionOfStep_2" }, alwaysRun=true, sequential=true)
    @Parameters("auth")
    public void testSanity_Step3(String auth) throws Exception {
        System.out.println("===Testing Sanity Step-3===");
        Assert.assertEquals(page.checkStep3Sanity(), true);
    }
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testSanity_Step3" }, alwaysRun=true, sequential=true)
    @Parameters("auth")
    public void testHeader_Step3(String auth) throws Exception {
        System.out.println("===Testing Header Step-3===");
        Assert.assertEquals(page.checkHeader(), true);
     }
    
   @Test(groups = "integrationtest",dependsOnMethods="testHeader_Step3", alwaysRun=true)
   @Parameters("auth")
    public void testNewFooter_Step3(String auth) throws Exception
    {
       System.out.println("===Testing New Footer Step-3===");
        Assert.assertEquals(page.checkNewFooter(), true);
    } 
   
   @Test(groups = "integrationtest", dependsOnMethods = { "testNewFooter_Step3" }, alwaysRun=true, sequential=true)
   @Parameters("auth")
   public void testFooter_Step3(String auth) throws Exception {
       System.out.println("===Testing Footer Step-3===");
       Assert.assertEquals(page.checkFooter(), true);
   }
   
   @Test(groups = "integrationtest", dependsOnMethods = { "testFooter_Step3" }, alwaysRun=true, sequential=true)
   @Parameters("auth")
   public void testPaymentStep(String auth) throws Exception {
       System.out.println("===Testing Payment Step===");
       Assert.assertEquals(page.checkPaymentStep(), true);
   }
   
 //=========================================== STEP-3 ============================================================// 
 //=========================================== STEP-4 ============================================================// 
   
   @Test(groups = "integrationtest", dependsOnMethods = { "testPaymentStep" }, sequential=true, alwaysRun=true)
   @Parameters("auth")
   public void testSanity_Step4(String auth) throws Exception {
       System.out.println("===Testing Sanity Step-4===");
       Assert.assertEquals(page.checkStep4Sanity(), true);
   }
   @Test(groups = "integrationtest", dependsOnMethods = { "testSanity_Step4" }, alwaysRun=true, sequential=true)
   @Parameters("auth")
   public void testHeader_Step4(String auth) throws Exception {
       System.out.println("===Testing Header Step-4===");
       Assert.assertEquals(page.checkHeader(), true);
    }
   
  @Test(groups = "integrationtest",dependsOnMethods="testHeader_Step4", alwaysRun=true)
  @Parameters("auth")
   public void testNewFooter_Step4(String auth) throws Exception
   {
      System.out.println("===Testing New Footer Step-4===");
       Assert.assertEquals(page.checkNewFooter(), true);
   } 
  
  @Test(groups = "integrationtest", dependsOnMethods = { "testNewFooter_Step4" }, alwaysRun=true, sequential=true)
  @Parameters("auth")
  public void testFooter_Step4(String auth) throws Exception {
      System.out.println("===Testing Footer Step-4===");
      Assert.assertEquals(page.checkFooter(), true);
  }
  //=========================================== STEP-4 ============================================================//   
    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        System.out.println("===Quiting===");
        page.quitDriver();
    }
}
