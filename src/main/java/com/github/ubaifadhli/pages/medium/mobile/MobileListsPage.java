package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.pages.medium.MobilePageObject;
import com.github.ubaifadhli.util.SleepHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileListsPage extends MobilePageObject {
    private By CREATE_NEW_LIST_BUTTON = MobileBy.id("com.medium.reader:id/btn_new_list");
    private By NEW_LIST_NAME_FIELD = MobileBy.id("com.medium.reader:id/text_field_input_list_name");
    private By POPUP_CREATE_NEW_LIST_BUTTON = MobileBy.id("com.medium.reader:id/btn_create");
    private By SECOND_LIST_NAME = MobileBy.xpath("(//android.widget.TextView[@resource-id='com.medium.reader:id/tv_name'])[2]");
    private By LIST_ARTICLE_COUNT = MobileBy.id("com.medium.reader:id/tv_counters");
    private By READING_LIST_BUTTON = MobileBy.xpath("//android.widget.TextView[@text='Reading List']");

    public MobileListsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void createNewList(String newListName) {
        WebElement createNewListButton = getElementAfterClickable(CREATE_NEW_LIST_BUTTON);
        createNewListButton.click();

        WebElement newListNameField = getElementAfterClickable(NEW_LIST_NAME_FIELD);
        newListNameField.sendKeys(newListName);

        WebElement popupCreateNewListButton = getElementAfterClickable(POPUP_CREATE_NEW_LIST_BUTTON);
        popupCreateNewListButton.click();

        SleepHelper.sleepForSeconds(2);
    }

    public String getSecondListName() {
        WebElement secondListName = getElementAfterVisible(SECOND_LIST_NAME);
        return secondListName.getText();
    }

    public int getReadingListArticleCount() {
        WebElement listArticleCount = getElementAfterVisible(LIST_ARTICLE_COUNT);

        String articleCount = listArticleCount.getText().split(" ")[0];

        return Integer.parseInt(articleCount);
    }

    public void clickSecondList() {
        swipeUp();
        SleepHelper.sleepForSeconds(1);

        WebElement secondListName = getElementAfterClickable(SECOND_LIST_NAME);
        secondListName.click();
    }

    public void clickReadingList() {
        WebElement readingListButton = getElementAfterClickable(READING_LIST_BUTTON);
        readingListButton.click();
    }
}
