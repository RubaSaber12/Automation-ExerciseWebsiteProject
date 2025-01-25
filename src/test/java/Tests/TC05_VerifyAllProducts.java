package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P05_ProductsPage;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

public class TC05_VerifyAllProducts {
    public String ProductsURL = getPropertyValue("environments", "ProductsURL");

    public TC05_VerifyAllProducts() throws IOException {
    }

    @BeforeMethod
    public void Setup() throws IOException {
        setupDriver(getPropertyValue("environments", "Browser"));
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("environments", "BaseURL"));
        LogsUtils.info("Page is redirected to the URL");
        Utility.generalWait(getDriver());
    }

    @Test
    public void VerifyProductsPage() {
        new P05_ProductsPage(getDriver())
                .ClickOnProductButton()
                .VerifyTheProductsPageURL(ProductsURL)
                .verifyAllProductExist()
                .verifyAllProductsAppears()
                .viewProductDetails()
                .VerifyProductDetailsIsExist();
    }

    @AfterMethod
    public void Quit() {
        quitDriver();
    }

}
