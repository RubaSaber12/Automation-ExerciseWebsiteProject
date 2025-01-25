package Pages;

import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Utilities.Utility.*;

public class P05_ProductsPage {
    private static final Logger log = LoggerFactory.getLogger(P05_ProductsPage.class);
    private final By productButton = By.xpath("//a[@href='/products']");
    private final By allProduct = By.xpath("//h2[normalize-space()='All Products']");
    private final By getAllProduct = By.className(".product-image-wrapper");
    private final By viewProduct = By.xpath("//div[6]//div[1]//div[2]//ul[1]//li[1]//a[1]");
    private final By productName = By.xpath("//h2[normalize-space()='Winter Top']");
    private final By productCategory = By.xpath("//p[normalize-space()='Category: Women > Tops']");
    private final By productPrice = By.xpath("//span[normalize-space()='Rs. 600']");
    private final By productQuantity = By.xpath("//label[normalize-space()='Quantity:']");
    private final By productAvailability = By.xpath("//div[@class='product-details']//p[2]");
    private final By productCondition = By.xpath("//body//section//p[3]");
    private final By productBrand = By.xpath("//body//section//p[4]");
    private final WebDriver driver;

    public P05_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public P05_ProductsPage ClickOnProductButton() {
        ClickingOnElement(driver, productButton);
        LogsUtils.info("The page redirect to products List");
        return this;
    }

    public P05_ProductsPage VerifyTheProductsPageURL(String expectedURL) {
        verifyURLEquals(expectedURL);
        LogsUtils.info("The products page URL is " + expectedURL);
        return this;
    }

    public P05_ProductsPage verifyAllProductExist() {
        scrollToElement(driver, allProduct);
        verifyElementVisible(allProduct);
        return this;
    }

    public P05_ProductsPage verifyAllProductsAppears() {
        verifyElementVisible(getAllProduct);
        return this;
    }

    public P05_ProductsPage viewProductDetails() {
        scrollToElement(driver, viewProduct);
        ClickingOnElement(driver, viewProduct);
        LogsUtils.info("The product Details is opened");
        return this;
    }

    public P05_ProductsPage VerifyProductDetailsIsExist() {
        verifyElementVisible(productName);
        String productNameText = getText(driver, productName);
        LogsUtils.info("The Product name is " + productNameText);

        verifyElementVisible(productCategory);
        String productCategoryText = getText(driver, productCategory);
        LogsUtils.info("The Product Category is " + productCategoryText);


        verifyElementVisible(productPrice);
        String productpriceText = getText(driver, productPrice);
        LogsUtils.info("The Product Price is $" + productpriceText);

        verifyElementVisible(productQuantity);
        String productQuantityText = getText(driver, productQuantity);
        LogsUtils.info("The Product availability is " + productQuantityText);


        verifyElementVisible(productAvailability);
        String productAvailabilityText = getText(driver, productAvailability);
        LogsUtils.info("The Product availability is " + productAvailabilityText);


        verifyElementVisible(productCondition);
        String productCondtionText = getText(driver, productCondition);
        LogsUtils.info("The Product condition is " + productCondtionText);


        verifyElementVisible(productBrand);
        String productBrandText = getText(driver, productBrand);
        LogsUtils.info("The product Brand is " + productBrandText);


        return this;

    }


}
