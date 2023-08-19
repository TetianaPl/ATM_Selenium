package seleniumWebdriver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import static seleniumWebdriver.steps.SetDriver.*;

public class TestSetupGrid {
    protected WebDriver driver;

    @BeforeClass
    @Parameters({"myBrowser", "myPlatform", "remoteDriverURL"})
    public void setUpGrid(String myBrowser, String myPlatform, String remoteDriverURL) {
        switch (myBrowser) {
            case "chrome":
                driver = setRemoteDriverChrome(myPlatform, remoteDriverURL);
                break;
            case "firefox":
                driver = setRemoteDriverFirefox(myPlatform, remoteDriverURL);
                break;
            case "MicrosoftEdge":
                driver = setRemoteDriverEdge(myPlatform, remoteDriverURL);
                break;
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }
}
