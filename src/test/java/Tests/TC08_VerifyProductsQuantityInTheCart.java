package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P08_AddingRandomProducts;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

public class TC08_VerifyProductsQuantityInTheCart {
    SoftAssert soft = new SoftAssert();
    private String productsQuantityStr;
    private int productsQuantity;

    @BeforeMethod
    public void Setup() throws IOException {
        Faker faker = new Faker();
        productsQuantity = faker.number().numberBetween(30, 30);
        productsQuantityStr = String.valueOf(productsQuantity);
        setupDriver(getPropertyValue("environments", "Browser"));
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("environments", "BaseURL"));
        LogsUtils.info("Page is redirected to the URL");
        Utility.generalWait(getDriver());
    }

    @Test
    public void VerifyProductsQuantityInTheCart() {
        new P08_AddingRandomProducts(getDriver())
                .OpenProductsPage()
                .AddingProductsOnCart(productsQuantityStr);
        soft.assertEquals(new P08_AddingRandomProducts(getDriver()).GetQuantity(), productsQuantity);
        soft.assertAll();
    }

    @AfterMethod
    public void Quit() {
        quitDriver();
    }


}
