package com.github.ubaifadhli.pages.medium.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebLoginPage {
    private WebDriver webDriver;

    private By TWITTER_USERNAME_FIELD = By.id("username_or_email");
    private By TWITTER_PASSWORD_FIELD = By.id("password");
    private By TWITTER_SIGN_IN_BUTTON = By.xpath("//input[@value='Sign In']");



    public WebLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillTwitterLoginCredentials(String email, String password) {
        webDriver.findElement(TWITTER_USERNAME_FIELD).sendKeys(email);
        webDriver.findElement(TWITTER_PASSWORD_FIELD).sendKeys(password);

        webDriver.findElement(TWITTER_SIGN_IN_BUTTON).click();
    }
}
