package com.github.ubaifadhli.pages.medium.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileLoginPage {
    AppiumDriver<MobileElement> appiumDriver;

    private By TWITTER_USERNAME_FIELD = MobileBy.xpath("//android.widget.EditText[@resource-id='username_or_email']");
    private By TWITTER_PASSWORD_FIELD = MobileBy.xpath("//android.widget.EditText[@resource-id='password']");
    private By TWITTER_SIGN_IN_BUTTON = MobileBy.xpath("//android.widget.Button[@resource-id='allow']");

    private By MOBILE_TWITTER_WEB_VIEW = MobileBy.id("com.medium.reader:id/tw__web_view");
    private By MOBILE_HOME_TITLE = MobileBy.id("com.medium.reader:id/title");

    public MobileLoginPage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void fillTwitterLoginCredentials(String email, String password) {
        appiumDriver.findElement(MOBILE_TWITTER_WEB_VIEW).click();

        appiumDriver.findElement(TWITTER_USERNAME_FIELD).sendKeys(email);
        appiumDriver.findElement(TWITTER_PASSWORD_FIELD).sendKeys(password);

        appiumDriver.findElement(TWITTER_SIGN_IN_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_HOME_TITLE));
    }
}
