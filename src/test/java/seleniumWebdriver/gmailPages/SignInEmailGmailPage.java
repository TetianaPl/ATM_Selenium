package seleniumWebdriver.gmailPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;

import static org.testng.Assert.assertEquals;


public class SignInEmailGmailPage extends PageObject {



    @FindBy(id = "identifierId")
    private WebElement email;

    //    @FindBy(xpath = "//span[text()='Next']")
    @FindBy(xpath = "//*[@id='identifierNext']/div/button")
    private WebElement nextButton;


    public SignInEmailGmailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return email.isDisplayed();
    }


    public void enterEmail(String userEmail) {
        email.clear();
        email.sendKeys(userEmail);
    }

    public PageObject clickNext() {
        nextButton.click();
        return new PageObject(driver);
    }
}
