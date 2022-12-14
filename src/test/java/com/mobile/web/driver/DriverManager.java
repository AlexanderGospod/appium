package com.mobile.web.driver;

import com.mobile.web.configuration.AddressConfigurator;
import com.mobile.web.configuration.CapabilitiesConfigurator;
import com.mobile.web.configuration.ConfigurationReader;
import com.mobile.web.configuration.EnvironmentTypes;
import com.mobile.web.util.RequestUtils;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

public class DriverManager {
    private static final Logger LOG = LogManager.getRootLogger();
    private static final EnvironmentTypes ENVIRONMENT_TYPE = EnvironmentTypes.valueOf(ConfigurationReader.get().env().toUpperCase());
    private static AndroidDriver driver;


    private DriverManager() {

    }

    public static AndroidDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static AndroidDriver  createDriver() {
        switch (ENVIRONMENT_TYPE) {
            case LOCAL:
                driver = new AndroidDriver (AddressConfigurator.getAppiumDriverLocalService(ConfigurationReader.get().appiumPort()),
                        CapabilitiesConfigurator.getLocalCapabilities());
                break;
            case MOBILE_CLOUD:
                preparedCloudDevice(ConfigurationReader.get().cloudApiUrl(),
                        ConfigurationReader.get().cloudDeviceSerial(),
                        RequestUtils.getKey(),
                        ConfigurationReader.get().appPath());
                driver = new AndroidDriver(AddressConfigurator.getUrl(ConfigurationReader.get().cloudUrl()),
                        CapabilitiesConfigurator.getCloudCapabilities());
                break;
            default:
                throw new IllegalArgumentException(format("Unexpected environment value %s", ENVIRONMENT_TYPE));
        }
        LOG.info("Driver is created");
        LOG.info("Environment is {}", ENVIRONMENT_TYPE);
        return driver;
    }
    private static void preparedCloudDevice(String url, String serial, String key, String appPath){
        RequestUtils.takeDevice(url, serial, key);
        RequestUtils.installApp(url, serial, key, appPath);
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(driverInstance -> {
            driverInstance.quit();
            driver = null;
            LOG.info("driver was closed");
        });
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s DEVICE_UDID emu kill", ConfigurationReader.get().udid()));
            LOG.info("AVD is closed");
        } catch (IOException e) {
            LOG.info("AVD was not closed, message: {}", e.getMessage());
        }
    }
}
