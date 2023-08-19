package seleniumWebdriver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static seleniumWebdriver.steps.SetDriver.*;

public class TestSetup {
    protected static WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = setDriverChrome();
//        driver = setDriverFirefox();
//        driver = setDriverEdge();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
