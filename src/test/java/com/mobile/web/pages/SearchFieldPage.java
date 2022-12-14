package com.mobile.web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchFieldPage extends AbstractPage {
    private final String BASE_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//*[@aria-label='Search']")
    private WebElement inputSearch;

    public SearchFieldPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public SearchResultsPage searchInformation(String wordForSearch) {
        inputSearch.sendKeys(wordForSearch);
        inputSearch.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }


    @Override
    protected AbstractPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
