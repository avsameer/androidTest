package com.sonyericsson.automatedtests.quick;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/***
 * Class registers the Event listern class we created 
 * @author gshah
 *
 */
public class TestWithEvenRegistered {
 
 WebDriver driver = new FirefoxDriver();
 EventFiringWebDriver eventFiringWebDriver;
 @BeforeClass
 public void setup() {
//  eventFiringWebDriver = new EventFiringWebDriver(driver);
  //eventFiringWebDriver.register(new WebDriverEventListenerDemo());
  driver.get("http://google.com");
  
 }
 
 /**
  * "qq" is wrong element id and so NoElementFound Exception will 
  * be generated, which will call our overridden method of WebDriverEventListenerDemo
  * class and will take the screenshot. 
  */
 @Test
 public void test(){
     Assert.assertTrue(1==2,"fails");
     try
     {
 // eventFiringWebDriver.navigate().to("http://shop-dev-int.sonymobile.com/gb/phones/");
  Assert.assertTrue(1==2,"fails");
 // eventFiringWebDriver.findElement(By.name("qq6"));
     }
     catch(Throwable ex)
     {
         System.out.println("catch");
     }
  
 }
}
