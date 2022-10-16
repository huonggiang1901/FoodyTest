package listeners;


import common.helpers.Logger;
import driver.DriverManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Logger.fail(iTestResult.getThrowable().getLocalizedMessage());
            try {
                Logger.screenshot("Fail screenshot");
            } catch (Exception e) {
                // TODO: handle exception
            }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Logger.fail(iTestResult.getThrowable().getLocalizedMessage());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
