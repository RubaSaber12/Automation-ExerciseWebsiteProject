package Pages;

import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.*;

public class P01_RegisterPage {
    private final WebDriver driver;

    // Locators
    private final By SignUpLoginButton = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By SignUpName = By.xpath("//input[@placeholder='Name']");
    private final By SignUpEmail = By.xpath("//input[@data-qa='signup-email']");
    private final By SignUpButton = By.xpath("//button[normalize-space()='Signup']");
    private final By NewUserSignUpMessage = By.cssSelector(".signup-form h2");
    private final By MrGender = By.id("id_gender1");
    private final By MrsGender = By.id("id_gender2");
    private final By PasswordField = By.xpath("//input[@id='password']");
    private final By daysSelect = By.id("days");
    private final By MonthsSelect = By.id("months");
    private final By YearsSelect = By.id("years");
    private final By Newsletter = By.id("newsletter");
    private final By offerOptin = By.id("optin");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By companyNameInput = By.id("company");
    private final By addressOneInput = By.id("address1");
    private final By addressTwoInput = By.id("address2");
    private final By countrySelectInput = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipCodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");
    private final By createAccountButton = By.xpath("//button[normalize-space()='Create Account']");
    private final By accountCreated = By.xpath("//h2[@data-qa='account-created']");
    private final By continueCreateAccount = By.xpath("//a[normalize-space()='Continue']");
    private final By DeleteAccount = By.xpath("//a[normalize-space()='Delete Account']");
    private final By accountDeleted = By.xpath("//b[normalize-space()='Account Deleted!']");
    private final By continueDeleteAccount = By.xpath("//a[normalize-space()='Continue']");
    private final By logOut = By.xpath("//a[normalize-space()='Logout']");


    public P01_RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_RegisterPage clickOnSignUpLoginButton() {
        ClickingOnElement(driver, SignUpLoginButton);
        return this;
    }

    public P01_RegisterPage fillNameAndEmail(String name, String email) {
        SendData(driver, SignUpName, name);
        SendData(driver, SignUpEmail, email);
        return this;
    }

    public P01_RegisterPage clickOnSignUpButton() {
        ClickingOnElement(driver, SignUpButton);
        return this;
    }

    public P01_RegisterPage fillUserRegisterForm(String password, String day, String month, String year, String firstName, String lastName, String company, String addressOne, String addressTwo, String country, String state, String city, String zipCode, String mobileNumber) {
        ClickingOnElement(driver, MrsGender);
        SendData(driver, PasswordField, password);
        LogsUtils.info("Password: " + password);
        selectingFromDropDown(driver, daysSelect, day);
        selectingFromDropDown(driver, MonthsSelect, month);
        selectingFromDropDown(driver, YearsSelect, year);
        scrollToElement(driver, Newsletter);
        ClickingOnElement(driver, Newsletter);
        ClickingOnElement(driver, offerOptin);
        SendData(driver, firstNameInput, firstName);
        SendData(driver, lastNameInput, lastName);
        SendData(driver, companyNameInput, company);
        SendData(driver, addressOneInput, addressOne);
        SendData(driver, addressTwoInput, addressTwo);
        selectingFromDropDown(driver, countrySelectInput, country);
        SendData(driver, stateInput, state);
        SendData(driver, cityInput, city);
        SendData(driver, zipCodeInput, zipCode);
        SendData(driver, mobileNumberInput, mobileNumber);
        return this;
    }

    public void createAccount() {
        ClickingOnElement(driver, createAccountButton);
    }

    public void continueCreateAccount() {
        ClickingOnElement(driver, continueCreateAccount);
        LogsUtils.info("Account Created!");
    }

    public boolean isAccountCreated(String expectedText) {
        return verifyEquals(accountCreated, expectedText);
    }

    public boolean isNewUserSignUpMessageDisplayed(String expectedText) {
        return verifyEquals(NewUserSignUpMessage, expectedText);
    }

    public P01_RegisterPage deleteAccount() {
        ClickingOnElement(driver, DeleteAccount);
        return this;
    }

    public boolean isAccountDeleted(String expectedText) {
        return verifyEquals(accountDeleted, expectedText);
    }

    public P01_RegisterPage continueDeleteAccount() {
        ClickingOnElement(driver, continueDeleteAccount);
        return this;
    }

    public P01_RegisterPage ClickOnLogout() {
        ClickingOnElement(driver, logOut);
        LogsUtils.info("The User Logged out Successfully");
        return this;
    }
}
