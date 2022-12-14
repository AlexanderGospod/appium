package com.mobile.web.util;

import com.mobile.web.configuration.ConfigurationReader;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class RequestUtils {
    private static final Logger LOG = LogManager.getRootLogger();
    private static final String AUTHORIZATION_NAME = "Authorization";
    private static final String AUTHORIZATION_VALUE = "Bearer %s";
    private static final     String STATUS_CODE_MESSAGE = "Status code is: {}";




    private RequestUtils() {
    }

    public static void main(String[] args) {
        System.out.println(getUrl());
        takeDevice(ConfigurationReader.get().cloudApiUrl(),
                ConfigurationReader.get().cloudDeviceSerial(),
                getKey());
    }


    public static void takeDevice(String url, String serial, String key) {
        LOG.info("Attempt to take a device with serial {}", serial);
        Response response = given()
                .header(AUTHORIZATION_NAME, format(AUTHORIZATION_VALUE, key))
                .header("Content-Type", "application/json")
                .when()
                .post(format("%s/device/%s", url, serial));
        LOG.info(STATUS_CODE_MESSAGE, response.getStatusCode());
    }

    public static void installApp(String url, String serial, String key, String appPath) {
        LOG.info("Attempt to install app on the {} device", serial);
        Response response = given()
                .header(AUTHORIZATION_NAME, format(AUTHORIZATION_VALUE, key))
                .param("fileId", new File(appPath))
                .when()
                .post(format("%s/storage/install/%s", url, serial));
        LOG.info(STATUS_CODE_MESSAGE, response.getStatusCode());
        response.getBody().prettyPrint();
    }
    public static String getKey(){
        String key = null;
        try {
            key = URLEncoder.encode(ConfigurationReader.get().cloudKey(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(key);
        return key;
    }
    public static String getUrl(){
        return format("https://%s:%s@%s/wd/hub", ConfigurationReader.get().cloudProjectName(),
                getKey(), ConfigurationReader.get().cloudAppiumHub());
    }

}
