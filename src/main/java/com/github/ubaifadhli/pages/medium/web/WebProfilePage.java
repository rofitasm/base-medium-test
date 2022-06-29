package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.pages.medium.WebPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebProfilePage extends WebPageObject {
    private By USER_ARTICLE_TITLE = By.xpath("//article//h1/a");

    public WebProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickUserArticleTitle() {
        WebElement userArticleTitle = getElementAfterClickable(USER_ARTICLE_TITLE);
        userArticleTitle.click();
    }

    public String getFirstUserArticleTitle() {
        WebElement userArticleTitle = getElementAfterVisible(USER_ARTICLE_TITLE);
        return userArticleTitle.getText();
    }
}
