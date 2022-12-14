package com.mobile.web.pages;

import com.mobile.web.util.StringUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class CalculationPage extends AbstractPage {

    @FindBy(xpath = "//*[text()='Compute Engine']")
    private WebElement buttonComputeEngine;

    public CalculationPage activateTheComputeEngineSection() {
        closeShadowPopUpWindow();
        switchToIframe();
        buttonComputeEngine.click();
        //driver.switchTo().defaultContent();
        return this;
    }
    private static WebElement getShadowRoot(WebDriver driver,WebElement shadowHost) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
    }
    private void closeShadowPopUpWindow() {
        if (driver instanceof ChromeDriver || driver instanceof AndroidDriver) {
            WebElement shadowHost = driver.findElement(By.xpath("//*[contains(@bubble-text, 'Hi there')]"));
            WebElement shadowRoot = getShadowRoot(driver,shadowHost);
            shadowRoot.findElement(By.cssSelector(".close")).click();
        }

// FOR selenium 3
//        if (driver instanceof ChromeDriver || driver instanceof AndroidDriver) {
//            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                    .until(ExpectedConditions.numberOfElementsToBeMoreThan
//                            (By.xpath("//*[contains(@bubble-text, 'Hi there')]"), 0));
//            WebElement shadow_host = driver.findElement(By.xpath("//*[contains(@bubble-text, 'Hi there')]"));
//            Object shadowRoot = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadow_host);
//            String id = (String) ((Map<String, Object>) shadowRoot).get("shadow-6066-11e4-a52e-4f735466cecf");
//            RemoteWebElement shadowRootElement = new RemoteWebElement();
//            shadowRootElement.setParent((RemoteWebDriver) driver);
//            shadowRootElement.setId(id);
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            WebElement shadowContent = shadowRootElement.findElement(By.cssSelector(".close"));
//            shadowContent.click();
//        }
    }

    private void switchToIframe() {
        By generalIframe = By.xpath("//*[contains(@src, 'products/calculator')]");
        By localIframe = By.cssSelector("#myFrame");
        driver.switchTo().frame(driver.findElement(generalIframe))
                .switchTo().frame(driver.findElement(localIframe));
    }


    @FindBy(xpath = "//*[contains(text(), 'Number of instances')]//following::input[1]")
    private WebElement fieldNumberOfInstances;

    @FindBy(xpath = "(//*[contains(text(), 'Operating System')]//following::*[contains(@id, 'select')])[1]")
    private WebElement fieldOperatingSystem;

    @FindBy(xpath = "//*[contains(@class, 'menu-container')] //*[contains(text(), 'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)')]")
    private WebElement freeOperatingSystem;

    @FindBy(xpath = "//*[@placeholder = 'VM Class']")
    private WebElement fieldVMClass;

    @FindBy(xpath = "//*[contains(@class, 'menu-container')] //*[text()='Regular']")
    private WebElement regularVMClass;

    @FindBy(xpath = "//*[@placeholder='Series']")
    private WebElement fieldSeries;

    @FindBy(xpath = "//*[contains(@class, 'menu-container')] //*[contains(text(), 'N1')]")
    private WebElement neededSeries;

    @FindBy(xpath = "//*[@placeholder = 'Instance type']")
    private WebElement fieldInstanceType;

    @FindBy(xpath = "//*[contains(@class, 'menu-container')] //*[contains(text(), 'n1-standard-8 (vCPUs: 8, RAM: 30GB)')]")
    private WebElement neededInstanceType;

    @FindBy(xpath = "//*[contains(text(),'Add GPUs.')]")
    private WebElement buttonAddGPUs;
    private final By buttonAddGPUsLocator = By.xpath("//*[contains(text(),'Add GPUs.')]");


    @FindBy(xpath = "//*[@placeholder='GPU type']")
    private WebElement gPUTypeField;

    @FindBy(xpath = "//*[contains(text(), 'NVIDIA Tesla V100')]")
    private WebElement neededGPUType;


    @FindBy(xpath = "//*[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUsField;

    @FindBy(xpath = "//*[contains(@class, 'md-active')]//*[contains(text(), '1')]")
    private WebElement neededNumberOfGPU;

    @FindBy(xpath = "//*[contains(@aria-label, 'Local SSD:')] /*[contains(@id, 'select_value')]")
    private WebElement localSSDField;

    @FindBy(xpath = "//*[contains(text(), '2x375 GB')]")
    private WebElement neededLocalSSD;

    @FindBy(xpath = "//*[contains(@aria-label, 'Datacenter location')]/md-select-value")
    private WebElement datacenterLocationField;

    @FindBy(xpath = "//*[contains(@class, 'md-active')]//*[@value='europe-west3']")
    private WebElement neededDatacenterLocation;

    @FindBy(xpath = "//*[@placeholder='Committed usage']")
    private WebElement commitedUsageField;

    @FindBy(xpath = "//*[contains(@class, 'md-active')]//*[text()='1 Year']")
    private WebElement neededCommitedUsage;

    @FindBy(xpath = "//*[@name='ComputeEngineForm']//*[@aria-label='Add to Estimate']")
    private WebElement buttonAddToEstimate;

    @FindBy(xpath = "//*[contains(@class, 'google-symbols')][text()='email']") ////*[@aria-label='Email']
    private WebElement buttonEmailEstimate;

    @FindBy(css = "#code")
    private WebElement emailFieldAtTestIO;
    @FindBy(css = "#submit_code")
    private WebElement createEmail;

    @FindBy(xpath = "//*[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@aria-label='Send Email']")
    private WebElement buttonSendEmailWithTotalEstimate;

    @FindBy(css = "#messages")
    private WebElement messageFromGoogleCloud;
    @FindBy(xpath = "//*[contains(text(), 'Total Estimated Cost')]")
    private WebElement totalEstimatedCostLocator;



    public CalculationPage fillOutTheForm() {
        fieldNumberOfInstances.sendKeys("4");
        fieldOperatingSystem.click();
//        driver.hideKeyboard();
        freeOperatingSystem.click();
        fieldVMClass.click();
        regularVMClass.click();
        fieldSeries.click();
        waitClickableOfElement(neededSeries);
        neededSeries.click();
        fieldInstanceType.click();
        waitClickableOfElement(neededInstanceType);
        neededInstanceType.click();
        waitClickableOfElement(buttonAddGPUs);
        buttonAddGPUs.click();
        waitClickableOfElement(gPUTypeField);
        gPUTypeField.click();
        waitClickableOfElement(neededGPUType);
        neededGPUType.click();
        numberOfGPUsField.click();
        waitClickableOfElement(neededNumberOfGPU);
        neededNumberOfGPU.click();
        localSSDField.click();
        waitClickableOfElement(neededLocalSSD);
        neededLocalSSD.click();
        datacenterLocationField.click();
        waitClickableOfElement(neededDatacenterLocation);
        neededDatacenterLocation.click();
        commitedUsageField.click();
        waitClickableOfElement(neededCommitedUsage);
        neededCommitedUsage.click();
        buttonAddToEstimate.click();
        return this;
    }

    StringUtils stringUtils = new StringUtils();
    private final String newRandomEmail = stringUtils.getRandomString(8);

    public CalculationPage createRandomEmail() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.navigate().to("https://qa.team/");
        emailFieldAtTestIO.sendKeys(newRandomEmail);
        createEmail.click();
        driver.switchTo().window(tabs.get(0));
        return this;
    }

    private static String totalEstimatedCostOnTheCalculatorPage;

    public String getTotalEstimatedCostOnTheCalculatorPage() {
        return totalEstimatedCostOnTheCalculatorPage;
    }

    public CalculationPage sentTotalPriceOnThisRandomEmail() {
        switchToIframe();
        waitClickableOfElement(buttonEmailEstimate);
        buttonEmailEstimate.click();
        driver.getWindowHandles();
        waitClickableOfElement(emailField);
        emailField.sendKeys(newRandomEmail + "@qa.team");
        waitClickableOfElement(buttonSendEmailWithTotalEstimate);
        buttonSendEmailWithTotalEstimate.click();
        waitClickableOfElement(totalEstimatedCostLocator);
        totalEstimatedCostOnTheCalculatorPage = totalEstimatedCostLocator
                .getText().replace("per 1", "").replaceAll("[^.,0-9]", "");
        return this;
    }

    public String takeInformationAboutCostFromEmail() {
        String totalEstimatedCostOnTheTempEmailPage;
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        int countOfRefreshPage = 0;
        while (driver.findElements(By.cssSelector(".message.closed")).size() == 0 && countOfRefreshPage < 30) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driver.navigate().refresh();
            countOfRefreshPage++;
        }
        messageFromGoogleCloud.click();
        totalEstimatedCostOnTheTempEmailPage = driver.findElement(By.cssSelector(".row")).getText();
        return totalEstimatedCostOnTheTempEmailPage;
    }

    public CalculationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected CalculationPage openPage() {
        return null;
    }


}
