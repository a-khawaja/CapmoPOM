package pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CheckOutPage extends PageBase {

    public CheckOutPage(WebDriver driver, ExtentTest extentTest) {
        super(driver, extentTest);

    }

    @FindBy(how = How.XPATH, using = "//span[@class='title']")
    WebElement HEADER;

    @FindBy(how = How.XPATH, using = "//input[@id='first-name']")
    WebElement FIRST_NAME;

    @FindBy(how = How.XPATH, using = "//input[@id='last-name']")
    WebElement LAST_NAME;

    @FindBy(how = How.XPATH, using = "//input[@id='postal-code']")
    WebElement ZIPCODE;

    @FindBy(how = How.XPATH, using = "//input[@id='continue']")
    WebElement CONTINUE;

    @FindBy(how = How.XPATH, using = "//button[@id='cancel']")
    WebElement CANCEL;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Payment Information:')]")
    WebElement PAYMENT_INFO_HEADER;

    @FindBy(how = How.XPATH, using = "//div[@class='summary_info']//div[2]")
    WebElement PAYMENT_INFO_TEXT;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Shipping Information:')]")
    WebElement SHIPPING_INFO_HEADER;

    @FindBy(how = How.XPATH, using = "//div[@class='summary_info']//div[4]")
    WebElement SHIPPING_INFO_TEXT;

    @FindBy(how = How.XPATH, using = "//div[@class='summary_total_label']")
    WebElement TOTAL_SUM;

    @FindBy(how = How.XPATH, using = "//button[@id='finish']")
    WebElement FINISH_BUTTON;

    @FindBy(how = How.XPATH, using = "//h2[@class='complete-header']")
    WebElement SUCCESS_HEADER;

    @FindBy(how = How.XPATH, using = " //div[@class='summary_tax_label']")
    WebElement TAX;


    public String getHeader() {
        return HEADER.getText();
    }

    public void enterFirstName(String name) throws Exception {
        waitForElementTobeClickable(FIRST_NAME);
        if (isElementPresent(FIRST_NAME)) {
            inputText(FIRST_NAME, name);
        } else throw new Exception();
    }

    public void enterLastName(String password) throws Exception {
        waitForElementTobeClickable(LAST_NAME);
        if (isElementPresent(LAST_NAME)) {
            inputText(LAST_NAME, password);
        } else throw new Exception();
    }

    public void enterZipCode(String password) throws Exception {
        waitForElementTobeClickable(ZIPCODE);
        if (isElementPresent(ZIPCODE)) {
            inputText(ZIPCODE, password);
        } else throw new Exception();
    }

    public void clickContinueButton() throws InterruptedException {
        click(CONTINUE);
    }

    public void clickCancelButton() throws InterruptedException {
        click(CANCEL);
    }

    public void enterCheckOutDetails(String first, String last, String Zipcode) throws Exception {
        enterFirstName(first);
        enterLastName(last);
        enterZipCode(Zipcode);
    }

    public boolean verifyPaymentAndShippingInfo() {
        boolean flag1 = false;

        String paymentinfo = PAYMENT_INFO_TEXT.getText();
        String shippinginfo = SHIPPING_INFO_TEXT.getText();

        if (PAYMENT_INFO_HEADER.isDisplayed() && SHIPPING_INFO_HEADER.isDisplayed() && !paymentinfo.equalsIgnoreCase("") && !shippinginfo.equalsIgnoreCase(""))
            flag1 = true;

        return flag1;
    }

    public double getTotalAmount() {
        String total = TOTAL_SUM.getText();
        return Double.parseDouble(total.split("\\$")[1]);
    }

    public void clickFinish() throws InterruptedException {
        click(FINISH_BUTTON);
    }

    public String getSuccessMessage() {
        return SUCCESS_HEADER.getText();
    }

    public String getTaxAmount() {
        String tax = TAX.getText();
        return tax.split("\\$")[1];
    }
}
