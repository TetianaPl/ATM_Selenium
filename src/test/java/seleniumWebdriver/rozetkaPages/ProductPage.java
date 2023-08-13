package seleniumWebdriver.rozetkaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;

public class ProductPage extends PageObject {

    @FindBy(className = "tabs__list")
    private WebElement navigationBar;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return navigationBar.isDisplayed();
    }

    public PageObject clickTab(int tabNumber) {
        navigationBar.findElement(By.xpath("li[" + tabNumber + "]")).click();
        return new PageObject(driver);
    }

}
