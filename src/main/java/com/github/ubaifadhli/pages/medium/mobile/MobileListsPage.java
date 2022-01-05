package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileListsPage {
    private AppiumDriver<MobileElement> appiumDriver;

    private By CREATE_NEW_LIST_BUTTON = MobileBy.id("com.medium.reader:id/btn_new_list");
    private By NEW_LIST_NAME_FIELD = MobileBy.id("com.medium.reader:id/text_field_input_list_name");
    private By POPUP_CREATE_NEW_LIST_BUTTON = MobileBy.id("com.medium.reader:id/btn_create");
    private By SECOND_LIST_NAME = MobileBy.xpath("(//android.widget.TextView[@resource-id='com.medium.reader:id/tv_name'])[2]");
    private By LIST_ARTICLE_COUNT = MobileBy.id("com.medium.reader:id/tv_counters");

    public MobileListsPage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void createNewList(String newListName) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement createNewListButton = wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_LIST_BUTTON));
        createNewListButton.click();

        WebElement newListNameField = wait.until(ExpectedConditions.elementToBeClickable(NEW_LIST_NAME_FIELD));
        newListNameField.sendKeys(newListName);

        WebElement popupCreateNewListButton = wait.until(ExpectedConditions.elementToBeClickable(POPUP_CREATE_NEW_LIST_BUTTON));
        popupCreateNewListButton.click();

        SleepHelper.sleepForSeconds(2);
    }

    public String getSecondListName() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement secondListName = wait.until(ExpectedConditions.visibilityOfElementLocated(SECOND_LIST_NAME));
        return secondListName.getText();
    }

    public int getFirstListArticleCount() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);

        WebElement listArticleCount = wait.until(ExpectedConditions.visibilityOfElementLocated(LIST_ARTICLE_COUNT));

        String articleCount = listArticleCount.getText().split(" ")[0];

        return Integer.parseInt(articleCount);
    }
}
