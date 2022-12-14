package com.mobile.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//*[@aria-label='Open search']")
    private WebElement buttonSearch;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public SearchFieldPage expandTheSearchField() {
        waitClickableOfElement(buttonSearch);
        buttonSearch.click();
        return new SearchFieldPage(driver);
    }
    @Override
    public MainPage openPage() {
        driver.get(BASE_URL);
        return this;
    }
}
