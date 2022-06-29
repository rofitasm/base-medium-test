package com.github.ubaifadhli.pages.medium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebPageObject {
    private WebDriver webDriver;

    public WebPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(getWebDriver(), 30);
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
}
