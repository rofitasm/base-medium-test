import com.github.ubaifadhli.pages.medium.mobile.*;
import com.github.ubaifadhli.pages.medium.web.*;
import com.github.ubaifadhli.util.DatetimeHelper;
import com.github.ubaifadhli.util.DriverFactory;
import com.github.ubaifadhli.util.PropertiesReader;
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
//                new Object[]{"WEB", DriverFactory.createWebDriver(DriverManagerType.CHROME)},
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
    public void createNewArticle(String platform, RemoteWebDriver platformDriver) {
        String ARTICLE_TITLE = "Test article " + DatetimeHelper.getCurrentDatetime();

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webCreateArticlePage = new WebCreateArticlePage(platformDriver);

            webHomePage.openPage();
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

            mobileHomePage.refreshPage();

            assertThat(mobileHomePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
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
        String BIO = "Current bio " + DatetimeHelper.getCurrentDatetime();

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webSettingsPage = new WebSettingsPage(platformDriver);
            webUserPublicProfilePage = new WebUserPublicProfilePage(platformDriver);

            webHomePage.openPage();
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
        String NEW_LIST_NAME = "List name " + DatetimeHelper.getCurrentDatetime();

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webListsPage = new WebListsPage(platformDriver);

            webHomePage.openPage();
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

            mobileHomePage.goToListsPage();

            assertThat(mobileListsPage.getSecondListName(), equalTo(NEW_LIST_NAME));
        }
    }

    @Test(dataProvider = "drivers")
    public void addArticleToList(String platform, RemoteWebDriver platformDriver) {
        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webListsPage = new WebListsPage(platformDriver);
            webArticlePage = new WebArticlePage(platformDriver);

            webHomePage.openPage();
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

    @Test(dataProvider = "drivers")
    public void userChecksMembershipOffering(String platform, RemoteWebDriver platformDriver) {

    }

    @Test(dataProvider = "drivers")
    public void userEditsAnArticle(String platform, RemoteWebDriver platformDriver) {

    }

    @Test(dataProvider = "drivers")
    public void userChecksFollowingInProfile(String platform, RemoteWebDriver platformDriver) {

    }

    @Test(dataProvider = "drivers")
    public void userChangesUsername(String platform, RemoteWebDriver platformDriver) {

    }

    @Test(dataProvider = "drivers")
    public void userChangesToMaximumBio(String platform, RemoteWebDriver platformDriver) {

    }

    @Test(dataProvider = "drivers")
    public void userDeletesAnArticleList(String platform, RemoteWebDriver platformDriver) {

    }

    @Test(dataProvider = "drivers")
    public void userRemovesArticleFromList(String platform, RemoteWebDriver platformDriver) {

    }

    @Test(dataProvider = "drivers")
    public void userChangesToExistingUsername(String platform, RemoteWebDriver platformDriver) {

    }
}
