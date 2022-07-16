package com.github.ubaifadhli.pages.medium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.event.KeyEvent;
import java.util.List;

public class MobilePageObject {
    private AppiumDriver appiumDriver;

    public MobilePageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public AppiumDriver getMobileDriver() {
        return appiumDriver;
    }

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(getMobileDriver(), 30);
    }

    public WebElement getElementAfterClickable(By locator) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement getElementAfterVisible(By locator) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> getElementsAfterVisible(By locator) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void pressEnter() {
        ((AndroidDriver) getMobileDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
