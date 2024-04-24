import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage checkout() {
        By checkoutButtonSelector = By.id("checkout");
        driver.findElement(checkoutButtonSelector).click();
        return new CheckoutPage(driver);
    }

    public boolean isItemInCart(String itemName) {
        // Locate all items in the cart by a common attribute, e.g., item names
        // This is an example selector, and you'll need to adjust it based on the actual HTML structure of the cart page.
        List<WebElement> itemsInCart = driver.findElements(By.className("cart_item"));
        for (WebElement item : itemsInCart) {
            WebElement itemNameElement = item.findElement(By.className("inventory_item_name"));
            if (itemNameElement.getText().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    // Adds method to remove an item based on its name
    public void removeItemFromCart(String itemName) {
        // Replace spaces with dashes and lowercase all letters to match the id format
        String formattedName = itemName.toLowerCase().replace(" ", "-");
        By removeButtonSelector = By.id("remove-" + formattedName);
        WebElement removeButton = driver.findElement(removeButtonSelector);
        removeButton.click();;
    }

}