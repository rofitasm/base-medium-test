package com.github.ubaifadhli.pages.medium;

import io.appium.java_client.AppiumDriver;
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

public class MobilePageObject {
    private AppiumDriver appiumDriver;

    public MobilePageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public AppiumDriver getMobileDriver() {
        return appiumDriver;
    }

    public void pressBack() {
        ((AndroidDriver) getMobileDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void pressKey(AndroidKey key) {
        ((AndroidDriver) getMobileDriver()).pressKey(new KeyEvent(key));
    }

    public void pressEnter() {
        ((AndroidDriver) getMobileDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public boolean isKeyboardShown() {
        return ((AndroidDriver) getMobileDriver()).isKeyboardShown();
    }

    public void refreshPage() {
        int deviceMiddleX = getMobileDriver().manage().window().getSize().getWidth() / 2;
        int deviceMiddleY = getMobileDriver().manage().window().getSize().getHeight() / 2;

        int deviceEndY = (int) (deviceMiddleY * 1.5);

        TouchAction touchAction = new TouchAction(getMobileDriver());

        touchAction.press(PointOption.point(deviceMiddleX, deviceMiddleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(deviceMiddleX, deviceEndY))
                .release()
                .perform();
    }

    public void swipeUp() {
        int deviceMiddleX = getMobileDriver().manage().window().getSize().getWidth() / 2;
        int deviceMiddleY = getMobileDriver().manage().window().getSize().getHeight() / 2;

        int deviceEndY = (int) (deviceMiddleY * 0.5);

        TouchAction touchAction = new TouchAction(getMobileDriver());

        touchAction.press(PointOption.point(deviceMiddleX, deviceMiddleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(deviceMiddleX, deviceEndY))
                .release()
                .perform();
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
}
