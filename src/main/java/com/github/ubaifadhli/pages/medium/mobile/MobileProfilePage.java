package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.pages.medium.MobilePageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileProfilePage extends MobilePageObject {
    private By USER_ARTICLE_TITLE = MobileBy.xpath("//android.widget.TextView[@resource-id='com.medium.reader:id/common_item_paragraph_text']");

    public MobileProfilePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    public void clickUserArticleTitle() {
        WebElement userArticleTitle = getElementAfterClickable(USER_ARTICLE_TITLE);
        userArticleTitle.click();
    }

    public String getFirstUserArticleTitle() {
        WebElement userArticleTitle = getElementAfterVisible(USER_ARTICLE_TITLE);
        return userArticleTitle.getText();
    }
}
