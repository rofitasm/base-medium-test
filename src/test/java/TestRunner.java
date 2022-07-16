import com.github.ubaifadhli.pages.medium.mobile.*;
import com.github.ubaifadhli.pages.medium.web.*;
import com.github.ubaifadhli.util.*;
import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestRunner {
    private WebHomePage webHomePage;
    private WebLoginPage webLoginPage;
    private WebArticlePage webArticlePage;

    private MobileHomePage mobileHomePage;
    private MobileLoginPage mobileLoginPage;
    private MobileArticlePage mobileArticlePage;

    @DataProvider(name = "drivers", parallel = true)
    public Object[][] getDataProvider() {
        return new Object[][]{
                new Object[]{"WEB", DriverFactory.createWebDriver(DriverManagerType.CHROME)},
                new Object[]{"MOBILE", DriverFactory.createMobileDriver()},
        };
    }

    public boolean isCurrentPlatformWeb(String platform) {
        return platform.equals("WEB");
    }

    public boolean isCurrentPlatformMobile(String platform) {
        return platform.equals("MOBILE");
    }

    public String getPropertyByPlatform(String propertyName, String platform) {
        PropertiesReader reader = new PropertiesReader("application.properties");

        return reader.getPropertyAsString(platform.toLowerCase() + "." + propertyName);
    }

    @Test(dataProvider = "drivers")
    public void login(String platform, RemoteWebDriver platformDriver) {
        String username = getPropertyByPlatform("login.twitter.username", platform);
        String password = getPropertyByPlatform("login.twitter.password", platform);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webLoginPage = new WebLoginPage(platformDriver);

            webHomePage.openPage();
            webHomePage.goToTwitterLoginPage();

            webLoginPage.fillTwitterLoginCredentials(username, password);
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileLoginPage = new MobileLoginPage((AppiumDriver) platformDriver);

            mobileHomePage.goToTwitterLoginPage();

            mobileLoginPage.fillTwitterLoginCredentials(username, password);
            mobileLoginPage.waitForHomeTitle();
        }
    }

    @Test(dataProvider = "drivers")
    public void searchForArticle(String platform, RemoteWebDriver platformDriver) {
        String SEARCH_KEYWORD = "spring boot";
        String EXPECTED_FIRST_ARTICLE_NAME = "How to scale Microservices with Message Queues, Spring Boot, and Kubernetes";

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);

            webHomePage.openPage();

            webHomePage.searchForArticle(SEARCH_KEYWORD);

            assertThat(webHomePage.getFirstArticleTitle(), equalTo(EXPECTED_FIRST_ARTICLE_NAME));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);

            mobileHomePage.searchForArticle(SEARCH_KEYWORD);

            assertThat(mobileHomePage.getFirstArticleTitle(), equalTo(EXPECTED_FIRST_ARTICLE_NAME));
        }
    }


    @Test(dataProvider = "drivers")
    public void createNewComment(String platform, RemoteWebDriver platformDriver) {
        String COMMENT = "Try to comment " + DatetimeHelper.getCurrentDatetime();

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webArticlePage = new WebArticlePage(platformDriver);

            webHomePage.openPage();
            webHomePage.openFirstUserArticle();
            webArticlePage.createComment(COMMENT);

            assertThat(webArticlePage.getFirstCommentText(), equalTo(COMMENT));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileArticlePage = new MobileArticlePage((AppiumDriver) platformDriver);

            mobileHomePage.openFirstUserArticle();
            mobileArticlePage.createComment(COMMENT);

            assertThat(mobileArticlePage.getFirstCommentText(), equalTo(COMMENT));
        }
    }

}
