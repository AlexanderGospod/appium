package com.mobile.web.test;

import com.mobile.web.pages.CalculationPage;
import com.mobile.web.pages.MainPage;
import com.mobile.web.util.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({TestListener.class})

public class SelectingVMTest extends CommonConditions {

    @Test
    public void totalPriceInTheEmail() {
        String totalPriceInTheEmail = new MainPage(driver)
                .openPage()
                .expandTheSearchField()
                .searchInformation("Google Cloud Platform Pricing Calculator")
                .openThePageWithTheResults()
                .fillOutTheForm()
                .createRandomEmail()
                .sentTotalPriceOnThisRandomEmail()
                .takeInformationAboutCostFromEmail();
        Assert.assertTrue(totalPriceInTheEmail.contains(new CalculationPage(driver).getTotalEstimatedCostOnTheCalculatorPage())
                , "The price sent by mail differs from the one calculated on the calculator page");
    }
}
