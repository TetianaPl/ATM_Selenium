package seleniumWebdriver.rozetkaPages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;

public class ProductPage extends PageObject {
//    private Actions actions;
    @FindBy(xpath = "//ul[@class='tabs__list']")
    private WebElement navigationBar;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return navigationBar.isDisplayed();
    }

    public void clickTab(String tabName) {
        WebElement element = navigationBar.findElement(By.xpath("//a[contains(@href, '" + tabName + "')]"));
//Opens new tab in chrome
//        element.click();
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].click();", element);
    }
}
