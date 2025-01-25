package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P06_SearchingProduct;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

public class TC06_VerifySearchingProducts {
    public String ProductsURL = getPropertyValue("environments", "ProductsURL");
    SoftAssert soft = new SoftAssert();

    public TC06_VerifySearchingProducts() throws IOException {
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
    public void VerifySearchingProcess() {
        new P06_SearchingProduct(getDriver())
                .ClickOnProductButton()
                .VerifyTheProductsPageURL(ProductsURL)
                .SearchOnProduct("T-shirt")
                .PressSearchButton();
        soft.assertEquals(new P06_SearchingProduct(getDriver()).VerifyAllProductsRelatedToSearchAreVisible(), 3);
        soft.assertAll();

    }

    @AfterMethod
    public void Quit() {
        quitDriver();
    }

}
