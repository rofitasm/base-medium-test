package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileSettingsPage {
    private AppiumDriver<MobileElement> appiumDriver;

    private By EDIT_BIO_BUTTON = MobileBy.xpath("(//android.widget.Button)[2]");
    private By EDIT_BIO_FIELD = MobileBy.xpath("//android.widget.EditText");
    private By SAVE_BIO_BUTTON = MobileBy.xpath("(//android.widget.Button)[2]");
    private By USER_PUBLIC_PROFILE_LINK = MobileBy.xpath("(//android.view.View[@content-desc=\"Profile\"])[2]/android.widget.TextView");

    private By MOBILE_WEB_VIEW = MobileBy.xpath("//android.webkit.WebView");

    public MobileSettingsPage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void editBio(String bioText) {
        SleepHelper.sleepForSeconds(2);

        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement mobileWebView = wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_WEB_VIEW));
        mobileWebView.click();

        WebElement editBioButton = wait.until(ExpectedConditions.elementToBeClickable(EDIT_BIO_BUTTON));
        editBioButton.click();

        WebElement editBioField = wait.until(ExpectedConditions.visibilityOfElementLocated(EDIT_BIO_FIELD));
        editBioField.sendKeys(bioText);

        ((AndroidDriver) appiumDriver).pressKey(new KeyEvent(AndroidKey.BACK));

        SleepHelper.sleepForSeconds(2);

        WebElement saveBioButton = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BIO_BUTTON));
        saveBioButton.click();

        SleepHelper.sleepForSeconds(2);

        WebElement userPublicProfileLink = wait.until(ExpectedConditions.elementToBeClickable(USER_PUBLIC_PROFILE_LINK));
        userPublicProfileLink.click();
    }
}
