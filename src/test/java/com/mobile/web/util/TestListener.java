package com.mobile.web.util;


import com.mobile.web.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger LOG = LogManager.getRootLogger();

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("{} started", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("{} passed", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            saveScreenshotOnFailure(DriverManager.getDriver());
            LOG.info("Test {} failed, the screenshot was taken", result.getName());
        } catch (Exception e) {
            LOG.error("Failed to save screenshot {}", e.getMessage());
        }
        saveLogs(result.getName() + result.getEndMillis());
    }

    @Attachment(value = "Screennshot", type = "image/png")
    public byte[] saveScreenshotOnFailure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Stacktrac", type = "text/plain")
    public static String saveLogs(String message) {
        return message;
    }

}
