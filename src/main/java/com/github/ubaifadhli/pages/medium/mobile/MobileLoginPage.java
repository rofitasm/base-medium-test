package com.github.ubaifadhli.pages.medium.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);
        
        WebElement mobileTwitterWebView = wait.until(ExpectedConditions.elementToBeClickable(MOBILE_TWITTER_WEB_VIEW));
        mobileTwitterWebView.click();

        WebElement twitterUsernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(TWITTER_USERNAME_FIELD));
        twitterUsernameField.sendKeys(email);

        WebElement twitterPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(TWITTER_PASSWORD_FIELD));
        twitterPasswordField.sendKeys(password);

        WebElement twitterSignInButton = wait.until(ExpectedConditions.elementToBeClickable(TWITTER_SIGN_IN_BUTTON));
        twitterSignInButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_HOME_TITLE));
    }
}
