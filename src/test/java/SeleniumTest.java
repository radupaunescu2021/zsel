import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.assertFalse;


import static org.testng.AssertJUnit.assertTrue;

public class SeleniumTest {


    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.out.println("Before test###########################");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }

    @DataProvider(name = "loginCredentials")
    public Object[][] provideLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "loginCredentials")
    public void testLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginWithValidUser(username,password);
                assertTrue(productsPage.isProductsPageLoaded());
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.performLogin("invalid_user", "wrong_password");
        assertTrue("Error message should be visible", loginPage.isErrorMessageVisible());
    }

    @Test
    public void testAddItemToCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginWithValidUser("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        CartPage cartPage = productsPage.goToCart();
        assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));
    }


    @Test
    public void testCheckoutProcess() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginWithValidUser("standard_user", "secret_sauce");
        // Add an item to the cart
        productsPage.addItemToCart("Sauce Labs Backpack");
        CartPage cartPage = productsPage.goToCart();
        assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));

        // Proceed to checkout
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.fillInDetails("John", "Doe", "12345");

        // Finalize the order
        OrderConfirmationPage orderConfirmationPage = checkoutPage.finishCheckout();
        assertTrue(orderConfirmationPage.isOrderSuccess());
    }

    @Test
    public void testCancelOrderProcess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidUser("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCart("Sauce Labs Onesie");
        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.fillInDetails("John", "Doe", "90210");
        checkoutPage.cancelCheckout();

        // Verify that the order has been cancelled and user is redirected to the products page
        assertTrue("User should be redirected back to products page after cancelling", productsPage.isProductsPageLoaded());
    }

    @Test
    public void testRemoveItemFromCart() {
        // Set up WebDriver and pages
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // Login and add item to the cart
        loginPage.loginWithValidUser("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        CartPage cartPage = productsPage.goToCart();

        // Now remove the item and check the cart
        cartPage.removeItemFromCart("Sauce Labs Backpack");
        assertFalse(cartPage.isItemInCart("Sauce Labs Backpack"), "Item should not be in the cart after removal");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }

        System.out.println("After test");
    }
}
