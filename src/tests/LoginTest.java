package tests;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.TestBase;

import util.DataUtil;

public class LoginTest extends TestBase {

	@Test(dataProvider = "getData", priority = 1)
	public void LoginPage(Hashtable<String, String> data) throws Exception {
		startreport("Verify Login feature");
		invoke();
		extentTest.log(LogStatus.PASS, "Logged in ");
		if (data.get("Description").equalsIgnoreCase("Happy path")) {
			container.loginPage.LoginNormal(data.get("Username"), data.get("Password"));
			Assert.assertTrue(container.loginPage.verifyLoginSuccessful());
		}
	}

	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "LoginTest", "CAPMO_TASK");

	}  
}
