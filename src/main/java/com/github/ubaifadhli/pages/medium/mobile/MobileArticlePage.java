package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.pages.medium.MobilePageObject;
import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MobileArticlePage extends MobilePageObject {
    private By COMMENT_BUTTON = MobileBy.AccessibilityId("Read responses");
    private By COMMENT_FIELD = MobileBy.id("com.medium.reader:id/response_editor");
    private By PUBLISH_COMMENT_BUTTON = MobileBy.AccessibilityId("Publish response");
    private By ARTICLE_COMMENTS = MobileBy.id("com.medium.reader:id/response_text");

    public MobileArticlePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void createComment(String commentText) {
        WebElement commentButton = getElementAfterClickable(COMMENT_BUTTON);
        commentButton.click();

        WebElement commentField = getElementAfterClickable(COMMENT_FIELD);
        commentField.click();
        commentField.sendKeys(commentText);

        WebElement publishCommentButton = getElementAfterClickable(PUBLISH_COMMENT_BUTTON);
        publishCommentButton.click();

        SleepHelper.sleepForSeconds(2);
    }

    public String getFirstCommentText() {
        List<WebElement> articleComments = getElementsAfterVisible(ARTICLE_COMMENTS);
        return articleComments.get(0).getText();
    }

}
