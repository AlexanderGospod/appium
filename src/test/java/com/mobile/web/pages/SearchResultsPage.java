package com.mobile.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage extends AbstractPage {
    @FindBy(xpath = "//*[contains(@href, 'products/calculator')]/b[text()='Google Cloud Pricing Calculator']")
    private WebElement buttonDesiredMaterial;

    public CalculationPage openThePageWithTheResults() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions.elementToBeClickable(buttonDesiredMaterial));
        buttonDesiredMaterial.click();
        return new CalculationPage(driver);
    }

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected SearchResultsPage openPage() {
        return null;
    }

}
