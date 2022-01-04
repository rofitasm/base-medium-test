package com.github.ubaifadhli.pages.medium.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUserPublicProfilePage {
    private WebDriver webDriver;

    private By USER_BIO = By.xpath("//h2//following-sibling::div/p");

    public WebUserPublicProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUserBio() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement userBio = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_BIO));
        return userBio.getText();
    }
}
