package Pages;

import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.*;

public class P04_LogoutPage {
    private final By signUpLoginButton = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By loginToYourAccount = By.xpath("//h2[normalize-space()='Login to your account']");
    private final By emailAddress = By.xpath("//input[@data-qa='login-email']");
    private final By password = By.xpath("//input[@placeholder='Password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By loginUserName = By.xpath("//b[normalize-space()='RubaSaber']");
    private final By logOut = By.xpath("//a[normalize-space()='Logout']");
    private final WebDriver driver;


    public P04_LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P04_LogoutPage ClickOnLogin() {
        ClickingOnElement(driver, signUpLoginButton);
        LogsUtils.info("It's redirect to LoginPage");
        return this;
    }

    //Validation
    public boolean verifyLoginToYourAccountIsVisible(String expectedText) {
        return verifyEquals(loginToYourAccount, expectedText);
    }

    public P04_LogoutPage FillingEmailAddressAndPassword(String ExistingEmail, String ExistingPassword) {
        SendData(driver, emailAddress, ExistingEmail);
        LogsUtils.info("The Existing Email is: " + ExistingEmail);
        SendData(driver, password, ExistingPassword);
        LogsUtils.info("The Existing password is: " + ExistingPassword);
        ClickingOnElement(driver, loginButton);
        LogsUtils.info("The User Logged In Successfully");
        return this;
    }

    public P04_LogoutPage VerifyLoginAsIsVisible(String expectedText) {
        verifyEquals(loginUserName, expectedText);
        LogsUtils.info("The Login As Is Visible");

        return this;
    }

    public P04_LogoutPage ClickOnLogout() {
        ClickingOnElement(driver, logOut);
        LogsUtils.info("The User Logged out Successfully");
        return this;
    }

}
