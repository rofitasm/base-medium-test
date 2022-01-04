package com.github.ubaifadhli.pages.medium.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileHomePage {
    AppiumDriver<MobileElement> appiumDriver;

    private By SIGN_IN_BUTTON = MobileBy.id("com.medium.reader:id/sign_in_prompt");
    private By SIGN_IN_WITH_TWITTER_BUTTON = MobileBy.id("com.medium.reader:id/sign_in_twitter_button");

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
        appiumDriver.findElement(SEARCH_BUTTON).click();

        WebElement searchInput = appiumDriver.findElement(SEARCH_INPUT);

        searchInput.click();
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void openFirstUserArticle() {
        appiumDriver.findElement(PROFILE_BUTTON).click();

        appiumDriver.findElements(USER_ARTICLE_TITLE).get(0).click();
    }

    public void createNewArticle() {
        appiumDriver.findElement(PROFILE_BUTTON).click();

        appiumDriver.findElement(CREATE_NEW_ARTICLE_BUTTON).click();
    }

    public void deleteArticle() {
        appiumDriver.findElements(ARTICLE_ELLIPSIS).get(0).click();

        appiumDriver.findElement(DELETE_ARTICLE_BUTTON).click();

        appiumDriver.findElement(CONFIRM_DELETE_ARTICLE_BUTTON).click();
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
        return appiumDriver.findElements(SEARCH_RESULT_TITLE).get(0).getText();
    }

    public String getFirstUserArticleTitle() {
        return appiumDriver.findElements(USER_ARTICLE_TITLE).get(0).getText();
    }
}
