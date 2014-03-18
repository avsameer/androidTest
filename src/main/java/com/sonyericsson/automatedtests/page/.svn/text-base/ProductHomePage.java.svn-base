package com.sonyericsson.automatedtests.page;

import java.nio.ReadOnlyBufferException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.selenesedriver.IsElementDisplayed;
import org.openqa.selenium.internal.selenesedriver.TakeScreenshot;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;

public class ProductHomePage extends CwsAbstractBaseTest {
    String host = "";
    public WebDriver driver;
    public String parent;
    public Set<String> handlers;
    public ProductHomePage(String browser, String targetHost, boolean javascriptRequired) throws Exception {
        init(browser, targetHost, javascriptRequired);
    }
    public void quitDriver() {
        Reporter.log("Calling Quit Driver");
        destroy();
    }
    public boolean isHeaderPresent(String base) throws Exception {
    	try
    	{
    	//String pathToItem = "//div[@class='cols sales-item-row first-row  clearfix loaded']/a";
    	//Assert.assertTrue(isElementPresent(By.xpath(pathToItem + "/img")), "Image of item not present"); 
    	if(itemHavingAddToCart.isEmpty())
    	{
    	    if(!searchPHPwithButton(By.cssSelector("a.cta-button.cta-add-to-cart.large")))
    	    {
    	        Reporter.log("No phone found with add to cart button, now checking in accessories");
    	        getDriver().navigate().to(base+"/accessories/");
    	        if(!searchPHPwithButton(By.cssSelector("a.cta-button.cta-add-to-cart.large")))
    	        {
    	            Reporter.log("No product found with add to cart button in other category also");
    	        }

    	    }    
    	}
    	else
    	{
    	    getDriver().get(itemHavingAddToCart);
    	    waitForElementPresent(By.cssSelector("a.cta-button.cta-add-to-cart.large"));
    	}
    	
        return this.testQuickHeaderNavLinksGeneric();
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    public boolean isFooterPresent() throws Exception {
        return this.testQuickFooterNavLinksGeneric();
    }
    public boolean isConfigboxPresent() throws Exception {
    	try
    	{
        int j = 0;
        int i = 0;
        Assert.assertTrue(isElementDisplayed(By.cssSelector("div.sales-item-config.box-shadow")));
        Reporter.log("Config box present");
        Assert.assertTrue(isElementDisplayed(By.cssSelector("h1.margin-bottom-20")));
        Reporter.log("Product name present");
        Assert.assertTrue(isElementDisplayed(By.cssSelector("span.ddText")));
        Assert.assertTrue(isElementDisplayed(By.cssSelector("span.ddButton")));
        Reporter.log("Quantity selector present");
        waitForElementPresent(By.xpath("//div[@class='float-left color-swatches']/h2"));
        Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='float-left color-swatches']/h2")));
        Reporter.log("Color selector title present");
        //waitForElementPresent(By.xpath("//div[@class='float-left color-swatches']/img[@class='noscale loaded']"));
       // Assert.assertTrue(isElementDisplayed(By.cssSelector("div.float-left.color-swatches > a > img.noscale.loaded")));
        //Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='float-left color-swatches']/img[@class='noscale loaded']")),"Element not found");
        Assert.assertTrue(isElementDisplayed(By.cssSelector("div.float-left.color-swatches > h2 + img")) || isElementDisplayed(By.cssSelector("div.float-left.color-swatches > h2 + a >img")),"Colour swatches not found");
        Reporter.log("Color selectors present");

        if(getDriver().findElement(By.cssSelector("p.strike-price")).getText().isEmpty()) //if strike price not present it becomes empty
        {
            Assert.assertTrue(isElementDisplayed(By.xpath("//p[@class=' price']")));
            Reporter.log("Product Price present");
        }
        else
        {
            Reporter.log("Strike Price present");
            Assert.assertTrue(isElementDisplayed(By.xpath("//p[@class='highlight price']")));
            Reporter.log("Product Sale Price present");
        }       
        Assert.assertTrue(isElementDisplayed(By.cssSelector("p.tax-info > span")));
        Reporter.log("Tax text present");
        Assert.assertTrue(isElementDisplayed(By.xpath("//div[contains(@class,'payment-options')]")));
       // Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='payment-options']/img[contains(@src,'Payment_methods/CreditCards')]")));
        Assert.assertFalse(getDriver().findElement(By.xpath("//div[contains(@class,'payment-options')]/img")).getAttribute("src").isEmpty());
        Reporter.log("Payment options present");
        i = getElementCount(By.xpath("//ul[@class='store-benefits']/li"));
        for (j = 1; j < i; j++) {
            Assert.assertTrue(isElementDisplayed(By.xpath("//ul[@class='store-benefits']/li[" + j + "]")));
        }
        Reporter.log("Payment benefits present:" + j);
        Assert.assertTrue(isElementDisplayed(By.cssSelector("a.cta-button.cta-add-to-cart.large")));
        Reporter.log("Add to cart button present:");
        return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    public boolean checkAddtoCartWorking() throws Exception
    {
    	try
    	{
    	WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    	Assert.assertTrue(isElementDisplayed(By.cssSelector("div.cart-top-wrapper.cart-empty")),"Disabled cart not present");
    	Reporter.log("Disabled Cart Present");
    	click(By.cssSelector("a.cta-button.cta-add-to-cart.large"));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.cart-container.cart-loading")));
    	Thread.sleep(500);
    	Assert.assertFalse(isElementDisplayed(By.cssSelector("div.cart-top-wrapper.cart-empty")),"Disabled cart still present");
    	Reporter.log("Disabled Cart Not Present");
    	Assert.assertTrue(Integer.parseInt(getText(By.cssSelector("a#cart-checkout>span.number")))==1,"Count of item added is not correct");
    	Reporter.log("Item added successfully");
    	click(By.cssSelector("a.cta-button.cta-add-to-cart.large"));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.cart-container.cart-loading")));
        Thread.sleep(500);
    	Assert.assertTrue(Integer.parseInt(getText(By.cssSelector("a#cart-checkout>span.number")))==2,"Count of item added is not correct");
    	Reporter.log("Item added successfully");
    	click(By.cssSelector("a.remove"));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.cart-container.cart-loading")));
        Thread.sleep(500);
    	Assert.assertTrue(Integer.parseInt(getText(By.cssSelector("a#cart-checkout>span.number")))==1,"Count of item removed is not correct");
    	Reporter.log("Item removed successfully");
    	click(By.cssSelector("a.remove"));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.cart-container.cart-loading")));
        Thread.sleep(500);
    	Assert.assertTrue(isElementDisplayed(By.cssSelector("div.cart-top-wrapper.cart-empty")),"Disabled cart not present");
    	Reporter.log("Disabled Cart Present");
    	return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    public Object isInfoboxPresent() throws Exception {
    	try
    	{
    		int j = 0;
    		int i = 0;
    		Assert.assertTrue(isElementDisplayed(By.cssSelector("div.tab-pane.box-shadow")));
    		Reporter.log("Info box present:");
    		i = getElementCount(By.xpath("//ul[@class='tab-nav']/li"));
    		for (j = 1; j <= i; j++) {
    			Assert.assertTrue(isElementDisplayed(By.xpath("//ul[@class='tab-nav']/li[" + j + "]/a")));
    			String str=getDriver().findElement(By.xpath("//ul[@class='tab-nav']/li[" + j + "]/a")).getText();
    			if(j==2)
    				getDriver().findElement(By.xpath("//ul[@class='tab-nav']/li[" + j + "]/a")).click();
    		}
    		Reporter.log("Info tabs present:" + i);

    		Assert.assertTrue(isElementDisplayed(By.cssSelector("div.tab-pane.box-shadow > ul >li")));
    		Reporter.log("Info box Contains Items in box");
    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}
    }
    public Object isRecsetPresent() throws Exception {
    	try
    	{
    		int j = 0;
    		int i = 0;
    		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)", "");
    		WebDriverWait wait = new WebDriverWait(getDriver(), 120);
    		//waitForElementPresent(By.xpath("//div[@class='float-left recommended-sales-item']"));
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='float-left recommended-sales-item']")));
    		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='recommended']/h2")));
    		Reporter.log("Recommended set heading present:");
    		i = getElementCount(By.xpath("//div[@class='float-left recommended-sales-item']"));
    		Reporter.log("Total "+i+"recommended products available");
    		for (j = 1; j <= i; j++) {
    			Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='float-left recommended-sales-item']["+j+"]/a[1]/img")));
    			Reporter.log("Product "+j+"image present");
    			Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='float-left recommended-sales-item']["+j+"]/a/h3")));
    			Reporter.log("Product "+j+"name present");
    			if(isElementDisplayed(By.xpath("//div[@class='float-left recommended-sales-item']["+j+"]/a/div/p[@class='price']")))
    			{
    			    Assert.assertFalse(isElementDisplayed(By.xpath("//div[@class='float-left recommended-sales-item']["+j+"]/a/div/p[@class='strike-price']")));
    			    Reporter.log("Product "+j+"price present and strike-price not present");
    			}
    			else if(isElementDisplayed(By.xpath("//div[@class='float-left recommended-sales-item']["+j+"]/a/div/p[@class='sales-price price']")))
    			{
    			    Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='float-left recommended-sales-item']["+j+"]/a/div/p[@class='strike-price']")));
    			    Reporter.log("Product "+j+"sale price and strike-price present");
    			}
    		}
    		Reporter.log("Recommended products present:" + j);
    		return true;
    	}
    	catch(Throwable ex)
    	{
    		Reporter.log(ex.getMessage());
    		Reporter.log(Arrays.toString(ex.getStackTrace()));
    		return false;
    	}

    }
    public boolean isSocialBarAvailable() throws Exception
    {
    	boolean flag=false;
    	if(isSocialBarPresent())
    		flag=true;
    	return flag;
    }
    public boolean isSmallBannersAvailable() throws Exception
    {
    	boolean flag=false;
    	if(isSmallBannerPresent())
    		flag=true;
    	return flag;
    }
    public boolean checkNotifyMe(String host, String locale) throws Exception
    {
        try
        {
            if(itemHavingPreregister.isEmpty())
            {
                getDriver().navigate().to(host+"/"+locale+"/phones");

                if(searchPHPwithButton(By.cssSelector("a.cta-button.cta-notify-me.large")))
                {
                    Assert.assertTrue(isElementDisplayed(By.xpath("//div[contains(@class,'coming-soon')]")),"Notify me present but comming soon not present");     
                    return IsNotifyMeWorking();
                }
                else
                {
                    getDriver().navigate().to(host+"/"+locale+"/accessories");

                    if(searchPHPwithButton(By.cssSelector("a.cta-button.cta-notify-me.large")))
                    {
                        Assert.assertTrue(isElementDisplayed(By.xpath("//div[contains(@class,'coming-soon')]")),"Notify me present but comming soon not present");
                        return IsNotifyMeWorking();
                    }
                    else
                    {
                        Reporter.log("Notify me button not found in phones and accessories");
                        return true;
                    }

                }
            }
            else
            {
                if(itemHavingPreregister.equals("F"))
                {
                    Reporter.log("Pre-Register button not found");
                    return true;
                }
                else
                {
                    getDriver().get(itemHavingPreregister);
                    return IsNotifyMeWorking();
                }

               // getDriver()
            }
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    public boolean IsNotifyMeWorking()
    {
        try
        {
            Assert.assertFalse(getDriver().findElement(By.cssSelector("a.cta-button.cta-notify-me.large")).getAttribute("title").isEmpty(),"Tool tip of Pre-register button is empty");
            Reporter.log("Tool tip of Pre-register button is not empty");
            String prodName = getDriver().findElement(By.cssSelector("div.sales-item-config.box-shadow > h1")).getText().split("-")[0];
            click(By.cssSelector("a.cta-button.cta-notify-me.large"));
            Reporter.log("Pre-register button clicked");
            Thread.sleep(3000);
            switchWindow();
            Assert.assertFalse(getDriver().findElement(By.xpath("//div[@id='doc']/h1")).getText().isEmpty(), "Heading of pop up not present");
            Reporter.log("heading of pop up present");
            String text = getDriver().findElement(By.xpath("//div[@id='register-wrapper']/p")).getText();
            Assert.assertTrue(text.contains(prodName),"Product name is not present in Paragraph");
            Reporter.log("Product name is present in Paragraph");
            Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='submit-cancel terms-and-conditions btn-disabled']")),"Button is not disabled");
            Reporter.log("Button is disabled before accepting T&C");      
            getDriver().findElement(By.id("acceptTermsAndConditions")).click();
            getDriver().findElement(By.id("send")).click();
            Reporter.log("Sign Up button clicked");
            WebDriverWait wait = new WebDriverWait(getDriver(), 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='error']/p")));
            Reporter.log("Error message appeared");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy_hhmmss");
            String formattedDate = sdf.format(date);
            String testMail = formattedDate+"@gmail.com";
            Reporter.log("testing with email : "+testMail);
            getDriver().findElement(By.id("emailAddress")).sendKeys(testMail);          
            getDriver().findElement(By.id("send")).click();
            waitForElementPresent(By.xpath("//div[@class='success']/p"));
            Assert.assertFalse(getDriver().findElement(By.xpath("//div[@class='success']/p")).getText().isEmpty(), "Success message not appeared");
            Reporter.log("Success message appeared");
            switchWindow();
            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
        
    }
}