import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/mike.zorin/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void assertElementHasTextTest() {

        waitForElementByXpathAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "The element did not open!",
                5);
        waitForElementByXpathAndSandKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "The element for this locator does not contain text!",
                5);
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "The element did not clear the field!",
                5);
        waitForElementByIdAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "The element was not closed!",
                5);


    }


    private WebElement assertElementHasText(By by, String errorMessage, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement assertElementHasText(By by, String errorMessage) {
        return assertElementHasText(by, errorMessage, 5);

    }

    private WebElement waitForElementByXpathAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, errorMessage, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpathAndSandKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, errorMessage, 5);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementByIdAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, errorMessage, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementByIdAndSandKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, errorMessage, 5);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }
}
