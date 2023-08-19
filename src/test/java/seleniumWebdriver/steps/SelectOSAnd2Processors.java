package seleniumWebdriver.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumWebdriver.rozetkaPages.CatalogSectionPage;

import java.time.Duration;

import static seleniumWebdriver.steps.VerifyElementIsInSelectedList.verifyElementIsInSelectedList;

public class SelectOSAnd2Processors {
    public static boolean selectOSAnd2Processors(WebDriver driver, String system, String processor1, String processor2) {
        CatalogSectionPage catalogSectionPage = new CatalogSectionPage(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));

//        catalogSectionPage.scrollToOS(system);
//Why does not work?
//        showElement(driver, system);
        catalogSectionPage.clickOS(system);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//rz-selected-filters//ul/li"), 2));

//        catalogSectionPage.scrollToProcessor(processor1);
        catalogSectionPage.clickProcessor(processor1);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//rz-selected-filters//ul/li"), 3));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        catalogSectionPage.scrollToProcessor(processor2);
        catalogSectionPage.clickProcessor(processor2);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//rz-selected-filters//ul/li"), 4));

        return verifyElementIsInSelectedList(driver, system)
                && verifyElementIsInSelectedList(driver, processor1)
                && verifyElementIsInSelectedList(driver, processor2);
    }
}
