package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.pages.medium.WebPageObject;
import com.github.ubaifadhli.util.SleepHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebSettingsPage extends WebPageObject {
    private By EDIT_BIO_BUTTON = By.xpath("//button[@data-action='edit-bio']");
    private By EDIT_BIO_FIELD = By.xpath("//p[contains(@class, 'graf--p')]");
    private By SAVE_BIO_BUTTON = By.xpath("//button[@data-action='save-bio']");
    private By USER_PUBLIC_PROFILE_LINK = By.xpath("//p[@data-default-value='Add your short bio']//following-sibling::div/a");

    public WebSettingsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void editBio(String bioText) {
        WebElement editBioButton = getElementAfterClickable(EDIT_BIO_BUTTON);
        editBioButton.click();

        WebElement editBioField = getElementAfterVisible(EDIT_BIO_FIELD);
        editBioField.clear();
        editBioField.sendKeys(bioText);

        SleepHelper.sleepForSeconds(2);

        WebElement saveBioButton = getElementAfterClickable(SAVE_BIO_BUTTON);
        saveBioButton.click();

        SleepHelper.sleepForSeconds(2);

        WebElement userPublicProfileLink = getElementAfterClickable(USER_PUBLIC_PROFILE_LINK);
        userPublicProfileLink.click();
    }
}
