package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.pages.medium.MobilePageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileHomePage extends MobilePageObject {

    private By SIGN_IN_BUTTON = MobileBy.id("com.medium.reader:id/sign_in_prompt");
    private By SIGN_IN_WITH_TWITTER_BUTTON = MobileBy.id("com.medium.reader:id/sign_in_twitter_button");

    public MobileHomePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void goToTwitterLoginPage() {
        WebElement signInButton = getElementAfterClickable(SIGN_IN_BUTTON);
        signInButton.click();

        WebElement signInWithTwitterButton = getElementAfterClickable(SIGN_IN_WITH_TWITTER_BUTTON);
        signInWithTwitterButton.click();
    }
}
