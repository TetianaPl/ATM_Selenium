package seleniumWebdriver.rozetkaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.testng.Assert.*;

public class RozetkaTest extends TestSetup {
    //    private String baseUrl = "https://rozetka.com.ua/ua/";
    private HomePage homePage;
    private CatalogSectionPage catalogSectionPage;
    private ProductPage productPage;
    private ProductPageCharacteristicsTab characteristicsTab;
    private Wait<WebDriver> wait;
    private Actions actions;

    @BeforeClass
    public void launchBrowser_CreatePage() {
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        actions = new Actions(driver);
    }

    @DataProvider(name = "checkSearchData")
    public static Object[][] checkSearchData() {
        return new Object[][]{{"https://rozetka.com.ua/ua/", "Ноутбук"}};
    }

    @Test(priority = 1, dataProvider = "checkSearchData")
    public void checkSearch(String baseUrl, String product) {
        driver.get(baseUrl);
        homePage = new HomePage(driver);
        assertTrue(homePage.isInitialized());

        homePage.enterSearchFor(product);
        catalogSectionPage = homePage.clickSearch();
        wait.until(d -> catalogSectionPage.isInitialized());

        assertEquals(URLDecoder.decode(driver.getCurrentUrl(), StandardCharsets.UTF_8), "https://rozetka.com.ua/ua/notebooks/c80004/#search_text=ноутбук");
        assertEquals(catalogSectionPage.viewHeading(), "Ноутбуки");
    }

    @DataProvider(name = "checkBrandSelectionData")
    public static Object[][] checkBrandSelectionData() {
        return new Object[][]{{"https://rozetka.com.ua/ua/notebooks/c80004/#search_text=ноутбук", "Panasonic"}};
    }

    @Test(priority = 2, dataProvider = "checkBrandSelectionData")
    public void checkBrandSelection(String baseUrl, String product) {
        driver.get(baseUrl);
        catalogSectionPage = new CatalogSectionPage(driver);
        assertTrue(catalogSectionPage.isInitialized());

        catalogSectionPage.clickBrand(product);

        assertTrue(driver.findElement(By.xpath("//div[@class='catalog-selection ng-star-inserted']//a[contains(@href, '" + product.toLowerCase() + "')]")).isDisplayed());
    }

    @DataProvider(name = "checkBrandDeselectionData")
    public static Object[][] checkBrandDeselectionData() {
        return new Object[][]{{"https://rozetka.com.ua/ua/notebooks/c80004/producer=panasonic/", "Panasonic"}};
    }

    @Test(priority = 3, dataProvider = "checkBrandDeselectionData")
    public void checkBrandDeselection(String baseUrl, String product) {
        driver.get(baseUrl);
        catalogSectionPage = new CatalogSectionPage(driver);
        assertTrue(catalogSectionPage.isInitialized());

        driver.findElement(By.xpath("//a[contains(text(), '" + product + "')]/span[@class='catalog-selection__remove-icon']")).click();
        wait.until(stalenessOf(driver.findElement(By.xpath("//rz-filter-section-autocomplete//ul"))));

        assertFalse(driver.findElement(By.xpath("//rz-filter-section-autocomplete//a[@data-id='" + product + "']")).getAttribute("class").contains("--checked"));
    }

    @DataProvider(name = "checkOSAndProcessorSelectionData")
    public Object[][] checkOSAndProcessorSelectionData() {
        return new Object[][]{{"https://rozetka.com.ua/ua/notebooks/c80004/", "Linux", "Intel Core i7", "Intel Pentium"}};
    }

    @Test(priority = 4, dataProvider = "checkOSAndProcessorSelectionData")
    public void checkOSAndProcessorSelection(String baseUrl, String system, String processor1, String processor2) {
        driver.get(baseUrl);
        catalogSectionPage = new CatalogSectionPage(driver);
        assertTrue(catalogSectionPage.isInitialized());

        selectOSAnd2Processors(system, processor1, processor2);

        driver.findElement(By.cssSelector("button.catalog-selection__link.catalog-selection__link_type_reset")).click();
    }

    @Test(priority = 5, dataProvider = "checkOSAndProcessorSelectionData")
    public void checkOSAndProcessorInCharacteristicsTab(String baseUrl, String system, String processor1, String processor2) {
        driver.get(baseUrl);
        catalogSectionPage = new CatalogSectionPage(driver);
        assertTrue(catalogSectionPage.isInitialized());

        selectOSAnd2Processors(system, processor1, processor2);

        String originalWindow = driver.getWindowHandle();

        WebElement catalogItem = driver.findElement(By.cssSelector("rz-grid.ng-star-inserted > ul > li:nth-child(1)"));
        actions.keyDown(org.openqa.selenium.Keys.CONTROL)
                .keyDown(Keys.SHIFT)
                .click(catalogItem).perform();
        wait.until(numberOfWindowsToBe(2));

        productPage = catalogSectionPage.clickCatalogItem(2);
        wait.until(numberOfWindowsToBe(3));

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                verifyOSAndProcessor(system, processor1, processor2);
            }
        }
    }

    private CatalogSectionPage selectOSAnd2Processors(String system, String processor1, String processor2) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-filter-name='processor']//a[@data-id='" + processor2 + "']"))).click();
        assertTrue(catalogSectionPage.isInitialized());
        assertTrue(driver.findElement(By.xpath("//div[@class='catalog-selection ng-star-inserted']//a[contains(text(), '" + processor2 + "')]")).isDisplayed());


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-filter-name='processor']//a[@data-id='" + processor1 + "']"))).click();
        assertTrue(catalogSectionPage.isInitialized());
        assertTrue(driver.findElement(By.xpath("//div[@class='catalog-selection ng-star-inserted']//a[contains(text(), '" + processor1 + "')]")).isDisplayed());

        catalogSectionPage = catalogSectionPage.clickOS(system);
        assertTrue(catalogSectionPage.isInitialized());
        assertTrue(driver.findElement(By.xpath("//div[@class='catalog-selection ng-star-inserted']//a[contains(text(), '" + system + "')]")).isDisplayed());

        return catalogSectionPage;
    }

    private void verifyOSAndProcessor(String system, String processor1, String processor2) {
        String characteristicsTabLink = driver.findElement(By.xpath("//ul[@class='tabs__list']/li[2]/a")).getAttribute("href");
        driver.get(characteristicsTabLink);
        characteristicsTab = new ProductPageCharacteristicsTab(driver);
        assertEquals(system, characteristicsTab.getOS());
        assertTrue(characteristicsTab.getProcessor().contains(processor1) || characteristicsTab.getProcessor().contains(processor2));
    }

}
