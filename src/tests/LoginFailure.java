package tests;

import java.util.Hashtable;

import BasePage.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import util.DataUtil;

public class LoginFailure extends TestBase {

	@Test(dataProvider = "getData", priority = 1)
	public void LoginFailure(Hashtable<String, String> data) throws Exception {
		startreport("Verify Sign Up failure");
		invoke();
		extentTest.log(LogStatus.PASS, "Log in Failed as expected");
		if (data.get("Description").equalsIgnoreCase("Wrong username")){
			Assert.assertEquals(container.loginPage.verifyLoginFailureWrongUserName(data.get("Username"), data.get("Password")), "Epic sadface: Username and password do not match any user in this service");
			Assert.assertFalse(container.loginPage.verifyLoginSuccessful());
		} else if (data.get("Description").equalsIgnoreCase("Wrong password")){
			Assert.assertEquals(container.loginPage.verifyLoginFailureWrongPassword(data.get("Username"), data.get("Password")), "Epic sadface: Username and password do not match any user in this service");
			Assert.assertFalse(container.loginPage.verifyLoginSuccessful());
		}
	}

	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "LoginFailure", "CAPMO_TASK");

	}  
}
