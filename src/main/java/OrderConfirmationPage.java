import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public  class OrderConfirmationPage {
    private WebDriver driver;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderSuccess() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            // Wait until the confirmation message is visible using the data-test attribute
            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2[data-test='complete-header']")));
            return confirmationMessage.getText().contains("Thank you for your order!");
        } catch (Exception e) {
            return false; // Element not found within the wait period or does not contain the expected text
        }
    }
}
