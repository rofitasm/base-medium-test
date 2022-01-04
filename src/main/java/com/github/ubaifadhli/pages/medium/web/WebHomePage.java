package com.github.ubaifadhli.pages.medium.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebHomePage {
    private WebDriver webDriver;

    private By SIGN_IN_BUTTON = By.xpath("//a[text()='Sign In']");
    private By SIGN_IN_WITH_TWITTER = By.xpath("//div[text()='Sign in with Twitter']");

    private By SETTINGS_BUTTON = By.xpath("//a[text()='Settings']");
    private By LISTS_BUTTON = By.xpath("//a[text()='Lists']");
    private By SEARCH_BUTTON = By.xpath("//button[@aria-label='Search']");
    private By SEARCH_INPUT = By.xpath("//input[@aria-label='search']");
    private By SEARCH_RESULT_TITLE = By.xpath("//div[@class='postArticle-content']//h3");

    private By HOME_ARTICLE = By.xpath("//a/h2");

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
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));
        searchButton.click();

        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT));

        searchInput.click();
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void goToSettingsPage() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_BUTTON));
        profileButton.click();

        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(SETTINGS_BUTTON));
        settingsButton.click();
    }

    public void goToListsPage() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_BUTTON));
        profileButton.click();

        WebElement listsButton = wait.until(ExpectedConditions.elementToBeClickable(LISTS_BUTTON));
        listsButton.click();
    }

    public void goToPublishedArticlePage() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_BUTTON));
        profileButton.click();

        WebElement dropdownArticleButton = wait.until(ExpectedConditions.elementToBeClickable(DROPDOWN_ARTICLE_BUTTON));
        dropdownArticleButton.click();

        WebElement publishedArticleSection = wait.until(ExpectedConditions.elementToBeClickable(PUBLISHED_ARTICLE_SECTION));
        publishedArticleSection.click();
    }

    public void openFirstUserArticle() {
        goToPublishedArticlePage();

        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        List<WebElement> userArticleTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_ARTICLE_TITLE));
        userArticleTitles.get(0).click();
    }

    public void createNewArticle() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_BUTTON));
        profileButton.click();

        WebElement createNewArticleButton = wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_ARTICLE_BUTTON));
        createNewArticleButton.click();
    }

    public void openFirstHomeArticle() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        List<WebElement> homeArticles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(HOME_ARTICLE));
        homeArticles.get(0).click();
    }

    public void deleteArticle() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        List<WebElement> articleEllipses = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ARTICLE_ELLIPSIS));
        articleEllipses.get(0).click();

        WebElement deleteArticleButton = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ARTICLE_BUTTON));
        deleteArticleButton.click();

        WebElement confirmDeleteArticleButton = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_DELETE_ARTICLE_BUTTON));
        confirmDeleteArticleButton.click();
    }

    public String getFirstArticleTitle() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        List<WebElement> searchResultTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SEARCH_RESULT_TITLE));
        return searchResultTitles.get(0).getText();
    }

    public String getFirstUserArticleTitle() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        List<WebElement> userArticleTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_ARTICLE_TITLE));
        return userArticleTitles.get(0).getText();
    }
}
