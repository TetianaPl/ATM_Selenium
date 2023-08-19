package seleniumWebdriver.steps;

import org.openqa.selenium.WebDriver;
import seleniumWebdriver.rozetkaPages.ProductPage;
import seleniumWebdriver.rozetkaPages.ProductPageCharacteristicsTab;

public class VerifyOSAndProcessorMatchFilter {
    public static boolean verifyOSAndProcessorMatchFilter(WebDriver driver, String system, String processor1, String processor2) {
        ProductPage productPage = new ProductPage(driver);
        productPage.clickTab("characteristics");
        ProductPageCharacteristicsTab characteristicsTab = new ProductPageCharacteristicsTab(driver);
        return system.equals(characteristicsTab.getOS()) &&
                (characteristicsTab.getProcessor().contains(processor1) || characteristicsTab.getProcessor().contains(processor2));
    }
}
