package Pages;

import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.*;

public class P02_HomePage {

    private final WebDriver driver;

    private final By signUpLoginButton = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By loginToYourAccount = By.xpath("//h2[normalize-space()='Login to your account']");
    private final By emailAddress = By.xpath("//input[@data-qa='login-email']");
    private final By password = By.xpath("//input[@placeholder='Password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By loginUserName = By.xpath("//b[normalize-space()='RubaSaber']");
    private final By deleteAccount = By.xpath("//a[normalize-space()='Delete Account']");
    private final By accountDeleted = By.xpath("//b[normalize-space()='Account Deleted!']");
    private final By continueDeleteAccount = By.xpath("//a[normalize-space()='Continue']");
    private final By logOut = By.xpath("//a[normalize-space()='Logout']");

    public P02_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public P02_HomePage clickOnLogin() {
        ClickingOnElement(driver, signUpLoginButton);
        LogsUtils.info("Redirected to Login Page");
        return this;
    }

    public boolean verifyLoginToYourAccountIsVisible(String expectedText) {
        return verifyEquals(loginToYourAccount, expectedText);
    }

    public P02_HomePage fillEmailAddressAndPassword(String existingEmail, String existingPassword) {
        SendData(driver, emailAddress, existingEmail);
        LogsUtils.info("Entered Email: " + existingEmail);
        SendData(driver, password, existingPassword);
        LogsUtils.info("Entered Password: " + existingPassword);
        ClickingOnElement(driver, loginButton);
        LogsUtils.info("User logged in successfully");
        return this;
    }

    public P02_HomePage verifyLoginUserNameIsVisible() {
        verifyElementVisible(loginUserName);
        LogsUtils.info("Username is visible, login successful");
        return this;
    }

    public P02_HomePage deleteAccount() {
        ClickingOnElement(driver, deleteAccount);
        LogsUtils.info("Account deletion initiated");
        ClickingOnElement(driver, continueDeleteAccount);
        LogsUtils.info("Account deleted successfully");
        return this;
    }

    public P02_HomePage verifyAccountDeleted() {
        verifyElementVisible(accountDeleted);
        LogsUtils.info("Account deletion verified");
        return this;
    }

    public P02_HomePage ClickOnLogout() {
        ClickingOnElement(driver, logOut);
        LogsUtils.info("The User Logged out Successfully");
        return this;
    }
}
