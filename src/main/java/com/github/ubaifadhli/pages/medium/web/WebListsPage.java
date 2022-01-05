package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebListsPage {
    private WebDriver webDriver;

    private By CREATE_NEW_LIST_BUTTON = By.xpath("//button[text()='New list']");
    private By NEW_LIST_NAME_FIELD = By.xpath("//input[@placeholder='Give it a name']");
    private By POPUP_CREATE_NEW_LIST_BUTTON = By.xpath("//button[child::span[text()='Create']]");
    private By SECOND_LIST_NAME = By.xpath("(//a/div/div/h2)[2]");
    private By LIST_ARTICLE_COUNT = By.xpath("//div/div/p");

    public WebListsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void createNewList(String newListName) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement createNewListButton = wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_LIST_BUTTON));
        createNewListButton.click();

        WebElement newListNameField = wait.until(ExpectedConditions.elementToBeClickable(NEW_LIST_NAME_FIELD));
        newListNameField.sendKeys(newListName);

        WebElement popupCreateNewListButton = wait.until(ExpectedConditions.elementToBeClickable(POPUP_CREATE_NEW_LIST_BUTTON));
        popupCreateNewListButton.click();

        SleepHelper.sleepForSeconds(3);
    }

    public String getSecondListName() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement secondListName = wait.until(ExpectedConditions.visibilityOfElementLocated(SECOND_LIST_NAME));
        return secondListName.getText();
    }

    public int getFirstListArticleCount() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement listArticleCount = wait.until(ExpectedConditions.visibilityOfElementLocated(LIST_ARTICLE_COUNT));

        String articleCount = listArticleCount.getText().split(" ")[0];

        return Integer.parseInt(articleCount);
    }
}
