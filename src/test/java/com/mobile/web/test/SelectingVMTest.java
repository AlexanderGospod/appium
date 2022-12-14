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
                .activateTheComputeEngineSection()
                .fillOutTheForm()
                .createRandomEmail()
                .sentTotalPriceOnThisRandomEmail()
                .takeInformationAboutCostFromEmail();
        Assert.assertTrue(totalPriceInTheEmail.contains(new CalculationPage(driver).getTotalEstimatedCostOnTheCalculatorPage())
                , "The received message does not contain the price for the month the same as on the calculator page");
    }
}
