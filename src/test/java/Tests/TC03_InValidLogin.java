package Tests;

import Pages.P03_FailedLoginPage;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;

public class TC03_InValidLogin {
    private final String InvalidEmailAddress = new Faker().internet().emailAddress();
    private final String InvalidPassword = new Faker().internet().password();

    @BeforeMethod
    public void Setup() throws IOException {
        setupDriver(getPropertyValue("environments", "Browser"));
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("environments", "BaseURL"));
        LogsUtils.info("Page is redirected to the URL");
        Utility.generalWait(getDriver());
    }

    @Test
    public void InvalidLogin() {
        new P03_FailedLoginPage(getDriver())
                .ClickOnLogin()
                .verifyLoginToYourAccountIsVisible("Login to your account");
        new P03_FailedLoginPage(getDriver())
                .FillingEmailAddressAndPassword(InvalidEmailAddress, InvalidPassword);
        Assert.assertTrue(new P03_FailedLoginPage(getDriver()).verifyIncorrectDataMsg("Your email or password is incorrect!"));
    }

    @AfterMethod
    public void Quit() {
        quitDriver();
    }
}
