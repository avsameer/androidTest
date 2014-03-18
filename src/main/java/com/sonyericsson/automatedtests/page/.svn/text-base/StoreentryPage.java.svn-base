package com.sonyericsson.automatedtests.page;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.fest.swing.annotation.GUITest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.CwsSeleniumBaseTest;

public class StoreentryPage extends CwsAbstractBaseTest {
    String host = "";
    public WebDriver driver;
    public String parent;
    public Set<String> handlers;
    
    public StoreentryPage(String browser, String targetHost, boolean javascriptRequired) throws Exception {

        init(browser, targetHost, javascriptRequired);
    }
    public void quitDriver() {
        Reporter.log("Calling Quit Driver");
        destroy();
    }
    public boolean isHeaderPresent() throws Exception {
        return this.testQuickHeaderNavLinksGeneric();
    }
    public boolean isFooterPresent() throws Exception {
        return this.testQuickFooterNavLinksGeneric();
    }
    public boolean isTopPaymentBannerPresent() throws Exception {
        try
        {
            Assert.assertFalse(getDriver().findElement(By.xpath("//div[@class='float-left payment-options']/img")).getAttribute("src").isEmpty());
            Reporter.log("Payment option present");
            Assert.assertFalse(getDriver().findElement(By.xpath("//div[@class='float-left payment-options']/img[2]")).getAttribute("src").isEmpty());
            Reporter.log("Verify  By option present");
            Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='float-left store-benefits']")),"Benifits not present");
            int count = getElementCount(By.xpath("//div[@class='float-left store-benefits']/ul/li"));
            for (int i = 1; i <= count; i++) {
                Assert.assertTrue(!getText(By.xpath("//div[@class='float-left store-benefits']/ul/li[" + i + "]")).isEmpty(),"Empty benifit present");
            }
            Reporter.log("Payment benefits present: " + count);
            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    public boolean isTopBannerPresent() throws Exception {
        try
        {
            Assert.assertTrue(isElementDisplayed(By.className("store-top-carousel")),"Store carousel not present");
            Reporter.log("Store carousel present");
            Assert.assertTrue(isElementDisplayed(By.className("scrollable")),"Class scrollable not present");
            Assert.assertTrue(isElementDisplayed(By.className("items")),"Class items not present");
            Reporter.log("Banners present");
            List<WebElement> totalDivs = getDriver().findElements(By.cssSelector("div.items > div"));
            List<WebElement> clonedDivs = getDriver().findElements(By.cssSelector("div.items > div.cloned"));
            int countOfBanners = totalDivs.size() - clonedDivs.size();
            Reporter.log("Total Banners present :" + countOfBanners);
            if(countOfBanners > 1)
            {
                Assert.assertTrue(isElementDisplayed(By.id("navi-container")),"Navi container not displayed");
                Assert.assertTrue(isElementDisplayed(By.className("navi")),"Navi not displayed");
                List<WebElement> elements = getDriver().findElements(By.cssSelector("div.navi > a"));
                Reporter.log("Banner selectors present: " + elements.size());
                Assert.assertEquals(elements.size(), countOfBanners,"Count of navigators and banners not matching");
                Reporter.log("Banners and navigators are proper");
            }
            else
            {
                Assert.assertFalse(isElementDisplayed(By.id("navi-container")),"Navi container displayed for single banner");
                Assert.assertFalse(isElementDisplayed(By.className("navi")),"Navi displayed for single banner");
                Reporter.log("Single banner so no navigator found");
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
    public boolean isPopularProductsPresent() throws Exception {
        try
        {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)", "");
            Assert.assertTrue(isElementDisplayed(By.className("wrapper")),"Popular product section not present");
            Reporter.log("Popular products section present");
            Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='wrapper']/div/h2")));

            Assert.assertFalse(getText(By.xpath("//div[@class='row margin-bottom-20']/h2")).isEmpty(),"Heading not present or blank present");
            Reporter.log("Heading present");
            //  Assert.assertTrue(getDriver().getPageSource().contains("Popular phones &amp; accessories"));
            Reporter.log("Popular products heading present");
            List<WebElement> elements = findElement(By.className("sales-item"));
            Reporter.log(elements.size() + " Popular products present");
            scrollPage();
            if(isPhonesPresent())
                return true;
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
    public boolean isQuickSelectionPresent() throws Exception {
        try
        {
            Assert.assertTrue(isElementDisplayed(By.className("quick-selection")),"Space for quick selection present");
            Reporter.log("Quick selection Box present");
            waitForElementPresent(By.cssSelector("div.tab-pane.box-shadow.loaded > div > span.ddText"));
            //Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[1]/span[@class='ddText']")));
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.tab-pane.box-shadow.loaded > div > span.ddText")),"Go to phone dropdown not present");
            Reporter.log("Goto phone dropdown present");
            //Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[1]/span[@class='ddButton']")));
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.tab-pane.box-shadow.loaded > div > span.ddButton")),"Dropdown button not present");

            // Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[2]/span[@class='ddText']")));
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.tab-pane.box-shadow.loaded > div + select + div > span.ddText")),"Go to accessory dropdown not present");
            Reporter.log("Goto accessory dropdown present");
            // Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[2]/span[@class='ddButton']")));
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.tab-pane.box-shadow.loaded > div + select + div > span.ddButton")),"Dropdown button not present");

            Reporter.log("Phone and Accessory selector present");

            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }

    }
    public boolean checkPhoneQuickSelection(String locale)
    {
        String failures = "";
        try
        {
            itemHavingAddToCart = "";

            itemHavingPreregister = "";
            boolean flag=true;
            Reporter.log("***********Checking phone selector************");
            click(By.xpath("//div[@class='tab-pane box-shadow loaded']/div"));
            Thread.sleep(1000);
            int count = getElementCount(By.xpath("//div[@id='customDDOptions']/a"));
            Reporter.log("Number of items : "+count);
            for(int i=2;i<=count;i++)
            {
                String item = getText(By.xpath("//div[@id='customDDOptions']/a["+i+"]"));
                try
                {
                    Reporter.log(item);
                    click(By.xpath("//div[@id='customDDOptions']/a["+i+"]"));
                    waitForElementPresent(By.xpath("//div[@class='info-box tab-panes']"));
                    Thread.sleep(3000);
                    getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    Assert.assertTrue(checkButtons(),"Proper button not available");
                    getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='info-box tab-panes']")),"Info box not displayed");
                    Assert.assertTrue(isElementDisplayed(By.xpath("//div[contains(@class,'sales-item-config')]")),"Config box not displayed");
                    Reporter.log("PHP page loaded");
                    if(!CwsSeleniumBaseTest.phoneHash.isEmpty())
                        Assert.assertTrue(isPhpPageMatchesWithCatPage("phone"),"PHP not matching with category page");
                    //Assert.assertFalse(isDupSwatch(getDriver().findElements(By.xpath("//img[@class='noscale loaded']"))),"Duplicate colour swatches present");
                    Assert.assertFalse(isDupSwatch(),"Duplicate colour swatches present");
                    Reporter.log("Duplicate colour swatches not present");


                }
                catch(AssertionError e)
                {
                    try{
                        failures = failures + item +"\n";
                        flag = false;
                        Reporter.log("***FAILURE OCCURED***");
                        Reporter.log(e.getMessage());

                        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
                        
                        String[] name = getDriver().getCurrentUrl().split("/buy/");
                        String file = locale.toUpperCase() + "_" + name[1].replace("/", "_");
                        FileUtils.copyFile(scrFile, new File("c:\\ECOM_FAILURES\\"+folder1+"\\"+folder2+"\\"+"Store_Entry"+"\\"+file+".png"));
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                finally
                {
                    //openUrl(url);
                    getDriver().navigate().back();
                    ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)", "");
                    waitForElementPresent(By.xpath("//div[@class='tab-pane box-shadow loaded']/div"));
                    click(By.xpath("//div[@class='tab-pane box-shadow loaded']/div"));
                    Reporter.log("===========================================");
                }
            }
            Reporter.log("***********Checking phone selector completed************");
            if(itemHavingPreregister.isEmpty())
                itemHavingPreregister="F";
            Assert.assertTrue(flag,"SOME FAILURES ARE FOUND PLEASE SEE ABOVE LOG");
            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            if(!failures.trim().isEmpty())
            {
                Reporter.log("FAILED ITEMS ARE:");
                Reporter.log(failures);
            }
            return false;
        }

    }
    public boolean checkAccessoryQuickSelection(String url,String locale)
    {
        String failures = "";
        try
        {
            boolean flag=true;
            getDriver().get(url);
            scrollPage();
            waitForElementPresent(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[2]"));
            Reporter.log("***********Checking accessory selector************");
            click(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[2]"));
            Thread.sleep(1000);
            int count = getElementCount(By.xpath("//div[@id='customDDOptions']/a"));
            Reporter.log("Number of items : "+count);

            for(int i=2;i<=count;i++)
            {
                String item = getText(By.xpath("//div[@id='customDDOptions']/a["+i+"]"));
                try
                {  
                    Reporter.log(item);
                    click(By.xpath("//div[@id='customDDOptions']/a["+i+"]"));
                    waitForElementPresent(By.xpath("//div[@class='info-box tab-panes']"));
                    Thread.sleep(3000);
                    getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    Assert.assertTrue(checkButtons(),"Proper button not available");
                    getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='info-box tab-panes']")),"Info box not displayed");
                    Assert.assertTrue(isElementDisplayed(By.xpath("//div[contains(@class,'sales-item-config')]")),"Config box not displayed");
                    Reporter.log("PHP page loaded");
                    if(!CwsSeleniumBaseTest.accessoryHash.isEmpty())
                        Assert.assertTrue(isPhpPageMatchesWithCatPage("accessory"),"PHP not matching with category page");
                    //Assert.assertFalse(isDupSwatch(getDriver().findElements(By.xpath("//img[@class='noscale loaded']"))),"Duplicate colour swatches present");
                    Assert.assertFalse(isDupSwatch(),"Duplicate colour swatches present");
                    Reporter.log("Duplicate colour swatches not present");

                }
                catch(AssertionError e)
                {
                    failures = failures + item +"\n";
                    flag = false;
                    Reporter.log("***FAILURE OCCURED***");
                    Reporter.log(e.getMessage());
                    try
                    {
                        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
                        
                        String[] name = getDriver().getCurrentUrl().split("/buy/");
                        String file = locale.toUpperCase() + "_" + name[1].replace("/", "_");
                        FileUtils.copyFile(scrFile, new File("c:\\ECOM_FAILURES\\"+folder1+"\\"+folder2+"\\"+"Store_Entry"+"\\"+file+".png"));
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                finally
                {
                    //openUrl(url);
                    getDriver().navigate().back();
                    ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)", "");
                    waitForElementPresent(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[2]"));
                    click(By.xpath("//div[@class='tab-pane box-shadow loaded']/div[2]"));
                    Reporter.log("===========================================");
                }
            }
            Reporter.log("***********Checking accessory selector completed************");
            if(itemHavingPreregister.isEmpty())
                itemHavingPreregister="F";
            Assert.assertTrue(flag,"SOME FAILURES ARE FOUND PLEASE SEE ABOVE LOG");
            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            if(!failures.trim().isEmpty())
            {
                Reporter.log("FAILED ITEMS ARE:");
                Reporter.log(failures);
            }
            return false;
        }

    }

    public boolean checkButtons() throws Exception
    {
        try
        {
            try
            {
                if(getDriver().findElement(By.cssSelector("a.cta-button.cta-add-to-cart.large")).isDisplayed())
                {
                    if(itemHavingAddToCart.isEmpty())
                        itemHavingAddToCart = getDriver().getCurrentUrl();
                    Assert.assertTrue(isElementDisplayed(By.xpath("//div[contains(@class,'in-stock')]")),"Add to cart present. in stock not present");
                    Reporter.log("Add to cart button present with In stock");
                    Assert.assertTrue(isElementDisplayed(By.className("customDropDownControl")),"Quantity selector dropdown not present");
                    Reporter.log("Quantity selector dropdown present");

                    return true;
                }
            }
            catch(NoSuchElementException ex){}
            try
            {
                if(getDriver().findElement(By.xpath("//div[contains(@class,'out-of-stock')]")).isDisplayed())
                {
                    Assert.assertFalse(isElementDisplayed(By.cssSelector("a.cta-button.cta-notify-me.large")),"Out of stock present and notify me also present");
                    Assert.assertFalse(isElementDisplayed(By.cssSelector("a.cta-button.cta-add-to-cart.large")),"Out of stock present and Add to cart also present");
                    Reporter.log("No button present as status is out of stock");
                    Assert.assertFalse(isElementDisplayed(By.className("customDropDownControl")),"Quantity selector dropdown present");
                    Reporter.log("Quantity selector dropdown not present");
                    return true;

                }
            }
            catch(NoSuchElementException ex){}
            try
            {
                if(getDriver().findElement(By.cssSelector("a.cta-button.cta-notify-me.large")).isDisplayed())
                {
                    if(itemHavingPreregister.isEmpty() || itemHavingPreregister.equals("F"))
                        itemHavingPreregister = getDriver().getCurrentUrl();
                    Assert.assertTrue(isElementDisplayed(By.xpath("//div[contains(@class,'coming-soon')]")),"Notify me present but coming soon not present");
                    Reporter.log("Notify me button present with coming soon");
                    Assert.assertFalse(isElementDisplayed(By.className("customDropDownControl")),"Quantity selector dropdown present");
                    Reporter.log("Quantity selector dropdown not present");

                    return true;
                } 
            }
            catch(NoSuchElementException ex){}
            try
            {
                if(getDriver().findElement(By.xpath("//div[contains(@class,'coming-soon')]")).isDisplayed())
                {
                    Reporter.log("COMING SOON PRESENT BUT NOTIFY ME BUTTON NOT PRESENT");
                    Assert.assertFalse(isElementDisplayed(By.className("customDropDownControl")),"Quantity selector dropdown present");
                    Reporter.log("Quantity selector dropdown not present");
                    return true;
                }
            }
            catch(NoSuchElementException ex){}
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
        return false;
    }
    public boolean isPhpPageMatchesWithCatPage(String product)
    {
        HashMap<String, String> prodHashMap;
        if(product.equalsIgnoreCase("phone"))
            prodHashMap = CwsSeleniumBaseTest.phoneHash;
        else
            prodHashMap = CwsSeleniumBaseTest.accessoryHash;
        try
        {
            String currentUrl = getDriver().getCurrentUrl();         
            if(!currentUrl.endsWith("/"))
                currentUrl = currentUrl + "/";
            String value = prodHashMap.get(currentUrl);
            if(value != null)
            {
                String[] values = value.split(";");
                String image = values[0];
                String name = values[1];
                String noSwatches = values[2];
                String colorList = values[3];
                String price = values[4];
                String strikePrice = values[5];


                if(image.equals("T"))
                {
                    Assert.assertTrue(isElementDisplayed(By.cssSelector("[width='460'][height='360']")),"Product Image not present");
                    Assert.assertFalse(getDriver().findElement(By.cssSelector("[width='460'][height='360']")).getAttribute("src").isEmpty(),"Product Image not available at PHP but available at category page");
                    Reporter.log("Product Image present");
                }
                else if(image.equals("F"))
                {
                    Assert.assertTrue(getDriver().findElement(By.cssSelector("[width='460'][height='360']")).getAttribute("src").isEmpty(),"Product Image available at PHP but not at category page");
                }
                Assert.assertTrue(getDriver().findElement(By.xpath("//div[contains(@class,'sales-item-config')]/h1")).getText().replace("(", "").replace(")", "").contains(name),"Product name not present or not matching");
                Reporter.log("Product name present and matching");

                List<WebElement> colorSwatches = getDriver().findElements(By.xpath("//img[@class='noscale loaded']"));
                int count = colorSwatches.size();
                Assert.assertEquals(count, Integer.parseInt(noSwatches),"Count of swatches not matching");
                Reporter.log("Colour swatches count matching");
                if(count > 0)
                {
                    for(WebElement swatch : colorSwatches)
                    {
                        String src = swatch.getAttribute("src").trim();
                        colorList = colorList.replaceFirst(Pattern.quote(src), "");
                    }
                    colorList = colorList.replace("|", "").trim();
                    Assert.assertTrue(colorList.isEmpty(),"Swatches color mismatch found");
                }
                else
                {
                    Assert.assertTrue(colorList.equals("NOSWATCH"),"Looks swatches present at category page but not present at PHP");
                }
                Reporter.log("Swatches are proper no mismatch found in color");

                if(!strikePrice.equals("F"))
                {
                    Assert.assertTrue(isElementDisplayedIn(By.className("strike-price")),"Strike price not present at PHP but present at category page");
                    Assert.assertEquals(getDriver().findElement(By.xpath("//div[contains(@class,'sales-item-config')]")).findElement(By.className("strike-price")).getText().trim(), strikePrice,"Strike price not matching");
                    Assert.assertFalse(isElementDisplayedIn(By.xpath(".//p[@class=' price']")),"Price displayed with strike price");
                    Reporter.log("Strike price ok");
                    Assert.assertEquals(getDriver().findElement(By.xpath("//div[contains(@class,'sales-item-config')]")).findElement(By.xpath(".//p[@class='highlight price']")).getText().trim(), price);
                    Reporter.log("Sale price matching");
                }
                else if(price != null)
                {
                    if(!price.isEmpty())
                    {
                        Assert.assertEquals(getDriver().findElement(By.xpath("//div[contains(@class,'sales-item-config')]")).findElement(By.xpath(".//p[@class=' price']")).getText().trim(), price,"Price not matching");
                        Reporter.log("Price is matching");
                        Assert.assertTrue(getDriver().findElement(By.xpath("//div[contains(@class,'sales-item-config')]")).findElement(By.className("strike-price")).getText().trim().isEmpty(),"Strike price present");
                        Assert.assertFalse(isElementDisplayedIn(By.xpath(".//p[@class='highlight price']")),"Sales price present");
                    }
                    else
                    {
                        Assert.assertFalse(isElementDisplayedIn(By.xpath(".//p[@class=' price']")),"Price present");
                        Assert.assertFalse(isElementDisplayedIn(By.xpath(".//p[@class='highlight price']")),"Sales price present");
                        Assert.assertFalse(isElementDisplayedIn(By.className("strike-price")),"strike price present");

                    }
                }
            }
            return true;
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
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
}
