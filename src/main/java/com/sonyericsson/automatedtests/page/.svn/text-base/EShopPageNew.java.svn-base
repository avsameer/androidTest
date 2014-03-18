package com.sonyericsson.automatedtests.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.sikuli.script.Screen;
import org.hamcrest.core.IsNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.SelectOption;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.antlr.PythonParser.return_stmt_return;
import org.testng.Assert;
import org.testng.Reporter;

import sun.security.provider.certpath.OCSP.RevocationStatus;
import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.thoughtworks.selenium.Selenium;

public class EShopPageNew extends CwsAbstractBaseTest {
    private static final String BRACKET = "\\)";
	String host = "";
    public WebDriver driver;
    public String parent;
    public Set<String> handlers;
    public String itemName;
    public String itemCost;
    public EShopPageNew(String browser, String targetHost, boolean javascriptRequired) throws Exception {
        init(browser, targetHost, javascriptRequired);
    }
    public void quitDriver() {
        Reporter.log("Calling Quit Driver");
        destroy();
    }
    public boolean isDataTransferredToCheckoutPage() throws Exception {

    	try
    	{
    		//String pathToItem = "//div[@class='cols sales-item-row first-row  clearfix loaded']/a";
    		//Assert.assertTrue(isElementPresent(By.xpath(pathToItem + "/img")), "Image of item not present");
    		Reporter.log("Searching product with Add to cart button...");
    		if(!searchPHPwithButton(By.cssSelector("a.cta-button.cta-add-to-cart.large")))
    		{
    		    Reporter.log("No product found with add to cart button");
    		    return false;
    		}
    		itemName = getText(By.tagName("h1"));
    		Reporter.log("Product name under testing is :"+ itemName);
    		click(By.cssSelector("a.cta-button.cta-add-to-cart.large"));
    		Reporter.log("Add to cart clicked");
    		Thread.sleep(3000);
    		if(isElementDisplayed(By.cssSelector("div.cart-top-wrapper.cart-empty")))
    		{
    		    Reporter.log("Cart is still empty, clicking add to cart again");
    		    click(By.cssSelector("a.cta-button.cta-add-to-cart.large"));
    		}
    		Assert.assertTrue(isElementPresent(By.id("cart-checkout")), "Go to checkout button not present");
    		click(By.id("cart-checkout"));
    		Reporter.log("Go to checkout clicked");
    		Thread.sleep(5000);
    		waitForElementPresent(By.xpath("//table[@class='sales-item-table']"));
    		if (isElementDisplayed(By.xpath("//table[@class='sales-item-table']"))&& isElementDisplayed(By.cssSelector("tr.sales-item.available"))) {
    				Reporter.log("Checkout page displayed");
    				return true;
    		}
    		Reporter.log("Element(s) not found");
    		return false;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
   
    public boolean checkHeader() throws Exception {
        return this.testQuickHeaderNavLinksGenericForCheckout();
    }
    public boolean checkFooter() throws Exception {
    	return this.testQuickFooterNavLinksGeneric();
    }
    
    public boolean checkNewFooter() throws Exception {
        return this.isNewFooterPresent();
    }
    
    public boolean checkStep1Sanity() throws Exception {
        try
        {
            Assert.assertTrue(isElementDisplayed(By.tagName("h1")) && isElementDisplayed(By.className("chk-icon")),"Checkout icon at top not found");
            Reporter.log("Checkout icon at top found");
            if(isElementDisplayed(By.className("step-row")))
            { 
                checkStepsAndCheckoutIcon();
                Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 1,"Step 1 is not selected");
                Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 1,"Step 1 name is not selected");
                Reporter.log("Step 1 is selected properly");
                Assert.assertEquals(getIndexOfSelectedStep(By.cssSelector("div.step-row.marker"), "step-num"), 1,"Marker is not at step 1");
                Reporter.log("Marker is at step 1");

            }
            else
                throw ( new Exception("Row showing step numbers not found"));
            Assert.assertTrue(isElementDisplayed(By.id("cart-details")),"Details not available");
            Reporter.log("Details available");
            Assert.assertTrue(isElementDisplayed(By.className("sales-item-table")),"Item table not displayed");
            Reporter.log("Item table displayed");
            int count = getElementCount(By.xpath("//table[@class='sales-item-table']/thead/tr/th"));
            Assert.assertEquals(count, 5,"Headings of table not found or not proper");
            for(int i=1 ; i<count ; i++)
            {
                Assert.assertFalse(getText(By.xpath("//table[@class='sales-item-table']/thead/tr/th["+i+"]")).isEmpty(),"Empty Heading found");
                Reporter.log("Tested heading : "+i);
            }
            Reporter.log("Headings of table are proper");

            Assert.assertTrue(isElementDisplayed(By.xpath("//tr[@class='sales-item available']")),"Item row not available");
            Assert.assertTrue(isElementDisplayed(By.xpath("//table[@class='sales-item-table']/tbody/tr/td[@class='item-desc']/img")),"Item image not found");
            Reporter.log("Item image available");
            Assert.assertTrue(isElementDisplayed(By.xpath("//table[@class='sales-item-table']/tbody/tr/td[@class='item-desc']/div/p[@class='item-status status-instock']")),"Instock status not found");
            Reporter.log("Instock status found");
            Assert.assertTrue(isElementDisplayed(By.xpath("//table[@class='sales-item-table']/tbody/tr/td[@class='item-price']")),"Place for price not present");
            Assert.assertFalse(getText(By.xpath("//table[@class='sales-item-table']/tbody/tr/td[@class='item-price']")).isEmpty(),"Unit Price is empty");
            Reporter.log("Non empty unit price present");
            Assert.assertTrue(isElementDisplayed(By.xpath("//table[@class='sales-item-table']/tbody/tr/td[@class='item-quantity']")),"Place for quantity not present");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("a.btn-minus")),"Minus button not found");
            Reporter.log("Minus button available");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("a.btn-plus")),"Plus button not found");
            Reporter.log("Plus button available");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("span.qty-txt")),"Quantity not found");
            Reporter.log("Quantity available");
            Assert.assertTrue(isElementDisplayed(By.xpath("//table[@class='sales-item-table']/tbody/tr/td[@class='item-total']")),"Item subtotal not present");
            Assert.assertFalse(getText(By.xpath("//table[@class='sales-item-table']/tbody/tr/td[@class='item-total']")).isEmpty(),"Item subtotal is empty");
            Reporter.log("Non empty subtotal price present");
            Assert.assertTrue(isElementDisplayed(By.xpath("//table[@class='sales-item-table']/tbody/tr/td[@class='item-remove']/a[@class='btn-remove']")),"Button to remove item not present");
            Reporter.log("Button to remove item present");
            Assert.assertTrue(isElementDisplayed(By.id("shipping-options_cDD")) && isElementDisplayed(By.cssSelector("div#shipping-options_cDD > span.ddText")) &&
                    isElementDisplayed(By.cssSelector("div#shipping-options_cDD > span.ddButton")),"Delivery options dropdown not present");
            Reporter.log("Delivery option dropdown proper");
            Assert.assertTrue(isElementDisplayed(By.id("coupon-code")),"Coupon textbox not present");
            Reporter.log("Coupon textbox present");

            Assert.assertTrue(isElementDisplayed(By.cssSelector("p.shipping-note > img")),"Flag image not present");
            Reporter.log("Flag image present");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("p.shipping-note > span.icon-world")),"World map not present");
            Reporter.log("World map present");
            Assert.assertFalse(getText(By.cssSelector("p.shipping-note > strong")).isEmpty(),"Country name not present");
            Reporter.log("Country name present");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("p.shipping-note > a.tool-tip-trigger")),"Link to change country not present");
            Reporter.log("Link to change country present");

            Assert.assertFalse(getText(By.cssSelector("div.cart-summary > p.cart-total > span")).isEmpty(),"Total price not present");
            Reporter.log("Total price present");
            Assert.assertFalse(getText(By.cssSelector("div.cart-summary > p.cart-total > small")).isEmpty(),"VAT info not present");
            Reporter.log("VAT info present");

            Assert.assertTrue(isElementDisplayed(By.cssSelector("a.cta-button.btn-next")),"Next Step button not present");
            Reporter.log("Next Step button present");
            Assert.assertTrue(checkCheckoutURL(),"Checkout url not proper");
            Reporter.log("Checkout URL is proper");

            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    
    public boolean checkStep2Sanity()
    {
        try
        { 
            itemCost = getText(By.cssSelector("p.cart-total > span"));
            getDriver().findElement(By.cssSelector("a.cta-button.btn-next")).click();
            waitForElementPresent(By.id("delivery-address"));
            Reporter.log("Step 2 loaded");
            checkStepsAndCheckoutIcon();
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 2,"Step 2 is not selected");
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 2,"Step 2 name is not selected");
            Reporter.log("Step 2 is selected properly");
            Assert.assertEquals(getIndexOfSelectedStep(By.cssSelector("div.step-row.marker"), "step-num"), 2,"Marker is not at step 2");
            Reporter.log("Marker is at step 2");
            Assert.assertFalse(getText(By.cssSelector("div.block-login > h2")).isEmpty(),"Login block not available or returning customer text not present");
            Assert.assertFalse(getText(By.cssSelector("div.block-login > p")).isEmpty(),"Login block not available or paragraph text not present");
            Assert.assertFalse(getText(By.cssSelector("div.block-login > p > a")).isEmpty(),"Login block not available or login link not present");
            Reporter.log("Login block is proper");      
            List <WebElement> elements = getDriver().findElement(By.id("delivery-address")).findElements(By.tagName("input"));
            Assert.assertEquals(elements.size(), 9,"Delivery address textBoxes not displayed");
            Reporter.log("Delivery address textBoxes displayed");
            Assert.assertFalse(getText(By.cssSelector("div#delivery-address > h2")).isEmpty(),"Delivery address heading not found");
            Reporter.log("Delivery address heading found");
            List <WebElement> elements1 = getDriver().findElement(By.id("billing-address")).findElements(By.tagName("input"));
            Assert.assertEquals(elements1.size(), 9,"Billing address textBoxes not available");
            Reporter.log("Billing address textBoxes available");
            Assert.assertFalse(getText(By.cssSelector("div#billing-address > h2")).isEmpty(),"Billing address heading not found");
            Reporter.log("Billing address heading found");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("p.same-as.checked > input#sameasdelivery")),"Billing address not hidden");
            Reporter.log("Billing address hidden");
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)", "");
            Thread.sleep(2000);
            Screen s = new Screen();
            Reporter.log("Uncheck checkbox of billing address");
           s.click("img/checked.png");
           Assert.assertFalse(isElementDisplayed(By.cssSelector("p.same-as.checked > input#sameasdelivery")),"Billing address hidden");
            Reporter.log("Billing address textboxes displayed");
            Reporter.log("Check checkbox of billing address");
            Thread.sleep(2000);
            s.click("img/unchecked.png");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("p.same-as.checked > input#sameasdelivery")),"Billing address not hidden");
            Reporter.log("Billing address textboxes hidden");
            Thread.sleep(2000);
            s.click("img/checked.png");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("a.cta-button.btn-next")),"Next Step button not present");
            Reporter.log("Next Step button present");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("a.cta-button.btn-grey")),"Back button not present");
            Reporter.log("Back button present");
            Assert.assertTrue(checkCheckoutURL(),"Checkout url not proper");
            Reporter.log("Checkout URL is proper");
            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    public boolean isStep1Functional() throws Exception
    {
    	try
    	{
    		waitForElementPresent(By.cssSelector("a.btn-plus"));
    		Assert.assertFalse(isElementDisplayed(By.cssSelector("a.btn-minus.active")),"Inplace of inactive minus button active minus button found");
    		Reporter.log("Inactive minus button available");
    		Assert.assertTrue(isElementDisplayed(By.cssSelector("a.btn-plus.active")),"Active plus button not found");
    		Reporter.log("Active plus button available");	
    		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
    		int clickCount = 0;
    		for(int i=1;i<5;i++)
    		{
    		    getDriver().findElement(By.cssSelector("a.btn-plus.active")).click();
    		    clickCount = i;
    		    if(i < 4)
    		    {
    		        try
    		        {
    		            Thread.sleep(1000);
    		            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
    		        }
    		        catch(Exception ex)
    		        {}
    		    }
    		    try
    		    {
    		        if(getDriver().findElement(By.cssSelector("p.item-status.status-ltdstock.error-msg")).isDisplayed() || getDriver().findElement(By.cssSelector("p.item-status.status-maxltd.error-msg")).isDisplayed())
    		        {
    		            break;
    		        }
    		    }
    		    catch(Throwable ex){
    		        
    		    }
    		}
    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
    		if(clickCount == 4)
    		    Assert.assertEquals(getText(By.className("qty-txt")), String.valueOf(5),"Quantity should be 5 but it is not");
    		else
    		    Assert.assertTrue(Integer.valueOf(getText(By.className("qty-txt"))) > 1,"Quantity should be greater than 1");
    		Reporter.log("Quantity increases on click of plus");
    		Assert.assertFalse(isElementDisplayed(By.cssSelector("a.btn-plus.active")),"Inplace of inactive plus button active plus button found");
    		Reporter.log("Inactive plus button available on max quantity reached or stock limit reached");
    		Assert.assertTrue(isElementDisplayed(By.cssSelector("a.btn-minus.active")),"Active minus button not found");
    		Reporter.log("Active minus button available on max quantity reached or stock limit reached");
    		Assert.assertTrue(isElementDisplayed(By.cssSelector("p.item-status.error-msg")),"Warning message not appeared on max quantity reached or stock limit reached");
    		Reporter.log("Warning appeared on max quantity reached or stock limit reached");
    		Assert.assertTrue(isElementDisplayed(By.cssSelector("a.cta-button.btn-next.disabled")),"Disabled next step button not found");
    		Reporter.log("Next Step button disabled on max quantity reached or stock limit reached");
    		for(int i=1;i<= clickCount;i++)
    		{
    		    getDriver().findElement(By.cssSelector("a.btn-minus.active")).click();
    		    if(i != clickCount)
    		    {
    		        Thread.sleep(1000);
    		        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
    		    }
    		}
    		
    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
    		Assert.assertEquals(getText(By.className("qty-txt")), String.valueOf(1),"Quantity is not equal to 1 after decresing");
    		Reporter.log("Quantity decreases on click of minus");
    		Assert.assertFalse(isElementDisplayed(By.cssSelector("a.cta-button.btn-next.disabled")),"Next Step button not enabled");
    		Reporter.log("Next Step button Enabled");    		
    		return true;
    	}
    	catch (Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    	
    }
    public boolean IsVoucherWorking()
    {
    	try
    	{
    	    //test total = subtotal + delivery - discount if any
    		float initialPrice = removeCurrencySign(getText(By.cssSelector("td.item-price > p")));
    		Reporter.log("Unit price is : "+initialPrice);
    		int quantity = Integer.valueOf(getText(By.cssSelector("span.qty-txt")));
    		Reporter.log("Quantity is : "+quantity);
    		float subtotal = removeCurrencySign(getText(By.cssSelector("td.item-total")));
    		Reporter.log("Subtotal is : "+subtotal);
    		float deliveryCost = removeCurrencySign(getText(By.cssSelector("span.ddText > span + span")));
    		Reporter.log("Delivery cost is : "+deliveryCost);
    		float discountBefore = 0;
    		float strikeDifference = 0;
    	
    		boolean isDiscountPresent = false;
    		boolean isStrikePresent = false;
    		
    		if(isElementDisplayed(By.cssSelector("p.highlight")))
    		{
    		    Reporter.log("Strike price found");
    		    isStrikePresent = true;
    		    strikeDifference = (removeCurrencySign(getText(By.cssSelector("p.strike-price"))) - removeCurrencySign(getText(By.cssSelector("p.highlight")))) * quantity;
    		    Reporter.log("Difference in Strike price and sale price is : "+strikeDifference);
    		}
    		
    		List<WebElement> discountElementsBefore = null;
    		if(isElementDisplayed(By.cssSelector("div.promotions-info > p.promotion-info > span.highlight")))
            {
    		    Reporter.log("Promotional discount found");
                isDiscountPresent = true;
                Reporter.log("Checking discount messages");
                Assert.assertTrue(isDiscountMessagesProper(),"Discount messages not proper");
                Reporter.log("Discount messages are proper");
                discountElementsBefore = getDriver().findElements(By.cssSelector("div.promotions-info > p.promotion-info > span.highlight"));
                for(WebElement ele : discountElementsBefore)
                {
                    discountBefore += removeCurrencySign(ele.getText().substring(1));
                }
                Reporter.log("Total promotional discount before any coupon applied : "+discountBefore);
                //discount = removeCurrencySign(getText(By.cssSelector("div.promotions-info > p.promotion-info > span.highlight")).substring(1)); 
            }
   		
    		
    		float cartTotal = removeCurrencySign(getText(By.cssSelector("p.cart-total > span")));
    		Reporter.log("Cart total is before any coupon applied : "+cartTotal);
    		Assert.assertEquals(subtotal, (initialPrice * quantity),5e-2,"Subtotal is not proper may be error in calculation");
    		Assert.assertEquals(cartTotal,(subtotal + deliveryCost - discountBefore),5e-2,"cartTotal is not proper may be error in calculation");
    		if(isDiscountPresent || isStrikePresent)
    		{
    		    Reporter.log("Strike price or discount found, You Save should appear");
    		    Assert.assertTrue(isElementDisplayed(By.cssSelector("p.you-save > span")),"You Save not appeared");
    		    Reporter.log("You Save appeared");
    		    Assert.assertEquals(removeCurrencySign(getText(By.cssSelector("p.you-save > span"))), discountBefore+strikeDifference,5e-2,"You Save price not proper");
    		    Reporter.log("You Save price is proper");
    		}
    		
    		
    		waitForElementPresent(By.id("coupon-code"));
    		
    		//testing with invalid code
    		Reporter.log("Testing for invalid voucher code");
    		typeText(By.id("coupon-code"),"InvalidCode");
    		WebDriverWait wait = new WebDriverWait(getDriver(), 120);
    		try
    		{
    		    Thread.sleep(5000);
    		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("exposeMask")));
    		    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.req-err")));
    		}
    		catch(Exception ex)
    		{}
    		Assert.assertTrue(getDriver().findElement(By.cssSelector("p.error-msg.msg-invalid")).isDisplayed(),"Error message for invalid voucher not shown");
    		Reporter.log("Error message for invalid voucher present");
    		
    		//Testing with valid voucher
    		Reporter.log("Testing for valid voucher code");
    		getDriver().findElement(By.id("coupon-code")).clear();
    		typeText(By.id("coupon-code"),"FreeShipping_Testing");
    		try
            {
                Thread.sleep(5000);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("exposeMask")));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.read-only")));
            }
            catch(Exception ex)
            {}
            Assert.assertTrue(isElementDisplayed(By.className("indicator")),"Correct sign not present");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("p.field.applied  > span.read-only > input#coupon-code")) && 
                    !getDriver().findElement(By.cssSelector("p.field.applied  > span.read-only > input#coupon-code")).getAttribute("disabled").isEmpty(),
                    "Text box not get disabled");

            Reporter.log("Correct sign present, textbox disabled, Coupon applied successfully!!!");

            float discountAfter = 0;
            Reporter.log("Checking discount messages");
            Assert.assertTrue(isDiscountMessagesProper(),"Discount messages not proper");
            Reporter.log("Discount messages are proper");
            List<WebElement> discountElementsAfter = getDriver().findElements(By.cssSelector("div.promotions-info > p.promotion-info > span.highlight"));
            for(WebElement ele : discountElementsAfter)
            {
                discountAfter += removeCurrencySign(ele.getText().substring(1));
            }
            Reporter.log("Total discount after coupon applied : "+discountAfter);
            if(discountElementsBefore == null)
            {
                Assert.assertEquals(discountElementsAfter.size(), 1,"Discount applied message not appeared");
            }
            else
            {
                Assert.assertEquals(discountElementsAfter.size(), discountElementsBefore.size()+1,"Discount applied message not appeared");
            }
            Reporter.log("Discount applied message appeared properly");
            
            float cartTotal_afterDiscount = removeCurrencySign(getText(By.cssSelector("p.cart-total > span")));
            Reporter.log("Cart total after coupon applied : "+cartTotal_afterDiscount);
            float expectedCartTotal = (float) (((cartTotal - deliveryCost)*0.50) + deliveryCost);
            Assert.assertEquals(cartTotal_afterDiscount, expectedCartTotal,5e-2,"50% discount not applied by coupon");
            Reporter.log("50% discount applied by coupon");

    		Assert.assertEquals(cartTotal_afterDiscount, (subtotal + deliveryCost - discountAfter), 5e-2,"New cart total not proper");
    		Reporter.log("Cart total updated properly after application of coupon");
    		Assert.assertTrue(isElementDisplayed(By.className("you-save")),"You save text not found");
    		Reporter.log("You save text found");
    		float youSave = discountAfter + strikeDifference;
    		Assert.assertEquals(removeCurrencySign(getText(By.cssSelector("p.you-save > span"))), youSave,5e-2,"You save ammount not proper");
    		Reporter.log("You save ammount proper");
    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    
    public boolean isStep2Functional(String auth, String username, String password)
    {
        try
        {           
            WebDriverWait wait = new WebDriverWait(getDriver(), 300);
            Assert.assertFalse(isElementDisplayed(By.cssSelector("div.view-mode")) && isElementDisplayed(By.cssSelector("div.edit-mode")),"Blank textboxes not displayed");
            Reporter.log("Blank textboxes displayed");
            
            if(auth.equals("anonymous"))
            {
                populateHashsWithAddress("anonymous");
                //Fill details in Delivery address
                Reporter.log("Filling details is delivery address...");
                getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='firstName']")).sendKeys(deliveryHash.get("fname"));
                getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='lastName']")).sendKeys(deliveryHash.get("lname"));
                getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='street1']")).sendKeys(deliveryHash.get("street1"));
                getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='street2']")).sendKeys(deliveryHash.get("street2"));
                getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='zipOrPostalCode']")).sendKeys(deliveryHash.get("zipcode"));
                getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='city']")).sendKeys(deliveryHash.get("city"));
                getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='email']")).sendKeys(deliveryHash.get("email"));
                getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='phoneNumber']")).sendKeys(deliveryHash.get("number"));
                Reporter.log("Delivery address filled successfully");
                
               // ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-100)", "");
               // Thread.sleep(2000);

                //Fill details in Billing address
                //Screen s = new Screen();
                //s.click("img/checked.png");
                Reporter.log("Filling details is billing address...");
                getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='firstName']")).sendKeys(billingHash.get("fname"));
                getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='lastName']")).sendKeys(billingHash.get("lname"));
                getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='street1']")).sendKeys(billingHash.get("street1"));
                getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='street2']")).sendKeys(billingHash.get("street2"));
                getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='zipOrPostalCode']")).sendKeys(billingHash.get("zipcode"));
                getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='city']")).sendKeys(billingHash.get("city"));
                getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='phoneNumber']")).sendKeys(billingHash.get("number"));
                Reporter.log("Billing address filled successfully");
                
                getDriver().findElement(By.cssSelector("a.cta-button.btn-next")).click();
            }
            else if(auth.equals("loggedin"))
            {
                Assert.assertTrue(isLoginFunctionalityWorking(username, password),"LOGIN ERROR");
                populateHashsWithAddress("loggedin");
                Reporter.log("Checking for Staff discount.....");
                if(isElementDisplayed(By.cssSelector("div.staff-discount.grey-box")))
                {
                    Reporter.log("User got a staff discount");
                    Reporter.log("Going back to Step-1 to check updated price");
                    getDriver().findElement(By.cssSelector("a.cta-button.btn-grey")).click();
                    waitForElementPresent(By.cssSelector("p.cart-total > span"));
                    Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 1,"Step 1 is not selected");
                    Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 1,"Step 1 name is not selected");
                    Reporter.log("Step-1 loaded successfully");
                    itemCost = getText(By.cssSelector("p.cart-total > span"));
                    Reporter.log("Updated price stored,Going to Step-2");
                    getDriver().findElement(By.cssSelector("a.cta-button.btn-next")).click();
                    Thread.sleep(3000);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
                }
                else
                {
                    Reporter.log("User did not get Staff Discount.");
                }
                Reporter.log("Checking all fields at Step-2 passed validation....");
                if(fillIfValidationAppeared())
                {
                    populateHashsWithAddress("loggedin");
                    Reporter.log("Details saved, going to Step-3");
                    getDriver().findElement(By.cssSelector("a.cta-button.btn-next")).click();
                }
            }
                       
            Thread.sleep(3000);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
            Thread.sleep(3000);
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 3,"Step 3 is not selected");
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 3,"Step 3 name is not selected");
            Reporter.log("Step-3 loaded, going to step-2 to verify details are there");
            getDriver().findElement(By.cssSelector("a.cta-button.btn-grey")).click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 2,"Step 2 is not selected");
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 2,"Step 2 name is not selected");
            Reporter.log("Step-2 loaded");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.view-mode")),"Textboxes displayed");
            Reporter.log("No textboxes displayed");
            
            //testing details in delivery address
            Reporter.log("Checking Delivery address after coming back");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='firstName']/preceding-sibling::span")).getText()), getLowerTrimed(deliveryHash.get("fname")),"First name not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='lastName']/preceding-sibling::span")).getText()), getLowerTrimed(deliveryHash.get("lname")),"Last name not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='street1']/preceding-sibling::span")).getText()), getLowerTrimed(deliveryHash.get("street1")),"Street1 not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='street2']/preceding-sibling::span")).getText()), getLowerTrimed(deliveryHash.get("street2")),"Street2 not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='zipOrPostalCode']/preceding-sibling::span")).getText()), getLowerTrimed(deliveryHash.get("zipcode")),"ZIP code not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='city']/preceding-sibling::span")).getText()), getLowerTrimed(deliveryHash.get("city")),"City not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='email']/preceding-sibling::span")).getText()), getLowerTrimed(deliveryHash.get("email")),"Email not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='phoneNumber']/preceding-sibling::span")).getText()), getLowerTrimed(deliveryHash.get("number")),"Phone not matches");
            Reporter.log("All details matches in Delivery address after coming back");
            
          //testing details in billing address
            Reporter.log("Checking Billing address after coming back");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='firstName']/preceding-sibling::span")).getText()), getLowerTrimed(billingHash.get("fname")),"First name not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='lastName']/preceding-sibling::span")).getText()), getLowerTrimed(billingHash.get("lname")),"Last name not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='street1']/preceding-sibling::span")).getText()), getLowerTrimed(billingHash.get("street1")),"Street1 not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='street2']/preceding-sibling::span")).getText()), getLowerTrimed(billingHash.get("street2")),"Street2 not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='zipOrPostalCode']/preceding-sibling::span")).getText()), getLowerTrimed(billingHash.get("zipcode")),"ZIP code not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='city']/preceding-sibling::span")).getText()), getLowerTrimed(billingHash.get("city")),"City not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='phoneNumber']/preceding-sibling::span")).getText()), getLowerTrimed(billingHash.get("number")),"Phone not matches");
            Reporter.log("All details matches in Billing address after coming back");
            
            //check edit detail link
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div#billing-address > a.edit-address.cta-link")),"Edit all addresses link not present");
            Reporter.log("Edit all addresses link present");
            
            click(By.cssSelector("div#billing-address > a.edit-address.cta-link"));
            Reporter.log("Edit address link clicked");
            Thread.sleep(2000);
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.edit-mode")),"Textboxes not displayed");
            Reporter.log("Textboxes displayed");
            
            //testing details in delivery address after hitting edit
            Reporter.log("Checking Delivery address after hitting edit");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='firstName']")).getAttribute("value")), getLowerTrimed(deliveryHash.get("fname")),"First name not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='lastName']")).getAttribute("value")), getLowerTrimed(deliveryHash.get("lname")),"Last name not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='street1']")).getAttribute("value")), getLowerTrimed(deliveryHash.get("street1")),"Street1 not matches");
            String street2_d = getLowerTrimed(deliveryHash.get("street2"));
            if(street2_d.equals("-"))
                Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='street2']")).getAttribute("value")), "","Street2 not matches");
            else   
                Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='street2']")).getAttribute("value")), street2_d,"Street2 not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='zipOrPostalCode']")).getAttribute("value")), getLowerTrimed(deliveryHash.get("zipcode")),"ZIP code not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='city']")).getAttribute("value")), getLowerTrimed(deliveryHash.get("city")),"City not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='email']")).getAttribute("value")), getLowerTrimed(deliveryHash.get("email")),"Email not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='phoneNumber']")).getAttribute("value")), getLowerTrimed(deliveryHash.get("number")),"Phone not matches");
            Reporter.log("All details matches in Delivery address after hitting edit");
            
          //testing details in billing address after hitting edit
            Reporter.log("Checking Billing address after hitting edit");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='firstName']")).getAttribute("value")), getLowerTrimed(billingHash.get("fname")),"First name not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='lastName']")).getAttribute("value")), getLowerTrimed(billingHash.get("lname")),"Last name not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='street1']")).getAttribute("value")), getLowerTrimed(billingHash.get("street1")),"Street1 not matches");
            String street2_b = getLowerTrimed(billingHash.get("street2"));
            if(street2_b.equals("-"))
                Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='street2']")).getAttribute("value")), "","Street2 not matches");
            else
                Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='street2']")).getAttribute("value")), street2_b,"Street2 not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='zipOrPostalCode']")).getAttribute("value")), getLowerTrimed(billingHash.get("zipcode")),"ZIP code not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='city']")).getAttribute("value")), getLowerTrimed(billingHash.get("city")),"City not matches");
            Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='phoneNumber']")).getAttribute("value")), getLowerTrimed(billingHash.get("number")),"Phone not matches");
            Reporter.log("All details matches in Billing address after hitting edit");

            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    
    public boolean checkStep3Sanity()
    {
        try
        {
            String currentUrl = getDriver().getCurrentUrl();
            String env = "";
            if(currentUrl.contains("shop-dev-int") || currentUrl.contains("shop-dev-demo"))
            {
                env = "local";
            }
            else if(currentUrl.contains("shop-stage.sonymobile.com") || currentUrl.contains("shop-int.sonymobile.com"))
            {
                env = "stage";
            }
            else if(currentUrl.contains("shop.sonymobile.com"))
            {
                env = "prod";
            }
         
            WebDriverWait wait = new WebDriverWait(getDriver(), 300);
            getDriver().findElement(By.cssSelector("a.cta-button.btn-next")).click();
            Thread.sleep(3000);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
            Thread.sleep(3000);
            checkStepsAndCheckoutIcon();
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 3,"Step 3 is not selected");
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 3,"Step 3 name is not selected");
            Reporter.log("Step 3 is selected properly");
            Assert.assertEquals(getIndexOfSelectedStep(By.cssSelector("div.step-row.marker"), "step-num"), 3,"Marker is not at step 3");
            Reporter.log("Marker is at step 3");
            String url = getDriver().findElement(By.tagName("iframe")).getAttribute("src");
            Reporter.log("Iframe src is : "+url);
            if(env.equals("prod"))
                Assert.assertTrue(url.contains("https://payment.sonymobile.com/payment/SEMC"),"Iframe do not has proper src");
            else if(env.equals("stage") || env.equals("local"))
                Assert.assertTrue(url.contains("https://payment-stage.sonymobile.com/payment/SEMC"),"Iframe do not has proper src");
           // else if(env.equals("local"))
               // Assert.assertTrue(url.contains("https://global-engineplus-uat.moduslink.com/payment/SEMC"),"Iframe do not has proper src");
            Assert.assertTrue(checkCheckoutURL(),"Checkout url not proper");
            Reporter.log("Checkout URL is proper");
            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    
    public boolean checkPaymentStep()
    {
        try
        {         
            switchFrame(0);

            click(By.cssSelector("span.ddButton"));
            Thread.sleep(1000);
            List <WebElement> elements = getDriver().findElements(By.cssSelector("div#customDDOptions > a"));
            for(WebElement ele : elements)
            {
                if(ele.getText().equals("AMEX"))
                {
                    ele.click();
                    break;
                }
            }
            Reporter.log("AMEX selected for payment");
            Thread.sleep(1000);

            typeText(By.id("ctl00_ContentPlaceHolder1_CreditCardControl2_txtCardNumber"), "378282000000131");
            typeText(By.id("ctl00_ContentPlaceHolder1_CreditCardControl2_txtCardHolder"), "test");
            click(By.xpath("//div[@id='ctl00_ContentPlaceHolder1_CreditCardControl2_ddlExpiryYear_cDD']/span"));
            click(By.xpath("//div[@id='customDDOptions']/a[6]"));
            typeText(By.id("ctl00_ContentPlaceHolder1_CreditCardControl2_txtCvcCode"), "001");
            click(By.id("ctl00_ContentPlaceHolder1_TermsAndConditionsImage"));
            click(By.id("ctl00_ContentPlaceHolder1_SubmitPaymentbutton1"));
            Reporter.log("Card details filled and place order button clicked");
            getDriver().switchTo().defaultContent();
            return true;

        }
        catch(Throwable ex)
        {
            getDriver().switchTo().defaultContent();
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    public boolean isLoginFunctionalityWorking(String username, String password) throws Exception {
    	try
    	{
    		driver = getDriver();
    		parent = driver.getWindowHandle();
    		handlers = driver.getWindowHandles();
    		
    		click(By.cssSelector("div.block-login > p > a"));
    		Reporter.log("Log In Link clicked");
    		switchWindow();
    		Thread.sleep(10000);
    		waitForElementPresent(By.id("j_username"));
    		if(!isElementDisplayed(By.id("j_username")))
    		    switchWindow();
    		
    		if (isElementDisplayed(By.id("j_username"))) {
    			// handlers = driver.getWindowHandles();
    			//  String popupHandle = driver.getWindowHandle();
    			typeText(By.id("j_username"), username);
    			typeText(By.id("j_password"), password);
    			click(By.id("submit-button"));
    			Thread.sleep(8000);
    			getDriver().switchTo().window(parent);
    			Thread.sleep(10000);
    			waitForElementPresent(By.cssSelector("div.view-mode"));
    			Reporter.log("User Logged in successfully");
    			
    			return true;	
    		}
    		else
    		    return false;
    		
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    
    public boolean checkBillingDetailsPage() throws Exception {
    	try
    	{
    		waitForElementPresent(By.id("step2BtnClick"));
    		Thread.sleep(10000);
    		Assert.assertTrue(isElementDisplayed(By.id("accordionPanel1")));
    		Assert.assertTrue(isElementDisplayed(By.className("actHdr")));
    		Assert.assertTrue(isElementDisplayed(By.id("address")));
    		Assert.assertTrue(isElementDisplayed(By.className("bdDtlsTxt")));
    		Assert.assertTrue(isElementDisplayed(By.id("billingAddress")));
    		Assert.assertTrue(isElementDisplayed(By.className("bdDtlsHdr")));
    		Assert.assertTrue(isElementDisplayed(By.className("bdDtlsBlkTxt")));
    		Assert.assertTrue(isElementDisplayed(By.className("form")));
    		Assert.assertTrue(isElementDisplayed(By.id("deliveryAddress")));
    		Assert.assertTrue(isElementDisplayed(By.className("delivery")));
    		Assert.assertTrue(isElementDisplayed(By.className("ddDtlsHdr")));
    		Assert.assertTrue(isElementDisplayed(By.className("ddDtlsBlkTxt")));
    		Assert.assertTrue(isElementDisplayed(By.className("DeliveryOpt")));
    		Assert.assertTrue(isElementDisplayed(By.className("delOptHdr")));
    		Assert.assertTrue(isElementDisplayed(By.className("dropMenu")));
    		Assert.assertTrue(isElementDisplayed(By.className("delOptHdr")));
    		Assert.assertTrue(isElementDisplayed(By.id("shippingOptions_cDD")));
    		Reporter.log("Billing details page verified");

    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    
    public boolean checkDeliveryMethod(String locale) throws Exception
    {
    	try
    	{
    	    WebDriverWait wait = new WebDriverWait(getDriver(), 120);
    	    Thread.sleep(5000);
    	    if(locale.equals("gb"))
    	    {
    	        float currentTotalPrice = removeCurrencySign(getText(By.cssSelector("p.cart-total > span")));
    	        float currentDelPrice = removeCurrencySign(getText(By.cssSelector("span.ddText > span + span")));
    	        Reporter.log("Current delivery cost is : "+currentDelPrice);
    	        Reporter.log("Current Total cost is : "+currentTotalPrice);
    	        Reporter.log("Checking second delivery option present............");
    	        getDriver().findElement(By.className("ddButton")).click();
    	        if(isElementDisplayed(By.cssSelector("div#customDDOptions > a+a")))
    	        {
    	            getDriver().findElement(By.cssSelector("div#customDDOptions > a+a")).click();
    	            Thread.sleep(3000);
    	            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
    	            wait.until(ExpectedConditions.elementToBeClickable(By.className("ddButton")));
    	            Reporter.log("Second delivery option found and selected");
                    float newDelPrice = removeCurrencySign(getText(By.cssSelector("span.ddText > span + span")));
                    float newTotalExpected = currentTotalPrice - currentDelPrice + newDelPrice;
                    Assert.assertEquals(removeCurrencySign(getText(By.cssSelector("p.cart-total > span"))), newTotalExpected,5e-2,"Total price not as expected after changing delivery option");
                    Reporter.log("Total price changed properly after changing delivery option");
                    Reporter.log("Again selecting first delivery option");
                    getDriver().findElement(By.className("ddButton")).click();
                    getDriver().findElement(By.cssSelector("div#customDDOptions > a")).click();
                    Thread.sleep(3000);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
                    wait.until(ExpectedConditions.elementToBeClickable(By.className("ddButton")));
                    Reporter.log("First delivery option selected");
                    Assert.assertEquals(removeCurrencySign(getText(By.cssSelector("p.cart-total > span"))), currentTotalPrice,5e-2,"Total price not as expected after changing delivery option again");
                    Reporter.log("Total price changed properly after changing delivery option again");
    	        }
    	        else
    	        {
    	            Reporter.log("Second delivery option not found".toUpperCase());
    	        }   
    	    }
    	    else
    	    {
    	        String currentTotalPrice = getText(By.cssSelector("p.cart-total > span"));
                String currentDelPrice = getText(By.cssSelector("span.ddText > span + span"));
                Reporter.log("Current delivery cost is : "+currentDelPrice);
                Reporter.log("Current Total cost is : "+currentTotalPrice);
                Reporter.log("Checking second delivery option present............");
                getDriver().findElement(By.className("ddButton")).click();
                if(isElementDisplayed(By.cssSelector("div#customDDOptions > a+a")))
                {
                    getDriver().findElement(By.cssSelector("div#customDDOptions > a+a")).click();
                    Thread.sleep(3000);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
                    wait.until(ExpectedConditions.elementToBeClickable(By.className("ddButton")));
                    Reporter.log("Second delivery option found and selected");
                    String newDelPrice = getText(By.cssSelector("span.ddText > span + span"));
                    String newTotalPrice = getText(By.cssSelector("p.cart-total > span"));
                    Assert.assertFalse(newTotalPrice.equals(currentTotalPrice),"Total price not as expected after changing delivery option");
                    Reporter.log("Total price changed properly after changing delivery option");
                    Reporter.log("Again selecting first delivery option");
                    getDriver().findElement(By.className("ddButton")).click();
                    getDriver().findElement(By.cssSelector("div#customDDOptions > a")).click();
                    Thread.sleep(3000);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
                    wait.until(ExpectedConditions.elementToBeClickable(By.className("ddButton")));
                    Reporter.log("First delivery option selected");
                    String new2TotalPrice = getText(By.cssSelector("p.cart-total > span"));
                    Assert.assertEquals(new2TotalPrice,currentTotalPrice,"Total price not as expected after changing delivery option again");
                    Reporter.log("Total price changed properly after changing delivery option again");
                }
                else
                {
                    Reporter.log("Second delivery option not found".toUpperCase());
                }   
    	    }
    		return true;
    	}
    	catch (Throwable ex)
    	{
    	    Reporter.log("Note: Failures in checking delivery option may occur due to Shipping discount if any");
    	    Reporter.log("please find product name tested in testDataTransferToCheckoutPage method and check manually");;
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	} 	
    }
   
    public boolean checkStep4Sanity() throws Exception 
    {
    	try
    	{
    	    for(int i=0;i<20;i++)
    	    {
    	        if(isElementDisplayed(By.cssSelector("div.LoadingBody")))
    	        {
    	            Thread.sleep(1000);
    	        }
    	        else
    	            break;
    	    }
    	   
    	   waitForElementPresent(By.cssSelector("div.order-confirmation"));
    	   Assert.assertTrue(isElementDisplayed(By.cssSelector("div.order-confirmation")),"Confirmation step not loaded");
    	   getDriver().switchTo().defaultContent();
    	   Reporter.log("Confirmation step loaded");
    	   checkStepsAndCheckoutIcon();
           Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 4,"Step 4 is not selected");
           Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 4,"Step 4 name is not selected");
           Reporter.log("Step 4 is selected properly");
           Assert.assertEquals(getIndexOfSelectedStep(By.cssSelector("div.step-row.marker"), "step-num"), 4,"Marker is not at step 4");
           Reporter.log("Marker is at step 4");
           Assert.assertTrue(checkCheckoutURL(),"Checkout url not proper");
           Reporter.log("Checkout URL is proper");
    	   Assert.assertFalse(getDriver().findElement(By.cssSelector("div.order-confirmation > h2")).getText().isEmpty(),"Thanks giving heading not appeared");
    	   Reporter.log("Thanks giving heading appeared");
    	   Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.cssSelector("div.order-confirmation > h2 + p > strong")).getText()), getLowerTrimed(deliveryHash.get("email")),"Email at confirmation message is not wtaht given at step2");
    	   Reporter.log("Proper email appeared at confirmation");
    	   Assert.assertFalse(getDriver().findElement(By.cssSelector("div.order-confirmation > h2 + p + p > strong")).getText().isEmpty(),"Order number not appeared");
    	   Reporter.log("Order number appeared");
    	    
    	   Assert.assertFalse(getDriver().findElement(By.cssSelector("div.order-summary > h2")).getText().isEmpty(),"Order Summary heading not appeared");
           Reporter.log("Order Summary heading appeared");
           Assert.assertTrue(isElementDisplayed(By.cssSelector("div.summary-print > a")),"Print not appeared");
           Reporter.log("Print appeared");
           
           Reporter.log("Checking Delivery address details....");
           Assert.assertFalse(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[1]/h3")).getText().isEmpty(),"heading is empty");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[1]/table/tbody/tr[1]/td")).getText()), getLowerTrimed((deliveryHash.get("fname")+ " "+deliveryHash.get("lname"))),"Name not matching");
           String street2_d = getLowerTrimed(deliveryHash.get("street2"));
           if(street2_d.equals("-"))
               Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[1]/table/tbody/tr[2]/td")).getText()), getLowerTrimed(deliveryHash.get("street1")),"Address not matching");
           else
               Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[1]/table/tbody/tr[2]/td")).getText()), getLowerTrimed((deliveryHash.get("street1")+ " "+deliveryHash.get("street2"))),"Address not matching");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[1]/table/tbody/tr[3]/td")).getText()), getLowerTrimed(deliveryHash.get("zipcode")),"ZIP code not matching");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[1]/table/tbody/tr[4]/td")).getText()), getLowerTrimed(deliveryHash.get("city")),"City not matching");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[1]/table/tbody/tr[6]/td")).getText()), getLowerTrimed(deliveryHash.get("email")),"Email not matching");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[1]/table/tbody/tr[7]/td")).getText()), getLowerTrimed(deliveryHash.get("number")),"Number not matching");
           Reporter.log("Delivery address matched successfully");
           
           Reporter.log("Checking Billing address details....");
           Assert.assertFalse(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[2]/h3")).getText().isEmpty(),"heading is empty");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[2]/table/tbody/tr[1]/td")).getText()), getLowerTrimed((billingHash.get("fname")+ " "+billingHash.get("lname"))),"Name not matching");
           String street2_b = getLowerTrimed(billingHash.get("street2"));
           if(street2_b.contains("-"))
               Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[2]/table/tbody/tr[2]/td")).getText()), getLowerTrimed(billingHash.get("street1")),"Address not matching");
           else
               Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[2]/table/tbody/tr[2]/td")).getText()), getLowerTrimed((billingHash.get("street1")+ " "+billingHash.get("street2"))),"Address not matching");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[2]/table/tbody/tr[3]/td")).getText()), getLowerTrimed(billingHash.get("zipcode")),"ZIP code not matching");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[2]/table/tbody/tr[4]/td")).getText()), getLowerTrimed(billingHash.get("city")),"City not matching");
           Assert.assertEquals(getLowerTrimed(getDriver().findElement(By.xpath("//div[contains(@class,'order-add-summary')]/div[2]/table/tbody/tr[6]/td")).getText()), getLowerTrimed(billingHash.get("number")),"Number not matching");
           Reporter.log("Billing address matched successfully");
           
           Reporter.log("Cheacking order summary...");
           Assert.assertFalse(getDriver().findElement(By.cssSelector("div.order-cost-summary > h3")).getText().isEmpty(),"Order Summar heading not present");
           List<WebElement> elements = getDriver().findElements(By.cssSelector("table.sales-item-table > thead > tr > th"));
           for(WebElement ele : elements)
           {
               Assert.assertFalse(ele.getText().isEmpty(),"Order table heading is empty");
           }
           Assert.assertTrue(isElementDisplayed(By.cssSelector("tr.sales-item > td.item-desc > img")),"Item image not present at order summary");
           Assert.assertEquals(getText(By.cssSelector("tr.sales-item > td.item-desc > div")).trim(), itemName,"Item name at order summary is differ from PHP");
           Assert.assertFalse(getText(By.cssSelector("tr.sales-item > td.item-price > p")).isEmpty(),"Unit price not present");
           Assert.assertEquals(getText(By.cssSelector("tr.sales-item > td.item-quantity > div > span.qty-txt")), String.valueOf(1),"Quantity is not correct");
           Assert.assertFalse(getText(By.cssSelector("tr.sales-item > td.item-total")).isEmpty(),"Subtotal not present");
           Assert.assertFalse(getText(By.cssSelector("tr.add-cost-info")).isEmpty(),"Delivery type not present");
           Assert.assertEquals(getText(By.cssSelector("div.order-total > p > em")), itemCost, "Total is not same as step 1");
           Assert.assertFalse(getText(By.cssSelector("div.order-total > p.tax-info")).isEmpty(),"Tax info not present");
           Reporter.log("Order summary is proper");
    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    public boolean isCheckoutPageProper() throws Exception {
    	try
    	{
    		switchWindow();
    		if (isElementDisplayed(By.className("itemContainer")) && isElementDisplayed(By.className("itemList")) && isElementDisplayed(By.id("siItemList"))) {
    			if (isElementDisplayed(By.className("itemContainer")) && isElementDisplayed(By.className("itemListFooter")) && isElementDisplayed(By.tagName("a"))) {
    				//click(By.className("itemContainer"), By.className("itemListFooter"), By.tagName("a"));
    			}
    		}
    		if (isElementDisplayed(By.id("accordionPanel1"))) {
    			click(By.id("step2BtnClick"));
    			return true;
    		}
    		Reporter.log("Element not displayed");
    		return false;
    	}
    	catch (Exception ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
 
    public boolean fillIfValidationAppeared() throws Exception
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), 300);
        Reporter.log("Going to Step-3");
        getDriver().findElement(By.cssSelector("a.cta-button.btn-next")).click();
        Thread.sleep(10000);
        if(isElementDisplayed(By.cssSelector("input.req-err")))
        {
            Reporter.log("Some fields are not passing the validation, filling details at Step-2");
            getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='street1']")).clear();
            getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='zipOrPostalCode']")).clear();
            getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='city']")).clear();
            getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='phoneNumber']")).clear();

            getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='street1']")).clear();
            getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='zipOrPostalCode']")).clear();
            getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='city']")).clear();
            getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='phoneNumber']")).clear();

            getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='street1']")).sendKeys("Andheri East");
            getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='zipOrPostalCode']")).sendKeys("400708");
            getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='city']")).sendKeys("Mumbai");
            getDriver().findElement(By.id("delivery-address")).findElement(By.cssSelector("input[name='phoneNumber']")).sendKeys("123456789");

            getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='street1']")).sendKeys("Andheri East");
            getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='zipOrPostalCode']")).sendKeys("400708");
            getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='city']")).sendKeys("Mumbai");
            getDriver().findElement(By.id("billing-address")).findElement(By.cssSelector("input[name='phoneNumber']")).sendKeys("123456789");
            Reporter.log("Details filled at Step-2");
            
            Reporter.log("Again going to Step-3");
            getDriver().findElement(By.cssSelector("a.cta-button.btn-next")).click();

            Thread.sleep(3000);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 3,"Step 3 is not selected");
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 3,"Step 3 name is not selected");
            Reporter.log("Step-3 loaded, going to Step-2 to store details");
            Thread.sleep(3000);
            getDriver().findElement(By.cssSelector("a.cta-button.btn-grey")).click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='updating']")));
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-num"), 2,"Step 2 is not selected");
            Assert.assertEquals(getIndexOfSelectedStep(By.className("step-row"), "step-name"), 2,"Step 2 name is not selected");
            Reporter.log("Step-2 loaded successfully");
            return true;
        }
        else
        {
            Reporter.log("All fields have passed validation no need to fill");
            return false;
        }

    }
}
