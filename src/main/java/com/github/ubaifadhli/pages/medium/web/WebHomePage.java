package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.pages.medium.WebPageObject;
import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebHomePage extends WebPageObject {
    private By SIGN_IN_BUTTON = By.xpath("//a[text()='Sign In']");
    private By SIGN_IN_WITH_TWITTER = By.xpath("//div[text()='Sign in with Twitter']");

    public WebHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage() {
        getWebDriver().manage().window().maximize();
        getWebDriver().get("https://www.medium.com");

        SleepHelper.sleepForSeconds(1);
    }

    public void goToTwitterLoginPage() {
        WebElement signInButton = getElementAfterClickable(SIGN_IN_BUTTON);
        signInButton.click();

        WebElement signInWithTwitterButton = getElementAfterClickable(SIGN_IN_WITH_TWITTER);
        signInWithTwitterButton.click();
    }
}
