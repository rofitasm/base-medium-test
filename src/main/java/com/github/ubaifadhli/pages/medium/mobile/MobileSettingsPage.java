package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.pages.medium.MobilePageObject;
import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileSettingsPage extends MobilePageObject {
    private By EDIT_BIO_BUTTON = MobileBy.xpath("(//android.widget.Button)[2]");
    private By EDIT_BIO_FIELD = MobileBy.xpath("//android.widget.EditText[@resource-id='editor_6']");
    private By SAVE_BIO_BUTTON = MobileBy.xpath("(//android.widget.Button)[2]");
    private By USER_PUBLIC_PROFILE_LINK = MobileBy.xpath("(//android.view.View[@content-desc='Profile'])[2]/android.widget.TextView");

    private By MOBILE_WEB_VIEW = MobileBy.xpath("//android.webkit.WebView");

    public MobileSettingsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void editBio(String bioText) {
        SleepHelper.sleepForSeconds(2);

        WebElement mobileWebView = getElementAfterVisible(MOBILE_WEB_VIEW);
        mobileWebView.click();

        while (!isKeyboardShown()) {
            WebElement editBioButton = getElementAfterClickable(EDIT_BIO_BUTTON);
            editBioButton.click();

            SleepHelper.sleepForSeconds(1);
        }

        WebElement editBioField = getElementAfterVisible(EDIT_BIO_FIELD);
        editBioField.click();
        editBioField.sendKeys(bioText);

        pressBack();

        SleepHelper.sleepForSeconds(2);

        WebElement saveBioButton = getElementAfterClickable(SAVE_BIO_BUTTON);
        saveBioButton.click();

        SleepHelper.sleepForSeconds(2);

        WebElement userPublicProfileLink = getElementAfterClickable(USER_PUBLIC_PROFILE_LINK);
        userPublicProfileLink.click();
    }
}
