package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebArticlePage {
    private WebDriver webDriver;

    private By COMMENT_BUTTON = By.xpath("//div[child::*[local-name()='svg' and @aria-label='responses']]");
    private By COMMENT_FIELD =By.xpath("//div[@role='textbox']");
    private By PUBLISH_COMMENT_BUTTON = By.xpath("//button[text()='Respond']");
    private By ARTICLE_COMMENTS = By.xpath("//pre/div/div/div");



    public WebArticlePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void createComment(String commentText) {
        webDriver.findElement(COMMENT_BUTTON).click();

        WebElement commentField = webDriver.findElement(COMMENT_FIELD);

        commentField.click();
        commentField.sendKeys(commentText);

        webDriver.findElement(PUBLISH_COMMENT_BUTTON).click();

        SleepHelper.sleepForSeconds(2);
    }

    public String getFirstCommentText() {
        return webDriver.findElements(ARTICLE_COMMENTS).get(0).getText();
    }
}
