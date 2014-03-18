package com.sonyericsson.automatedtests.page;

import java.util.Arrays;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;

public class FilteredCategoryPage extends CwsAbstractBaseTest {
    String host = "";
    public WebDriver driver;
    public String parent;
    public Set<String> handlers;
    public FilteredCategoryPage(String browser, String targetHost, boolean javascriptRequired) throws Exception {
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
    public boolean isProductsPresent() throws Exception {
    	scrollPage();
    	if(isPhonesPresent())
    		return true;
    	else
    		return false;
      /*  int i = getElementCount(By.xpath("//html/body/div[4]/div/div[3]/div"));
        Reporter.log("Row:" + i);
        for (int k = 1; k < i; k++) {
            int j = getElementCount(By.xpath("//html/body/div[4]/div/div[3]/div[" + k + "]/div"));
            Reporter.log("Col:" + j);
            for (int l = 1; l < j; l++) {
                if (l % 2 != 0) {
                    Assert.assertTrue(isElementDisplayed(By.xpath("//html/body/div[4]/div/div[3]/div[" + k + "]/div["
                            + l + "]/img")));
                    Reporter.log("Product image at: " + k + " : " + l);

                    Assert.assertTrue(isElementDisplayed(By.xpath("//html/body/div[4]/div/div[3]/div[" + k + "]/div["
                            + l + "]/h3")));
                    Reporter.log("Product name at: " + k + " : " + l);

                    Assert.assertTrue(isElementDisplayed(By.xpath("//html/body/div[4]/div/div[3]/div[" + k + "]/div["
                            + l + "]/p[1]")));
                    Reporter.log("Product cost at: " + k + " : " + l);
                    Assert.assertTrue(isElementDisplayed(By.xpath("//html/body/div[4]/div/div[3]/div[" + k + "]/div["
                            + l + "]/a")));
                    Reporter.log("Product button at: " + k + " : " + l);
                    try {
                        int m = getElementCount(By.xpath("//html/body/div[4]/div/div[3]/div[" + k + "]/div[" + l
                                + "]/ul/li"));
                        for (int z = 1; z < m; z++) {
                            Assert.assertTrue(isElementDisplayed(By.xpath("//html/body/div[4]/div/div[3]/div[" + k
                                    + "]/div[" + l + "]/ul/li[" + z + "]")));
                        }
                        Reporter.log("Product benefits at: " + k + " : " + l);
                    } catch (Exception e) {
                        Reporter.log("Product benefits missing at: " + k + " : " + l);
                    }
                    try {
                        int m = getElementCount(By.xpath("//html/body/div[4]/div/div[3]/div[" + k + "]/div[" + l
                                + "]/p[2]/img"));
                        for (int z = 1; z < m; z++) {
                            Assert.assertTrue(isElementDisplayed(By.xpath("//html/body/div[4]/div/div[3]/div[" + k
                                    + "]/div[" + l + "]/p[2]/img[" + z + "]")));
                        }
                        Reporter.log("Product color swatch at: " + k + " : " + l);
                    } catch (Exception e) {
                        Reporter.log("Product color swatch missing at: " + k + " : " + l);
                    }
                } else {
                    // if (!(k == rowCnt - 1)) 
                    {
                        Reporter.log("Verticle devider: " + k + " : " + l);
                        Assert.assertTrue(isElementDisplayed(By.xpath("//html/body/div[4]/div/div[3]/div[" + k
                                + "]/div[" + l + "]")));
                        Reporter.log("Verticle devider found at: " + k + " : " + l);
                    }
                }
            }
        }*/
    	
    /*	 int i = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')]"));
         Reporter.log("No of rows : " + i);
         Reporter.log("----------------------------------------");
         for (int k = 1; k <= i; k++) {
             int j = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a"));
             Reporter.log(j + " :Products in row: " + k);
             Reporter.log("----------------------------------------");
             for (int x = 1; x <= j; x++) {
                 Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/img")));
                 Reporter.log("Place for Product image at: " + k + " : " + x);
                 if(getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/img")).getAttribute("class").contains("spinner"))
                 	Reporter.log("SPINNER PRESENT INSTEAD OF IMAGE "+ k + " : " + x);
                 else {
 					Reporter.log("Image present at "+ k + " : " + x);
 				}
                 Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/h3")));
    
                 String name = getText(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/h3"));
                 Reporter.log(name + " at: " + k + " : " + x);
                 Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/p[contains(@class,'price')]")));
                 Reporter.log("Product price at: " + k + " : " + x);
                 try
                 {
                 Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/p[contains(@class,'price')]/span")));
                 Assert.assertFalse(getText(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/p[contains(@class,'price')]/span")).isEmpty());
                 Reporter.log("Product strike through price at: " + k + " : " + x);
                 }
                 catch (AssertionError e) {
                 	Reporter.log("PRODUCT STRIKE THROUGH PRICE MISSING : " + k + " : " + x);
 				}
                         
                 
                 Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/span[@class='cta-button theme-button']")));
                 Reporter.log("Product button at: " + k + " : " + x);
                 try {
         
                         Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/ul[@class='bulleted-list']/li")));
                     Reporter.log("Product benefits at: " + k + " : " + x);
                     } 
                 catch (AssertionError e) {
                     Reporter.log("PRODUCT BENEFIT MISSING AT: " + k + " : " + x);
                 }
                 try {
                         Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+k+"]/a["+x+"]/p[@class='color-swatches']")));
                     
                     Reporter.log("Product color swatch at: " + k + " : " + x);
                 } catch (AssertionError e) {
                     Reporter.log("PRODUCT COLOR SWATCH MISSING AT: " + k + " : " + x);
                 }
                 Reporter.log("----------------------------------------");
             }
         }

        return true;*/
    }
    public Object[][] prepareList() throws Exception
    {
    	 ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)", "");
    	waitForElementPresent(By.xpath("//p[starts-with(@class,'quick-link')]"));
    	int count = getElementCount(By.xpath("//p[starts-with(@class,'quick-link')]"));
    	Object[][] list = new Object[count][2];
    	for(int i=0;i<count;i++)
    	{
    		int j=i+1;
    		list[i][0] = getDriver().findElement(By.xpath("//p[starts-with(@class,'quick-link')]["+j+"]/a")).getAttribute("href");
    		list[i][1]=getDriver().findElement(By.xpath("//p[starts-with(@class,'quick-link')]["+j+"]/a")).getText();

    	}
    	return list;
    }
    public boolean isPHPpage()
    {
    	try
    	{
    		getDriver().findElement(By.cssSelector("div.info-box.tab-panes"));
    		return true;
    	}
    	catch(Exception ex)
    	{
    		return false;
    	}
    }
}
