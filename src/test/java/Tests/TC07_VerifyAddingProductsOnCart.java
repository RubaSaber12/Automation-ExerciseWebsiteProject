package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P07_AddProductToCart;
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

public class TC07_VerifyAddingProductsOnCart {
    SoftAssert soft = new SoftAssert();

    @BeforeMethod
    public void Setup() throws IOException {
        setupDriver(getPropertyValue("environments", "Browser"));
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("environments", "BaseURL"));
        LogsUtils.info("Page is redirected to the URL");
        Utility.generalWait(getDriver());
    }

    @Test
    public void VerifyAddingProductsToCart() {
        new P07_AddProductToCart(getDriver())
                .ClickOnProductsPage()
                .AddingProductsToCart()
                .GetFirstPrice();
        soft.assertEquals(new P07_AddProductToCart(getDriver()).GetFirstPrice(), 500);
        new P07_AddProductToCart(getDriver())
                .GetSecondPrice();
        soft.assertEquals(new P07_AddProductToCart(getDriver()).GetSecondPrice(), 1000);
        new P07_AddProductToCart(getDriver())
                .GetFirstQuantity();
        new P07_AddProductToCart(getDriver())
                .GetSecondQuantity();
        new P07_AddProductToCart(getDriver())
                .calculateTotalPriceForFirstProduct();
        soft.assertEquals(new P07_AddProductToCart(getDriver()).calculateTotalPriceForFirstProduct(), 500);
        new P07_AddProductToCart(getDriver())
                .calculateTotalPriceForSecondProduct();
        soft.assertEquals(new P07_AddProductToCart(getDriver()).calculateTotalPriceForSecondProduct(), 1000);
        soft.assertAll();

    }

    @AfterMethod
    public void Quit() {
        quitDriver();
    }

}
