package seleniumWebdriver.rozetkaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;

public class HomePage extends PageObject {

    @FindBy(xpath = "//input[@name = 'search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='button button_color_green button_size_medium search-form__submit']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return searchField.isDisplayed();
    }

    public void enterSearchFor(String query) {
        searchField.clear();
        searchField.sendKeys(query);
    }

    public CatalogSectionPage clickSearch() {
        searchButton.click();
        return new CatalogSectionPage(driver);
    }
}
