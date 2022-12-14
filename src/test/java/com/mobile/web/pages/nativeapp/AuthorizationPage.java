package com.mobile.web.pages.nativeapp;
import com.mobile.web.driver.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage extends BasePage{
    @AndroidFindBy(accessibility= "login-btn-login")
    private WebElement loginButton;
    @AndroidFindBy(accessibility = "onBoarding-btn-next")
    private WebElement nextButton;
    @AndroidFindBy(accessibility= "onBoarding-btn-start")
    private WebElement startButton;
    @AndroidFindBy(accessibility= "Use your personal or corporate email")
    private WebElement emailField;
    @AndroidFindBy(accessibility= "Continue")
    private WebElement ContinueButton;
    @AndroidFindBy(accessibility= "At this step we do not record your data, until you provide your consent")
    private WebElement warningMessageThatSuchEmailWasNotFound;
    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));

    public AuthorizationPage navigateToAuthorizationWindow(){
        skipAllOnBoardingWindows();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return this;
    }
    public AuthorizationPage fillEmailFieldWIthInvalidCredentials(String unregisteredEmail){
        wait.until(ExpectedConditions.visibilityOf(emailField));
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        Actions a = new Actions(DriverManager.getDriver());
        a.sendKeys(unregisteredEmail);
        a.perform();
        DriverManager.getDriver().hideKeyboard();
        ContinueButton.click();
        return this;
    }
    public boolean isThereWarningMessage(){
        wait.until(ExpectedConditions.visibilityOf(warningMessageThatSuchEmailWasNotFound));
        return warningMessageThatSuchEmailWasNotFound.isDisplayed();
    }
    private void skipAllOnBoardingWindows(){
        for (int i = 0; i < 5; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            nextButton.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(startButton));
        startButton.click();
    }


}
