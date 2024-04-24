import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCart(String itemName) {
        By addToCartButtonSelector = By.xpath("//div[contains(text(), '" + itemName + "')]/following::button");
        driver.findElement(addToCartButtonSelector).click();
    }

    public CartPage goToCart() {
        By cartSelector = By.id("shopping_cart_container");
        driver.findElement(cartSelector).click();
        return new CartPage(driver);
    }

    public boolean isProductsPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container"))).isDisplayed();
        } catch (Exception e) {
            return false; // Element not found or not visible within the wait time
        }
    }

}
