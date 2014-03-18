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
public class UsingEShopPage extends EcomSetup{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static EShopPage page;

    @BeforeClass(groups={"integrationtest","quick","onlyGB"})
    @Parameters({ "browser", "locale", "page", "javascriptRequired","product" })
    public void UsingEShopPage1(String browser, String locale, String page1, boolean javascriptRequired, String product)
            throws Exception {
        Logger.getLogger(HTMLUNIT_CLASSNAME).setLevel(HTMLUNIT_LOG_LEVEL);
        CwsAbstractBaseTest.locale=locale;
        page = new EShopPage(browser, host, javascriptRequired);
        String url = "http://" + host + "/" + locale + "/" + page1 + "/";
        page.openUrl(url);
    }
    
    
    @Test(groups = { "integrationtest" }, sequential=true)
    @Parameters("product")

    /**
     * In this method buy now is clicked for an item then php loaded then clicked on add to cart
     * Then verified that first step check out page is loaded
     */
    public void testDataTransferToCheckoutPage(String product) throws Exception {
        Assert.assertEquals(page.isDataTransferredToCheckoutPage(product), true);
    }
    
    
    /**
     * In this method Footer is tested
     * @throws Exception
     */
   @Test(groups = "integrationtest", dependsOnMethods = { "testDataTransferToCheckoutPage" }, alwaysRun=true, sequential=true)
    @Parameters("product")
    public void testFooter(String product) throws Exception {
        Assert.assertEquals(page.checkFooter(), true);
    }
    
    /**
     * In this method First Step of check out page or order summary page is tested for 
     * functional working
     * @throws Exception
     */
    @Test(groups = "integrationtest", dependsOnMethods = { "testFooter" }, alwaysRun=true,sequential=true)
    @Parameters("product")
    public void testFunctionOfOrderSummary(String prod) throws Exception
    {
    	Assert.assertEquals(page.isOrderSummaryPageFunctional(prod), true);
    }
    
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testFunctionOfOrderSummary" }, alwaysRun=true,sequential=true)
    @Parameters({"locale", "product"})
    public void testVoucherFeature(String locale, String product) throws Exception
    {
        if(locale.contains("gb"))
        {
            Assert.assertEquals(page.IsVoucherWorking(), true);
        }
        else
            Reporter.log("This test is executed for gb locale only");
    }
    /**
     * In this method First Step Check Out page is verified that all fields are present.
     * Then Continue button is clicked.
     * @throws Exception
     */
    @Test(groups = "integrationtest", dependsOnMethods = { "testVoucherFeature" }, alwaysRun=true,sequential=true)
     @Parameters("product")
    public void testOrderSummaryPageSanity(String product) throws Exception {
        Assert.assertEquals(page.checkOrderSummaryPageSanity(product), true);
    }
    
    
    
    
    
    @Test(groups = { "integrationtest" }, dependsOnMethods = { "testOrderSummaryPageSanity" }, alwaysRun=true, sequential=true)
    @Parameters({"product" })
    public void testLoginFunctionality_San(String product) throws Exception {
        Assert.assertEquals(page.isLoginFunctionalityWorking(username, password), true);
    }
    
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testLoginFunctionality_San" }, sequential=true)
    @Parameters("product")
    public void testBillingDetailsPage(String product) throws Exception {
        Assert.assertEquals(page.checkBillingDetailsPage(), true);
    }
    
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testBillingDetailsPage" }, alwaysRun=true, sequential=true)
    @Parameters("product")
    public void testDeliveryMethod(String product) throws Exception {
        Assert.assertEquals(page.checkDeliveryMethod(), true);
    }
    
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testDeliveryMethod" }, alwaysRun=true, sequential=true)
    @Parameters("product")
    public void testPaymentPage(String product) throws Exception {
        Assert.assertEquals(page.isPaymentPageDisplayed(), true);
    }
    
    
    @Test(groups = "integrationtest", dependsOnMethods = { "testPaymentPage" }, sequential=true, alwaysRun=true)
    @Parameters("product")
    public void testConfirmationPage(String product) throws Exception {
        Assert.assertEquals(page.isconfirmationPageDisplayed(product), true);
        Assert.assertEquals(page.checkFooter(), true);
    }
    //    @Test(groups = { "integrationtest" }, dependsOnMethods = { "testDataTransferToCheckoutPage" })
    //    @Parameters({ "username", "password" })
    //    public void testLoginFunctionality_Int(String username, String password) throws Exception {
    //        Assert.assertEquals(page.isLoginFunctionalityWorking(username, password), true);
    //    }
    //    @Test(groups = "integrationtest", dependsOnMethods = { "testLoginFunctionality_Int" })
    //    public void testCheckoutPage() throws Exception {
    //        Assert.assertEquals(page.isCheckoutPageProper(), true);
    //    }
    
    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        page.quitDriver();
    }
}
