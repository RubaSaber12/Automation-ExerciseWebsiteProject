package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import static DriverFactory.DriverFactory.getDriver;
import static Utilities.WaitsUtils.explicitlyWaitForVisibility;

public class Utility {
    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";

    // ToDo: Clicking on Element Function
    public static void ClickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    // ToDo: Sending data Function
    public static void SendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).sendKeys(data);

    }


    //ToDo: Get Text
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    //ToDo: General Wait
    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //ToDo: Scrolling
    public static void scrollin
    (WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    //ToDo:Convert Locator to Web Element
    public static WebElement findWebElement(WebDriver driver, By locator) {

        return driver.findElement(locator);
    }

    //ToDo: Set time Stamp
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    //ToDo: Taking ScreenShot
    public static void takeScreenShot(WebDriver driver, String screenshotName) {
        try {
            //Capture Screenshot using Take ScreenShot
            File screenshotsrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //Save Screenshot to files
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotsrc, screenshotFile);

            //Attach the ScreenShot To Allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ToDo: Select from DropDown
    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);

    }

    // ToDo: Handle Checkbox
    public static void handleCheckbox(WebDriver driver, By locator, boolean check) {
        WebElement checkbox = findWebElement(driver, locator);
        if (checkbox.isSelected() != check) {
            checkbox.click();
        }
    }


    public static void TakeFullScreenShot(WebDriver driver, String screenshotName) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL).save(SCREENSHOTS_PATH);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    //TODO:  Scroll to specific element
    public static void scrollToElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) (driver)).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", findWebElement(driver, locator));
    }

    //ToDo:Checking exist data.
    public static boolean verifyEquals(By locator, String expectedText) {
        return getText(getDriver(), locator).equals(expectedText);
    }

    //ToDo:Checking exist URL
    public static boolean verifyURLEquals(String expectedURL) {
        String currentURL = getDriver().getCurrentUrl();
        return currentURL.equals(expectedURL);
    }

    public static boolean verifyElementVisible(By locator) {
        try {
            explicitlyWaitForVisibility(getDriver(), locator);
            LogsUtils.info("Verify element visible " + locator);
            return true;
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }
    }

    //ToDo:DoubleClickOnElement
    public static void doubleClickOnElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver).doubleClick(element).perform();
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];

    }

}

