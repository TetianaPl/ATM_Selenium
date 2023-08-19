package seleniumWebdriver.rozetkaPages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;

public class CatalogSectionPage extends PageObject {
    private JavascriptExecutor jsExec = (JavascriptExecutor) driver;
    private WebElement element = null;

    @FindBy(css = "h1.catalog-heading.ng-star-inserted")
    private WebElement sectionHeading;


    @FindBy(xpath = "//ul[@class='catalog-selection__list']")
    private WebElement catalogSelectionList;

    @FindBy(css = "button.catalog-selection__link.catalog-selection__link_type_reset")
    private WebElement deselectAllButton;

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

    public WebElement getCatalogSelectionList() {
        return catalogSelectionList;
    }

    public void clickOS(String system) {
        element = operationalSystems.findElement(By.xpath("//a[@data-id='" + system + "']"));
        jsExec.executeScript("arguments[0].click();", element);
    }

    public void scrollToOS(String system) {
        element = operationalSystems.findElement(By.xpath("//a[@data-id='" + system + "']"));
        jsExec.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickProcessor(String processor) {
        element = processors.findElement(By.xpath("//a[@data-id='" + processor + "']"));
//When I used just element.click() in Firefox, I got an error that this element is obscured by another one
        jsExec.executeScript("arguments[0].click();", element);
//Why it does not work?
//        jsExec.executeScript("document.querySelector('div[data-filter-name='processor'] a[data-id='" + processor + "']').click();");
    }

    public void scrollToProcessor(String processor) {
        element = processors.findElement(By.xpath("//a[@data-id='" + processor + "']"));
        jsExec.executeScript("arguments[0].scrollIntoView();", element);
    }

    public boolean verifyItemIsChecked(WebElement element) {
        return element.getAttribute("class").contains("--checked");
    }

    public void deselectItem(String item) {
        catalogSelectionList.findElement(By.xpath("//a[contains(text(), '" + item + "')]/span[@class='catalog-selection__remove-icon']")).click();
    }

    public void deselectAll() {
        Actions actions = new Actions(driver);
        actions.moveToElement(deselectAllButton).click().build().perform();
    }

    public ProductPage openCatalogItemInNewTab(int index) {
        element = catalogItems.findElement(By.xpath("li[" + index + "]"));
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(element).build().perform();
        return new ProductPage(driver);
    }
}


