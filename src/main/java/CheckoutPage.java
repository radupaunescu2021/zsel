import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private WebDriver driver;

    private By firstNameSelector = By.id("first-name");
    private By lastNameSelector = By.id("last-name");
    private By zipCodeSelector = By.id("postal-code");
    private By continueButtonSelector = By.id("continue");
    private By finishButtonSelector = By.id("finish");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderConfirmationPage fillInDetails(String firstName, String lastName, String zipCode) {
        driver.findElement(firstNameSelector).sendKeys(firstName);
        driver.findElement(lastNameSelector).sendKeys(lastName);
        driver.findElement(zipCodeSelector).sendKeys(zipCode);
        driver.findElement(continueButtonSelector).click();
        return new OrderConfirmationPage(driver);
    }

    public OrderConfirmationPage finishCheckout() {
        driver.findElement(finishButtonSelector).click();
        return new OrderConfirmationPage(driver);
    }

    public void cancelCheckout() {
        WebElement cancelButton = driver.findElement(By.id("cancel"));  // Adjust the ID according to your application
        cancelButton.click();

        // Optionally handle any alerts or confirmations that appear when cancelling
    }
}