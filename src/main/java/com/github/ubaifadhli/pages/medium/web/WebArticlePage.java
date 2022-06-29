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
    private By ARTICLE_COMMENTS = By.xpath("//pre/div/div/div");
    private By ADD_TO_BOOKMARK_BUTTON = By.xpath("//button[child::*[local-name()='svg' ] and @aria-label='Add to list bookmark button']");
    private By WRITER_PROFILE = By.xpath("//h2[contains(@class, 'pw-author-name')]");

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

    public void clickAddToBookmarkButton() {
        WebElement addToBookmarkButton = getElementAfterClickable(ADD_TO_BOOKMARK_BUTTON);
        addToBookmarkButton.click();

        SleepHelper.sleepForSeconds(1);

        getWebDriver().navigate().back();
    }

    public void goToWriterProfile() {
        WebElement writerProfile = getElementAfterClickable(WRITER_PROFILE);
        writerProfile.click();
    }
}
