import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    private By usernameSelector = By.id("user-name");
    private By passwordSelector = By.id("password");
    private By loginButtonSelector = By.id("login-button");
    private By errorMessageSelector = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage loginWithValidUser(String username, String password) {
        driver.findElement(usernameSelector).sendKeys(username);
        driver.findElement(passwordSelector).sendKeys(password);
        driver.findElement(loginButtonSelector).click();
        return new ProductsPage(driver);
    }

    public void performLogin(String username, String password) {
        driver.findElement(usernameSelector).sendKeys(username);
        driver.findElement(passwordSelector).sendKeys(password);
        driver.findElement(loginButtonSelector).click();
    }

    public boolean isErrorMessageVisible() {
        try {
            WebElement errorElement = driver.findElement(errorMessageSelector);
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;  // Element not found, meaning no error message is displayed
        }
    }
}