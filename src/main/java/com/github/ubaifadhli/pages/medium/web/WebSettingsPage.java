package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebSettingsPage {
    private WebDriver webDriver;

    private By EDIT_BIO_BUTTON = By.xpath("//button[@data-action='edit-bio']");
    private By EDIT_BIO_FIELD = By.xpath("//p[contains(@class, 'graf--p')]");
    private By SAVE_BIO_BUTTON = By.xpath("//button[@data-action='save-bio']");
    private By USER_PUBLIC_PROFILE_LINK = By.xpath("//p[@data-default-value='Add your bio']//following-sibling::div/a");

    public WebSettingsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void editBio(String bioText) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebElement editBioButton = wait.until(ExpectedConditions.elementToBeClickable(EDIT_BIO_BUTTON));
        editBioButton.click();

        WebElement editBioField = wait.until(ExpectedConditions.elementToBeClickable(EDIT_BIO_FIELD));
        editBioField.clear();
        editBioField.sendKeys(bioText);

        WebElement saveBioButton = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BIO_BUTTON));
        saveBioButton.click();

        SleepHelper.sleepForSeconds(2);

        WebElement userPublicProfileLink = wait.until(ExpectedConditions.elementToBeClickable(USER_PUBLIC_PROFILE_LINK));
        userPublicProfileLink.click();
    }
}
