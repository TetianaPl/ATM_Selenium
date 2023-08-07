package seleniumWebdriver.gmailPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumWebdriver.TestSetup;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class SignInEmailGmailPageTest extends TestSetup {
    private String baseUrl = "https://accounts.google.com/";
    private SignInEmailGmailPage signInEmailGmailPage;
    private SignInPasswordGmailPage signInPasswordGmailPage;
    private WebDriverWait wait;

    @BeforeClass
    public void launchBrowser_CreatePage() {
        driver.get(baseUrl);
        signInEmailGmailPage = new SignInEmailGmailPage(driver);
        assertTrue(signInEmailGmailPage.isInitialized());
    }

    @Test
    public void checkEmailFilling() {
        driver.manage().deleteAllCookies();
        signInEmailGmailPage.enterEmail("234");
        signInEmailGmailPage.clickNext();
    }

}
