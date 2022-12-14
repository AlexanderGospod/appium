package com.mobile.web.test.nativeapp;

import com.mobile.web.pages.nativeapp.AuthorizationPage;
import com.mobile.web.test.CommonConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthorizationPageTest extends CommonConditions {
    @Test
    public void authorizationWIthInvalidCredentials() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        boolean warningMessage = authorizationPage
                .navigateToAuthorizationWindow()
                .fillEmailFieldWIthInvalidCredentials("Lorem@gmail.com")
                .isThereWarningMessage();
        Assert.assertTrue(warningMessage);
    }
}
