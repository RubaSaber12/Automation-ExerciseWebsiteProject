package Tests;

import Pages.P02_HomePage;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;

public class TC02_LoginWithValidData {
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
    public void ValidLogin() {
        new P02_HomePage(getDriver())
                .clickOnLogin()
                .verifyLoginToYourAccountIsVisible("Login to your account");
        new P02_HomePage(getDriver())
                .fillEmailAddressAndPassword(SignUpEmailAdress, PasswordAccontInfo)
                .verifyLoginUserNameIsVisible();
        //    new P02_HomePage(getDriver())
        //            .deleteAccount()
        //           .verifyAccountDeleted();
    }

    @AfterMethod
    public void Quit() {
        quitDriver();
    }
}
