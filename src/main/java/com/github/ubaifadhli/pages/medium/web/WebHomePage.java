package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.pages.medium.WebPageObject;
import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebHomePage extends WebPageObject {
    private By SIGN_IN_BUTTON = By.xpath("//a[text()='Sign In']");
    private By SIGN_IN_WITH_TWITTER = By.xpath("//div[text()='Sign in with Twitter']");
    private By SEARCH_INPUT = By.xpath("//input[@aria-label='search']");
    private By PROFILE_BUTTON = By.xpath("//button[@aria-label='user options menu']");
    private By DROPDOWN_PROFILE_BUTTON = By.xpath("//a[contains(@href, '/@') and descendant::img]");
    private By SEARCH_RESULT_TITLE = By.xpath("//a[@aria-label='Post Preview Title']//h2");

    public WebHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage() {
        getWebDriver().manage().window().maximize();
        getWebDriver().get("https://www.medium.com");

        SleepHelper.sleepForSeconds(1);
    }

    public void goToTwitterLoginPage() {
        WebElement signInButton = getElementAfterClickable(SIGN_IN_BUTTON);
        signInButton.click();

        WebElement signInWithTwitterButton = getElementAfterClickable(SIGN_IN_WITH_TWITTER);
        signInWithTwitterButton.click();
    }

    public void searchForArticle(String keyword) {
        WebElement searchInput = getElementAfterClickable(SEARCH_INPUT);

        searchInput.click();
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void goToPublishedArticlePage() {
        WebElement profileButton = getElementAfterClickable(PROFILE_BUTTON);
        profileButton.click();

        WebElement dropdownArticleButton = getElementAfterClickable(DROPDOWN_PROFILE_BUTTON);
        dropdownArticleButton.click();
    }

    public void openFirstUserArticle() {
        goToPublishedArticlePage();

        SleepHelper.sleepForSeconds(1);
    }

    public String getFirstArticleTitle() {
        List<WebElement> searchResultTitles = getElementsAfterVisible(SEARCH_RESULT_TITLE);
        return searchResultTitles.get(0).getText();
    }
}
