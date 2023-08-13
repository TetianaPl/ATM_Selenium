package seleniumWebdriver.rozetkaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;

public class ProductPageCharacteristicsTab extends PageObject {

    @FindBy(xpath = "//a[contains(@href, '/20886')]")
    private WebElement operationalSystem;

    @FindBy(xpath = "//a[contains(@href, '/processor')]")
    private WebElement processor;

    public ProductPageCharacteristicsTab(WebDriver driver) {
        super(driver);
    }

    public String getOS() {
        return operationalSystem.getText();
    }

    public String getProcessor() {
        return processor.getText();
    }
}
