package com.sonyericsson.automatedtests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.locators.GoogleChromeLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.seleniumemulation.ElementFinder;
import org.openqa.selenium.internal.seleniumemulation.GetAttribute;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.BrowserVersion;


/**
 * This class basically acts as an interface for selenium methods and reduces
 * the lines of code in actual test scripts
 */
public class CwsAbstractBaseTest implements ICwsTest {
    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";
    public static String itemHavingAddToCart = "";
    public static String itemHavingPreregister = "";
    public static String folder2 = "";
    public static String folder1 = "";
    public static String locale="";
    CwsSeleniumBaseTest obj;
    public static WebDriver driver = null;
    public HashMap<String, String> deliveryHash = new HashMap<String, String>();
    public HashMap<String, String> billingHash = new HashMap<String, String>();
    protected void init(String browser, String targetHost, boolean javascriptRequired) throws Exception {
        if(folder1.isEmpty())
        {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy");
            folder1 = sdf.format(date);
        }
        if(folder2.isEmpty())
        {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh_mm");
            folder2 = sdf.format(date);
        }
        System.out.println("Init called");
        driver = loadWebDriver(browser, javascriptRequired);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        obj = new CwsSeleniumBaseTest(driver);
        testAuthentication(targetHost);
    }
    protected void destroy() {
        if (null != driver) {
            driver.quit();
        }
    }
    
    private WebDriver loadWebDriver(String browser, boolean javascriptRequired) throws Exception {
        final String strHubUrl = "HUB_URL";
        if (browser.equalsIgnoreCase("*firefox")) {
          /* String firebugFilePath = "C:\\firebug-1.11.2-fx.xpi";
            FirefoxProfile profile = new FirefoxProfile();      
            profile.addExtension(new File(firebugFilePath));*/
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            /*ChromeDriverService service = new ChromeDriverService.Builder().usingChromeDriverExecutable(
                    new File(PropertyLoader.getProperty("CHROMEDRIVER"))).usingAnyFreePort().build();
            service.start();*/


            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            driver = new ChromeDriver();
            //driver = new RemoteWebDriver(new URL(PropertyLoader.getProperty(strHubUrl)), capability);

        } else if (browser.equalsIgnoreCase("*ie")) {
            driver = new InternetExplorerDriver();

          //   DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
           // driver = new RemoteWebDriver(new URL(PropertyLoader.getProperty(strHubUrl)), capability);
        } else if (browser.equalsIgnoreCase("*none")) {

            HtmlUnitDriver htmldriver = new HtmlUnitDriver(); //new HtmlUnitDriver();
            htmldriver.setJavascriptEnabled(javascriptRequired);
            return htmldriver;
        }
        return driver;
    }
    protected WebDriver getDriver() {
        return this.driver;
    }
    public void openUrl(Object... o) throws Exception {
        if (o[0] instanceof String)
            obj.openUrl((String) o[0]);
        else
            Assert.fail("Method expects a <String> type argument");
    }
    public boolean isElementPresent(By locator) throws Exception {
        try
        {
            getDriver().findElement(locator);
            return true;
        }
        catch(Exception ex)
        {
           // Reporter.log("Element not present");
            return false;
        }
   
        
    }
    public void waitForElementPresent(By loc) throws InterruptedException {
        for (int i = 0; i < 60; i++) {
            try {
                getDriver().findElement(loc);
                break;

            } catch (Exception ex) {
                Thread.sleep(1000);
            }
        }
    }
    public boolean isElementDisplayed(By locator) throws Exception {
        try
        {
            getDriver().findElement(locator).isDisplayed();
            return true;
        }
        catch(Throwable ex)
        {
           // Reporter.log("Element not Displayed");
            return false;
        }
    }
    public boolean isElementDisplayedIn(By locator) throws Exception {
        try
        {
            getDriver().findElement(By.xpath("//div[contains(@class,'sales-item-config')]")).findElement(locator).isDisplayed();
            return true;
        }
        catch(Exception ex)
        {
           // Reporter.log("Element not Displayed");
            return false;
        }
    }
    public boolean isLinkPresent(Object... o) throws Exception {
        if (o[0] instanceof String)
            return obj.isLinkPresent((String) o[0]);
        else
            Assert.fail("Method expects a <String> type argument");

        return false;
    }
    /**
     * this method assists in clicking the element
     */
    public void click(By locator) throws Exception {
       try
       {
           getDriver().findElement(locator).click();
       }
       catch(Exception ex)
       {
           Reporter.log(ex.getMessage());
       }
    }
    public List<WebElement> findElement(Object... o) throws Exception {
        boolean flag = true;
        By objBy[] = new By[o.length];
        for (int i = 0; i < o.length; i++) {
            if (!(o[i] instanceof By)) {
                flag = false;
                break;
            } else {
                objBy[i] = (By) o[i];
            }
        }
        if (flag)
            return obj.findElement(objBy);
        else
            Assert.fail("Method expects <By> type arguments");

        return null;
    }
    public int getElementCount(Object... o) throws Exception {
        boolean flag = true;
        By objBy[] = new By[o.length];
        for (int i = 0; i < o.length; i++) {
            if (!(o[i] instanceof By)) {
                flag = false;
                break;
            } else {
                objBy[i] = (By) o[i];
            }
        }
        if (flag)
            return obj.getElementCount(objBy);
        else
            Assert.fail("Method expects <By> type arguments");

        return 0;
    }
    public void typeText(Object... o) throws Exception {
        if ((o[0] instanceof By) && (o[1] instanceof String))
            obj.typeText((By) o[0], (String) o[1]);
        else
            Assert.fail("Method expects (<By>, <String>) type arguments");
    }

    public String getText(Object... o) throws Exception {
        boolean flag = true;
        By objBy[] = new By[o.length];
        for (int i = 0; i < o.length; i++) {
            if (!(o[i] instanceof By)) {
                flag = false;
                break;
            } else {
                objBy[i] = (By) o[i];
            }
        }
        if (flag)
            return obj.getText(objBy);
        else
            Assert.fail("Method expects <By> type arguments");

        return "";
    }
    public float removeCurrencySign(String price) {
        try
        {
        String tempPrice = price.replace(",", "").substring(1);
        return Float.parseFloat(tempPrice);
        }
        catch(Exception ex)
        { 
            return 0;
        }
    }
    public int getIndexOfSelectedStep(By mainDiv, String classtofind)
    {
        try
        {
            int count=1;
            List<WebElement> elements = getDriver().findElement(mainDiv).findElements(By.cssSelector("div."+classtofind));
            for(WebElement ele : elements)
            {
                if(ele.getAttribute("class").contains("current"))
                {
                    return count;
                }
                else
                    count++;
            }
            return count;
        }
        catch(Exception ex)
        { 
            return 0;
        }
    }

    public String getPageSource() throws Exception {
        return obj.getPageSource();
    }

    public void submit(Object... o) throws Exception {
        if (o[0] instanceof By)
            obj.submit((By) o[0]);
        else
            Assert.fail("Method expects <By> type arguments");
    }
    public boolean isTextPresent(Object... o) throws Exception {
        if (o[0] instanceof String)
            return obj.isTextPresent((String) o[0]);
        else
            Assert.fail("Method expects a <String> type argument");

        return false;
    }
    public void goBack() throws Exception {
        getDriver().navigate().back();
    }
    public Element parseXml() throws Exception {
        Element docEle = null;
        try {
            String pageSource = getDriver().getPageSource();
            //System.out.println("SOURCE--\n\n" + pageSource);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            Document dom;
            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            //parse using builder to get DOM representation of the XML file
            dom = db.parse(new ByteArrayInputStream(pageSource.getBytes("UTF-8")));

            docEle = dom.getDocumentElement();
            //System.out.println(docEle.getNodeName());
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            Assert.fail("Error", e);
        }
        return docEle;
    }

    public void mouseAction(WebElement element1, WebElement element2) throws Exception {
        Actions builder = new Actions(getDriver());

        Action MoveMouseAction = builder.clickAndHold(element1).moveToElement(element2).release(element2).build();

        MoveMouseAction.perform();
    }

    public void switchWindow() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public void switchFrame(Object o) {
        if (o instanceof Integer) {
            driver.switchTo().frame((Integer) o);
        } else if (o instanceof WebElement) {
            driver.switchTo().frame((WebElement) o);
        } else if (o instanceof String) {
            driver.switchTo().frame((String) o);
        }
    }

    public void closeWindow() {
        driver.switchTo().window(driver.getWindowHandle()).close();
    }

    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }
    public String getAttribute(By loc, String attribute) throws Exception {
        WebElement element = findElement(loc).get(0);
        return element.getAttribute(attribute);
    }
    public boolean testQuickHeaderNavLinksGeneric() throws Exception {
        try {
            int i = 0;
            Reporter.log("Test Header element for this page");
            Assert.assertTrue(isElementPresent(By.cssSelector("#bnw-super-nav-list")),"Supper/Top header not present");
            Reporter.log("Super header present");
            int cnt = getElementCount(By.xpath("//ul[@id='bnw-super-nav-list']/li"));
            Reporter.log("Super nav " + cnt);
            for (i = 2; i <= cnt; i++) {
                Assert.assertTrue(isElementPresent(By.xpath("//ul[@id='bnw-super-nav-list']/li[" + i + "]")),"Link at super header(Login/Signup/Change country not present");
                String navText = getText(By.xpath("//ul[@id='bnw-super-nav-list']/li[" + i + "]"));
                Reporter.log("Super nav link present " + i + " : " + navText);
            }
            Assert.assertTrue(isElementPresent(By.className("logo")),"Sony logo's header not present");
            Assert.assertTrue(isElementDisplayed(By.xpath("//nav[@id='access']/div/a")),"Sony logo not present");
            Reporter.log("Logo present ");
            cnt = getElementCount(By.xpath("//ul[@id='menu-primary-menu']/li"));
            Reporter.log("Links present in Sony logo's header : "+cnt);
            for (i = 1; i <= cnt; i++) {
                Assert.assertTrue(isElementPresent(By.xpath("//ul[@id='menu-primary-menu']/li[" + i + "]/a")),"Link not present in Sony logo's header");
                String navText = getText(By.xpath("//ul[@id='menu-primary-menu']/li[" + i + "]/a"));
                Reporter.log("Primary menu link present " + i + " : " + navText);
                if (!isLinkPresent(navText)) {
                    return false;
                }
            }
            Assert.assertTrue(isElementPresent(By.className("search")),"Search bar not present");
            Reporter.log("Search box present ");
            Assert.assertTrue(isElementPresent(By.cssSelector("nav.sub-nav")),"Add to cart's header not present");
            Reporter.log("Sub navigation present");
            Assert.assertTrue(isElementPresent(By.id("cart-button")),"Cart not present");
            Reporter.log("Cart icon present");
            Reporter.log("Done - Header test OK");
            return true;
        } catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
   
    public boolean testQuickHeaderNavLinksGenericForCheckout() throws Exception {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-250)", "");
            int i = 0;
            Reporter.log("Test Header element for this page");
            Assert.assertTrue(isElementPresent(By.cssSelector("#bnw-super-nav-list")),"Supper/Top header not present");
            Reporter.log("Super header present");
            int cnt = getElementCount(By.xpath("//ul[@id='bnw-super-nav-list']/li"));
            Reporter.log("Super nav " + cnt);
            for (i = 2; i <= cnt; i++) {
                Assert.assertTrue(isElementPresent(By.xpath("//ul[@id='bnw-super-nav-list']/li[" + i + "]")),"Link at super header(Login/Signup/Change country not present");
                String navText = getText(By.xpath("//ul[@id='bnw-super-nav-list']/li[" + i + "]"));
                Reporter.log("Super nav link present " + i + " : " + navText);
            }
            Assert.assertTrue(isElementPresent(By.className("logo")),"Sony logo's header not present");
            Assert.assertTrue(isElementDisplayed(By.xpath("//nav[@id='access']/div/a")),"Sony logo not present");
            Reporter.log("Logo present ");
            cnt = getElementCount(By.xpath("//ul[@id='menu-primary-menu']/li"));
            Reporter.log("Links present in Sony logo's header : "+cnt);
            for (i = 1; i <= cnt; i++) {
                Assert.assertTrue(isElementPresent(By.xpath("//ul[@id='menu-primary-menu']/li[" + i + "]/a")),"Link not present in Sony logo's header");
                String navText = getText(By.xpath("//ul[@id='menu-primary-menu']/li[" + i + "]/a"));
                Reporter.log("Primary menu link present " + i + " : " + navText);
                if (!isLinkPresent(navText)) {
                    return false;
                }
            }
            Assert.assertTrue(isElementPresent(By.className("search")),"Search bar not present");
            Reporter.log("Search box present ");       
            return true;
        } catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    public void testFooterNavLinksGeneric() throws Exception {
        try {
            Reporter.log("Test Footer element for this page");
            Assert.assertTrue(isElementPresent(By.id("ft")), "Footer should be present");
            WebElement element = getDriver().findElement(By.id("cwsFooternavLinks"));
            List<WebElement> lst_anchors = getDriver().findElement(By.id("cwsFooternavLinks")).findElements(
                    By.tagName("A"));

            for (int i = 0; i < lst_anchors.size(); i++) {
                Reporter.log("i=" + i);
                if (getDriver().findElement(By.id("cwsFooternavLinks")).findElements(By.tagName("A")).get(i)
                        .getAttribute("target").equals("")) {
                    String anchorText = getDriver().findElement(By.id("cwsFooternavLinks"))
                            .findElements(By.tagName("A")).get(i).getText();
                    getDriver().findElement(By.id("cwsFooternavLinks")).findElements(By.tagName("A")).get(i).click();

                    Assert.assertNotNull(getElementCount(By.xpath("//*")), "Page contents for link " + anchorText
                            + " should not be empty");

                    //selObj1.setObject("$");

                    //Assert.assertFalse(isTextPresent(selObj1),"Test unrendered velocity code");

                    //goBackAndWait(By.id("cwsFooternavLinks"), 10);
                }
            }

            Reporter.log("Done - Footer test OK");
        } catch (Throwable ex) {
            Reporter.log(ex.getMessage());
        }

    }
    public boolean testQuickFooterNavLinksGeneric() throws Exception {
        try {
            Reporter.log("Test Footer element for this page");
            Assert.assertTrue(isElementPresent(By.cssSelector("footer")),"Footer is not present");
            Reporter.log("Footer present");
            Assert.assertTrue(isElementPresent(By.cssSelector("a.logo")),"Sony logo not present");
            Reporter.log("Logo present");
            int cnt = getElementCount(By.xpath("//ul[starts-with(@id,'menu-footer-menu')]/li"));
            Reporter.log("Number of links present in footer :"+cnt);
            for (int i = 1; i < cnt; i++) {
                Assert.assertTrue(isElementPresent(By
                        .xpath("//ul[starts-with(@id,'menu-footer-menu')]/li[" + i + "]/a")),"Link not present in footer");
                String footerText = getText(By.xpath("//ul[starts-with(@id,'menu-footer-menu')]/li[" + i + "]/a"));
                Reporter.log("Footer link present " + footerText);
                if (!isLinkPresent(footerText)) {
                    return false;
                }
            }
            Assert.assertTrue(isElementPresent(By.xpath("//div/nav/p")),"Copyright not present");
            Reporter.log("footer copyright is visible");
            Assert.assertTrue(isElementPresent(By.cssSelector("ul.get-connected-list")),"Stay connected list not present");
            Reporter.log("Stay connected list displayed");
            Reporter.log("Done - Footer test OK");
            return true;
        } catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }

    public void testFooterNavLinksGeneric1() throws Exception {
        Reporter.log("Test Footer element for this page");
        Assert.assertTrue(isElementPresent(By.id("cwsFooter")), "Footer should be present");
        WebElement element = getDriver().findElement(By.id("cwsFooternavLinks"));
        List<WebElement> lst_anchors = getDriver().findElement(By.id("cwsFooternavLinks"))
                .findElements(By.tagName("A"));

        for (int i = 0; i < lst_anchors.size(); i++) {
            Reporter.log("i=" + i);
            if (getDriver().findElement(By.id("cwsFooternavLinks")).findElements(By.tagName("A")).get(i)
                    .getAttribute("target").equals("")) {
                String anchorText = getDriver().findElement(By.id("cwsFooternavLinks")).findElements(By.tagName("A"))
                        .get(i).getText();
                getDriver().findElement(By.id("cwsFooternavLinks")).findElements(By.tagName("A")).get(i).click();

                Assert.assertNotNull(getElementCount(By.xpath("//*")), "Page contents for link " + anchorText
                        + " should not be empty");

                //selObj1.setObject("$");

                //Assert.assertFalse(isTextPresent(selObj1),"Test unrendered velocity code");

                //goBackAndWait(By.id("cwsFooternavLinks"), 2);
            }
        }
        Reporter.log("Done - Footer test OK");
    }

    private void confirmUser() throws Exception {
        ((JavascriptExecutor) getDriver()).executeScript("window.confirm = function(msg) { return true; }");
    }
    public String getDomain(String targetHost) {
        String strArr[] = targetHost.split("[.]");
        String initial = strArr[0];
        if (initial.startsWith(HTTP)) {
            initial = initial.replaceFirst(HTTP, "");
        } else if (initial.startsWith(HTTPS)) {
            initial = initial.replaceFirst(HTTPS, "");
        }
        return initial;
    }
    protected void testAuthentication(String targetHost) throws Exception {
        long initTime = System.currentTimeMillis();
        System.out.println("Start authentication: " + initTime);
        String domain = getDomain(targetHost);
        System.out.println(domain);
        if (domain.equalsIgnoreCase("www")) {
            ;
        } else

        {
            if (domain.contains("dev-demo")) {
                openUrl("http://eshop:eshopdev22@" + domain + ".sonymobile.com/storefront/gb", false);
                //  confirmUser();
            } else if (domain.contains("dev-int")) {
                openUrl("http://eshop:eshopdev18@" + domain + ".sonymobile.com/storefront/gb", false);
                // confirmUser();
            } else if (domain.contains("shop-stage")) {
                openUrl("http://eshop:eshopstage@shop-stage.sonymobile.com", false);
              //  openUrl("http://eshop:eshopstage@"+targetHost, false);
             
            } else if (domain.contains("shop-int")) {
                getDriver().navigate().to("http://eshop:eshopint@shop-int.sonymobile.com/gb/phones");
//                openUrl("http://eshop:eshopint@shop-int.sonymobile.com/", false);
                //confirmUser();
            } else if (domain.contains("shop.sonymobile"))
                openUrl("http://eshop:eshopProd@shop.sonymobile.com/gb/", false);
            System.out.println("Total authentication time: " + (System.currentTimeMillis() - initTime) / 1000);
        }
    }
    public void scrollPage() throws Exception {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
       for (int second = 0;second<100; second++) 
       {
           if(isElementDisplayed(By.xpath("//div[@class='full-wrapper store-footer clearfix loaded']")))
           {
               ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,150)", "");
               getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                break;
            } 
           else 
           {
                ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,150)", "");
            }
        }
      //  JavascriptExecutor js = (JavascriptExecutor)getDriver();
        //js..executeScript("Math.max(document.documentElement['clientHeight'], document.body['scrollHeight'], document.documentElement['scrollHeight'], document.body['offsetHeight'], document.documentElement['offsetHeight']);");
        //js.executeScript("window.scrollTo(0,Math.max(document.documentElement['clientHeight'], document.body['scrollHeight'], document.documentElement['scrollHeight'], document.body['offsetHeight'], document.documentElement['offsetHeight']));");
     //   js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight," + "document.body.scrollHeight,document.documentElement.clientHeight));");
    }

    public boolean isPhonesPresent() throws Exception {
        //wait for all the images to be loaded
        try
        {
            WebDriverWait wait = new WebDriverWait(getDriver(), 120);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[contains(@class,'spinner')]")));
        }
        catch (Exception ex)
        {
            Reporter.log("Some images are not loaded");
        }
        try {
            int i = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')]"));
            Reporter.log("No of rows : " + i);
            Reporter.log("----------------------------------------");
            boolean flag = true;
            for (int k = 1; k <= i; k++) {
                int j = getElementCount(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a"));
                Reporter.log(j + " :Products in row: " + k);
                Reporter.log("----------------------------------------");
                for (int x = 1; x <= j; x++) {
                    try
                    {
                        Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["
                                + k + "]/a[" + x + "]/img")),"Place for product image not present");
                        Reporter.log("Place for Product image at: " + k + " : " + x);

                        //Test spinner should not be present instead of image
                        if (getDriver()
                                .findElement(
                                        By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x
                                                + "]/img")).getAttribute("class").contains("spinner")){
                            throw new Exception("SPINNER PRESENT INSTEAD OF IMAGE " + k + " : " + x);
                        } 
                        if (getDriver()
                                .findElement(
                                        By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x
                                                + "]/img")).getAttribute("src").isEmpty()){
                            throw new Exception("SPINNER PRESENT INSTEAD OF IMAGE " + k + " : " + x);
                        }   
                        else {
                            Reporter.log("Image present at " + k + " : " + x);
                        }

                        //Test Space for product name and product name should not blank
                        Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["
                                + k + "]/a[" + x + "]/h3")),"Space for product name not present at "+ k + " : " + x);
                        Reporter.log("Space for product name present");
                        String name = getText(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x
                                + "]/h3"));
                        Assert.assertFalse(name.isEmpty(),"Product Name is blank at " + k + " : " + x);
                        Reporter.log(name + " at: " + k + " : " + x);

                        //Test price (sale price should not be present and strike price should be blank)
                        if(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k + "]/a[" + x + "]/p[@class=' price']")))
                        {
                            Reporter.log("Space for Product price present at: " + k + " : " + x);
                            String price = getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k + "]/a[" + x + "]/p[@class=' price']")).getText();
                            if(price.isEmpty())
                                Reporter.log("PRICE IS BLANK AT : " + k + " : " + x);
                            else
                                Reporter.log("Price present at : " + k + " : " + x);
                            Assert.assertFalse(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k + "]/a[" + x + "]/p[@class='sales-price price']")),"Sale price found at : "+ k + " : " + x);
                            if(!price.isEmpty())
                            {
                                String strikePrice = getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k + "]/a[" + x + "]/p[@class=' price']/span[@class='strike-price']")).getText().trim();
                                Assert.assertTrue(strikePrice.isEmpty(),"Strike price present at : "+ k + " : " + x);
                            }
                        }
                        //Test Sale-Price (sale price should be present and not blank, strike price should be present and not blank, Price should not present)
                        else if(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x + "]/p[@class='sales-price price']")))
                        {
                            Reporter.log("Space for Product sale-price present at: " + k + " : " + x);
                            String salePrice = getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x + "]/p[@class='sales-price price']")).getText();
                            String strikePrice = getDriver().findElement(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x + "]/p[@class='sales-price price']/span[@class='strike-price']")).getText();
                            salePrice = salePrice.replace(strikePrice, " ").trim();
                            Assert.assertFalse(salePrice.isEmpty(),"Sale price is blank at : "+ k + " : " + x);
                            Reporter.log("Sale price present at : "+ k + " : " + x);
                            Assert.assertFalse(strikePrice.isEmpty(),"Strike price is blank at : "+ k + " : " + x);
                            Reporter.log("Strike price present at : "+ k + " : " + x);
                            Assert.assertFalse(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["+ k + "]/a[" + x + "]/p[@class=' price']")),"Price present at :"+ k + " : " + x);
                        }

                        //Test buy now button
                        Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'cols sales-item-row')]["
                                + k + "]/a[" + x + "]/span[@class='cta-button theme-button']")),"Buy now button not present at "+k + " : " + x);
                        Reporter.log("Product button at: " + k + " : " + x);

                        try {

                            Assert.assertTrue(isElementDisplayed(By
                                    .xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x
                                            + "]/ul[@class='bulleted-list']/li")),"Product benifits not present at");
                            Reporter.log("Product benefits at: " + k + " : " + x);
                        } catch (AssertionError e) {
                            Reporter.log("PRODUCT BENEFIT MISSING AT: " + k + " : " + x);
                        }
                        try {
                            Assert.assertTrue(isElementDisplayed(By
                                    .xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x
                                            + "]/p[@class='color-swatches']")),"Colour swatches not present");

                            Reporter.log("Product color swatch at: " + k + " : " + x);


                        } catch (AssertionError e) {
                            Reporter.log("PRODUCT COLOR SWATCH MISSING AT: " + k + " : " + x);
                        }
                        /*          boolean isDuplSwatch = obj.isDuplicateSwatches(getDriver().findElements(By.xpath("//div[starts-with(@class,'cols sales-item-row')][" + k + "]/a[" + x
                                + "]/p[@class='color-swatches']/img[@class='loaded']")));
                        Assert.assertFalse(isDuplSwatch,"Duplicate swatches found at : "+ k + " : " + x);
                        Reporter.log("No duplicate swatches found");*/
                        Reporter.log("----------------------------------------");
                    }
                    catch(Throwable e)
                    {
                        flag = false;
                        Reporter.log("*******FAILURE OCCURED*********");
                        Reporter.log(e.getMessage());
                    }
                }

            }
            if(flag)
                return true;
            else
                throw (new Exception("Some failure(s) has occured please see log"));
        } catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
public void populateHashMap()
{
    getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    obj.populateHashMap();
    getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
public boolean isDupSwatch()
{
    if(obj.isDuplicateSwatches())
        return true;
    else
        return false;
}
    public boolean isSocialBarPresent() throws Exception {
        try {
            //New Social bar
           
            Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li")),"Share title not present");
            Reporter.log("Share title present");
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li[@class='sb-fb']")),"Facebook bar not present");
            Reporter.log("Facebook bar present");
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li[@class='sb-twitter']")),"Twitter bar not present");
            Reporter.log("Twitter bar present");
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li[@class='sb-googleplusone']")),"g+1 bar not present");
            Reporter.log("g+1 bar present");
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li[@class='sb-pinterest']")),"Pinterest bar not present");
            Reporter.log("Pinterest bar present");
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li[@class='sb-total']")),"Total not present");
            Reporter.log("Total present");
            
            //Old social bar
         /*  Assert.assertTrue(isElementDisplayed(By.xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li/h2")),"Share title not present");
            Reporter.log("Share title present");
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li[@class='sb-fb']")),"Facebook bar not present");
            Reporter.log("Facebook bar present");
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li[@class='sb-twtr']")),"Twitter bar not present");
            Reporter.log("Twitter bar present");
            Assert.assertTrue(isElementDisplayed(By
                    .xpath("//div[starts-with(@class,'row social-bar-row')]/ul/li[@class='sb-g-plusone']")),"g+1 bar not present");
            Reporter.log("g+1 bar present");*/

            return true;
        } catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }

    }
    public boolean isSmallBannerPresent() throws Exception {
        try {
            List<WebElement> smallBanners = getDriver().findElements(By.cssSelector("a.small-banner"));
            Assert.assertTrue(smallBanners.size() > 0,"No Small Banner found");
            Reporter.log("Small Banners found : " + smallBanners.size());
            for(WebElement banner : smallBanners)
            {
                Assert.assertTrue(banner.findElement(By.cssSelector("img.box-shadow.loaded")).isDisplayed(),"Small banner image not present");              
            }
            Reporter.log("Small banners tested and are proper");
            return true;
        } 
        catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            Reporter.log(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }

    public boolean isNewFooterPresent() throws Exception {
        try {
            scrollPage();
            waitForElementPresent(By.cssSelector("div.full-wrapper.store-footer.clearfix.loaded"));
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.full-wrapper.store-footer.clearfix.loaded")),
                    " Space for new footer not present");
            Reporter.log("Space for new footer present");
            Assert.assertFalse(getText(By.cssSelector("div.row.cols > div > h2")).isEmpty(), "Heading 1 not present");
            Reporter.log("Heading 1 present");
            int count = getElementCount(By.cssSelector("div.row.cols > div > h2 + ul > li"));
            for (int i = 1; i <= count; i++) {
                String text = getText(By.xpath("//div[@class='row cols']/div/ul/li[" + i + "]"));
                Assert.assertFalse(text.isEmpty(), "List is blank at item " + i);
            }
            Reporter.log("Text are present in list");

            Assert.assertFalse(getText(By.cssSelector("div.row.cols > div + div > h2")).isEmpty(),
                    "Heading 2 not present");
            Reporter.log("Heading 2 present");
            Assert.assertFalse(getText(By.cssSelector("div.row.cols > div + div > h2 + p")).isEmpty(),
                    "Description not present");
            Reporter.log("Description present");
            int count1 = getElementCount(By.cssSelector("div.row.cols > div +div > h2 + p+  ul > li"));
            for (int i = 1; i <= count1; i++) {
                String text = getText(By.xpath("//div[@class='row cols']/div/ul/li[" + i + "]/a"));
                Assert.assertFalse(text.isEmpty(), "List is blank or link not present at item " + i);
            }
            Reporter.log("Text and links are present in list");

            Assert.assertFalse(getText(By.cssSelector("div.row.cols > div + div + div > h2")).isEmpty(),
                    "Heading 1 not present");
            Reporter.log("Heading 3 present");
            Assert.assertFalse(getText(By.cssSelector("div.row.cols > div + div + div > h2 + p")).isEmpty(),
                    "Heading 1 not present");
            Reporter.log("Description present");
            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.row.cols > div + div + div > h2 + p + img")),
                    "Visa image not present");
            Reporter.log("Visa image present");
            Assert.assertTrue(
                    isElementDisplayed(By.cssSelector("div.row.cols > div + div + div > h2 + p + img + img")),
                    "Verified image not present");
            Reporter.log("Verified image present");
            Assert.assertTrue(
                    isElementDisplayed(By.cssSelector("div.row.cols > div + div + div > h2 + p + img + img + p >a")),
                    "Link for list of country not present");
            Reporter.log("Link for list of country present");

            Assert.assertTrue(isElementDisplayed(By.cssSelector("div.divider.blue-purple")), "Blue divider not present");
            Reporter.log("Blue divider present");
            return true;
        } catch (Throwable ex) {
            Reporter.log(ex.getMessage());
            return false;
        }
    }
    public boolean searchPHPwithButton(By loc) throws Exception
    {
        //scrollPage();

        int rows = getElementCount(By.xpath("//div[contains(@class,'cols sales-item-row')]"));
        for(int i=1;i<=rows;i++)
        {
            int prodInEachRow = getElementCount(By.xpath("//div[contains(@class,'cols sales-item-row')]["+i+"]/a"));
            for(int j=1;j<=prodInEachRow;j++)
            {
                String product = getText(By.xpath("//div[contains(@class,'cols sales-item-row')]["+i+"]/a["+j+"]/h3"));
                click(By.xpath("//div[contains(@class,'cols sales-item-row')]["+i+"]/a["+j+"]"));
                if(isElementDisplayed(loc))
                {  
                    Reporter.log("Button found in " + product);
                    return true;
                }
                else
                    getDriver().navigate().back();
            }
        }
        Reporter.log("Button "+loc+" not found");
        return false;
  
    }
    public void checkStepsAndCheckoutIcon()
    {
        List<WebElement> elements = null;
        elements = getDriver().findElement(By.className("step-row")).findElements(By.cssSelector("div.step-num"));
        Assert.assertEquals(elements.size(), 4, "4 step numbers not present");
        Reporter.log("4 step numbers present");
        for(WebElement ele : elements)
        {
            Assert.assertFalse(ele.getText().isEmpty(),"Empty Step number found");
        }
        
        elements = getDriver().findElement(By.className("step-row")).findElements(By.cssSelector("div.step-name"));
        Assert.assertEquals(elements.size(), 4, "4 step names not present");
        Reporter.log("4 step names present");
        for(WebElement ele : elements)
        {
            Assert.assertFalse(ele.getText().isEmpty(),"Empty Step name found");
        }
        
        elements = getDriver().findElement(By.className("step-row")).findElements(By.cssSelector("div.step-num.current"));
        Assert.assertEquals(elements.size(), 1, "Current step number not present");
        Reporter.log("Current step number present");
        
        elements = getDriver().findElement(By.className("step-row")).findElements(By.cssSelector("div.step-name.current"));
        Assert.assertEquals(elements.size(), 1, "Current step name not present");
        Reporter.log("Current step name present");
    }
    public String getLowerTrimed(String text)
    {
        return text.toLowerCase().trim();
    }
    /**
     * @param mode
     *  mode = anonymous for Anonymous user
     * mode = loggedin for Logged in user
     */
    public void populateHashsWithAddress(String mode)
    {
        try
        {
            deliveryHash.clear();
            billingHash.clear();
            if(mode.equals("anonymous"))
            {
                deliveryHash.put("fname", "Fname");
                deliveryHash.put("lname", "Lname");
                deliveryHash.put("street1", "Street1");
                deliveryHash.put("street2", "Street2");
                deliveryHash.put("zipcode", "400708");
                deliveryHash.put("city", "Mumbai");
                deliveryHash.put("email", "gbccgsemc@live.com");
                deliveryHash.put("number", "123456789");

                billingHash.put("fname", "FnameB");
                billingHash.put("lname", "LnameB");
                billingHash.put("street1", "Street1B");
                billingHash.put("street2", "Street2B");
                billingHash.put("zipcode", "400707");
                billingHash.put("city", "Navi Mumbai");
                billingHash.put("number", "123123123");
            }
            if(mode.equals("loggedin"))
            {
                deliveryHash.put("fname", getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='firstName']/preceding-sibling::span")).getText());
                deliveryHash.put("lname", getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='lastName']/preceding-sibling::span")).getText());
                deliveryHash.put("street1", getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='street1']/preceding-sibling::span")).getText());
                deliveryHash.put("street2", getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='street2']/preceding-sibling::span")).getText());
                deliveryHash.put("zipcode", getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='zipOrPostalCode']/preceding-sibling::span")).getText());
                deliveryHash.put("city", getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='city']/preceding-sibling::span")).getText());
                deliveryHash.put("email", getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='email']/preceding-sibling::span")).getText());
                deliveryHash.put("number", getDriver().findElement(By.id("delivery-address")).findElement(By.xpath(".//input[@name='phoneNumber']/preceding-sibling::span")).getText());

                billingHash.put("fname", getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='firstName']/preceding-sibling::span")).getText());
                billingHash.put("lname", getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='lastName']/preceding-sibling::span")).getText());
                billingHash.put("street1", getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='street1']/preceding-sibling::span")).getText());
                billingHash.put("street2", getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='street2']/preceding-sibling::span")).getText());
                billingHash.put("zipcode", getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='zipOrPostalCode']/preceding-sibling::span")).getText());
                billingHash.put("city", getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='city']/preceding-sibling::span")).getText());
                billingHash.put("number", getDriver().findElement(By.id("billing-address")).findElement(By.xpath(".//input[@name='phoneNumber']/preceding-sibling::span")).getText());
            }
        }
        catch(Exception ex)
        {
            Reporter.log("Error in populating hash table for address");
            Reporter.log(ex.getMessage());
        }
    }
    
    public boolean isDiscountMessagesProper()
    {
        try
        {
            List<WebElement> discountParas = getDriver().findElements(By.cssSelector("div.promotions-info > p.promotion-info"));
            Reporter.log("Total promotional discounts are : "+discountParas.size());
            for(WebElement para : discountParas)
            {
                List<WebElement> discountSpans = para.findElements(By.tagName("span"));
                Assert.assertEquals(discountSpans.size(), 2,"Error in display of discounts");
                for(WebElement span : discountSpans)
                {
                    Assert.assertFalse(span.getText().isEmpty(),"Empty discount found");
                }
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
    
    public boolean checkCheckoutURL()
    {
        try
        {
            String url = getDriver().getCurrentUrl();
            Assert.assertFalse(url.contains("storefront"));
            Assert.assertTrue(url.contains("checkout"));
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
