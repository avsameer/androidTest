package com.sonyericsson.automatedtests;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.WaitForCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.base.Function;

/**
 * This class basically implements the selenium methods of CwsSeleniumFactory and reduces
 * the lines of code in actual test scripts
 */
public class CwsSeleniumBaseTest {
    public static HashMap<String, String> accessoryHash = new HashMap<String, String>();
    public static HashMap<String, String> phoneHash = new HashMap<String, String>();
    WebElement element = null;
    protected long timeOutInSeconds = 5;
    WebDriver driver;

    CwsSeleniumBaseTest(WebDriver webdriver) {
        this.driver = webdriver;
    }

    protected WebDriver getDriver() {
        return driver;
    }
    public WebElement findElement(final By locator) throws Exception {
   
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        wait.until(presenceOfElementLocated(locator));
        return getDriver().findElement(locator);
    }
    public List<WebElement> findElements(final By locator) throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        wait.until(presenceOfElementLocated(locator));
        return getDriver().findElements(locator);
    }
    private Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
        return new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        };
    }

    void openUrl(String url) throws Exception {
    	
    		 getDriver().manage().window().maximize();
        getDriver().get(url);
       
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    boolean isElementPresent(By... object) throws Exception {

        boolean flag = false;
       
            element = findElement((By) object[0]);

            List<WebElement> lst_Elements = null;
            for (int i = 1; i < object.length; i++) {
                if (i == object.length - 1)
                    lst_Elements = element.findElements(object[i]);
                else
                    element = element.findElement(object[i]);
            }
            if (lst_Elements != null && lst_Elements.size() > 0)
                flag = true;
            else
                flag = element != null ? true : false;
        return flag;
    }
    boolean isElementDisplayed(By locator) throws Exception {
        try
        {
            getDriver().findElement(locator).isDisplayed();
            return true;
        }
        catch(Exception ex)
        {
           // Reporter.log("Element not Displayed");
            return false;
        }
    }

    boolean isLinkPresent(String link) throws Exception {
        return findElement(By.linkText(link)).isDisplayed();
    }

    void click(By... object) throws Exception {
        long initTime = System.currentTimeMillis();
        System.out.println("Before click: " + initTime);
        
            element = findElement(object[0]);
            for (int i = 1; i < object.length; i++) {
                element = element.findElement(object[i]);
            }

            element.click();
        System.out.println("Total time - click: " + (System.currentTimeMillis() - initTime) / 1000);
    }

    List<WebElement> findElement(By... object) throws Exception {

    	if (object.length > 1)
    		element = findElement(object[0]);
    	List<WebElement> lst_Elements_new = getDriver().findElements(object[0]);
    	List<WebElement> lst_Elements = null;
    	for (int i = 1; i < object.length; i++) {
    		if (i == object.length - 1) {
    			lst_Elements = element.findElements(object[i]);
    		} else
    			element = element.findElement(object[i]);
    	}
    	if (lst_Elements != null && lst_Elements.size() > 0)
    		return lst_Elements;
    	else {
    		if (lst_Elements_new != null)
    			return lst_Elements_new;
    		else
    			return null;
    	}

    }

    int getElementCount(By... object) throws Exception {
       
            element = findElement(object[0]);
            List<WebElement> lst_Elements_new = findElements(object[0]);
            List<WebElement> lst_Elements = null;
            for (int i = 1; i < object.length; i++) {
                if (i == object.length - 1)
                    lst_Elements = element.findElements(object[i]);
                else
                    element = element.findElement(object[i]);
            }

            if (lst_Elements != null && lst_Elements.size() > 0)
                return lst_Elements.size();
            else {
                if (lst_Elements_new != null)
                    return lst_Elements_new.size();
                else
                    return 0;
            }
    
    }

    String getText(By... object) throws Exception {
      
            element = findElement(object[0]);
            List<WebElement> lst_Elements_new = getDriver().findElements(object[0]);
            List<WebElement> lst_Elements = null;
            for (int i = 1; i < object.length; i++) {
                if (i == object.length - 1)
                    lst_Elements = element.findElements(object[i]);
                else
                    element = element.findElement(object[i]);
            }

            if (lst_Elements != null && lst_Elements.size() > 0)
                return lst_Elements.get(0).getText();
            else {
                if (lst_Elements_new != null)
                    return lst_Elements_new.get(0).getText();
                else
                    return "";
            }

    }

    public String getPageSource() throws Exception {
        return getDriver().getPageSource();
    }

    void typeText(By object, String text) throws Exception {
        findElement(object).sendKeys(text);
    }

    void submit(By object) throws Exception {
        findElement(object).submit();
    }

    boolean isTextPresent(String match) throws Exception {
        return getDriver().getPageSource().indexOf(match) > -1;
    }
    public void populateHashMap()
    {
        try {
            //no. of total rows
            int i = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')]"));
            for (int k = 1; k <= i; k++) {
                //no. of columns in each row
                int j = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a"));
                
                //iterate each item
                for (int x = 1; x <= j; x++) 
                {   
                    String value="";
                    String url=getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x+ "]")).getAttribute("href");
                    if(!url.endsWith("/"))
                    {
                        url = url + "/";
                    }
                    //Store Image availability
                    if(!getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x+ "]/img")).getAttribute("src").isEmpty())
                    {
                        if (getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x+ "]/img")).getAttribute("class").contains("spinner"))
                        {
                            value="F;";
                        }   
                        else 
                        {
                            value="T;";
                        }
                    }
                    else
                    {
                        value="F;";
                    }

                    //Store product Name 
                    String name = getText(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x+ "]/h3"));
                    name = name.replace("(", "").replace(")", "");
                    value = value+name+";";
                    
                    //Store no. of swatches
                    if(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x+ "]/p[@class='color-swatches']/img")))
                    {
                        int cnt = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x+ "]/p[@class='color-swatches']/img"));
                        value = value+cnt+";";
                        List<WebElement> swatches = getDriver().findElements(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x+ "]/p[@class='color-swatches']/img"));
                        String colorList = "";
                        for(WebElement swatch : swatches)
                        {
                            colorList += "|" + swatch.getAttribute("src").trim();
                        }
                        colorList = colorList.replaceFirst(Pattern.quote("|"), "");
                        value = value + colorList +";";
                    }
                    else
                    {
                        value = value+"0;";
                        value = value + "NOSWATCH;";
                    }

                    //Store price(if strike price is not present)
                    if(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k + "]/a[" + x + "]/p[@class=' price']")))
                    {
                        String price = getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k + "]/a[" + x + "]/p[@class=' price']"))
                                            .getText();
                        value= value+price+";F";
                    }
                    //store sale price and strike price
                    else if(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k +"]/a[" + x + "]/p[@class='highlight price']/span[@class='strike-price']")))
                    {
                        String strikePrice = getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k +"]/a[" + x + "]/p[@class='highlight price']/span[@class='strike-price']")).getText().trim();
                        String salePrice = getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k +"]/a[" + x + "]/p[@class='highlight price']")).getText().replace(strikePrice, "").trim();
                        value = value+salePrice+";"+strikePrice;
                    }
                    if(getDriver().getCurrentUrl().contains("phones"))
                    {
                        phoneHash.put(url, value);
                    }
                    else
                    {
                        accessoryHash.put(url, value);
                    }

                }
            }
        } catch (Exception ex) {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
        }
    }
    
    public boolean isDuplicateSwatches(List<WebElement> elements)
    {
        Reporter.log("No. of swatches : "+elements.size());
        List<String> list = new ArrayList<String>();
        for(WebElement ele : elements)
        {
            list.add(ele.getAttribute("alt"));
        }
        Set<String> set = new HashSet<String>(list);

        if(set.size() < list.size()){
            return true;
        }
        else
            return false;
    }
    
    public boolean isDuplicateSwatches()
    {
        try
        {
            List <WebElement> images = getDriver().findElement(By.cssSelector("div.color-swatches")).findElements(By.cssSelector("img.noscale.loaded"));
            List <WebElement> links = getDriver().findElement(By.cssSelector("div.color-swatches")).findElements(By.tagName("a"));
            List <String> hrefs = new ArrayList<String>();
            if(images.size() == links.size()+1)
            {
                hrefs.add(getDriver().getCurrentUrl());
                for(WebElement ele : links)
                {
                    hrefs.add(ele.getAttribute("href"));
                }
                Set<String> set = new HashSet<String>(hrefs);

                if(set.size() < hrefs.size())
                {
                    return true;
                }
                else
                    return false;

            }
            else
            {
                throw new Exception("Swatches are not proper");
            }

        }
        catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            return true;
        }
    }
}
