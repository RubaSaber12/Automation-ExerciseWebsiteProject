package DriverFactory;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser) {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        switch (browser.toLowerCase()) {
            case "edge":
                EdgeOptions EdgeOptions = new EdgeOptions();
                EdgeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(EdgeOptions));
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
                break;

            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driverThreadLocal.set(new SafariDriver(safariOptions));
                break;

            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                break;
        }

    }


    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        getDriver().quit();
        driverThreadLocal.remove();
    }

    public static void closeTab() {
        getDriver().close(); // Closes only the current tab
    }


}
