package seleniumWebdriver.rozetkaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumWebdriver.TestSetup;
import seleniumWebdriver.TestSetupGrid;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;

import static seleniumWebdriver.steps.SelectOSAnd2Processors.selectOSAnd2Processors;
import static seleniumWebdriver.steps.ShowElement.showElement;
import static seleniumWebdriver.steps.VerifyElementIsInSelectedList.verifyElementIsInSelectedList;
import static seleniumWebdriver.steps.VerifyOSAndProcessorMatchFilter.verifyOSAndProcessorMatchFilter;

public class RozetkaTest extends TestSetup {
//    public class RozetkaTest extends TestSetupGrid {
    private HomePage homePage;
    private CatalogSectionPage catalogSectionPage;
    private ProductPage productPage;
    public Wait<WebDriver> wait;
    private Actions actions;

    @BeforeClass
    public void testPreparing() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    @DataProvider(name = "testSearchData")
    public static Object[][] testSearchData() {
        return new Object[][]{{"https://rozetka.com.ua/ua/", "Ноутбук"}};
    }

    @Test(priority = 1, dataProvider = "testSearchData")
    public void testSearch(String baseUrl, String product) {
        driver.get(baseUrl);
        homePage = new HomePage(driver);
//        assertTrue(homePage.isInitialized());

        homePage.enterSearchFor(product);
        catalogSectionPage = homePage.clickSearch();
        wait.until(d -> catalogSectionPage.isInitialized());

        assertEquals(URLDecoder.decode(driver.getCurrentUrl(), StandardCharsets.UTF_8), "https://rozetka.com.ua/ua/notebooks/c80004/#search_text=ноутбук");
        assertEquals(catalogSectionPage.viewHeading(), "Ноутбуки");
    }

    @DataProvider(name = "testBrandSelectionData")
    public static Object[][] testBrandSelectionData() {
        return new Object[][]{{"https://rozetka.com.ua/ua/notebooks/c80004/#search_text=ноутбук", "Panasonic"}};
    }

    @Test(priority = 2, dataProvider = "testBrandSelectionData")
    public void testBrandSelection(String baseUrl, String product) {
        driver.get(baseUrl);
        catalogSectionPage = new CatalogSectionPage(driver);
//        assertTrue(catalogSectionPage.isInitialized());

        catalogSectionPage.clickBrand(product);
        wait.until(ExpectedConditions.visibilityOf(catalogSectionPage.getCatalogSelectionList()));

        assertTrue(verifyElementIsInSelectedList(driver, product));
    }

    @DataProvider(name = "testBrandDeselectionData")
    public static Object[][] testBrandDeselectionData() {
        return new Object[][]{{"https://rozetka.com.ua/ua/notebooks/c80004/producer=panasonic/", "Panasonic"}};
    }

    @Test(priority = 3, dataProvider = "testBrandDeselectionData")
    public void testBrandDeselection(String baseUrl, String product) {
        driver.get(baseUrl);
        catalogSectionPage = new CatalogSectionPage(driver);

        catalogSectionPage.deselectItem(product);

        wait.until(invisibilityOf(catalogSectionPage.getCatalogSelectionList()));

        WebElement element = showElement(driver, product);
        assertFalse(catalogSectionPage.verifyItemIsChecked(element));
    }

    @DataProvider(name = "testOSAndProcessorSelectionData")
    public Object[][] testOSAndProcessorSelectionData() {
        return new Object[][]{{"https://rozetka.com.ua/ua/notebooks/c80004/", "Linux", "Intel Core i7", "Intel Pentium"}};
    }

    @Test(priority = 4, dataProvider = "testOSAndProcessorSelectionData")
    public void testOSAndProcessorSelection(String baseUrl, String system, String processor1, String processor2) {
        driver.get(baseUrl);
        catalogSectionPage = new CatalogSectionPage(driver);
        assertTrue(catalogSectionPage.isInitialized());

        assertTrue(selectOSAnd2Processors(driver, system, processor1, processor2));

        catalogSectionPage.deselectAll();
    }

    @Test(priority = 5, dataProvider = "testOSAndProcessorSelectionData")
    public void testOSAndProcessorInCharacteristicsTab(String baseUrl, String system, String processor1, String processor2) {
        driver.get(baseUrl);
        catalogSectionPage = new CatalogSectionPage(driver);
//        assertTrue(catalogSectionPage.isInitialized());

        assertTrue(selectOSAnd2Processors(driver, system, processor1, processor2));

        String originalWindow = driver.getWindowHandle();

        productPage = catalogSectionPage.openCatalogItemInNewTab(1);
        wait.until(numberOfWindowsToBe(2));

        productPage = catalogSectionPage.openCatalogItemInNewTab(2);
        wait.until(numberOfWindowsToBe(3));


        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                assertTrue(verifyOSAndProcessorMatchFilter(driver, system, processor1, processor2));
            }
        }
    }
}
