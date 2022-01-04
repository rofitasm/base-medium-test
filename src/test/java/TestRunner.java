import com.github.ubaifadhli.pages.medium.mobile.MobileArticlePage;
import com.github.ubaifadhli.pages.medium.mobile.MobileCreateArticlePage;
import com.github.ubaifadhli.pages.medium.mobile.MobileHomePage;
import com.github.ubaifadhli.pages.medium.mobile.MobileLoginPage;
import com.github.ubaifadhli.pages.medium.web.WebArticlePage;
import com.github.ubaifadhli.pages.medium.web.WebCreateArticlePage;
import com.github.ubaifadhli.pages.medium.web.WebHomePage;
import com.github.ubaifadhli.pages.medium.web.WebLoginPage;
import com.github.ubaifadhli.util.DriverFactory;
import com.github.ubaifadhli.util.PropertiesReader;
import com.github.ubaifadhli.util.RandomGenerator;
import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class TestRunner {
    private WebHomePage webHomePage;
    private WebLoginPage webLoginPage;
    private WebCreateArticlePage webCreateArticlePage;
    private WebArticlePage webArticlePage;

    private MobileHomePage mobileHomePage;
    private MobileLoginPage mobileLoginPage;
    private MobileCreateArticlePage mobileCreateArticlePage;
    private MobileArticlePage mobileArticlePage;

    @DataProvider(name = "drivers", parallel = true)
    public Object[][] getDataProvider() {
        return new Object[][]{
                new Object[]{ "WEB", DriverFactory.createWebDriver(DriverManagerType.CHROME) },
                new Object[]{ "MOBILE", DriverFactory.createMobileDriver() },
        };
    }

    public boolean isCurrentPlatformWeb(String platform) {
        return platform.equals("WEB");
    }

    public boolean isCurrentPlatformMobile(String platform) {
        return platform.equals("MOBILE");
    }

    @Test(dataProvider = "drivers")
    public void login(String platform, RemoteWebDriver platformDriver) {
        PropertiesReader reader = new PropertiesReader("application.properties");

        String username = reader.getPropertyAsString("login.twitter.username");
        String password = reader.getPropertyAsString("login.twitter.password");

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
        }
    }

    @Test(dataProvider = "drivers")
    public void searchForArticle(String platform, RemoteWebDriver platformDriver) {
        String SEARCH_KEYWORD = "spring boot";
        String EXPECTED_FIRST_ARTICLE_NAME = "How to scale Microservices with Message Queues, Spring Boot, and Kubernetes";

        login(platform, platformDriver);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);

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
    public void createNewArticle(String platform, RemoteWebDriver platformDriver) {
        String ARTICLE_TITLE = "Test article " + RandomGenerator.generateString();

        login(platform, platformDriver);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webCreateArticlePage = new WebCreateArticlePage(platformDriver);

            webHomePage.createNewArticle();

            webCreateArticlePage.fillAndPublishArticle(ARTICLE_TITLE);

            webCreateArticlePage.goToPublishedArticlePage();

            assertThat(webHomePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileCreateArticlePage = new MobileCreateArticlePage((AppiumDriver) platformDriver);

            mobileHomePage.createNewArticle();

            mobileCreateArticlePage.fillAndPublishArticle(ARTICLE_TITLE);

            mobileHomePage.refreshProfilePage();

            assertThat(mobileHomePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
        }
    }

    @Test(dataProvider = "drivers")
    public void createNewComment(String platform, RemoteWebDriver platformDriver) {
        String COMMENT = "Try to comment " + RandomGenerator.generateString();

        login(platform, platformDriver);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webArticlePage = new WebArticlePage(platformDriver);

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

    @Test(dataProvider = "drivers")
    public void deleteArticle(String platform, RemoteWebDriver platformDriver) {
        createNewArticle(platform, platformDriver);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);

            String firstArticleTitle = webHomePage.getFirstUserArticleTitle();

            webHomePage.deleteArticle();

            String currentFirstArticleTitle = webHomePage.getFirstUserArticleTitle();

            assertThat(firstArticleTitle, not(currentFirstArticleTitle));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);

            String firstArticleTitle = mobileHomePage.getFirstUserArticleTitle();

            mobileHomePage.deleteArticle();

            String currentFirstArticleTitle = mobileHomePage.getFirstUserArticleTitle();

            assertThat(firstArticleTitle, not(currentFirstArticleTitle));
        }
    }


}
