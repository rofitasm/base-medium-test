import com.github.ubaifadhli.pages.medium.mobile.*;
import com.github.ubaifadhli.pages.medium.web.*;
import com.github.ubaifadhli.util.*;
import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestRunner {
    private WebHomePage webHomePage;
    private WebLoginPage webLoginPage;
    private WebCreateArticlePage webCreateArticlePage;
    private WebArticlePage webArticlePage;
    private WebListsPage webListsPage;
    private WebSettingsPage webSettingsPage;
    private WebUserPublicProfilePage webUserPublicProfilePage;
    private WebProfilePage webProfilePage;
    private WebMembershipPage webMembershipPage;
    private WebWriterPage webWriterPage;
    private WebFollowingPage webFollowingPage;
    private WebListDetailPage webListDetailPage;

    private MobileHomePage mobileHomePage;
    private MobileLoginPage mobileLoginPage;
    private MobileCreateArticlePage mobileCreateArticlePage;
    private MobileArticlePage mobileArticlePage;
    private MobileListsPage mobileListsPage;
    private MobileSettingsPage mobileSettingsPage;
    private MobileUserPublicProfilePage mobileUserPublicProfilePage;
    private MobileProfilePage mobileProfilePage;
    private MobileMembershipPage mobileMembershipPage;
    private MobileWriterPage mobileWriterPage;
    private MobileFollowingPage mobileFollowingPage;
    private MobileListDetailPage mobileListDetailPage;

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

            webProfilePage.deleteArticle();

            String currentFirstArticleTitle = webHomePage.getFirstUserArticleTitle();

            assertThat(firstArticleTitle, not(currentFirstArticleTitle));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);

            String firstArticleTitle = mobileHomePage.getFirstUserArticleTitle();

            mobileProfilePage.deleteArticle();

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
            mobileHomePage.goToAccountSettingsPage();

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

            int currentArticleCount = webListsPage.getReadingListArticleCount();

            webHomePage.openPage();

            webHomePage.openFirstHomeArticle();

            webArticlePage.clickAddToBookmarkButton();

            webHomePage.goToListsPage();

            int finalArticleCount = webListsPage.getReadingListArticleCount();

            assertThat(finalArticleCount, equalTo(currentArticleCount + 1));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileListsPage = new MobileListsPage((AppiumDriver) platformDriver);
            mobileArticlePage = new MobileArticlePage((AppiumDriver) platformDriver);

            mobileHomePage.goToListsPage();

            int currentArticleCount = mobileListsPage.getReadingListArticleCount();

            mobileHomePage.openFirstHomeArticle();

            mobileArticlePage.clickAddToBookmarkButton();

            mobileHomePage.goToListsPage();

            int finalArticleCount = mobileListsPage.getReadingListArticleCount();

            assertThat(finalArticleCount, equalTo(currentArticleCount + 1));
        }
    }

    @Test(dataProvider = "drivers")
    public void userChecksMembershipOffering(String platform, RemoteWebDriver platformDriver) {
        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webMembershipPage = new WebMembershipPage(platformDriver);

            webHomePage.openPage();

            webHomePage.goToMembershipPage();

            assertThat(webMembershipPage.getMonthlySubsPrice(), is(both(greaterThan(4.98)).and(lessThan(5.01))));
            assertThat(webMembershipPage.getAnnualSubsPrice(), is(both(greaterThan(49.98)).and(lessThan(50.01))));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileMembershipPage = new MobileMembershipPage((AppiumDriver) platformDriver);

            mobileHomePage.goToSettingsPage();
            mobileHomePage.goToMembershipPage();

            assertThat(mobileMembershipPage.getMonthlySubsPrice(), is(both(greaterThan(4.98)).and(lessThan(5.01))));
            assertThat(mobileMembershipPage.getAnnualSubsPrice(), is(both(greaterThan(49.98)).and(lessThan(50.01))));
        }
    }

    @Test(dataProvider = "drivers")
    public void userEditsAnArticle(String platform, RemoteWebDriver platformDriver) {
        String ARTICLE_TITLE = "Edited article " + DatetimeHelper.getCurrentDatetime();

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webProfilePage = new WebProfilePage(platformDriver);
            webCreateArticlePage = new WebCreateArticlePage(platformDriver);

            webHomePage.openPage();

            webHomePage.goToPublishedArticlePage();
            webProfilePage.editArticle();

            webCreateArticlePage.fillAndRepublishArticle(ARTICLE_TITLE);

            assertThat(webHomePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileProfilePage = new MobileProfilePage((AppiumDriver) platformDriver);
            mobileCreateArticlePage = new MobileCreateArticlePage((AppiumDriver) platformDriver);

            mobileHomePage.goToPublishedArticlePage();
            mobileProfilePage.editArticle();

            mobileCreateArticlePage.fillAndRepublishArticle(ARTICLE_TITLE);

            mobileHomePage.refreshPage();

            assertThat(mobileHomePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
        }
    }

    @Test(dataProvider = "drivers")
    public void userChecksFollowingInProfile(String platform, RemoteWebDriver platformDriver) {
        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webArticlePage = new WebArticlePage(platformDriver);
            webWriterPage = new WebWriterPage(platformDriver);
            webFollowingPage = new WebFollowingPage(platformDriver);

            webHomePage.openPage();

            webHomePage.openFirstHomeArticle();
            webArticlePage.goToWriterProfile();

            webWriterPage.followWriter();

            String followedWriter = webWriterPage.getWriterName();

            webHomePage.backToHomeFromArticleWriter();
            webHomePage.openFollowingPage();

            assertThat(followedWriter, in(webFollowingPage.getFollowingWritersName()));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileArticlePage = new MobileArticlePage((AppiumDriver) platformDriver);
            mobileWriterPage = new MobileWriterPage((AppiumDriver) platformDriver);
            mobileFollowingPage = new MobileFollowingPage((AppiumDriver) platformDriver);

            mobileHomePage.openFirstHomeArticle();
            mobileArticlePage.goToWriterProfile();

            mobileWriterPage.followWriter();

            String followedWriter = mobileWriterPage.getWriterName();

            mobileHomePage.backToHomeFromArticleWriter();
            mobileHomePage.openFollowingPage();

            assertThat(followedWriter, in(mobileFollowingPage.getFollowingWritersName()));
        }
    }

    @Test(dataProvider = "drivers")
    public void userChangesUsername(String platform, RemoteWebDriver platformDriver) {
        String NEW_USERNAME = "username" + RandomGenerator.generateNumberString();

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webSettingsPage = new WebSettingsPage(platformDriver);

            webHomePage.openPage();

            webHomePage.goToSettingsPage();

            webSettingsPage.editUsername(NEW_USERNAME);
            webSettingsPage.saveUsername();

            assertThat(webSettingsPage.getCurrentUsername(), equalTo(NEW_USERNAME));
            assertThat(webSettingsPage.getCurrentProfileURL(), containsString(NEW_USERNAME));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileSettingsPage = new MobileSettingsPage((AppiumDriver) platformDriver);

            mobileHomePage.goToSettingsPage();
            mobileHomePage.goToAccountSettingsPage();

            mobileSettingsPage.editUsername(NEW_USERNAME);
            mobileSettingsPage.saveUsername();

            NEW_USERNAME += "a";

            assertThat(mobileSettingsPage.getCurrentUsername(), equalTo(NEW_USERNAME));
            assertThat(mobileSettingsPage.getCurrentProfileURL(), containsString(NEW_USERNAME));
        }
    }

    @Test(dataProvider = "drivers")
    public void userChangesToMaximumBio(String platform, RemoteWebDriver platformDriver) {
        String MAX_LIMIT_BIO = RandomGenerator.generateStringByLength(160);

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webSettingsPage = new WebSettingsPage(platformDriver);
            webUserPublicProfilePage = new WebUserPublicProfilePage(platformDriver);

            webHomePage.openPage();
            webHomePage.goToSettingsPage();

            webSettingsPage.editBio(MAX_LIMIT_BIO);

            assertThat(webUserPublicProfilePage.getUserBio(), equalTo(MAX_LIMIT_BIO));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileSettingsPage = new MobileSettingsPage((AppiumDriver) platformDriver);
            mobileUserPublicProfilePage = new MobileUserPublicProfilePage((AppiumDriver) platformDriver);

            mobileHomePage.goToSettingsPage();
            mobileHomePage.goToAccountSettingsPage();

            mobileSettingsPage.editBio(MAX_LIMIT_BIO);

            assertThat(mobileUserPublicProfilePage.getUserBio(), equalTo(MAX_LIMIT_BIO));
        }
    }

    @Test(dataProvider = "drivers")
    public void userDeletesAnArticleList(String platform, RemoteWebDriver platformDriver) {
        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webListsPage = new WebListsPage(platformDriver);
            webListDetailPage = new WebListDetailPage(platformDriver);

            webHomePage.openPage();

            webHomePage.goToListsPage();

            String previousSecondListName = webListsPage.getSecondListName();

            webListsPage.clickSecondList();

            webListDetailPage.deleteList();

            String currentSecondListName = webListsPage.getSecondListName();

            assertThat(currentSecondListName, not(previousSecondListName));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileListsPage = new MobileListsPage((AppiumDriver) platformDriver);
            mobileListDetailPage = new MobileListDetailPage((AppiumDriver) platformDriver);

            mobileHomePage.goToListsPage();

            String previousSecondListName = mobileListsPage.getSecondListName();

            mobileListsPage.clickSecondList();

            mobileListDetailPage.deleteList();

            mobileHomePage.refreshPage();

            String currentSecondListName = mobileListsPage.getSecondListName();

            assertThat(currentSecondListName, not(previousSecondListName));
        }
    }

    @Test(dataProvider = "drivers")
    public void userRemovesArticleFromList(String platform, RemoteWebDriver platformDriver) {
        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webListsPage = new WebListsPage(platformDriver);
            webListDetailPage = new WebListDetailPage(platformDriver);

            webHomePage.openPage();

            webHomePage.goToListsPage();

            int previousArticleCount = webListsPage.getReadingListArticleCount();

            webListsPage.clickReadingList();

            webListDetailPage.removeFirstArticleFromList();

            webHomePage.goToListsPage();

            int currentArticleCount = webListsPage.getReadingListArticleCount();

            assertThat(currentArticleCount, equalTo(previousArticleCount - 1));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileListsPage = new MobileListsPage((AppiumDriver) platformDriver);
            mobileListDetailPage = new MobileListDetailPage((AppiumDriver) platformDriver);

            mobileHomePage.goToListsPage();

            int previousArticleCount = mobileListsPage.getReadingListArticleCount();

            mobileListsPage.clickReadingList();

            mobileListDetailPage.removeFirstArticleFromList();

            mobileHomePage.goToListsPage();

            mobileHomePage.refreshPage();

            int currentArticleCount = mobileListsPage.getReadingListArticleCount();

            assertThat(currentArticleCount, equalTo(previousArticleCount - 1));
        }
    }

    @Test(dataProvider = "drivers")
    public void userChangesToExistingUsername(String platform, RemoteWebDriver platformDriver) {
        String EXISTING_USERNAME = "john";

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webSettingsPage = new WebSettingsPage(platformDriver);

            webHomePage.openPage();

            webHomePage.goToSettingsPage();

            webSettingsPage.editUsername(EXISTING_USERNAME);

            assertThat(webSettingsPage.getUsernameErrorText(), equalTo("Username is not available"));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileSettingsPage = new MobileSettingsPage((AppiumDriver) platformDriver);

            mobileHomePage.goToSettingsPage();
            mobileHomePage.goToAccountSettingsPage();

            mobileSettingsPage.editUsername(EXISTING_USERNAME);

            assertThat(mobileSettingsPage.getUsernameErrorText(), equalTo("Username is not available"));
        }
    }

    @Test(dataProvider = "drivers")
    public void userLogsInUsingInvalidCredential(String platform, RemoteWebDriver platformDriver) {
        String username = getPropertyByPlatform("login.twitter.username", platform);
        String password = "invalidPassword";

        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webLoginPage = new WebLoginPage(platformDriver);

            webHomePage.openPage();
            webHomePage.goToTwitterLoginPage();

            webLoginPage.fillTwitterLoginCredentials(username, password);

            assertThat(webLoginPage.getLoginErrorText(), containsString("you entered did not match our records"));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileLoginPage = new MobileLoginPage((AppiumDriver) platformDriver);

            mobileHomePage.goToTwitterLoginPage();

            mobileLoginPage.fillTwitterLoginCredentials(username, password);

            assertThat(mobileLoginPage.getLoginErrorText(), containsString("you entered did not match our records"));
        }
    }

    @Test(dataProvider = "drivers")
    public void userUnfollowsWriter(String platform, RemoteWebDriver platformDriver) {
        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webArticlePage = new WebArticlePage(platformDriver);
            webWriterPage = new WebWriterPage(platformDriver);
            webFollowingPage = new WebFollowingPage(platformDriver);

            webHomePage.openPage();

            webHomePage.openFirstHomeArticle();
            webArticlePage.goToWriterProfile();

            webWriterPage.followWriter();

            webHomePage.backToHomeFromArticleWriter();
            webHomePage.openFollowingPage();

            SleepHelper.sleepForSeconds(1);

            int previousWritersCount = webFollowingPage.getFollowedWritersCount();

            webFollowingPage.unfollowWriter();

            int currentWritersCount = webFollowingPage.getFollowedWritersCount();

            assertThat(currentWritersCount, equalTo(previousWritersCount - 1));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileArticlePage = new MobileArticlePage((AppiumDriver) platformDriver);
            mobileWriterPage = new MobileWriterPage((AppiumDriver) platformDriver);
            mobileFollowingPage = new MobileFollowingPage((AppiumDriver) platformDriver);

            mobileHomePage.openFirstHomeArticle();
            mobileArticlePage.goToWriterProfile();

            mobileWriterPage.followWriter();

            mobileHomePage.backToHomeFromArticleWriter();
            mobileHomePage.openFollowingPage();

            SleepHelper.sleepForSeconds(1);

            int previousWritersCount = mobileFollowingPage.getFollowedWritersCount();

            mobileFollowingPage.unfollowWriter();
            mobileHomePage.openFollowingPage();

            SleepHelper.sleepForSeconds(1);

            int currentWritersCount = mobileFollowingPage.getFollowedWritersCount();

            assertThat(currentWritersCount, equalTo(previousWritersCount - 1));
        }
    }

    @Test(dataProvider = "drivers")
    public void userDeletesAComment(String platform, RemoteWebDriver platformDriver) {
        createNewComment(platform, platformDriver);

        if (isCurrentPlatformWeb(platform)) {
            webArticlePage = new WebArticlePage(platformDriver);

            SleepHelper.sleepForSeconds(1);

            int previousCommentCount = webArticlePage.getReplyCommentCount();

            webArticlePage.deleteComment();

            SleepHelper.sleepForSeconds(1);

            int currentCommentCount = webArticlePage.getReplyCommentCount();

            assertThat(currentCommentCount, equalTo(previousCommentCount - 1));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileArticlePage = new MobileArticlePage((AppiumDriver) platformDriver);

            SleepHelper.sleepForSeconds(1);

            int previousCommentCount = mobileArticlePage.getReplyCommentCount();

            mobileArticlePage.deleteComment();

            SleepHelper.sleepForSeconds(1);

            int currentCommentCount = mobileArticlePage.getReplyCommentCount();

            assertThat(currentCommentCount, equalTo(previousCommentCount - 1));
        }
    }

    @Test(dataProvider = "drivers")
    public void userChecksRecentlyVisitedArticle(String platform, RemoteWebDriver platformDriver) {
        if (isCurrentPlatformWeb(platform)) {
            webHomePage = new WebHomePage(platformDriver);
            webFollowingPage = new WebFollowingPage(platformDriver);

            webHomePage.openPage();

            String actualVisitedArticle = webHomePage.getFirstArticleTitle();

            webHomePage.openFirstHomeArticle();
            SleepHelper.sleepForSeconds(2);

            webHomePage.openFollowingPage();
            webFollowingPage.clickReadingHistoryTab();

            String recentlyVisitedArticle = webFollowingPage.getReadingHistoryArticle();

            assertThat(recentlyVisitedArticle, equalTo(actualVisitedArticle));
        }

        if (isCurrentPlatformMobile(platform)) {
            mobileHomePage = new MobileHomePage((AppiumDriver) platformDriver);
            mobileListsPage = new MobileListsPage((AppiumDriver) platformDriver);

            String actualVisitedArticle = mobileHomePage.getFirstArticleTitle();

            mobileHomePage.openFirstHomeArticle();
            SleepHelper.sleepForSeconds(2);

            mobileHomePage.pressBack();
            mobileHomePage.goToListsPage();
            mobileHomePage.refreshPage();

            SleepHelper.sleepForSeconds(1);

            mobileListsPage.clickRecentlyViewedArticleTab();

            String recentlyVisitedArticle = mobileListsPage.getRecentlyViewedArticle();

            mobileListsPage.refreshPage();
            SleepHelper.sleepForSeconds(1);

            assertThat(recentlyVisitedArticle, equalTo(actualVisitedArticle));
        }
    }
}
