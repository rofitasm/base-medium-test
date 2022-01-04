package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement commentButton = wait.until(ExpectedConditions.elementToBeClickable(COMMENT_BUTTON));
        commentButton.click();

        WebElement commentField = wait.until(ExpectedConditions.elementToBeClickable(COMMENT_FIELD));
        commentField.click();
        commentField.sendKeys(commentText);

        WebElement publishCommentButton = wait.until(ExpectedConditions.elementToBeClickable(PUBLISH_COMMENT_BUTTON));
        publishCommentButton.click();

        SleepHelper.sleepForSeconds(2);
    }

    public String getFirstCommentText() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        List<WebElement> articleComments = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ARTICLE_COMMENTS));
        return articleComments.get(0).getText();
    }
}
