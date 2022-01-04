package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MobileHomePage {
    AppiumDriver<MobileElement> appiumDriver;

    private By SIGN_IN_BUTTON = MobileBy.id("com.medium.reader:id/sign_in_prompt");
    private By SIGN_IN_WITH_TWITTER_BUTTON = MobileBy.id("com.medium.reader:id/sign_in_twitter_button");

    private By SETTINGS_BUTTON = MobileBy.AccessibilityId("Settings");
    private By LISTS_BUTTON = MobileBy.AccessibilityId("Reading List");
    private By ACCOUNT_BUTTON = MobileBy.id("com.medium.reader:id/item_account");

    private By HOME_ARTICLES = MobileBy.xpath("//android.widget.TextView[@resource-id='com.medium.reader:id/post_preview_title']");

    private By SEARCH_BUTTON = MobileBy.AccessibilityId("Discover something new");
    private By SEARCH_INPUT = MobileBy.id("com.medium.reader:id/search_input");
    private By SEARCH_RESULT_TITLE = MobileBy.xpath("//android.widget.TextView[@resource-id='com.medium.reader:id/post_preview_title']");

    private By PROFILE_BUTTON = MobileBy.id("com.medium.reader:id/profile_image");
    private By CREATE_NEW_ARTICLE_BUTTON = MobileBy.AccessibilityId("Write a story");
    private By USER_ARTICLE_TITLE = MobileBy.id("com.medium.reader:id/tvTitle");

    private By ARTICLE_ELLIPSIS = MobileBy.AccessibilityId("More options");
    private By DELETE_ARTICLE_BUTTON = MobileBy.xpath("//android.widget.TextView[@text='Delete']");
    private By CONFIRM_DELETE_ARTICLE_BUTTON = MobileBy.id("android:id/button1");


    public MobileHomePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void goToTwitterLoginPage() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON));
        signInButton.click();

        WebElement signInWithTwitterButton = wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_WITH_TWITTER_BUTTON));
        signInWithTwitterButton.click();
    }

    public void searchForArticle(String keyword) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));
        searchButton.click();

        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT));
        searchInput.click();

        // Need to find the element again because the DOM changes.
        searchInput = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT));
        searchInput.sendKeys(keyword);

        ((AndroidDriver) appiumDriver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void goToSettingsPage() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_BUTTON));
        profileButton.click();

        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(SETTINGS_BUTTON));
        settingsButton.click();

        WebElement accountButton = wait.until(ExpectedConditions.elementToBeClickable(ACCOUNT_BUTTON));
        accountButton.click();
    }

    public void goToListsPage() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement listsButton = wait.until(ExpectedConditions.elementToBeClickable(LISTS_BUTTON));
        listsButton.click();
    }

    public void openFirstUserArticle() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_BUTTON));
        profileButton.click();

        List<WebElement> userArticleTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_ARTICLE_TITLE));
        userArticleTitles.get(0).click();
    }

    public void createNewArticle() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_BUTTON));
        profileButton.click();

        WebElement createNewArticleButton = wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_ARTICLE_BUTTON));
        createNewArticleButton.click();

        SleepHelper.sleepForSeconds(2);
    }

    public void deleteArticle() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        List<WebElement> articleEllipses = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ARTICLE_ELLIPSIS));
        articleEllipses.get(0).click();

        WebElement deleteArticleButton = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ARTICLE_BUTTON));
        deleteArticleButton.click();

        WebElement confirmDeleteArticleButton = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_DELETE_ARTICLE_BUTTON));
        confirmDeleteArticleButton.click();
    }

    public void refreshProfilePage() {
        int deviceMiddleX = appiumDriver.manage().window().getSize().getWidth() / 2;
        int deviceMiddleY = appiumDriver.manage().window().getSize().getHeight() / 2;

        int deviceEndY = (int) (deviceMiddleY * 1.5);

        TouchAction touchAction = new TouchAction(appiumDriver);

        touchAction.press(PointOption.point(deviceMiddleX, deviceMiddleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(deviceMiddleX, deviceEndY))
                .release()
                .perform();
    }

    public String getFirstArticleTitle() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        List<WebElement> searchResultTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SEARCH_RESULT_TITLE));
        return searchResultTitles.get(0).getText();
    }

    public String getFirstUserArticleTitle() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        List<WebElement> userArticleTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_ARTICLE_TITLE));
        return userArticleTitles.get(0).getText();
    }

    public void openFirstHomeArticle() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        List<WebElement> userArticleTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(HOME_ARTICLES));
        userArticleTitles.get(0).click();
    }
}
