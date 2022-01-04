package com.github.ubaifadhli.pages.medium.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileCreateArticlePage {
    private AppiumDriver appiumDriver;

    private By ARTICLE_TEXT_AREA = MobileBy.id("com.medium.reader:id/common_edit_post_paragraph_text");
    private By PUBLISH_ARTICLE_BUTTON = MobileBy.id("com.medium.reader:id/publish_button");
    private By CONFIRM_PUBLISH_ARTICLE_BUTTON = MobileBy.id("com.medium.reader:id/edit_post_add_tags_dialog_publish");


    public MobileCreateArticlePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void fillAndPublishArticle(String articleTitle) {
        WebElement articleTextArea = appiumDriver.findElement(ARTICLE_TEXT_AREA);

        articleTextArea.click();
        articleTextArea.sendKeys(articleTitle);

        appiumDriver.findElement(PUBLISH_ARTICLE_BUTTON).click();

        appiumDriver.findElement(CONFIRM_PUBLISH_ARTICLE_BUTTON).click();
    }
}
