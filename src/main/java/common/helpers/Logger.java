package common.helpers;

import driver.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import org.apache.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class Logger {
    private static final org.apache.log4j.Logger log = LogManager.getLogger(Logger.class.getName());

    public static void info(String message) {
        log.info(message);
    }

    public static void step(String message){
        log.info(message);
        Allure.step(message);
    }

    public static void verify(String message) {
        message = "VERIFY POINT: " + message;
        log.info(message);
    }

    public static void warning(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void pass(String message) {
        message = "PASSED: " + message;
        Allure.step(message, Status.PASSED);
    }

    public static void fail(String message) {
        message = "FAILED: " + message;
        Allure.step(message, Status.FAILED);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static void screenshot(String screenshotFileName) {
        ByteArrayInputStream screenshot = new ByteArrayInputStream(
                ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment(DriverManager.getDriverName() + ": " + screenshotFileName, screenshot);
    }
}
