package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

public class LoginPage extends PageBase {

    public LoginPage(WebDriver driver, ExtentTest extentTest) {
        super(driver, extentTest);
    }

    @FindBy(how = How.XPATH, using = "//input[@id='user-name']")
    WebElement NAME_FIELD;

    @FindBy(how = How.XPATH, using = "//input[@id='login-button']")
    WebElement LOGIN_BUTTON;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    WebElement PASSWORD_FIELD;

    @FindBy(how = How.XPATH, using = "//div[@class='error-message-container error']")
    WebElement ERROR_HINT;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Products')]")
    WebElement PRODUCT_HEADER;


    public void enterUserName(String name) throws Exception {
        waitForElementTobeClickable(NAME_FIELD);
        if (isElementPresent(NAME_FIELD)) {
            inputText(NAME_FIELD, name);
        } else throw new Exception();
    }

    public void enterPassword(String password) throws Exception {
        waitForElementTobeClickable(PASSWORD_FIELD);
        if (isElementPresent(PASSWORD_FIELD)) {
            inputText(PASSWORD_FIELD, password);
        } else throw new Exception();
    }

    public void loginButton() throws InterruptedException {
        waitForElementTobeClickable(LOGIN_BUTTON);
        click(LOGIN_BUTTON);
    }

    public void LoginNormal(String name, String password) throws Exception {
        enterUserName(name);
        enterPassword(password);
        loginButton();
    }

    public boolean verifyLoginSuccessful() throws InterruptedException {
        boolean flag = false;

        waitForPageToLoad();

        if (isElementPresent(PRODUCT_HEADER)) {
            flag = true;
        }
        return flag;
    }

    public String verifyLoginFailureWrongPassword(String name, String password) throws Exception {
        enterUserName(name);
        enterPassword(password);
        loginButton();
        waitForPageToLoad();
        waitForElementTobeClickable(ERROR_HINT);
        Assert.assertTrue(isElementPresent(ERROR_HINT));
        String passwordHint = ERROR_HINT.getText();

        return passwordHint;
    }

    public String verifyLoginFailureWrongUserName(String name, String password) throws Exception {
        enterUserName(name);
        enterPassword(password);
        loginButton();
        waitForPageToLoad();
        waitForElementTobeClickable(ERROR_HINT);
        Assert.assertTrue(isElementPresent(ERROR_HINT));
        String userNameHint = ERROR_HINT.getText();

        return userNameHint;
    }

}