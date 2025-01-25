package Tests;

import Pages.P04_LogoutPage;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;

public class TC04_LogoutUser {
    private final String SignUpEmailAdress = getJsonData("SignUpData", "EmailAdress");
    private final String PasswordAccontInfo = getJsonData("FillingAccountInfo", "Password");


    @BeforeMethod
    public void Setup() throws IOException {
        setupDriver(getPropertyValue("environments", "Browser"));
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("environments", "BaseURL"));
        LogsUtils.info("Page is redirected to the URL");
        Utility.generalWait(getDriver());
    }

    @Test
    public void ValidLogout() {
        new P04_LogoutPage(getDriver())
                .ClickOnLogin()
                .verifyLoginToYourAccountIsVisible("Login to your account");
        new P04_LogoutPage(getDriver())
                .FillingEmailAddressAndPassword(SignUpEmailAdress, PasswordAccontInfo)
                .VerifyLoginAsIsVisible("Logged in as RubaSaber")
                .ClickOnLogout();
    }

    @AfterMethod
    public void Quit() {
        quitDriver();
    }
}
