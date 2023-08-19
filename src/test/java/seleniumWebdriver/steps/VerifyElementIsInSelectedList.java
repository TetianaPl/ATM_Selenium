package seleniumWebdriver.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyElementIsInSelectedList {
    public static boolean verifyElementIsInSelectedList(WebDriver driver, String elementName) {
        return driver.findElement(By.xpath("//div[@class='catalog-selection ng-star-inserted']//a[contains(text(), '" + elementName + "')]")).isDisplayed();
    }
}
