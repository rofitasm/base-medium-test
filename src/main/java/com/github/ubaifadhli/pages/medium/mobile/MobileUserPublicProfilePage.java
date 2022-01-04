package com.github.ubaifadhli.pages.medium.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileUserPublicProfilePage {
    private AppiumDriver<MobileElement> appiumDriver;

    private By USER_BIO = MobileBy.id("com.medium.reader:id/tvBio");
    private By ABOUT_SECTION = MobileBy.AccessibilityId("About");

    public MobileUserPublicProfilePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public String getUserBio() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement aboutSection = wait.until(ExpectedConditions.elementToBeClickable(ABOUT_SECTION));
        aboutSection.click();

        WebElement userBio = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_BIO));
        return userBio.getText();
    }
}
