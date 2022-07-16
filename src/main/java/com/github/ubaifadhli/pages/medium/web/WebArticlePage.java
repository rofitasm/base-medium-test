package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.pages.medium.WebPageObject;
import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebArticlePage extends WebPageObject {
    private By COMMENT_BUTTON = By.xpath("(//button[@aria-label='responses'])[2]");
    private By COMMENT_FIELD = By.xpath("//div[@role='textbox']");
    private By PUBLISH_COMMENT_BUTTON = By.xpath("//button[text()='Respond']");
    private By ARTICLE_COMMENTS = By.xpath("(//pre/div/div/div)[last()]");

    public WebArticlePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void createComment(String commentText) {
        WebElement commentButton = getElementAfterClickable(COMMENT_BUTTON);
        commentButton.click();

        WebElement commentField = getElementAfterClickable(COMMENT_FIELD);
        commentField.click();
        commentField.sendKeys(commentText);

        getElementAfterClickable(PUBLISH_COMMENT_BUTTON);

        WebElement publishCommentButton = getElementAfterClickable(PUBLISH_COMMENT_BUTTON);
        publishCommentButton.click();

        SleepHelper.sleepForSeconds(2);
    }

    public String getFirstCommentText() {
        List<WebElement> articleComments = getElementsAfterVisible(ARTICLE_COMMENTS);
        return articleComments.get(0).getText();
    }

}
