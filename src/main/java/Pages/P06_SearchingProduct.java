package Pages;

import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static DriverFactory.DriverFactory.getDriver;
import static Utilities.Utility.*;

public class P06_SearchingProduct {
    private final By productButton = By.xpath("//a[@href='/products']");
    private final By allProduct = By.xpath("//h2[normalize-space()='All Products']");
    private final By getAllProduct = By.className(".product-image-wrapper");
    private final By searchField = By.xpath("//input[@id='search_product']");
    private final By searchButton = By.xpath("//button[@id='submit_search']");
    private final By SearchedSection =
            By.xpath("//h2[normalize-space()='Searched Products']");
    private final List<WebElement> SearchResults = getDriver().findElements
            (By.xpath("//i[contains(@class,'fa fa-plus-square')]"));
    private final By addToCart = By.xpath("//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[2]//ul[1]//li[1]//a[1]");
    private final WebDriver driver;

    public P06_SearchingProduct(WebDriver driver) {
        this.driver = driver;
    }

    public P06_SearchingProduct ClickOnProductButton() {
        ClickingOnElement(driver, productButton);
        LogsUtils.info("The page redirect to products List");
        return this;
    }

    public P06_SearchingProduct VerifyTheProductsPageURL(String expectedURL) {
        verifyURLEquals(expectedURL);
        LogsUtils.info("The products page URL is " + expectedURL);
        return this;
    }

    public P06_SearchingProduct SearchOnProduct(String product) {
        SendData(driver, searchField, product);
        return this;
    }

    public P06_SearchingProduct PressSearchButton() {
        ClickingOnElement(driver, searchButton);
        return this;
    }

    public boolean VerifySearchedProduct() {
        return verifyElementVisible(SearchedSection);
    }

    public int VerifyAllProductsRelatedToSearchAreVisible() {
        scrollToElement(driver, addToCart);
        LogsUtils.info("Search Result Size: " + SearchResults.size());
        return SearchResults.size();
    }

}


