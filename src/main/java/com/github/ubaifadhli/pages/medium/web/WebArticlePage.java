package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

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
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        List<WebElement> articleComments = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ARTICLE_COMMENTS));
        return articleComments.get(0).getText();
    }
}
