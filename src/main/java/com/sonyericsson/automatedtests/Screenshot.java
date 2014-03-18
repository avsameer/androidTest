package com.sonyericsson.automatedtests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class Screenshot extends TestListenerAdapter
{
    @Override
    public void onTestFailure(ITestResult result)
    {
        try 
        {
            String fileName = "";
            String methodName = result.getName().trim();
            String className="";
            className = result.getMethod().toString().substring(0, result.getMethod().toString().indexOf(".", result.getMethod().toString().indexOf("Using"))).trim();
         /*   if(result.getMethod().toString().startsWith("Using"))
            {
                className = result.getMethod().toString().substring(0, result.getMethod().toString().indexOf(".")).trim();
            }
            else
            {
                className = result.getMethod().toString().substring(0, result.getMethod().toString().lastIndexOf(".")).trim();
            }*/
            Object[] paras =  result.getParameters();
            String parameters = "";
            for(Object o : paras)
            {
                parameters = parameters + "." + o.toString();
            }
            fileName = className + "." + methodName + "." + parameters.trim();
            File screenShot = ((TakesScreenshot)CwsAbstractBaseTest.driver).getScreenshotAs(OutputType.FILE);
            
            FileUtils.copyFile(screenShot, new File("c:\\ECOM_FAILURES\\"+CwsAbstractBaseTest.folder1+"\\"+CwsAbstractBaseTest.folder2+"\\"+
                    "Test_Failures"+"\\"+CwsAbstractBaseTest.locale.toUpperCase()+"\\" +fileName+".png"));
        } 
        catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

}
