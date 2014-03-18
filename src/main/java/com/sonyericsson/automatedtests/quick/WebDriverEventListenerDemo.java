package com.sonyericsson.automatedtests.quick;

import java.io.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestResult;

/***
07.* Implements WebDriverEventListener and overrides onException method
08.* @author Gaurang
09.*
10.*/
public class WebDriverEventListenerDemo implements WebDriverEventListener {


    public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
    }

    public void afterClickOn(WebElement arg0, WebDriver arg1) {
    }

    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
    }

    public void afterNavigateBack(WebDriver arg0) {
    }

    public void afterNavigateForward(WebDriver arg0) {
    }

    public void afterNavigateTo(String arg0, WebDriver arg1) {
    }

    public void afterScript(String arg0, WebDriver arg1) {
    }

    public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
    }

    public void beforeClickOn(WebElement arg0, WebDriver arg1) {
    }

    public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
    }

    public void beforeNavigateBack(WebDriver arg0) {
    }

    public void beforeNavigateForward(WebDriver arg0) {
    }

    public void beforeNavigateTo(String arg0, WebDriver arg1) {
        
    }

    public void beforeScript(String arg0, WebDriver arg1) {
        
    }

    public void onException(Throwable exception, WebDriver driver) {
        
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File("c:\\tmp\\exception.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
