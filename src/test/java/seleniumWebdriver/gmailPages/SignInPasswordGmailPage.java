package seleniumWebdriver.gmailPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;


public class SignInPasswordGmailPage extends PageObject {

    @FindBy(xpath = "//*[@data-profile-identifier]")
    private WebElement profile;

    @FindBy(xpath = "//*[@id='password']/div[1]/div/div[1]/input")
    private WebElement password;

    @FindBy(id = "passwordNext")
    private WebElement submit;

    public SignInPasswordGmailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return true;
    }


    public void enterPassword(String userPassword) {
        password.clear();
        password.sendKeys(userPassword);
    }

    public PageObject clickNext() {
        submit.click();
        return new PageObject(driver);
    }
}
