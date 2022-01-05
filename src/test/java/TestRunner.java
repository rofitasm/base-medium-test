import com.github.ubaifadhli.pages.medium.mobile.*;
import com.github.ubaifadhli.pages.medium.web.*;
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
    private WebListsPage webListsPage;
    private WebSettingsPage webSettingsPage;
    private WebUserPublicProfilePage webUserPublicProfilePage;

    private MobileHomePage mobileHomePage;
    private MobileLoginPage mobileLoginPage;
    private MobileCreateArticlePage mobileCreateArticlePage;
    private MobileArticlePage mobileArticlePage;
    private MobileListsPage mobileListsPage;
    private MobileSettingsPage mobileSettingsPage;
    private MobileUserPublicProfilePage mobileUserPublicProfilePage;

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

//        login(platform, platformDriver);

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
        String ARTICLE_TITLE = "Test article " + RandomGenerator.generateNumberString();

//        login(platform, platformDriver);

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
        String COMMENT = "Try to comment " + RandomGenerator.generateNumberString();

//        login(platform, platformDriver);

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

    @Test(dataProvider = "drivers")
    public void editBio(String platform, RemoteWebDriver platformDriver) {
        String BIO = "Current bio " + RandomGenerator.generateNumberString();

//        login(platform, platformDriver);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webSettingsPage = new WebSettingsPage(platformDriver);
            webUserPublicProfilePage = new WebUserPublicProfilePage(platformDriver);

            webHomePage.goToSettingsPage();

            webSettingsPage.editBio(BIO);

            assertThat(webUserPublicProfilePage.getUserBio(), equalTo(BIO));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileSettingsPage = new MobileSettingsPage((AppiumDriver) platformDriver);
            mobileUserPublicProfilePage = new MobileUserPublicProfilePage((AppiumDriver) platformDriver);

            mobileHomePage.goToSettingsPage();

            mobileSettingsPage.editBio(BIO);

            assertThat(mobileUserPublicProfilePage.getUserBio(), equalTo(BIO));
        }
    }

    @Test(dataProvider = "drivers")
    public void createNewList(String platform, RemoteWebDriver platformDriver) {
        String NEW_LIST_NAME = "List name " + RandomGenerator.generateNumberString();

//        login(platform, platformDriver);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webListsPage = new WebListsPage(platformDriver);

            webHomePage.goToListsPage();

            webListsPage.createNewList(NEW_LIST_NAME);

            webHomePage.goToListsPage();

            assertThat(webListsPage.getSecondListName(), equalTo(NEW_LIST_NAME));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileListsPage = new MobileListsPage((AppiumDriver) platformDriver);

            mobileHomePage.goToListsPage();

            mobileListsPage.createNewList(NEW_LIST_NAME);

            assertThat(mobileListsPage.getSecondListName(), equalTo(NEW_LIST_NAME));
        }
    }

    @Test(dataProvider = "drivers")
    public void addArticleToList(String platform, RemoteWebDriver platformDriver) {
//        login(platform, platformDriver);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webListsPage = new WebListsPage(platformDriver);
            webArticlePage = new WebArticlePage(platformDriver);

            webHomePage.goToListsPage();

            int currentArticleCount = webListsPage.getFirstListArticleCount();

            webHomePage.openPage();

            webHomePage.openFirstHomeArticle();

            webArticlePage.clickAddToBookmarkButton();

            webHomePage.goToListsPage();

            int finalArticleCount = webListsPage.getFirstListArticleCount();

            assertThat(finalArticleCount, equalTo(currentArticleCount + 1));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileListsPage = new MobileListsPage((AppiumDriver) platformDriver);
            mobileArticlePage = new MobileArticlePage((AppiumDriver) platformDriver);

            mobileHomePage.goToListsPage();

            int currentArticleCount = mobileListsPage.getFirstListArticleCount();

            mobileHomePage.openFirstHomeArticle();

            mobileArticlePage.clickAddToBookmarkButton();

            mobileHomePage.goToListsPage();

            int finalArticleCount = mobileListsPage.getFirstListArticleCount();

            assertThat(finalArticleCount, equalTo(currentArticleCount + 1));
        }
    }
}
