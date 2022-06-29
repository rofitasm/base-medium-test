package com.github.ubaifadhli.pages.medium.mobile;

import com.github.ubaifadhli.pages.medium.MobilePageObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class MobileFollowingPage extends MobilePageObject {
    private By WRITERS_NAME_TEXT = By.xpath("//div[preceding-sibling::h2]//h2");

    public MobileFollowingPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public List<String> getFollowingWritersName() {
        return getElementsAfterVisible(WRITERS_NAME_TEXT)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
