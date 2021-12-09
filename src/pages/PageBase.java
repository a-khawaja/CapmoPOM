package pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    /**
     * @author Hassan Amjad
     */
    static Logger log = Logger.getLogger(PageBase.class);
    public WebDriver driver;
    public Actions actions;
    public ExtentTest extentTest;

    /**
     * Constructor of the class.
     *
     * @param driver - driver
     */

    public PageBase(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    /**
     * Method to clear and input the text for an WebElement.
     *
     * @param element - WebElement to be clickable
     * @param value   - Value to be inserted
     */

    public void inputText(WebElement element, String value) {
        log.info("Executing INPUT text command for the element:" + element.toString() + " with value: " + value);
        waitForElementTobeClickable(element);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Method to wait for the element on the web page to be clickable
     *
     * @param element - WebElement to be clickable
     */

    public void waitForElementTobeClickable(WebElement element) {
        log.info("Executing waitForElementTobeClickable command for the element: " + element);
        WebDriverWait w = new WebDriverWait(driver, 30);
        try {
            log.info("Waiting for the element:" + element + " to be clickable");
            w.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to verify whether the element is present or not.
     *
     * @param element - WebElement to be check whether present or not.
     * @return - TRUE, if element is present.
     */

    public boolean isElementPresent(WebElement element) {
        log.info("Executing isElementPresent command on the element: " + element);
        boolean present = false;
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                present = true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return present;
    }

    /**
     * Method to click on an element.
     *
     * @param element - WebElement to be clicked.
     */

    public void click(WebElement element) throws InterruptedException {
        log.info("Executing CLICK command on the element: " + element);
        if (isElementPresent(element)) {
            waitForElementTobeClickable(element);
            try {
                element.click();
            } catch (ElementClickInterceptedException e) {
                wait(2000);
                element.click();
            }
        } else {
            System.out.println("Element not visible" + element);
        }
    }

    /**
     * Method which waits for the complete page to load
     */

    public void waitForPageToLoad() {
        log.info("Executing waitForPageToLoad command");
        log.info("Waiting for the page to load completely");
        String pageLoadStatus;
        do {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            pageLoadStatus = (String) js.executeScript("return document.readyState");
        } while (!pageLoadStatus.equals("complete"));
        log.info("Page is loaded completely");
    }

    /**
     * Method to sleep for desired time
     *
     * @param milliSeconds - Time in milliseconds for the statement to wait
     */
    public void wait(int milliSeconds) {
        try {
            log.info("Waiting for " + milliSeconds + " milli seconds");
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


