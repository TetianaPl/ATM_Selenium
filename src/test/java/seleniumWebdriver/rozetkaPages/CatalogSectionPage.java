package seleniumWebdriver.rozetkaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;

public class CatalogSectionPage extends PageObject {

    @FindBy(css = "h1.catalog-heading.ng-star-inserted")
    private WebElement sectionHeading;

    @FindBy(xpath = "//div[@data-filter-name='producer']//input[@name='searchline']")
    private WebElement producerSearch;

    @FindBy(xpath = "//div[@data-filter-name='producer']")
    private WebElement brands;

    @FindBy(xpath = "//div[@data-filter-name='processor']")
    private WebElement processors;

    @FindBy(xpath = "//div[@data-filter-name='20886']")
    private WebElement operationalSystems;

    @FindBy(xpath = "//rz-grid/ul")
    private WebElement catalogItems;

    public CatalogSectionPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return sectionHeading.isDisplayed();
    }

    public String viewHeading() {
        return sectionHeading.getText();
    }

    public void enterProducer(String producer) {
        producerSearch.sendKeys(producer);
    }

    public CatalogSectionPage clickBrand(String producer) {
        enterProducer(producer);
        brands.findElement(By.xpath("//rz-filter-section-autocomplete//ul[2]//a[@data-id='" + producer + "']")).click();
        return new CatalogSectionPage(driver);
    }

    public CatalogSectionPage clickOS(String system) {
        operationalSystems.findElement(By.xpath("//a[@data-id='" + system + "']")).click();
        return new CatalogSectionPage(driver);
    }

    public CatalogSectionPage clickProcessor(String processor) {
        processors.findElement(By.xpath("//a[@data-id='" + processor + "']")).click();
        return new CatalogSectionPage(driver);
    }

    public ProductPage clickCatalogItem(int index) {
        catalogItems.findElement(By.xpath("li[" + index + "]")).click();
        return new ProductPage(driver);
    }
}


