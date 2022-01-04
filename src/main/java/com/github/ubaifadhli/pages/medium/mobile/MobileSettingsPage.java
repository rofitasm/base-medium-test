package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileSettingsPage {
    private AppiumDriver<MobileElement> appiumDriver;

    private By EDIT_BIO_BUTTON = MobileBy.xpath("(//android.widget.Button)[2]");
    private By EDIT_BIO_FIELD = MobileBy.id("editor_6");
    private By SAVE_BIO_BUTTON = MobileBy.xpath("(//android.widget.Button)[2]");
    private By USER_PUBLIC_PROFILE_LINK = MobileBy.xpath("(//android.view.View[@content-desc=\"Profile\"])[2]/android.widget.TextView");

    public MobileSettingsPage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void editBio(String bioText) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement editBioButton = wait.until(ExpectedConditions.elementToBeClickable(EDIT_BIO_BUTTON));
        editBioButton.click();

        WebElement editBioField = wait.until(ExpectedConditions.elementToBeClickable(EDIT_BIO_FIELD));
        editBioField.clear();
        editBioField.sendKeys(bioText);

        WebElement saveBioButton = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BIO_BUTTON));
        saveBioButton.click();

        SleepHelper.sleepForSeconds(2);

        WebElement userPublicProfileLink = wait.until(ExpectedConditions.elementToBeClickable(USER_PUBLIC_PROFILE_LINK));
        userPublicProfileLink.click();
    }
}
