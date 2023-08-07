package seleniumWebdriver.ukrNetPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumWebdriver.PageObject;


public class SignInUkrNetPage extends PageObject {



    @FindBy(name = "login")
    private WebElement name;
    @FindBy(name = "password")
    private WebElement password;

    //    @FindBy(xpath = "//span[text()='Next']")
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;


    public SignInUkrNetPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return name.isDisplayed();
    }


    public void enterName(String userName) {
        name.clear();
        name.sendKeys(userName);
    }
    public void enterPassword(String userPassword) {
        password.clear();
        password.sendKeys(userPassword);
    }
    public PageObject clickSubmit() {
        submitButton.click();
        return new PageObject(driver);
    }
}
