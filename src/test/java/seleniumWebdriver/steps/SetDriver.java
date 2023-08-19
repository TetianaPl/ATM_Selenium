package seleniumWebdriver.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SetDriver {
    public static WebDriver setDriverChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        return new ChromeDriver(chromeOptions);
    }

    public static WebDriver setDriverFirefox() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        return new FirefoxDriver(firefoxOptions);
    }

    public static WebDriver setDriverEdge() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--disable-notifications");
        return new EdgeDriver(edgeOptions);
    }

    public static WebDriver setRemoteDriverChrome(String myPlatform, String remoteDriverURL) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPlatformName(myPlatform);
        chromeOptions.addArguments("--disable-notifications");
        try {
            return new RemoteWebDriver(new URL(remoteDriverURL), chromeOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WebDriver setRemoteDriverFirefox(String myPlatform, String remoteDriverURL) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPlatformName(myPlatform);
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        try {
            return new RemoteWebDriver(new URL(remoteDriverURL), firefoxOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WebDriver setRemoteDriverEdge(String myPlatform, String remoteDriverURL) {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPlatformName(myPlatform);
        edgeOptions.addArguments("--disable-notifications");
        try {
            return new RemoteWebDriver(new URL(remoteDriverURL), edgeOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
