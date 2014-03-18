package com.sonyericsson.automatedtests.quick;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fest.swing.annotation.GUITest;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sonyericsson.automatedtests.CwsAbstractBaseTest;
import com.sonyericsson.automatedtests.page.AccessoryshopPage;

@GUITest
public class getResponseTime{
    private static final Level HTMLUNIT_LOG_LEVEL = Level.OFF;
    private static final String HTMLUNIT_CLASSNAME = "com.gargoylesoftware.htmlunit";
    private static AccessoryshopPage page;
    private WebDriver driver;
    private HashMap<String, String> hash = new HashMap<String, String>();
    

    
    @BeforeClass (groups = "quicktest")
    @Parameters({ "browser", "host", "baseUri", "javascriptRequired" })
    
    public void UsingAccessoryshopPage1(String browser, String host, String baseUri, boolean javascriptRequired)
            throws Exception {
        driver = new FirefoxDriver();
        String url = "http://" + host + baseUri;
        driver.get(url);
        driver.manage().window().maximize();
    }
  
    
    
    @Test
    public void testAccessories() throws Exception 
    {
        String url =driver.getCurrentUrl();
        try 
        {
            int i = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')]"));

            for (int k = 1; k <= i; k++) 
            {
                int j = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a"));
                for (int x = 1; x <= j; x++) 
                {
                    String name = driver.findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["
                            + k + "]/a[" + x + "]/h3")).getText();
                    driver.findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["
                            + k + "]/a[" + x + "]/span[@class='cta-button theme-button']")).click();
                    if(isElementPresent(By.xpath("//a[@class='cta-button cta-add-to-cart large']")))
                    {
                        driver.findElement(By.xpath("//a[@class='cta-button cta-add-to-cart large']")).click();
                        Thread.sleep(2000);
                        long startTime = System.currentTimeMillis();
                        driver.findElement(By.id("cart-checkout")).click();
                        waitForElementPresent(By.id("couponCode"));
                        long endTime = System.currentTimeMillis();
                        long tookTime = endTime - startTime;
                        float inSec = tookTime/1000.0F;
                        hash.put(name, String.valueOf(inSec));
                        driver.manage().deleteAllCookies();
                        driver.get(url);
                    }
                    else
                    {
                        driver.get(url);
                    }
                   
                }
            }
        } catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
        }
    }
    
    int getElementCount(By loc) throws Exception 
    {
        try
        {
            List <WebElement> elements = driver.findElements(loc);
            if(elements != null && elements.size() != 0)
                return elements.size();
            else
                return 0;
        }
        catch(Exception ex)
        {
            return 0;
        }
    }
    public boolean isElementPresent(By locator) throws Exception {
        try
        {
            driver.findElement(locator);
            return true;
        }
        catch(Exception ex)
        {
           // Reporter.log("Element not present");
            return false;
        }
    }
    public void waitForElementPresent(By loc) throws InterruptedException {
        for (int i = 0; i < 120; i++) {
            try {
                driver.findElement(loc);
                break;

            } catch (Exception ex) {
                Thread.sleep(1000);
            }
        }
    }
    @DataProvider(name = "DP1")
    public Object[][] createData() 
    {       
        String[][] array = new String[hash.size()][2];
        int count = 0;
        for(Map.Entry<String,String> entry : hash.entrySet()){
            array[count][0] = entry.getKey();
            array[count][1] = entry.getValue();
            count++;
        }
        return array;
    }
    @Test(dataProvider="DP1",dependsOnMethods="testAccessories",alwaysRun=true)
    public void inserInExcel(String name, String time) throws Exception
    {
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String myDB = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+"C://test.xls"+ ";DriverID=22;READONLY=false";
            Connection con = DriverManager.getConnection(myDB);
            Statement stat=con.createStatement();
            stat.execute("insert into [Sheet1$] values ('"+name+"','"+time+"')");
            stat.close();
            con.close();
        }
        catch(Exception ex)
        {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
        }
    }
    @AfterClass
    public void quitDriver() 
    {
       driver.quit();
    }
}
