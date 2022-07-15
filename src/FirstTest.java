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
import java.util.List;

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
    public void testAmountOfEmptySearch() {

        waitForElementByXpathAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "The element did not open!",
                5);
        String searchLine = "Java";
        waitForElementByIdAndSandKeys(
                By.xpath("//*[@text='Search…']"),
                searchLine,
                "Cannot find search input",
                5);

        String searchResultLocator = "//android.widget.TextView[@test='Java']";
        String emptyResultLabel = "//*[@text='Java']";

        assertElementHasText(
                By.xpath(emptyResultLabel),
                "Cannot find empty result label by the request " + searchLine + " ",
                15);
        assertElementPresent(
                By.xpath(searchResultLocator),
                "We've found same result by request " + searchLine);
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


    private WebElement waitForElementByIdAndSandKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, errorMessage, 5);
        element.sendKeys(value);
        return element;
    }


    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);

        if (amountOfElements < 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be noy present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }

    }
}