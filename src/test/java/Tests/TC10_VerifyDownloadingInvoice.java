package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P10_DownloadInvoice;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Year;
import java.util.Random;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

public class TC10_VerifyDownloadingInvoice {
    private final String SignUpEmailAddress = getJsonData("SignUpData", "EmailAdress");
    private final String PasswordAccountInfo = getJsonData("FillingAccountInfo", "Password");
    // Initialize Faker once
    private final Faker faker = new Faker();
    private final SoftAssert soft = new SoftAssert();
    private final Random random = new Random();
    private final String cvv = String.format("%03d", faker.number().numberBetween(100, 999));
    private final String Month = String.format("%02d", faker.number().numberBetween(1, 12));
    // Generate a realistic expiry year (current year + up to 5 years)
    private final String year = String.valueOf(Year.now().getValue() + random.nextInt(6));

    private final String comment = faker.chuckNorris().fact().toUpperCase();
    private final String fullName = faker.name().fullName();
    private final String cardNumber = faker.finance().creditCard();


    @BeforeMethod
    public void Setup() throws IOException {
        setupDriver(getPropertyValue("environments", "Browser"));
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("environments", "BaseURL"));
        LogsUtils.info("Page is redirected to the URL");
        Utility.generalWait(getDriver());
    }

    @Test
    public void VerifyDownloadingInvoice() {
        new P10_DownloadInvoice(getDriver())
                .SignUpProcess(SignUpEmailAddress, PasswordAccountInfo)
                .AddingProductToCart()
                .CheckOutProcess(comment)
                .FillingCardData(fullName, cardNumber, cvv, Month, year)
                .PlacingOrder()
                .DownloadInvoice()
                .DeleteAccount();
    }

    @AfterMethod
    public void Quit() {
        LogsUtils.info("Test execution completed. Closing browser.");
        quitDriver();
    }

}
