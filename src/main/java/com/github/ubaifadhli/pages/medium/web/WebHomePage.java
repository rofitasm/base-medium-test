package com.github.ubaifadhli.pages.medium.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebHomePage {
    private WebDriver webDriver;

    private By SIGN_IN_BUTTON = By.xpath("//a[text()='Sign In']");
    private By SIGN_IN_WITH_TWITTER = By.xpath("//div[text()='Sign in with Twitter']");

    private By SEARCH_BUTTON = By.xpath("//button[@aria-label='Search']");
    private By SEARCH_INPUT = By.xpath("//input[@aria-label='search']");
    private By SEARCH_RESULT_TITLE = By.xpath("//div[@class='postArticle-content']//h3");

    private By PROFILE_BUTTON = By.xpath("//button[child::img]");
    private By DROPDOWN_ARTICLE_BUTTON = By.xpath("//a[text()='Stories']");
    private By PUBLISHED_ARTICLE_SECTION = By.xpath("//div[contains(text(), 'Published')]");

    private By CREATE_NEW_ARTICLE_BUTTON = By.xpath("//a[text()='Write a story']");
    private By USER_ARTICLE_TITLE = By.xpath("//a[contains(@href, 'your_stories_page') and parent::h3]");

    private By ARTICLE_ELLIPSIS = By.xpath("//button[@aria-controls='yourStoryActionsMenu']");
    private By DELETE_ARTICLE_BUTTON = By.xpath("//div[@id='yourStoryActionsMenu']//button");
    private By CONFIRM_DELETE_ARTICLE_BUTTON = By.xpath("//button[text()='Delete']");



    public WebHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openPage() {
        webDriver.get("https://www.medium.com");
    }

    public void goToTwitterLoginPage() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON));
        signInButton.click();

        WebElement signInWithTwitterButton = wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_WITH_TWITTER));
        signInWithTwitterButton.click();
    }


    public void searchForArticle(String keyword) {
        webDriver.findElement(SEARCH_BUTTON).click();

        WebElement searchInput = webDriver.findElement(SEARCH_INPUT);

        searchInput.click();
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void goToPublishedArticlePage() {
        webDriver.findElement(PROFILE_BUTTON).click();

        webDriver.findElement(DROPDOWN_ARTICLE_BUTTON).click();

        webDriver.findElement(PUBLISHED_ARTICLE_SECTION).click();
    }

    public void openFirstUserArticle() {
        goToPublishedArticlePage();

        webDriver.findElements(USER_ARTICLE_TITLE).get(0).click();
    }

    public void createNewArticle() {
        webDriver.findElement(PROFILE_BUTTON).click();

        webDriver.findElement(CREATE_NEW_ARTICLE_BUTTON).click();
    }

    public void deleteArticle() {
        webDriver.findElements(ARTICLE_ELLIPSIS).get(0).click();

        webDriver.findElement(DELETE_ARTICLE_BUTTON).click();

        webDriver.findElement(CONFIRM_DELETE_ARTICLE_BUTTON).click();
    }

    public String getFirstArticleTitle() {
        return webDriver.findElements(SEARCH_RESULT_TITLE).get(0).getText();
    }

    public String getFirstUserArticleTitle() {
        return webDriver.findElements(USER_ARTICLE_TITLE).get(0).getText();
    }
}
