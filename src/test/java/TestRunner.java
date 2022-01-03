import com.github.ubaifadhli.pages.medium.web.WebArticlePage;
import com.github.ubaifadhli.pages.medium.web.WebCreateArticlePage;
import com.github.ubaifadhli.pages.medium.web.WebHomePage;
import com.github.ubaifadhli.pages.medium.web.WebLoginPage;
import com.github.ubaifadhli.util.DriverFactory;
import com.github.ubaifadhli.util.PropertiesReader;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRunner {
    private WebHomePage webHomePage;
    private WebLoginPage webLoginPage;
    private WebCreateArticlePage webCreateArticlePage;
    private WebArticlePage webArticlePage;

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

        }
    }
}
