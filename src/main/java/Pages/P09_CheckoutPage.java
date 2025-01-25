package Pages;

import Utilities.LogsUtils;
import Utilities.WaitsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.*;

public class P09_CheckoutPage {
    private final By signUpLoginButton = By.xpath("//header/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[4]/a[1]");
    private final By emailAddress = By.xpath("//body/section[@id='form']/div[1]/div[1]/div[1]/div[1]/form[1]/input[2]");
    private final By password = By.xpath("//body/section[@id='form']/div[1]/div[1]/div[1]/div[1]/form[1]/input[3]");
    private final By loginButton = By.xpath("//button[contains(text(),'Login')]");
    private final By productsButton = By.xpath("//header/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]");
    private final By addToCart = By.xpath("//body/section[2]/div[1]/div[1]/div[2]/div[1]/div[6]/div[1]/div[1]/div[1]/a[1]");
    private final By viewCartButton = By.xpath("//u[contains(text(),'View Cart')]");
    private final By proceedToCheckout = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    private final By commentField = By.xpath("//body/section[@id='cart_items']/div[1]/div[6]/textarea[1]");
    private final By placeOrderButton = By.xpath("//a[contains(text(),'Place Order')]");
    private final By cardNameField = By.xpath("//body/section[@id='cart_items']/div[1]/div[3]/div[1]/div[2]/form[1]/div[1]/div[1]/input[1]");
    private final By cardNumberField = By.xpath("//body/section[@id='cart_items']/div[1]/div[3]/div[1]/div[2]/form[1]/div[2]/div[1]/input[1]");
    private final By cardCVCField = By.xpath("//body/section[@id='cart_items']/div[1]/div[3]/div[1]/div[2]/form[1]/div[3]/div[1]/input[1]");
    private final By cardExpMonthField = By.xpath("//body/section[@id='cart_items']/div[1]/div[3]/div[1]/div[2]/form[1]/div[3]/div[2]/input[1]");
    private final By cardExpYearField = By.xpath("//body/section[@id='cart_items']/div[1]/div[3]/div[1]/div[2]/form[1]/div[3]/div[3]/input[1]");
    private final By confirmOrderButton = By.xpath("//button[@id='submit']");
    private final By orderPlacedPh = By.xpath("//b[contains(text(),'Order Placed!')]");
    private final By continueOrder = By.xpath("//a[contains(text(),'Continue')]");
    private final WebDriver driver;

    public P09_CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P09_CheckoutPage SignUpProcess(String EmailText, String PasswordText) {
        ClickingOnElement(driver, signUpLoginButton);
        SendData(driver, emailAddress, EmailText);
        SendData(driver, password, PasswordText);
        LogsUtils.info("The User Email is " + EmailText + ", And the User password is " + PasswordText);
        ClickingOnElement(driver, loginButton);
        LogsUtils.info(" The User Logged in successfully");
        return this;
    }

    public P09_CheckoutPage AddingProductToCart() {
        ClickingOnElement(driver, productsButton);
        scrollToElement(driver, addToCart);
        ClickingOnElement(driver, addToCart);
        LogsUtils.info("The product added to cart successfully");
        ClickingOnElement(driver, viewCartButton);
        LogsUtils.info("The Cart is opened");
        return this;
    }

    public P09_CheckoutPage CheckOutProcess(String Comment) {
        ClickingOnElement(driver, proceedToCheckout);
        scrollToElement(driver, commentField);
        SendData(driver, commentField, Comment);
        LogsUtils.info("The Comment: " + Comment);
        ClickingOnElement(driver, placeOrderButton);
        return this;
    }

    public P09_CheckoutPage FillingCardData(String NameText, String NumberText, String CVCText, String ExpMonthText, String ExpYearText) {
        SendData(driver, cardNameField, NameText);
        SendData(driver, cardNumberField, NumberText);
        SendData(driver, cardCVCField, CVCText);
        SendData(driver, cardExpMonthField, ExpMonthText);
        SendData(driver, cardExpYearField, ExpYearText);
        LogsUtils.info("The Card Data is: Name: " + NameText + "Number: " + NumberText + "CVC: " + CVCText + "ExpMonth: " + ExpMonthText + "ExpYear: " + ExpYearText);
        return this;
    }

    public P09_CheckoutPage PlacingOrder() {
        scrollToElement(driver, confirmOrderButton);
        ClickingOnElement(driver, confirmOrderButton);
        WaitsUtils.generalWait(driver, 10);
        ClickingOnElement(driver, continueOrder);
        return this;
    }

    //Verifying
    public boolean VerifyOrderPlacedIsVisible(String ExpectedText) {
        return verifyEquals(orderPlacedPh, ExpectedText);
    }

}

