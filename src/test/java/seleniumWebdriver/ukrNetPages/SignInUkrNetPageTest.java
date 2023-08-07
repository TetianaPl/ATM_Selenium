package seleniumWebdriver.ukrNetPages;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumWebdriver.TestSetup;
import seleniumWebdriver.gmailPages.SignInEmailGmailPage;
import seleniumWebdriver.gmailPages.SignInPasswordGmailPage;

import static org.testng.Assert.assertTrue;

public class SignInUkrNetPageTest extends TestSetup {
    private String baseUrl = "https://accounts.ukr.net/";
    private SignInUkrNetPage signInUkrNetPage;


    @BeforeClass
    public void launchBrowser_CreatePage() {
        driver.get(baseUrl);
        signInUkrNetPage = new SignInUkrNetPage(driver);
        assertTrue(signInUkrNetPage.isInitialized());
    }

    @Test
    public void checkEmailFilling() {
        driver.manage().deleteAllCookies();
        signInUkrNetPage.enterName("");
        signInUkrNetPage.enterPassword("");
        signInUkrNetPage.clickSubmit();


    }

}
