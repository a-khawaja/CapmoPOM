package tests;

import BasePage.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.DataUtil;

import java.util.Hashtable;

public class CheckOutPageTests extends TestBase {

    @Test(dataProvider = "getData")
    public void verifyCheckOutOrder(Hashtable<String, String> data) throws Exception {
        startreport("Verify order has been been successfully placed");
        invoke();
        extentTest.log(LogStatus.PASS, "Order has been canceled at Checkout");
        container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
        container.productPage.clickAddToCart(data.get("ProductName"));
        container.productPage.clickAddToCart(data.get("SecondProductName"));
        container.productPage.clickCartButton();
        container.cartPage.clickCheckoutButton();
        Assert.assertEquals(container.checkOutPage.getHeader(), "CHECKOUT: YOUR INFORMATION");
        container.checkOutPage.enterCheckOutDetails(data.get("FirstName"), data.get("LastName"), data.get("ZipCode"));
        container.checkOutPage.clickContinueButton();
        Assert.assertEquals(container.cartPage.verifyCartListProductName(data.get("ProductName")), data.get("ProductName"));
        Assert.assertEquals(container.cartPage.verifyCartListProductName(data.get("SecondProductName")), data.get("SecondProductName"));
        Assert.assertTrue(container.checkOutPage.verifyPaymentAndShippingInfo());
        double total = Double.parseDouble(data.get("FirstProductPrice")) + Double.parseDouble(data.get("SecondProductPrice")) + Double.parseDouble(container.checkOutPage.getTaxAmount());
        Assert.assertEquals(container.checkOutPage.getTotalAmount(), total);
        container.checkOutPage.clickFinish();
        Assert.assertEquals(container.checkOutPage.getSuccessMessage(), "THANK YOU FOR YOUR ORDER");
    }

    @Test(dataProvider = "getData")
    public void verifyOderCancelOnCheckOutPage(Hashtable<String, String> data) throws Exception {
        startreport("Verify order has been canceled at Checkout");
        invoke();
        extentTest.log(LogStatus.PASS, "Order has been canceled at Checkout");
        container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
        container.productPage.clickAddToCart(data.get("ProductName"));
        container.productPage.clickAddToCart(data.get("SecondProductName"));
        container.productPage.clickCartButton();
        container.cartPage.clickCheckoutButton();
        Assert.assertEquals(container.checkOutPage.getHeader(), "CHECKOUT: YOUR INFORMATION");
        container.checkOutPage.enterCheckOutDetails(data.get("FirstName"), data.get("LastName"), data.get("ZipCode"));
        container.checkOutPage.clickContinueButton();
        container.checkOutPage.clickCancelButton();
        Assert.assertEquals(container.productPage.getHeader(), "PRODUCTS");
    }

    @DataProvider
    public Object[][] getData() {
        return DataUtil.getData(datatable, "ProductPage", "CAPMO_TASK");

    }
}
