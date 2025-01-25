package Pages;

import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.*;

public class P07_AddProductToCart {
    private final By productsPage = By.xpath("//a[@href='/products']");
    private final By firstProductAddToCart = By.xpath("//body/section[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/a[1]");
    private final By secondProductAddToCart = By.xpath("//body/section[2]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/a[1]");
    private final By continueShopping = By.xpath("//button[contains(text(),'Continue Shopping')]");
    private final By viewCartButton = By.xpath("//u[contains(text(),'View Cart')]");
    private final By firstProductPrice = By.xpath("//body[1]/section[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/p[1]");
    private final By firstProductQuantity = By.cssSelector("div.container div.table-responsive.cart_info:nth-child(3) table.table.table-condensed:nth-child(1) tbody:nth-child(2) tr:nth-child(1) td.cart_quantity > button.disabled");
    private final By secondProductPrice = By.xpath("//body[1]/section[1]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/td[3]/p[1]");
    private final By secondProductQuantity = By.cssSelector("div.container div.table-responsive.cart_info:nth-child(3) table.table.table-condensed:nth-child(1) tbody:nth-child(2) tr:nth-child(2) td.cart_quantity > button.disabled");
    private final By firstProductTotalPrice = By.xpath("//p[@class='cart_total_price'][normalize-space()='Rs. 500']");
    private final By secondProductTotalPrice = By.xpath("//p[normalize-space()='Rs. 1200']");
    private final WebDriver driver;

    public P07_AddProductToCart(WebDriver driver) {
        this.driver = driver;
    }

    public P07_AddProductToCart ClickOnProductsPage() {
        ClickingOnElement(driver, productsPage);
        LogsUtils.info("The products page is opened");
        scrollToElement(driver, firstProductAddToCart);
        return this;
    }

    public P07_AddProductToCart AddingProductsToCart() {
        //Add the first product To Cart
        ClickingOnElement(driver, firstProductAddToCart);
        LogsUtils.info("The First product Added");
        //Click On Continue Shopping
        ClickingOnElement(driver, continueShopping);
        // Add the Second Product To Cart
        ClickingOnElement(driver, secondProductAddToCart);
        LogsUtils.info("The Second product Added");
        //Click On View Cart
        ClickingOnElement(driver, viewCartButton);
        return this;
    }

    public int GetFirstPrice() {
        // Scroll to the element to make sure it's visible
        scrollToElement(driver, firstProductPrice);

        // Extract the text, remove currency symbol, trim spaces, and parse to Float
        String FirstPriceText = getText(driver, firstProductPrice).replace("Rs.", "").trim();
        LogsUtils.info("First Price " + FirstPriceText);

        // Parse and return the price
        return Integer.parseInt(FirstPriceText);
    }

    public int GetSecondPrice() {
        // Scroll to the element to make sure it's visible
        scrollToElement(driver, secondProductPrice);

        // Extract the text, remove currency symbol, trim spaces, and parse to Float
        String SecondPriceText = getText(driver, secondProductPrice).replace("Rs.", "").trim();
        LogsUtils.info("Second Price " + SecondPriceText);
        // Parse and return the price
        return Integer.parseInt(SecondPriceText);
    }

    public int GetFirstQuantity() {
        // Scroll to the element to make sure it's visible
        scrollToElement(driver, firstProductQuantity);

        // Extract the text, remove currency symbol, trim spaces, and parse to Float
        String FirstQuantityText = getText(driver, firstProductQuantity);
        LogsUtils.info("First Quantity:" + FirstQuantityText);
        // Parse and return the price
        return Integer.parseInt(FirstQuantityText);
    }

    public int GetSecondQuantity() {
        // Scroll to the element to make sure it's visible
        scrollToElement(driver, secondProductQuantity);

        // Extract the text, remove currency symbol, trim spaces, and parse to Float
        String SecondQuantityText = getText(driver, secondProductQuantity);
        // Parse and return the price
        LogsUtils.info("Second Quantity: " + SecondQuantityText);
        return Integer.parseInt(SecondQuantityText);
    }

    public int calculateTotalPriceForFirstProduct() {
        int Firstprice = GetFirstPrice();  // Get the price of the first product
        int Firstquantity = GetFirstQuantity();  // Get the quantity of the first product
        // Calculate total price and return
        return Firstprice * Firstquantity;
    }

    public int calculateTotalPriceForSecondProduct() {
        int price = GetSecondPrice();  // Get the price of the first product
        int quantity = GetSecondQuantity();  // Get the quantity of the first product
        // Calculate total price and return
        return price * quantity;
    }

}

