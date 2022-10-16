package tests;

import common.BrowserList;
import common.Constants;
import driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBase {

    @Parameters({"browser", "runMode"})
    @BeforeMethod
    public void beforeTest(BrowserList browser, String runMode) {
        DriverManager.initDriver(browser, runMode);
        DriverManager.openToPage(Constants.FOODY_URL);
        DriverManager.maximizeBrowser();
    }

    @AfterMethod
    public void afterTest() {
//        DriverManager.quit();
    }

}
