package com.github.ubaifadhli.pages.medium.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebLoginPage {
    private WebDriver webDriver;

    private By TWITTER_USERNAME_FIELD = By.id("username_or_email");
    private By TWITTER_PASSWORD_FIELD = By.id("password");
    private By TWITTER_SIGN_IN_BUTTON = By.xpath("//input[@value='Sign In']");



    public WebLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillTwitterLoginCredentials(String email, String password) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        
        WebElement twitterUsernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(TWITTER_USERNAME_FIELD));
        twitterUsernameField.sendKeys(email);

        WebElement twitterPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(TWITTER_PASSWORD_FIELD));
        twitterPasswordField.sendKeys(password);

        WebElement twitterSignInButton = wait.until(ExpectedConditions.elementToBeClickable(TWITTER_SIGN_IN_BUTTON));
        twitterSignInButton.click();
    }
}
