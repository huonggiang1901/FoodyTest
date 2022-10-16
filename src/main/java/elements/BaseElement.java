package elements;

import common.Constants;
import common.helpers.Logger;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static driver.DriverManager.getDriver;

public class BaseElement {
    private By byLocator;
    private String xpath;

    public BaseElement(String xpath) {
        this.byLocator = By.xpath(xpath);
        this.xpath = xpath;
    }

    public String getXpath() {
        return xpath;
    }

    public WebElement getElement() {
        return DriverManager.findElement(this.byLocator);
    }

    public List<WebElement> getElements() {
        return DriverManager.findElements(this.byLocator);
    }

    public By setDynamicValue(Object... str) {
        String newXpath = String.format(this.xpath, str);
        this.xpath = newXpath;
        return this.byLocator = By.xpath(newXpath);
    }

    public boolean isDisplayed() {
        try {
            Logger.info("Check " + this.byLocator + " is displayed.");
            return getElement().isDisplayed();
        } catch (Exception e) {
            Logger.error(this.byLocator + " is not displayed.");
            return false;
        }
    }

    public String getText() {
        try {
            Logger.info("Get text of element: "+ this.xpath);
            return getElement().getText();
        } catch (Exception e) {
            Logger.warning("Cannot get text of element: " + this.xpath);
            return null;
        }
    }

    public static String getWebElementText(WebElement element) {
        return element.getText();
    }

    public void click() {
        try {
            Logger.info("Click on  " + this.byLocator);
            getElement().click();
        } catch (Exception e) {
            Logger.error("Cannot click on element " + this.byLocator + " with error: " + e.getMessage());
        }
    }

    public static void clickElement(WebElement element){
        try {
            Logger.info("Click on element: " + element);
            element.click();
        } catch (Exception e) {
            Logger.error("Cannot click on element " + element + " with error: " + e.getMessage());
        }
    }

    public void waitForVisibility() {
        try {
            Logger.info("Wait for visibility of element: " + this.xpath);
            WebDriverWait wait = new WebDriverWait(getDriver(), Constants.TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.byLocator));
        } catch (Exception e) {
            Logger.error("Cannot wait for visibility of element: " + this.xpath);
            e.printStackTrace();
        }
    }

    public void waitForClickable(){
        try {
            Logger.info("Wait for clickable of element: " + this.xpath);
            new WebDriverWait(DriverManager.getDriver(), Constants.TIMEOUT)
                    .until(ExpectedConditions.elementToBeClickable(this.byLocator));
        }catch (Exception e) {
            Logger.error("Cannot wait for clickable of element: " + this.xpath);
            e.printStackTrace();
        }
    }

    public void waitForAllElementVisible() {
        try {
            Logger.info("Wait for visibility of all elements: " + this.xpath);
            new WebDriverWait(getDriver(), Constants.TIMEOUT)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.byLocator));
        } catch (Exception e) {
            Logger.error("Cannot wait for visibility of all elements: " + this.xpath);
            e.printStackTrace();
        }
    }

    public void waitForElementPresence(){
        try {
            Logger.info("Wait for all elements presence: " + this.xpath);
            new WebDriverWait(getDriver(), Constants.TIMEOUT)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(this.byLocator));
        } catch (Exception e) {
            Logger.error("Cannot wait for all elements presence: " + this.xpath);
            e.printStackTrace();
        }
    }

    public void moveMouse(){
        try{
            Logger.info("Hover mouse on element located at: " + this.byLocator);
            new Actions(DriverManager.getDriver()).moveToElement(getElement()).perform();
        }catch (Exception e){
            Logger.error("Cannot hover mouse on element " + this.byLocator + " with error: " + e.getMessage());
        }
    }

    public void scrollToBottom() {
        try {
            Logger.info("Scroll to element to bottom page" + this.byLocator);
            DriverManager.execJavaScript("arguments[0].scrollIntoView(false);", getElement());
        }catch (Exception e){
            Logger.error("Cannot scroll to element to bottom page" + this.byLocator + " with error: " + e.getMessage());
        }
    }

    public static void scrollToBottom(WebElement element) {
        DriverManager.execJavaScript("arguments[0].scrollIntoView(false);", element);
    }

    public String getAttribute(String attributeName) {
        try {
            Logger.info("Get attribute " + attributeName + " of " + this.xpath);
            return this.getElement().getAttribute(attributeName);
        } catch (Exception e) {
            Logger.warning(this.xpath + " does not have attribute " + attributeName);
            return null;
        }
    }

    public int getNumberOfElement(){
        return getElements().size();
    }

    public WebElement getElementByIndex(int index){
        return getElements().get(index);


    }

}
