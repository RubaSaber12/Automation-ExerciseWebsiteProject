package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_RegisterPage;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_VerifyRegister {

    private final String signUpUserName = getJsonData("SignUpData", "username");
    private final String signUpEmailAddress = getJsonData("SignUpData", "EmailAdress");
    private final String passwordAccountInfo = getJsonData("FillingAccountInfo", "Password");

    private final Faker faker = new Faker();
    private final String firstNameText = faker.name().firstName();
    private final String secondNameText = faker.name().lastName();
    private final String companyText = faker.company().name();
    private final String firstAddressText = faker.address().streetAddress();
    private final String secondAddressText = faker.address().secondaryAddress();
    private final String stateText = faker.address().state();
    private final String cityText = faker.address().cityName();
    private final String zipCodeText = faker.address().zipCode();
    private final String mobileNumberText = faker.number().digits(10);

    public TC01_VerifyRegister() throws IOException {
    }

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(getPropertyValue("environments", "Browser"));
        LogsUtils.info("Browser driver is opened");
        getDriver().get(getPropertyValue("environments", "BaseURL"));
        LogsUtils.info("Page is redirected to the URL");
        ((JavascriptExecutor) getDriver()).executeScript("document.documentElement.requestFullscreen();");
        LogsUtils.info("Browser switched to full-screen mode");
        Utility.generalWait(getDriver());
    }

    @Test
    public void validSignUp() {
        P01_RegisterPage registerPage = new P01_RegisterPage(getDriver());

        registerPage
                .clickOnSignUpLoginButton()
                .fillNameAndEmail(signUpUserName, signUpEmailAddress)
                .clickOnSignUpButton()
                .fillUserRegisterForm(passwordAccountInfo, "3", "December", "2000",
                        firstNameText, secondNameText, companyText, firstAddressText,
                        secondAddressText, "United States", stateText, cityText,
                        zipCodeText, mobileNumberText)
                .createAccount();

        Assert.assertTrue(registerPage.isAccountCreated("ACCOUNT CREATED!"));

        registerPage.continueCreateAccount();

        // Uncomment if deletion is needed
        // registerPage.DeleteAccount()
        //        .isAccountDeleted("Account Deleted!")
        //        .ContinueDeleteAccount();

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
