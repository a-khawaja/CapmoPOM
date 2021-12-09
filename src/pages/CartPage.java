package pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CartPage extends PageBase {

    public CartPage(WebDriver driver, ExtentTest extentTest) {
        super(driver, extentTest);
    }

    @FindBy(how = How.XPATH, using = "//span[@class='title']")
    WebElement HEADER;

    @FindBy(how = How.XPATH, using = "//button[@id='checkout']")
    WebElement CHECKOUT_BUTTON;

    @FindBy(how = How.XPATH, using = "//div[@class='cart_list']//div[@class='cart_item']")
    List<WebElement> CART_LIST;

    @FindBy(how = How.XPATH, using = "//button[@id='continue-shopping']")
    WebElement CONTINUE_SHOPPING;


    public String getPageHeader() {
        return HEADER.getText();
    }

    public String verifyCartListProductName(String productName1) {
        waitForPageToLoad();

        for (int i = 1; i <= CART_LIST.size(); i++) {
            int j = i + 2;
            String name = driver.findElement(By.xpath("//div[@class='cart_list']//div[" + j + "]//div[@class='cart_item_label']//a//div")).getText();

            if (name.equalsIgnoreCase(productName1)) {
                return name;
            }
        }
        return null;
    }

    public String verifyCartListProductDescription(String productdesc1) {
        waitForPageToLoad();

        for (int i = 1; i <= CART_LIST.size(); i++) {
            int j = i + 2;
            String name = driver.findElement(By.xpath("//div[@class='cart_list']//div[" + j + "]//div[@class='cart_item_label']//div[@class='inventory_item_desc']")).getText();

            if (name.equalsIgnoreCase(productdesc1)) {
                return name;
            }
        }
        return null;
    }

    public String verifyCartListProductPrice(String productPrice1) {
        waitForPageToLoad();

        for (int i = 1; i <= CART_LIST.size(); i++) {
            int j = i + 2;
            String name = driver.findElement(By.xpath("//div[@class='cart_list']//div[" + j + "]//div[@class='cart_item_label']//div[@class='item_pricebar']//div")).getText();

            if (name.equalsIgnoreCase(productPrice1)) {
                return name;
            }
        }
        return null;
    }

    public void clickRemoveFromCart(String productname) throws Exception {
        waitForPageToLoad();

        for (int i = 1; i <= CART_LIST.size(); i++) {
            int j = i + 2;
            String name = driver.findElement(By.xpath("//div[@class='cart_list']//div[" + j + "]//div[@class='cart_item_label']//a//div")).getText();

            if (name.equalsIgnoreCase(productname)) {
                click(driver.findElement(By.xpath("//div[@class='cart_list']//div[" + j + "]//div[@class='cart_item_label']//div[@class='item_pricebar']//button")));
                break;
            }
        }
    }

    public boolean verifyProductRemovedFromCart(String productname) {
        driver.navigate().refresh();
        waitForPageToLoad();
        boolean flag = true;
        for (int i = 1; i <= CART_LIST.size(); i++) {
            int j = i + 2;
            String name = driver.findElement(By.xpath("//div[@class='cart_list']//div[" + j + "]//div[@class='cart_item_label']//a//div")).getText();

            if (name.equalsIgnoreCase(productname)) {
                flag = false;
            }
        }
        return flag;
    }

    public void clickCheckoutButton() throws InterruptedException {
        click(CHECKOUT_BUTTON);
    }

    public void clickContinueShoppingButton() throws InterruptedException {
        click(CONTINUE_SHOPPING);
    }
}
