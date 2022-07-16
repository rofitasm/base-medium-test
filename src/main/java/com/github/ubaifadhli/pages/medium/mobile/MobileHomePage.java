package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.pages.medium.MobilePageObject;
import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MobileHomePage extends MobilePageObject {

    private By SIGN_IN_BUTTON = MobileBy.id("com.medium.reader:id/sign_in_prompt");
    private By SIGN_IN_WITH_TWITTER_BUTTON = MobileBy.id("com.medium.reader:id/sign_in_twitter_button");
    private By SEARCH_BUTTON = MobileBy.AccessibilityId("Discover something new");
    private By SEARCH_INPUT = MobileBy.id("com.medium.reader:id/search_input");
    private By PROFILE_BUTTON = MobileBy.id("com.medium.reader:id/image");
    private By USER_ARTICLE_TITLE = MobileBy.xpath("//android.widget.TextView[@resource-id='com.medium.reader:id/common_item_paragraph_text']");
    private By SEARCH_RESULT_TITLE = MobileBy.xpath("//android.widget.TextView[@resource-id='com.medium.reader:id/post_preview_title']");

    public MobileHomePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void goToTwitterLoginPage() {
        WebElement signInButton = getElementAfterClickable(SIGN_IN_BUTTON);
        signInButton.click();

        WebElement signInWithTwitterButton = getElementAfterClickable(SIGN_IN_WITH_TWITTER_BUTTON);
        signInWithTwitterButton.click();
    }

    public void searchForArticle(String keyword) {
        WebElement searchButton = getElementAfterClickable(SEARCH_BUTTON);
        searchButton.click();

        WebElement searchInput = getElementAfterClickable(SEARCH_INPUT);
        searchInput.click();

        // Need to find the element again because the DOM changes.
        searchInput = getElementAfterVisible(SEARCH_INPUT);
        searchInput.sendKeys(keyword);

        pressEnter();
    }

    public void openFirstUserArticle() {
        WebElement profileButton = getElementAfterClickable(PROFILE_BUTTON);
        profileButton.click();

        List<WebElement> userArticleTitles = getElementsAfterVisible(USER_ARTICLE_TITLE);
        userArticleTitles.get(0).click();

        SleepHelper.sleepForSeconds(1);
    }

    public String getFirstArticleTitle() {
        List<WebElement> searchResultTitles = getElementsAfterVisible(SEARCH_RESULT_TITLE);
        return searchResultTitles.get(0).getText();
    }

}
