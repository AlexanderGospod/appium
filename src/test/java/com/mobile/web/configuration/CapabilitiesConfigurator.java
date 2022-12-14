package com.mobile.web.configuration;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.PLATFORM_NAME;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;

public class CapabilitiesConfigurator {

    private CapabilitiesConfigurator() {

    }

    public static DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability(UDID, ConfigurationReader.get().udid());
        capabilities.setCapability(AVD, ConfigurationReader.get().localDeviceName());

        setCapabilitiesForWebApp(capabilities);
//        setCapabilitiesForNativeApp(capabilities);
        return capabilities;
    }

    public static DesiredCapabilities getCloudCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(UDID, ConfigurationReader.get().cloudDeviceSerial());
        setCommonCapabilities(capabilities);
        return capabilities;
    }

    public static void setCommonCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability(DEVICE_NAME, ConfigurationReader.get().localDeviceName());
        capabilities.setCapability(PLATFORM_NAME, ConfigurationReader.get().platformName());
        capabilities.setCapability(PLATFORM_VERSION, ConfigurationReader.get().platformVersion());
    }
    private static void setCapabilitiesForWebApp(DesiredCapabilities capabilities){
//        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("browserName", "Chrome");

    }
    private static void setCapabilitiesForNativeApp(DesiredCapabilities capabilities){
        capabilities.setCapability(APP_PACKAGE, ConfigurationReader.get().appPackage());
        capabilities.setCapability(APP_ACTIVITY, ConfigurationReader.get().appActivity());
        capabilities.setCapability(APP, new File(ConfigurationReader.get().appPath()).getAbsolutePath());
    }
}
