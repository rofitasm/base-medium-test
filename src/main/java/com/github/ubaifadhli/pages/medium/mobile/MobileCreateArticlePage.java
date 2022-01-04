package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileCreateArticlePage {
    private AppiumDriver appiumDriver;

    private By ARTICLE_TEXT_AREA = MobileBy.id("com.medium.reader:id/common_edit_post_paragraph_text");
    private By PUBLISH_ARTICLE_BUTTON = MobileBy.id("com.medium.reader:id/publish_button");
    private By CONFIRM_PUBLISH_ARTICLE_BUTTON = MobileBy.id("com.medium.reader:id/edit_post_add_tags_dialog_publish");


    public MobileCreateArticlePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void fillAndPublishArticle(String articleTitle) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement articleTextArea = wait.until(ExpectedConditions.elementToBeClickable(ARTICLE_TEXT_AREA));

        articleTextArea.click();
        articleTextArea.sendKeys(articleTitle);

        WebElement publishArticleButton = wait.until(ExpectedConditions.elementToBeClickable(PUBLISH_ARTICLE_BUTTON));
        publishArticleButton.click();

        WebElement confirmPublishArticleButton = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_PUBLISH_ARTICLE_BUTTON));
        confirmPublishArticleButton.click();
        SleepHelper.sleepForSeconds(2);
    }
}
