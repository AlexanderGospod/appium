package com.mobile.web.test;


import com.mobile.web.driver.DriverManager;
import com.mobile.web.util.TestListener;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonConditions {
    protected static AndroidDriver driver;


    @BeforeClass(description = "Create connection with driver")
    public void createSession() {
        driver = DriverManager.getDriver();
    }


    @AfterClass(description = "Close connection with driver")
    public void closeSession() {
        DriverManager.closeDriver();
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}
