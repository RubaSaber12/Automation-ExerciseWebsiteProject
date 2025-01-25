package Pages;

import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.*;

public class P03_FailedLoginPage {
    private final By signUpLoginButton = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By loginToYourAccount = By.xpath("//h2[normalize-space()='Login to your account']");
    private final By emailAddress = By.xpath("//input[@data-qa='login-email']");
    private final By password = By.xpath("//input[@placeholder='Password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By incorrectMsg = By.xpath("//p[normalize-space()='Your email or password is incorrect!']");
    private final WebDriver driver;

    public P03_FailedLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P03_FailedLoginPage ClickOnLogin() {
        ClickingOnElement(driver, signUpLoginButton);
        LogsUtils.info("It's redirect to LoginPage");
        return this;
    }

    //Validation
    public boolean verifyLoginToYourAccountIsVisible(String expectedText) {
        return verifyEquals(loginToYourAccount, expectedText);
    }

    public P03_FailedLoginPage FillingEmailAddressAndPassword(String ExistingEmail, String ExistingPassword) {
        SendData(driver, emailAddress, ExistingEmail);
        LogsUtils.info("The Existing Email is: " + ExistingEmail);
        SendData(driver, password, ExistingPassword);
        LogsUtils.info("The Existing password is: " + ExistingPassword);
        ClickingOnElement(driver, loginButton);
        LogsUtils.info("The user Fail to login");
        return this;
    }

    public boolean verifyIncorrectDataMsg(String expectedText) {
        return verifyEquals(incorrectMsg, expectedText);
    }
}
