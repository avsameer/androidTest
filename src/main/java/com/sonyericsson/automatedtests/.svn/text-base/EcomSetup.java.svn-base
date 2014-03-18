package com.sonyericsson.automatedtests;

import java.io.InputStream;
import java.util.Properties;

public class EcomSetup 
{
    public String host;
    public String username;
    public String password;
    public EcomSetup() 
    {
        try
        {
            Properties prop =new Properties();
            InputStream is = getClass().getResourceAsStream("/config.properties");
            prop.load(is);
            host = prop.getProperty("host");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        }
        catch(Exception ex)
        {}

    }
}


