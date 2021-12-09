package tests;

import BasePage.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.DataUtil;

import java.util.Hashtable;

public class CartPageTests extends TestBase {

    @Test(dataProvider = "getData")
    public void verifyCartPage(Hashtable<String, String> data) throws Exception {
        startreport("verify Cart Page has the products");
        invoke();
        extentTest.log(LogStatus.PASS, "verify Cart Page has the products");
        container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
        container.productPage.clickAddToCart(data.get("ProductName"));
        container.productPage.clickAddToCart(data.get("SecondProductName"));
        container.productPage.clickCartButton();
        Assert.assertEquals(container.cartPage.getPageHeader(), "YOUR CART");
        Assert.assertEquals(container.cartPage.verifyCartListProductName(data.get("ProductName")),data.get("ProductName"));
        Assert.assertEquals(container.cartPage.verifyCartListProductDescription(data.get("ProductDescription")),data.get("ProductDescription"));
        Assert.assertEquals(container.cartPage.verifyCartListProductPrice(data.get("ProductPrice")), data.get("ProductPrice"));
        Assert.assertEquals(container.cartPage.verifyCartListProductName(data.get("SecondProductName")), data.get("SecondProductName"));
        Assert.assertEquals(container.cartPage.verifyCartListProductDescription(data.get("SecondProductDescription")),data.get("SecondProductDescription"));
        Assert.assertEquals(container.cartPage.verifyCartListProductPrice(data.get("ProductPrice2")), data.get("ProductPrice2"));
    }

    @Test(dataProvider = "getData", priority = 1)
    public void verifyProductRemovalFromCart(Hashtable<String, String> data) throws Exception {
        startreport("verify Product Page is NOT Empty");
        invoke();
        extentTest.log(LogStatus.PASS, "Products list is not empty ");
        container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
        container.productPage.clickAddToCart(data.get("ProductName"));
        container.productPage.clickAddToCart(data.get("SecondProductName"));
        container.productPage.clickCartButton();
        container.cartPage.clickRemoveFromCart(data.get("ProductName"));
        Assert.assertTrue(container.cartPage.verifyProductRemovedFromCart(data.get("ProductName")));
    }

    @Test(dataProvider = "getData")
    public void verifyClickOnBackToShopping(Hashtable<String, String> data) throws Exception {
        startreport("verify Product Page is NOT Empty");
        invoke();
        extentTest.log(LogStatus.PASS, "Products list is not empty ");
        container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
        container.productPage.clickAddToCart(data.get("ProductName"));
        container.productPage.clickCartButton();
        container.cartPage.clickContinueShoppingButton();
        Assert.assertEquals(container.cartPage.getPageHeader(), "PRODUCTS");
    }

    @DataProvider
    public Object[][] getData() {
        return DataUtil.getData(datatable, "ProductPage", "CAPMO_TASK");

    }
}
