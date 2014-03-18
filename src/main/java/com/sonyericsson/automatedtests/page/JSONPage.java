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

public class JSONPage extends CwsAbstractBaseTest {
    private static final String BRACKET = "\\)";
    String host = "";
    public WebDriver driver;


    public JSONPage(String browser, String targetHost, boolean javascriptRequired) throws Exception {
        init(browser, targetHost, javascriptRequired);
    }
    public void quitDriver() {
        Reporter.log("Calling Quit Driver");
        destroy();
    }

    public boolean checkStoreInfoJSON(String host, String locale) throws Exception {
        try
        {
            Reporter.log("Testing Store Info JSON");
            String url = "http://" + host + "/storefront/store-info-json.ep?storeCode=SEMC-"+locale.toUpperCase();
            Reporter.log(url);
            openUrl(url);
            waitForElementPresent(By.xpath("//body"));
            Thread.sleep(5000);
            String unParsedDetails = getText(By.xpath("//body"));
            List<String> key = new ArrayList<String>();
            //  ArrayList<CharSequence> key = new ArrayList<CharSequence>();
            key.add("\"deliveryName\"");
            key.add("\"deliveryCost\"");
            if(checkKeyInJSON(key,unParsedDetails))
            {
                Reporter.log("Store Info JSON tested successfully it is working fine..");
                return true;
            }
            else
            {
                Reporter.log("There is some problem with Store Info JSON..");
                return false;
            }
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            return false;
        }
    }
    public boolean checkSalesItemsJSON(String host, String locale) throws Exception {
        try
        {
            Reporter.log("Testing Sales Items JSON");
            String url = "http://" + host
            + "/storefront/sales-items-json.ep?storeCode=SEMC-SE&pID=key.MobilePhone.Xperianeov";
            openUrl(url);

            waitForElementPresent(By.xpath("//body"));
            Thread.sleep(5000);
            String unParsedDetails = getText(By.xpath("//body"));

            Reporter.log(url);
            List<String> key = new ArrayList<String>();
            //  ArrayList<CharSequence> key = new ArrayList<CharSequence>();
            key.add("\"code\"");
            key.add("\"title\"");
            key.add("\"description\"");
            key.add("\"inTheBox\"");
            key.add("\"inTheBox\"");
            key.add("\"priceWithCurrency\"");
            key.add("\"strikeThroughPrice\"");
            key.add("\"stockLevel\"");
            key.add("\"imageUrl\"");
            key.add("\"imageFeatureUrl\"");
            key.add("\"cwsExternalId\"");
            key.add("\"maxOrderLimit\"");
            key.add("\"enableDate\"");
            key.add("\"color\"");
            key.add("\"benefits\"");
            if(checkKeyInJSON(key,unParsedDetails))
            {
                Reporter.log("Sales items JSON tested successfully it is working fine..");
                return true;
            }
            else
            {
                Reporter.log("There is some problem with Sales Items JSON..");
                return false;
            }
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            return false;
        }
    }
    public boolean checkPriceListingJSON(String host, String locale) throws Exception {
        try
        {
            Reporter.log("Testing Price Listing JSON");
            String url = "http://" + host + "/storefront/products-from-price-listing.ep?storeCode=SEMC-"+locale.toUpperCase();
            openUrl(url);
            waitForElementPresent(By.xpath("//body"));
            Thread.sleep(5000);
            String unParsedDetails = getText(By.xpath("//body"));
            Reporter.log(url);
            List<String> key = new ArrayList<String>();
            // ArrayList<CharSequence> key = new ArrayList<CharSequence>();
            key.add("\"cwsExternalId\"");
            key.add("\"fromPriceWithCurrency\"");
            key.add("\"featuredProducts\"");
            if(checkKeyInJSON(key,unParsedDetails))
            {
                Reporter.log("Price Listing JSON tested successfully it is working fine..");
                return true;
            }
            else
            {
                Reporter.log("There is some problem with Price Listing JSON..");
                return false;
            }
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            return false;
        }
    }
    public boolean checkSalesItemsJSONnew(String host, String locale) throws Exception {
        try
        {
            Reporter.log("Testing Sales Items JSON - New");
            String url = "http://" + host
            + "/storefront/sales-items-json.ep?storeCode=SEMC-SE&pID=key.MobilePhone.Xperianeov&isNew=true";
            openUrl(url);
            waitForElementPresent(By.xpath("//body"));
            Thread.sleep(5000);
            String unParsedDetails = getText(By.xpath("//body"));

            Reporter.log(url);
            List<String> key = new ArrayList<String>();
            //ArrayList<CharSequence> key = new ArrayList<CharSequence>();
            key.add("\"code\"");
            key.add("\"title\"");
            key.add("\"description\"");
            key.add("\"inTheBox\"");
            key.add("\"inTheBox\"");
            key.add("\"priceWithCurrency\"");
            key.add("\"strikeThroughPrice\"");
            key.add("\"stockLevel\"");
            key.add("\"imageUrl\"");
            key.add("\"imageFeatureUrl\"");
            key.add("\"cwsExternalId\"");
            key.add("\"maxOrderLimit\"");
            key.add("\"enableDate\"");
            key.add("\"productStatus\"");
            key.add("\"color\"");
            key.add("\"benefits\"");
            if(checkKeyInJSON(key,unParsedDetails))
            {
                Reporter.log("Sales items JSON - New tested successfully it is working fine..");
                return true;
            }
            else
            {
                Reporter.log("There is some problem with Sales Items JSON - New..");
                return false;
            }
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            return false;
        }
    }
    public boolean checkPriceListingJSONnew(String host, String locale) throws Exception {
        try
        {
            Reporter.log("Testing Price Listing JSON - New");
            String url = "http://" + host + "/storefront/products-from-price-listing.ep?storeCode=SEMC-"+locale.toUpperCase()+"&isNew=true";
            openUrl(url);
            waitForElementPresent(By.xpath("//body"));
            Thread.sleep(5000);
            String unParsedDetails = getText(By.xpath("//body"));
            Reporter.log(url);
            List<String> key = new ArrayList<String>();
            // ArrayList<CharSequence> key = new ArrayList<CharSequence>();
            key.add("\"cwsExternalId\"");
            key.add("\"fromPriceWithCurrency\"");
            key.add("\"productStatus\"");
            key.add("\"featuredProducts\"");
            if(checkKeyInJSON(key,unParsedDetails))
            {
                Reporter.log("Price Listing JSON - New tested successfully it is working fine..");
                return true;
            }
            else
            {
                Reporter.log("There is some problem with Price Listing JSON -New..");
                return false;
            }
        }
        catch(Throwable ex)
        {
            Reporter.log(ex.getMessage());
            return false;
        }
    }
    public boolean checkKeyInJSON(List<String> key,String unParsedDetails) throws Exception {
        System.out.println("test");
        // waitForElementPresent(By.xpath("//body"));
        // Thread.sleep(5000);
        //  String unParsedDetails = getText(By.xpath("//body"));
        Reporter.log("===================================================================================================");
        Reporter.log(unParsedDetails);
        Reporter.log("===================================================================================================");
        int i = unParsedDetails.indexOf("]") - unParsedDetails.indexOf("[");
        int savedCount = 0;
        if (i > 2) 
        {
            for (String checkString : key) 
            {
                if (unParsedDetails.contains(checkString)) 
                {
                    String cs =  checkString;
                    int lastIndex = 0;
                    int count = 0;
                    while (lastIndex != -1) 
                    {
                        lastIndex = unParsedDetails.indexOf(cs, lastIndex);
                        if (lastIndex != -1) 
                        {
                            count++;
                            lastIndex += cs.length();
                        }
                    }
                    if (savedCount == 0) 
                    {
                        savedCount = count;
                        Reporter.log("Feed name:" + cs + "::::Count:" + count);
                    } 
                    else 
                    {
                        if (cs != "\"featuredProducts\"") 
                        {
                            if (savedCount == count)
                                Reporter.log("Feed name:" + cs + "::::Count:" + count);
                            else 
                            {
                                Reporter.log("Count is not proper for keyword : "+cs+" it should be "+savedCount+" but is "+count);
                                return false;
                            }
                        }
                    }
                } 
                else 
                {
                    Reporter.log("Keyword "+checkString.toString()+" not found....");
                    return false;
                }
            }
        }
        else
        {
            Reporter.log("Almost Blank page ");
            return false;
        }
        return true;
    }
}
