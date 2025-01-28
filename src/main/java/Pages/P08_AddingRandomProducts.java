package Pages;

import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static Utilities.Utility.*;
import static Utilities.WaitsUtils.waitForElementPresent;

public class P08_AddingRandomProducts {
    private final By openProductPage = By.xpath("//header/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]");
    private final By viewProduct = By.xpath("//body/section[2]/div[1]/div[1]/div[2]/div[1]/div[6]/div[1]/div[2]/ul[1]/li[1]/a[1]");
    private final By productQuantity = By.xpath("//input[@id='quantity']");
    private final By addToCartButton = By.xpath("//body/section[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/span[1]/button[1]");
    private final By continueShoppingButton = By.xpath("//button[contains(text(),'Continue Shopping')]");
    private final By addProductToCart = By.xpath("//body/section[2]/div[1]/div[1]/div[2]/div[1]/div[8]/div[1]/div[1]/div[1]/a[1]");
    private final By viewCart = By.xpath("//u[contains(text(),'View Cart')]");
    private final By quantityInTheCart = By.xpath("//button[contains(text(),'30')]");

    private final WebDriver driver;

    public P08_AddingRandomProducts(WebDriver driver) {
        this.driver = driver;
    }

    public P08_AddingRandomProducts OpenProductsPage() {
        ClickingOnElement(driver, openProductPage);
        LogsUtils.info("The products page has opened");
        return this;
    }

    public P08_AddingRandomProducts AddingProductsOnCart(String Quantity) {
        scrollToElement(driver, viewProduct);
        ClickingOnElement(driver, viewProduct);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(productQuantity)).click();
        for (int i = 0; i < 5; i++) {
            actions.sendKeys(Keys.BACK_SPACE);  // Send the backspace key to remove the existing value
        }
        actions.perform();
        SendData(driver, productQuantity, Quantity);
        LogsUtils.info("The number of products is " + Quantity);
        ClickingOnElement(driver, addToCartButton);
        ClickingOnElement(driver, continueShoppingButton);
        ClickingOnElement(driver, openProductPage);
        scrollToElement(driver, addProductToCart);
        ClickingOnElement(driver, addProductToCart);
        ClickingOnElement(driver, viewCart);
        LogsUtils.info("The Cart page is opened");
        return this;
    }

    public int GetQuantity() {
        waitForElementPresent(quantityInTheCart);
        String getQuantity = getText(driver, quantityInTheCart);
        LogsUtils.info("the number of products are:" + getQuantity);
        return Integer.parseInt(getQuantity);
    }
}
