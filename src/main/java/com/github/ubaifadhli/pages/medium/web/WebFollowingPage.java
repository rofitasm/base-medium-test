package com.github.ubaifadhli.pages.medium.web;

import com.github.ubaifadhli.pages.medium.WebPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class WebFollowingPage extends WebPageObject {
    private By WRITERS_NAME_TEXT = By.xpath("//div[preceding-sibling::h2]//h2");

    public WebFollowingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getFollowingWritersName() {
        return getElementsAfterVisible(WRITERS_NAME_TEXT)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
