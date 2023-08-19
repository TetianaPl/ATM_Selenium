package seleniumWebdriver.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static seleniumWebdriver.steps.HighlightElement.highlightElement;

public class ShowElement {
    public static WebElement showElement(WebDriver driver, String product) {
        WebElement element = driver.findElement(By.xpath("//a[@data-id='" + product + "']"));
//In Firefox, JavascriptExecutor is the only way to scroll
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].scrollIntoView(false);", element);
        jsExec.executeScript("window.scrollTo(0, 200)");
//Why scrollTop += 20 does not work?
//        jsExec.executeScript("arguments[0].scrollTop += 20;", element);
        highlightElement(driver, element);
        return element;
    }
}
