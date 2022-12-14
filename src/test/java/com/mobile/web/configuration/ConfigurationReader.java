package com.mobile.web.configuration;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationReader {
    private static final Logger LOG = LogManager.getRootLogger();
    private static final Properties properties = new Properties();
    private static ConfigurationReader instance;

    private ConfigurationReader() {

    }

    public static ConfigurationReader get() {
        if (instance == null) {
            instance = new ConfigurationReader();
            try {
                properties.load(new FileInputStream("src/test/resources/test.properties"));
            } catch (IOException e) {
                LOG.error("Properties were not loaded");
            }
        }
        return instance;
    }

    public String env() {
        return properties.getProperty("env.type");
    }

    public String appPath() {
        return properties.getProperty("app.path");
    }

    public String appPackage() {
        return properties.getProperty("app.package");
    }

    public String appActivity() {
        return properties.getProperty("app.activity");
    }

    public String platformName() {
        return properties.getProperty("platform.name");
    }

    public String platformVersion() {
        return properties.getProperty("platform.version");
    }

    public String localDeviceName() {
        return properties.getProperty("local.device.name");
    }

    public String udid() {
        return properties.getProperty("udid");
    }

    public String appiumAddress() {
        return properties.getProperty("appium.address");
    }

    public int appiumPort() {
        return NumberUtils.toInt(properties.getProperty("appium.port"));
    }

    public String cloudKey() {
        return properties.getProperty("cloud.key");
    }

    public String cloudUrl() {
        return properties.getProperty("cloud.url");
    }

    public String cloudApiUrl() {
        return properties.getProperty("cloud.api.url");
    }

    public String cloudDeviceSerial() {
        return properties.getProperty("cloud.device.serial");
    }
    public String cloudProjectName() {
        return properties.getProperty("cloud.project.name");
    }

    public String cloudAppiumHub() {
        return properties.getProperty("cloud.appium.hub");
    }



}
