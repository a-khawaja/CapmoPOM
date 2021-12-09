package pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductPage extends PageBase {

    public ProductPage(WebDriver driver, ExtentTest extentTest) {
        super(driver, extentTest);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='inventory_list']//div[@class='inventory_item']")
    List<WebElement> INVENTORY_LIST;

    @FindBy(how = How.XPATH, using = "//button[@id='back-to-products']")
    WebElement BACK_TO_PRODUCTS_BUTTON;

    @FindBy(how = How.XPATH, using = "//a[@class='shopping_cart_link']")
    WebElement CART_BUTTON;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Products')]")
    WebElement PRODUCT_HEADER;


    public String getHeader() {
        return PRODUCT_HEADER.getText();
    }

    public int verifyListNotEmpty() {
        waitForPageToLoad();
        return INVENTORY_LIST.size();
    }

    public String verifyAddItemToCartButtonText(String product) throws Exception {
        String buttonName = "";
        clickAddToCart(product);

        for (int i = 1; i <= INVENTORY_LIST.size(); i++) {

            String name = driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='inventory_item_name']")).getText();

            if (name.equalsIgnoreCase(product)) {
                buttonName = driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='pricebar']//button")).getText();
                break;
            }
        }
        return buttonName;

    }

    public String getProductTitle(String product) throws Exception {

        waitForPageToLoad();

        for (int i = 1; i <= INVENTORY_LIST.size(); i++) {

            String name = driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='inventory_item_name']")).getText();

            if (name.equalsIgnoreCase(product)) {
                return name;
            }
        }
        return null;
    }

    public String getProductDescription(String productname) throws Exception {
        waitForPageToLoad();

        for (int i = 1; i <= INVENTORY_LIST.size(); i++) {

            String name = driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='inventory_item_name']")).getText();

            if (name.equalsIgnoreCase(productname)) {
                return driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='inventory_item_desc']")).getText();
            }
        }
        return null;
    }


    public String getProductPrice(String productname) throws Exception {
        waitForPageToLoad();

        for (int i = 1; i <= INVENTORY_LIST.size(); i++) {

            String name = driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='inventory_item_name']")).getText();

            if (name.equalsIgnoreCase(productname)) {
                return driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='pricebar']//div[@class='inventory_item_price']")).getText();
            }
        }
        return null;

    }

    public boolean getProductImages() throws Exception {

        boolean flag = true;
        waitForPageToLoad();

        for (int i = 0; i < INVENTORY_LIST.size(); i++) {

            String imageSource = driver.findElement(By.xpath("//a[@id='item_" + i + "_img_link']//img[@class='inventory_item_img']")).getAttribute("src");

            if (imageSource.equalsIgnoreCase("")) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void clickAddToCart(String productname) throws Exception {

        waitForPageToLoad();

        for (int i = 1; i <= INVENTORY_LIST.size(); i++) {

            String name = driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='inventory_item_name']")).getText();

            if (name.equalsIgnoreCase(productname)) {
                click(driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='pricebar']//button")));
                break;
            }
        }
    }

    public void clickProduct(String productname) throws Exception {
        waitForPageToLoad();

        for (int i = 1; i <= INVENTORY_LIST.size(); i++) {

            String name = driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='inventory_item_name']")).getText();

            if (name.equalsIgnoreCase(productname)) {
                click(driver.findElement(By.xpath("//div[@class='inventory_list']//div[" + i + "]//div[@class='inventory_item_description']//div[@class='inventory_item_name']")));
            }
        }

    }

    public String pageOpened() {
        return driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']")).getText();
    }

    public void clickBackToProduct() throws InterruptedException {
        click(BACK_TO_PRODUCTS_BUTTON);
    }

    public void clickCartButton() throws InterruptedException {
        click(CART_BUTTON);
    }
}
