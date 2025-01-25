package Listeners;

import Utilities.LogsUtils;
import org.openqa.selenium.bidi.log.Log;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestResultListenerClass implements ITestListener {
    public void onTestStart(ITestResult result) {
        LogsUtils.info("Test Case" + result.getName() + "Started");
    }

    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test Case" + result.getName() + "Passed");
    }
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test Case" + result.getName() + "Skipped");

    }

}
