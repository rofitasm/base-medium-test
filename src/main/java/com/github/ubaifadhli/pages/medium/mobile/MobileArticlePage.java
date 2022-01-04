package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileArticlePage {
    AppiumDriver<MobileElement> appiumDriver;

    private By COMMENT_BUTTON = MobileBy.AccessibilityId("Read responses");

    private By COMMENT_FIELD = MobileBy.id("com.medium.reader:id/response_editor");

    private By PUBLISH_COMMENT_BUTTON = MobileBy.AccessibilityId("Publish response");

    private By ARTICLE_COMMENTS = MobileBy.id("com.medium.reader:id/response_text");



    public MobileArticlePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void createComment(String commentText) {
        appiumDriver.findElement(COMMENT_BUTTON).click();

        WebElement commentField = appiumDriver.findElement(COMMENT_FIELD);

        commentField.click();
        commentField.sendKeys(commentText);

        appiumDriver.findElement(PUBLISH_COMMENT_BUTTON).click();

        SleepHelper.sleepForSeconds(2);
    }

    public String getFirstCommentText() {
        return appiumDriver.findElements(ARTICLE_COMMENTS).get(0).getText();
    }
}
