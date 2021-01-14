import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import static utils.TestRunner.*;

@Listeners(il.co.topq.difido.ReportManagerHook.class)
public class BaseTest implements Action {

    public WebDriver driver = null;

    @BeforeSuite
    public void setup() {
            driver = getNewChromeWebDriver();
    }

    private WebDriver getNewChromeWebDriver() {
        printToLog("Setting new ChromeWebDriver...");
        WebDriverManager.chromedriver().setup();
        WebDriver WebDriver = new ChromeDriver();
        return WebDriver;
    }


    @AfterClass
    public void afterClass() {
        if (driver != null) {
            takeScreenShot(driver);
        }
    }

    @AfterSuite
    public void afterSuite() {
        openHtmlReportFile(true, driver);
    }

    public void perform() {

    }

    public void openURL(String url) {
        if (driver == null) {
            driver = getNewChromeWebDriver();
        }
        driver.get(url);
        printToLog("Opening Url: " + url);
    }
}
