package com.sonyericsson.automatedtests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Element;

public interface ICwsTest {

    /**
     * this method is used for hitting a new Url
     */
    public void openUrl(Object... o) throws Exception;

    /**
     * this method checks if the element represented by the object is present
     */
    public boolean isElementPresent(By locator) throws Exception;

    /**
     * this method checks if the element represented by the object is displayed
     */
    public boolean isElementDisplayed(By locator) throws Exception;

    /**
     * this method checks presence of the link
     */
    public boolean isLinkPresent(Object... o) throws Exception;

    /**
     * this method assists in clicking the element
     */
    public void click(By loc) throws Exception;

    /**
     * returns a list containing element(s) specified by the object
     */
    public List<WebElement> findElement(Object... o) throws Exception;

    /**
     * this method returns the count of the elements having the respective objects
     */
    public int getElementCount(Object... o) throws Exception;

    /**
     * this method helps type text into a textbox
     */
    public void typeText(Object... o) throws Exception;

    /**
     * gets the text of the specified Object 
     */
    public String getText(Object... o) throws Exception;
    /**
     * gets the page source
     */
    public String getPageSource() throws Exception;
    /**
     * do submit
     */
    public void submit(Object... o) throws Exception;

    /**
     * checks the page-source for presence of certain characters 
     */
    public boolean isTextPresent(Object... o) throws Exception;

    /**
     * parses an xml element and returns the text in it
     */
    public Element parseXml() throws Exception;

    /**
     * this method is used to move the mouse from one location to another
     */
    public void mouseAction(WebElement element1, WebElement element2) throws Exception;

    /**
     * Switches to the popup window
     */
    public void switchWindow();

    /**
     * Close the popup window
     */
    public void closeWindow();

    /**
     * Delete cookies
     */
    public void deleteCookies();
    
    /**
     * go back to the previous page
     */
    public void goBack() throws Exception;

}