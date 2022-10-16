package driver;

import common.BrowserList;
import common.Constants;
import common.helpers.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static HashMap<String, RemoteWebDriver> driverMap = new HashMap<>();
    private static ThreadLocal<String> driverKey = new ThreadLocal<>();

    private static void set(String envName, RemoteWebDriver driver) {
        driverKey.set(Thread.currentThread().getId() + "." + envName);
        driverMap.put(driverKey.get(), driver);
    }

    public static void switchDriver(String envName) {
        driverKey.set(Thread.currentThread().getId() + "." + envName);
    }

    public static String getDriverName(){
        return driverKey.get().replaceAll("[0-9]", "").replace(".", "");
    }

    public static RemoteWebDriver getDriver() {
        return driverMap.get(driverKey.get());
    }

    public static void initDriver(BrowserList browser, String runMode) {
        try {
            Logger.info("Start " + browser + " in " + runMode);
            set(browser.name(), getBrowserDriver(runMode));
        } catch (MalformedURLException e) {
            Logger.error("Cannot start " + browser + " in " + runMode);
            e.printStackTrace();
        }
    }

    private static RemoteWebDriver getBrowserDriver(String runMode) throws MalformedURLException {
        RemoteWebDriver driver;
        if (runMode.equalsIgnoreCase("remote")) {
            WebDriverManager.chromedriver().setup();
            DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(new URL(Constants.HUB_GRID), chromeCapabilities);
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("incognito");
            driver = new ChromeDriver(option);
        }
        return driver;
    }

    public static void openToPage(String url) {
        Logger.info("Open page: " + url);
        getDriver().get(url);
        implicitlyWait();
    }

    public static void quit() {
        getDriver().quit();
    }

    private static void implicitlyWait(){
        getDriver().manage().timeouts().implicitlyWait(Constants.TIMEOUT, TimeUnit.SECONDS);
    }

    public static void maximizeBrowser() {
        try {
            Logger.info("Maximize browser");
            getDriver().manage().window().maximize();
        } catch (Exception e) {
            Logger.error("Cannot maximize browser " + e.getMessage());
        }
    }

    public static WebElement findElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> findElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static void waitForPageLoad(){
        try {
            Logger.info("Wait for page load complete");
            WebDriverWait explicitWait = new WebDriverWait(getDriver(), Constants.TIMEOUT);
            ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                            .equals("complete");
                }
            };
            explicitWait.until(jsLoad);
        } catch (Exception e) {
            Logger.error("Cannot load page");
            e.printStackTrace();
        }
    }

    public static Object execJavaScript(String script, Object... obj) {
        return ((JavascriptExecutor) getDriver()).executeScript(script, obj);
    }

    public static void sleepInSecond(int seconds) {
        try {
            Logger.info("Sleep in second: " + seconds);
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Logger.error("Cannot sleep in second: " + seconds);
            e.printStackTrace();
        }
    }

    public static void switchToWindowByID(String parentWindow) {
        Set<String> allWindows = getDriver().getWindowHandles();
        for (String childWindow : allWindows) {
            if (!childWindow.equals(parentWindow))
                getDriver().switchTo().window(childWindow);
        }
    }

    public static String getParentWindowID(){
        return getDriver().getWindowHandle();
    }

}