package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebCreateArticlePage {
    private WebDriver webDriver;

    private By ARTICLE_TEXT_AREA = By.xpath("//h3[contains(@class, 'graf--title')]");
    private By PUBLISH_ARTICLE_BUTTON = By.xpath("//button[@data-action='show-prepublish']");
    private By CONFIRM_PUBLISH_ARTICLE_BUTTON = By.xpath("//button[@data-action='publish']");


    public WebCreateArticlePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillAndPublishArticle(String articleTitle) {
        WebElement articleTextArea = webDriver.findElement(ARTICLE_TEXT_AREA);

        articleTextArea.click();
        articleTextArea.sendKeys(articleTitle);

        webDriver.findElement(PUBLISH_ARTICLE_BUTTON);
        webDriver.findElement(CONFIRM_PUBLISH_ARTICLE_BUTTON);

        SleepHelper.sleepForSeconds(2);

        webDriver.get("https://medium.com/me/stories/public");
    }
}
