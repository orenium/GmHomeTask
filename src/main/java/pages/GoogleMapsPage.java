package pages;

import il.co.topq.difido.model.Enums;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.TestRunner.*;

public class GoogleMapsPage extends BaseWebPage {

    @FindBy(xpath = "//input[@aria-label='חיפוש במפות Google']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@id='widget-zoom-in']")
    private WebElement zoomBtn;

    public GoogleMapsPage(WebDriver driver) {
        super(driver);
    }

    public void search(String searchTerm) {

        try {
            wait.until(ExpectedConditions.visibilityOf(searchField)).click();
            searchField.sendKeys(searchTerm);
            searchField.sendKeys(Keys.ENTER);
            takeScreenShot(driver);
        } catch (Exception ex) {
            report.log("GoogleMapsPage.search: "+ ex.getMessage(), Enums.Status.error);
        }
    }

    public void zoomInMap() {
        try {
            wait.until(ExpectedConditions.visibilityOf(zoomBtn)).click();
        } catch (Exception ex) {
            printToLog("GoogleMapsPage.zoomInMap: " + ex.getMessage());
        }
        takeScreenShot(driver);
    }
}
