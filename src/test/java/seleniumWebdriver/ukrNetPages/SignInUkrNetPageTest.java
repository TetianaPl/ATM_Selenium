package seleniumWebdriver.ukrNetPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumWebdriver.TestSetup;

public class SignInUkrNetPageTest extends TestSetup {
    private String baseUrl = "https://accounts.ukr.net/";
    private SignInUkrNetPage signInUkrNetPage;


    @BeforeClass
    public void launchBrowser_CreatePage() {
//        driver.get(baseUrl);
//        signInUkrNetPage = new SignInUkrNetPage(driver);
//        assertTrue(signInUkrNetPage.isInitialized());
    }

//
    @Test
    public void checkEmailFilling() {
//        driver.manage().deleteAllCookies();
//        signInUkrNetPage.enterName("test_qa123");
//        signInUkrNetPage.enterPassword("test_qa111");
//        signInUkrNetPage.clickSubmit();
//
//
        driver.get("https://www.ukr.net/");
        WebElement iframe = driver.findElement(By.cssSelector("[name=\"mail widget\"]"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.cssSelector("input[name=\"login\"]")).sendKeys("test_qa123");
        driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("test_qa111");
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    }

}
