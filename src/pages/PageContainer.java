package pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class PageContainer {

    public WebDriver driver;
    public LoginPage loginPage;
    public ExtentTest extentTest;
    public ProductPage productPage;
    public CartPage cartPage;
    public CheckOutPage checkOutPage;

    public PageContainer(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;
        initPages();
    }

    public void initPages() {
        loginPage = new LoginPage(driver, extentTest);
        productPage = new ProductPage(driver, extentTest);
        cartPage = new CartPage(driver, extentTest);
        checkOutPage = new CheckOutPage(driver, extentTest);

    }
}
