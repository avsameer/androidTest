package com.sonyericsson.automatedtests.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hamcrest.core.IsNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.SelectOption;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;

public class EShopPage extends CwsAbstractBaseTest {
    private static final String BRACKET = "\\)";
	String host = "";
    public WebDriver driver;
    public String parent;
    public Set<String> handlers;
    public EShopPage(String browser, String targetHost, boolean javascriptRequired) throws Exception {
        init(browser, targetHost, javascriptRequired);
    }
    public void quitDriver() {
        Reporter.log("Calling Quit Driver");
        destroy();
    }
    public boolean isDataTransferredToCheckoutPage(String product) throws Exception {

    	try
    	{
    		String pathToItem = "//div[@class='cols sales-item-row first-row  clearfix loaded']/a";
    		Assert.assertTrue(isElementPresent(By.xpath(pathToItem + "/img")), "Image of item not present");
    		Reporter.log("Item image available");
    		if(!searchPHPwithButton(By.cssSelector("a.cta-button.cta-add-to-cart.large")))
    		{
    		    Reporter.log("No product found with add to cart button");
    		    return false;
    		}
    		click(By.cssSelector("a.cta-button.cta-add-to-cart.large"));
    		Assert.assertTrue(isElementPresent(By.id("cart-checkout")), "Add to cart button not present");
    		click(By.id("cart-checkout"));
    		Thread.sleep(5000);
    		waitForElementPresent(By.xpath("//div[starts-with(@class,'itemContainer')]"));
    		if (isElementDisplayed(By.xpath("//div[starts-with(@class,'itemContainer')]"))&& isElementDisplayed(By.id("siItemList"))  && isElementDisplayed(By.className("sales-item"))) {
    			if (isElementDisplayed(By.className("itemListFooter"))) {
    				Reporter.log("Checkout page displayed");
    				return true;
    			}
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
   
    public boolean checkFooter() throws Exception {
    	return this.testQuickFooterNavLinksGeneric();
    }
    
    
    public boolean checkOrderSummaryPageSanity(String prod) throws Exception {
    	try
    	{
        List<WebElement> elements = null;
        Assert.assertTrue(isElementDisplayed(By.id("seLogo")));
        Reporter.log("Logo displayed");
        Assert.assertTrue(isElementDisplayed(By.tagName("h1")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//html/body/div/header/nav/div/a")));
        Reporter.log("Top link leave checkout and continue shopping displayed");
        Assert.assertTrue(isElementDisplayed(By.className("infoMsg")));
        Reporter.log("Info message at top displayed");
        elements = findElement(By.cssSelector("div.control_panel > a"));
        Assert.assertTrue(elements.size() == 2);
        Reporter.log("Signup & login links displayed");
        Assert.assertTrue(isElementDisplayed(By.id("accordionPanel0")));
        Assert.assertTrue(isElementDisplayed(By.id("edit1")));
        Assert.assertTrue(isElementDisplayed(By.className("actHdr")));
        Reporter.log("Heading displayed");
        Assert.assertTrue(isElementDisplayed(By.className("orderSumText")));
        Reporter.log("Text present, Ready to buy?.......");
        elements = findElement(By.className("itemListHdr"));
        Assert.assertTrue(elements.size() > 0);
        Assert.assertTrue(isElementDisplayed(By.xpath("//tr[@class='itemListHdr']/th")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//tr[@class='itemListHdr']/th[2]")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//tr[@class='itemListHdr']/th[3]")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//tr[@class='itemListHdr']/th[4]")));
        Reporter.log("Heading of columns present");
        elements = findElement(By.className("sales-item"));
        Assert.assertTrue(elements.size() > 0);
        Reporter.log("Item to be purchase is displayed");
        Assert.assertTrue(elements.get(0).findElement(By.className("itemDetail")) != null);
        Assert.assertTrue(elements.get(0).findElement(By.className("itemStatus")) != null);
        
        Assert.assertTrue(elements.get(0).findElement(By.className("qtyCont")) != null);
        Assert.assertTrue(elements.get(0).findElement(By.className("itemPrice")) != null);
        Assert.assertTrue(elements.get(0).findElement(By.className("btnDel")) != null);
        Assert.assertTrue(isElementPresent(By.id("couponCode")));
        Reporter.log("TextBox for coupon code present");
        Assert.assertTrue(isElementPresent(By.id("couponApply")));
        Reporter.log("Apply button present");
        int i = getElementCount(By.xpath("//div[@id='cart-total-summary']/table/tbody/tr"));
        for (int j = 1; j <= i; j++) {
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[@id='cart-total-summary']/table/tbody/tr["+j+"]/th")));
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[@id='cart-total-summary']/table/tbody/tr["+j+"]/td")));
        }
        Assert.assertTrue(isElementDisplayed(By.id("cart-total-summary")));
        Reporter.log("Price detail present");
        Assert.assertTrue(isElementDisplayed(By.tagName("p")));
        Assert.assertTrue(isElementPresent(By.xpath("//div[starts-with(@class,'itemContainer')]")));
        Assert.assertTrue(isElementPresent(By.tagName("a")));
        Assert.assertTrue(isElementPresent(By.className("itemListFooter")));
        Reporter.log("itemListFooter present");
        Assert.assertTrue(isElementPresent(By.className("shipping-note")));
        Reporter.log("Shipping notes present");
        Assert.assertTrue(isElementPresent(By.className("store-selector")));
        Reporter.log("Store selector present");
        //Test that recommended items present
        if(!(prod.equalsIgnoreCase("phone")||prod.equalsIgnoreCase("accessory")))
        {
            Assert.assertTrue(isElementPresent(By.id("recommended-products")));
            Assert.assertTrue(isElementPresent(By.xpath("//div[@id='recommended-products']/ul/li/div/img")));
            Reporter.log("Recommended sets present");
        }
       
        return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    
    public boolean isOrderSummaryPageFunctional(String prod) throws Exception
    {
    	try
    	{
    		waitForElementPresent(By.className("plus"));
    		Assert.assertTrue(isElementDisplayed(By.className("minusInactive")));
    		Reporter.log("Inactive minus button available");
    		Assert.assertTrue(isElementDisplayed(By.className("plus")));
    		Reporter.log("Active plus button available");	
    		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
    		int clickCount = 0;
    		for(int i=1;i<5;i++)
    		{
    		    getDriver().findElement(By.className("plus")).click();
    		    clickCount = i;
    		    if(i < 4)
    		    {
    		        try
    		        {
    		            wait.until(ExpectedConditions.elementToBeClickable(By.className("plus")));
    		        }
    		        catch(Exception ex)
    		        {}
    		    }
    		    try
    		    {
    		        if(getDriver().findElement(By.xpath("//p[@class='itemStatus error']")).isDisplayed())
    		        {
    		            break;
    		        }
    		    }
    		    catch(Exception ex){}
    		}
       		wait.until(ExpectedConditions.elementToBeClickable(By.className("minus")));
    		if(clickCount == 4)
    		    Assert.assertEquals(getText(By.className("qtyTxt")), String.valueOf(5));
    		else
    		    Assert.assertTrue(Integer.valueOf(getText(By.className("qtyTxt"))) > 1);
    		Reporter.log("Quantity increases on click of plus");
    		Assert.assertTrue(isElementDisplayed(By.className("plusInactive")));
    		Reporter.log("Inactive plus button available on max quantity reached or stock limit reached");
    		Assert.assertTrue(isElementDisplayed(By.className("minus")));
    		Reporter.log("Active minus button available on max quantity reached or stock limit reached");
    		Assert.assertTrue(isElementDisplayed(By.xpath("//p[@class='itemStatus error']")));
    		Reporter.log("Warning appeared on max quantity reached or stock limit reached");
    		Assert.assertTrue(getAttribute(By.xpath("//div[@class='itemListFooter']/a"),"class").contains("inactive"));
    		Reporter.log("Continue button disabled on max quantity reached or stock limit reached");
    		for(int i=1;i<= clickCount;i++)
    		{
    		    getDriver().findElement(By.className("minus")).click();
    		    if(i != clickCount)
    		        wait.until(ExpectedConditions.elementToBeClickable(By.className("minus")));
    		}
    		
    		wait.until(ExpectedConditions.elementToBeClickable(By.className("plus")));
    		Assert.assertEquals(getText(By.className("qtyTxt")), String.valueOf(1));
    		Reporter.log("Quantity decreases on click of minus");
    		Assert.assertFalse(getAttribute(By.xpath("//div[@class='itemListFooter']/a"),"class").contains("inactive"));
    		Reporter.log("Continue button Enabled");
    		if(!(prod.equalsIgnoreCase("phone")||prod.equalsIgnoreCase("accessory")))
    		{
    		    waitForElementPresent(By.xpath("//div[@id='recommended-products']/ul/li/div/img"));
    		    wait=new WebDriverWait(getDriver(), 120);
    		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='recommended-products']/ul/li/div/img")));
    			int i=getElementCount(By.xpath("//div[@id='recommended-products']/ul/li"));
    			Reporter.log("Recommended products : "+i );
    			getDriver().findElement(By.xpath("//a[@class='cta-button cta-add-to-cart']")).click();
    			//Thread.sleep(20000);
    			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='siItemList']/tbody/tr[2]")));
    			int addedItems=getElementCount(By.xpath("//table[@id='siItemList']/tbody/tr"));
    			Assert.assertEquals(addedItems, 2);
    			Reporter.log("Recommended item added in cart");
    			getDriver().findElement(By.xpath("//table[@id='siItemList']/tbody/tr[2]/td[@class='btnDel']/a")).click();
    			//Thread.sleep(20000);
    			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table[@id='siItemList']/tbody/tr[2]")));
    			int Items=getElementCount(By.xpath("//table[@id='siItemList']/tbody/tr"));
    			Assert.assertEquals(Items, 1);
    			Reporter.log("Recommended item removed from cart");
    		}
    		return true;
    	}
    	catch (Exception ex)
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
    		float initialPrice = removeCurrencySign(getText(By.cssSelector("td.itemPrice")));
    		float deliveryCost= removeCurrencySign(getText(By.cssSelector("tr.summaryDeliveryCost > td")));
    		waitForElementPresent(By.id("couponCode"));
    		
    		//invalid code
    		typeText(By.id("couponCode"),"InvalidCode");
    		click(By.id("couponApply"));
    		Thread.sleep(5000);
    		Reporter.log("Testing for invalid voucher code");
    		Assert.assertTrue(getDriver().findElement(By.id("couponCodeWrapper")).getAttribute("class").equalsIgnoreCase("status-invalid"));	
    		Reporter.log("Tested for invalid voucher code");
    		Reporter.log("Testing for valid voucher code");
    		getDriver().findElement(By.id("couponCode")).clear();
    		typeText(By.id("couponCode"),"FreeShipping_Testing");
    		click(By.id("couponApply"));
    		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.discountMsg")));
    		float discountedPrice = removeCurrencySign(getText(By.cssSelector("td.itemPrice")));
    		float discountedTotalPrice =removeCurrencySign(getText(By.cssSelector("tr.summaryTotal > td")));
    		float unitCost =removeCurrencySign(getText(By.cssSelector("tr.sales-item > td + td")));
    		int quantity = Integer.valueOf(getText(By.cssSelector("td.qtyCont > span")));
    		Reporter.log("Expected 50% discount");
    		if(quantity == 1)
    		    Assert.assertTrue(discountedPrice==(initialPrice*0.5));	//Giving 50% discount
    		else
    		{
    		    double expectedPrice = (unitCost*(quantity-1))+(unitCost*0.5);
    		    Assert.assertTrue(discountedPrice==expectedPrice);   //Giving 50% discount
    		}
    		//Assert.assertEquals(discountedTotalPrice, deliveryCost+discountedPrice);
    		Reporter.log("50% discount applied successfully");
    		return true;
    	}
    	catch(Throwable ex)
    	{
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
    		Reporter.log("Order summary page verified");
    		click(By.xpath("//a[@class='cta-button floatright toStep2']"));
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
    			switchWindow();
    			
    			waitForElementPresent(By.id("accordionPanel1"));
    			Reporter.log("User Logged in successfully");
    			Thread.sleep(20000);
    			if(getText(By.cssSelector("div.control_panel.signed-in > span")).equalsIgnoreCase(username))
    			{
    				Reporter.log("User name displayed");
    				if(isElementDisplayed(By.cssSelector("a.cta-link.widget_starter_logout_button")))
    				{
    					Reporter.log("logout link displayed");	
    					click(By.cssSelector("a.cta-link.widget_starter_logout_button"));
    					waitForElementPresent(By.id("couponCode"));
    					Reporter.log("User logged out and main page displayed");
    				}
    			}
    			Thread.sleep(5000);
    			click(By.xpath("//a[@class='cta-button floatright toStep2']"));
    			switchWindow();
    			Thread.sleep(10000);
    			waitForElementPresent(By.id("j_username"));
    			if (!isElementDisplayed(By.id("j_username")))
    			    switchWindow();
    			if (isElementDisplayed(By.id("j_username")))
    			{
    				typeText(By.id("j_username"), username);
    				typeText(By.id("j_password"), password);
    				click(By.id("submit-button"));
    				Thread.sleep(8000);
    				switchWindow();
    			
    				waitForElementPresent(By.id("accordionPanel1"));
    				return true;
    			}
    			else
    			    Reporter.log("Element(s) not found or unable to switch to popup window at re-login");
    			
    		}
    		Reporter.log("Element(s) not found or unable to switch to popup window");
    		return false;
    	}
    	catch(Exception ex)
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
    
    public boolean checkDeliveryMethod() throws Exception
    {
    	try
    	{
    		click(By.id("shippingOptions_cDD"));
    		Thread.sleep(1000);
    		String express = getText(By.cssSelector("div#customDDOptions > a+a"));
    		String[] splited = express.split(BRACKET);
    		String method = splited[0];
    		String costOfDel = splited[1];
    		click(By.cssSelector("div#customDDOptions > a+a")); 
    		WebDriverWait wait = new WebDriverWait(driver, 120);
    		wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//div[starts-with(@class,'panelSummary')]/descendant::span[@id='summaryDeliveryName']"), method.trim()+")"));
    		Assert.assertEquals(getText(By.xpath("//div[starts-with(@class,'panelSummary')]/descendant::span[@id='summaryDeliveryName']")).trim(),method+")","Express delivery text not found");
    		Assert.assertEquals(getText(By.xpath("//div[starts-with(@class,'panelSummary')]/descendant::tr[@class='summaryDeliveryCost']/td")).trim(),costOfDel.trim(),"Cost not matches of express del");
    		Reporter.log("Express delivery selected and reflected");

    		click(By.id("shippingOptions_cDD"));
    		Thread.sleep(1000);
    		String standard = getText(By.cssSelector("div#customDDOptions > a"));
    		splited = standard.split(BRACKET);
    		method = splited[0];
    		costOfDel = splited[1];
    		click(By.cssSelector("div#customDDOptions > a"));
    		wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//div[starts-with(@class,'panelSummary')]/descendant::span[@id='summaryDeliveryName']"), method.trim()+")"));
    		Assert.assertEquals(getText(By.xpath("//div[starts-with(@class,'panelSummary')]/descendant::span[@id='summaryDeliveryName']")).trim(),method+")","Standard delivery text not found");
    		Assert.assertEquals(getText(By.xpath("//div[starts-with(@class,'panelSummary')]/descendant::tr[@class='summaryDeliveryCost']/td")).trim(),costOfDel.trim(),"Cost not matches of standard del");
    		Reporter.log("Standard delivery selected and reflected");
    		return true;
    	}
    	catch (Exception ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}

    }
    public boolean isPaymentPageDisplayed() throws Exception {
    	try
    	{
    		boolean flag = false;
    		String env = (getDriver().getCurrentUrl().contains("shop.sonymobile.com")) ? "prod" : "other";
    		click(By.id("step2BtnClick"));
    		Thread.sleep(20000);
    		switchFrame(0);
    		waitForElementPresent(By.id("ctl00_ContentPlaceHolder1_SubmitPaymentbutton1"));
    		WebDriverWait wait = new WebDriverWait(getDriver(), 120);
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_SubmitPaymentbutton1")));
    		getDriver().switchTo().defaultContent();
    		Assert.assertTrue(isElementDisplayed(By.id("accordionPanel2")));
    		Assert.assertTrue(isElementDisplayed(By.id("accordionPanel2")));
    		Assert.assertTrue(isElementDisplayed(By.className("actHdr")));
    		// Assert.assertTrue(isElementDisplayed(By.tagName("p"), By.className("bdDtlsTxt")));
    		Assert.assertTrue(isElementDisplayed(By.className("iframe")));
    		Assert.assertTrue(isElementDisplayed(By.className("helpPnl")));
    		String url = getDriver().findElement(By.tagName("iframe")).getAttribute("src");
    		switchFrame(0);
    		try {
    			click(By.id("ctl00_ContentPlaceHolder1_CreditCardControl2_rbtnAmex"));
    			typeText(By.id("ctl00_ContentPlaceHolder1_CreditCardControl2_txtCardNumber"), "378282000000131");
    			typeText(By.id("ctl00_ContentPlaceHolder1_CreditCardControl2_txtCardHolder"), "test");
    			click(By.xpath("//div[@id='ctl00_ContentPlaceHolder1_CreditCardControl2_ddlExpiryYear_cDD']/span"));
    			click(By.xpath("//div[@id='customDDOptions']/a[6]"));
    			typeText(By.id("ctl00_ContentPlaceHolder1_CreditCardControl2_txtCvcCode"), "001");
    			click(By.id("ctl00_ContentPlaceHolder1_TermsAndConditionsImage"));
    			click(By.id("ctl00_ContentPlaceHolder1_SubmitPaymentbutton1"));
    			Reporter.log("Make payment page verified");
    			flag = true;
    		} 
    		catch (Exception e) {
    			Thread.sleep(1);
    		}
    		waitForElementPresent(By.id("seLogo"));
    		if(env.equals("prod"))
    		    Assert.assertTrue(url.contains("https://payment.sonymobile.com/payment/SEMC"),"Iframe do not has proper src");
    		else
    		    Assert.assertTrue(url.contains("https://payment-stage.sonymobile.com/payment/SEMC"),"Iframe do not has proper src");
    		return flag;
    	}
    	catch (Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    public boolean isconfirmationPageDisplayed(String product) throws Exception {
    	try
    	{
    		Assert.assertTrue(isElementDisplayed(By.id("seLogo")));
    		Assert.assertTrue(isElementDisplayed(By.tagName("h1")));
    		Assert.assertTrue(isElementDisplayed(By.className("cta-link")));
    		Assert.assertTrue(isElementDisplayed(By.className("actHdr")));
    		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='bd']/div/div[1]/p[1]/span")));
    		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='bd']/div/div[1]/p[3]/strong")));
    		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='bd']/div/div[2]/p")));
    		Assert.assertTrue(isElementDisplayed(By.className("cta-button")));
    		Assert.assertTrue(isElementDisplayed(By.className("needToTalk")));
    		Assert.assertTrue(isElementDisplayed(By.className("discoveryBlock")));
    		Assert.assertTrue(isElementDisplayed(By.className("helpfulTipsBlock")));
    		Reporter.log("Confirmation page verified");
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
    public boolean checkStoreInfoJSON(String host) throws Exception {
        String url = "http://" + host + "/storefront/store-info-json.ep?storeCode=SEMC-SE";
      //  openUrl(url);
        driver=getDriver();
        driver.navigate().to(url);
        Reporter.log(url);
        ArrayList<CharSequence> key = new ArrayList<CharSequence>();
        key.add("deliveryName");
        key.add("deliveryCost");
        return checkKeyInJSON(key);
    }
    public boolean checkSalesItemsJSON(String host) throws Exception {
        String url = "http://" + host
                + "/storefront/sales-items-json.ep?storeCode=SEMC-SE&pID=key.MobilePhone.Xperianeov";
       //openUrl(url);
     
        driver=getDriver();
        driver.navigate().to(url);
      
        Reporter.log(url);
        ArrayList<CharSequence> key = new ArrayList<CharSequence>();
        key.add("code");
        key.add("title");
        key.add("description");
        key.add("inTheBox");
        key.add("inTheBox");
        key.add("priceWithCurrency");
        key.add("strikeThroughPrice");
        key.add("stockLevel");
        key.add("imageUrl");
        key.add("imageFeatureUrl");
        key.add("cwsExternalId");
        key.add("maxOrderLimit");
        key.add("enableDate");
        key.add("color");
        key.add("benefits");
        return checkKeyInJSON(key);
    }
    public boolean checkPriceListingJSON(String host) throws Exception {
        String url = "http://" + host + "/storefront/products-from-price-listing.ep?storeCode=SEMC-SE";
       // openUrl(url);
        driver=getDriver();
        driver.navigate().to(url);
        Reporter.log(url);
        ArrayList<CharSequence> key = new ArrayList<CharSequence>();
        key.add("cwsExternalId");
        key.add("fromPriceWithCurrency");
        key.add("featuredProducts");
        return checkKeyInJSON(key);
    }
    public boolean checkSalesItemsJSONnew(String host) throws Exception {
        String url = "http://" + host
                + "/storefront/sales-items-json.ep?storeCode=SEMC-SE&pID=key.MobilePhone.Xperianeov&isNew=true";
       // openUrl(url);
        driver.navigate().to(url);
        Reporter.log(url);
        ArrayList<CharSequence> key = new ArrayList<CharSequence>();
        key.add("code");
        key.add("title");
        key.add("description");
        key.add("inTheBox");
        key.add("inTheBox");
        key.add("priceWithCurrency");
        key.add("strikeThroughPrice");
        key.add("stockLevel");
        key.add("imageUrl");
        key.add("imageFeatureUrl");
        key.add("cwsExternalId");
        key.add("maxOrderLimit");
        key.add("enableDate");
        key.add("productStatus");
        key.add("color");
        key.add("benefits");
        return checkKeyInJSON(key);
    }
    public boolean checkPriceListingJSONnew(String host) throws Exception {
        String url = "http://" + host + "/storefront/products-from-price-listing.ep?storeCode=SEMC-SE&isNew=true";
       // openUrl(url);
    
       driver=getDriver();
        driver.navigate().to(url);
        Reporter.log(url);
        ArrayList<CharSequence> key = new ArrayList<CharSequence>();
        key.add("cwsExternalId");
        key.add("fromPriceWithCurrency");
        key.add("productStatus");
        key.add("featuredProducts");
        return checkKeyInJSON(key);
    }
    private boolean checkKeyInJSON(ArrayList<CharSequence> key) throws Exception {
        String unParsedDetails = getText(By.xpath("//body"));
        Reporter.log(unParsedDetails);
        int i = unParsedDetails.indexOf("]") - unParsedDetails.indexOf("[");
        int savedCount = 0;
        if (i > 2) {
            for (CharSequence checkString : key) {
                if (unParsedDetails.contains(checkString)) {
                    String cs = (String) checkString;
                    int lastIndex = 0;
                    int count = 0;
                    while (lastIndex != -1) {
                        lastIndex = unParsedDetails.indexOf(cs, lastIndex);
                        if (lastIndex != -1) {
                            count++;
                            lastIndex += cs.length();
                        }
                    }
                    if (savedCount == 0) {
                        savedCount = count;
                        Reporter.log("Feed name:" + cs + "::::Count:" + count);
                    } else {
                        if (cs != "featuredProducts") {
                            if (savedCount == count)
                                Reporter.log("Feed name:" + cs + "::::Count:" + count);
                            else {
                                return false;
                            }
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
