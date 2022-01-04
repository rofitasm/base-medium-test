package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebCreateArticlePage {
    private WebDriver webDriver;

    private By ARTICLE_TEXT_AREA = By.xpath("//h3[contains(@class, 'graf--title')]");
    private By PUBLISH_ARTICLE_BUTTON = By.xpath("//button[@data-action='show-prepublish']");
    private By CONFIRM_PUBLISH_ARTICLE_BUTTON = By.xpath("//button[@data-action='publish']");


    public WebCreateArticlePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillAndPublishArticle(String articleTitle) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement articleTextArea = wait.until(ExpectedConditions.elementToBeClickable(ARTICLE_TEXT_AREA));

        articleTextArea.click();
        articleTextArea.sendKeys(articleTitle);

        WebElement publishArticleButton = wait.until(ExpectedConditions.elementToBeClickable(PUBLISH_ARTICLE_BUTTON));
        publishArticleButton.click();

        WebElement confirmPublishArticleButton = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_PUBLISH_ARTICLE_BUTTON));
        confirmPublishArticleButton.click();

        SleepHelper.sleepForSeconds(2);
    }

    public void goToPublishedArticlePage() {
        webDriver.get("https://medium.com/me/stories/public");
    }
}
