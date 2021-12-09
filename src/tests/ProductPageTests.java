package tests;

import java.util.Hashtable;

import BasePage.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import util.DataUtil;

public class ProductPageTests extends TestBase {

	@Test(dataProvider = "getData")
	public void verifyProductPageIsNOTEmpty(Hashtable<String, String> data) throws Exception {
		startreport("verify Product Page is NOT Empty");
		invoke();
		extentTest.log(LogStatus.PASS, "Products list is not empty ");
		container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
		Assert.assertNotEquals(container.productPage.verifyListNotEmpty(), 0);
	}

	@Test(dataProvider = "getData", priority = 1)
	public void verifyProductTitleIsPresent(Hashtable<String, String> data) throws Exception {
		startreport("Test cases for the product page");
		invoke();
		extentTest.log(LogStatus.PASS, "Products title is present");
		container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
		Assert.assertEquals(container.productPage.getProductTitle(data.get("ProductName")), data.get("ProductName"));
	}

	@Test(dataProvider = "getData", priority = 1)
	public void verifyProductDescriptionIsPresent(Hashtable<String, String> data) throws Exception {
		startreport("Test cases for the product page");
		invoke();
		extentTest.log(LogStatus.PASS, "Products description is present");
		container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
		Assert.assertEquals(container.productPage.getProductDescription(data.get("ProductName")), data.get("ProductDescription"));
	}

	@Test(dataProvider = "getData", priority = 1)
	public void verifyProductPriceIsPresent(Hashtable<String, String> data) throws Exception {
		startreport("Test cases for the product page");
		invoke();
		extentTest.log(LogStatus.PASS, "Products price is present");
		container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
		Assert.assertEquals(container.productPage.getProductPrice(data.get("ProductName")), data.get("ProductPrice"));
	}

	@Test(dataProvider = "getData", priority = 1)
	public void verifyAllProductImagesArePresent(Hashtable<String, String> data) throws Exception {
		startreport("Test cases for the product page");
		invoke();
		extentTest.log(LogStatus.PASS, "Products images are present");
		container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
		Assert.assertTrue(container.productPage.getProductImages());
	}

	@Test(dataProvider = "getData", priority = 1)
	public void verifyProductAddedToCart(Hashtable<String, String> data) throws Exception {
		startreport("Test cases for the product page");
		invoke();
		extentTest.log(LogStatus.PASS, "Products added to the cart");
		container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
		Assert.assertEquals(container.productPage.verifyAddItemToCartButtonText(data.get("ProductName")), "REMOVE");
	}

	@Test(dataProvider = "getData", priority = 1)
	public void verifyProductPageOpen(Hashtable<String, String> data) throws Exception {
		startreport("Test cases for the product page");
		invoke();
		extentTest.log(LogStatus.PASS, "Products list is not empty");
		container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
		container.productPage.clickProduct(data.get("ProductName"));
		Assert.assertEquals(container.productPage.pageOpened(), data.get("ProductName"));
		container.productPage.clickBackToProduct();
		Assert.assertEquals(container.productPage.getHeader(), "PRODUCTS");
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "ProductPage", "CAPMO_TASK");

	}  
}
