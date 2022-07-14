import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
                By.xpath("//*[@text='Search Wikipedia']"),
                "The element did not open!",
                5);
        waitForElementByIdAndSandKeys(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "The element for this locator does not contain text!",
                5);
        waitForElementByXpathAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5);
        assertElementHasText(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15);
        waitForElementByIdAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5);
        waitForElementByIdAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5);
        waitForElementByIdAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5);
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot delete 'My reading list'",
                5);
        String nameOfFolder = "Learning programming";
        waitForElementByIdAndSandKeys(
                By.id("org.wikipedia:id/text_input"),
                nameOfFolder,
                "Cannot pur text 'Learning programming' in article title",
                5);
        waitForElementByIdAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'Ok' button ",
                5);
        waitForElementByIdAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5);
        waitForElementByXpathAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "The element did not open!",
                5);
        waitForElementByIdAndSandKeys(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "The element for this locator does not contain text!",
                5);
        waitForElementByXpathAndClick(
                By.xpath("//android.widget.TextView[@text='Island of Indonesia, Southeast Asia']"),
                "Cannot click to element Java",
                5);
        waitForElementByIdAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5);
        waitForElementByIdAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5);
        waitForElementByXpathAndClick(
                By.xpath("//android.widget.TextView[@text='Learning programming']"),
                "Cannot click to "+ nameOfFolder+ "",
                5);
        waitForElementByIdAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5);
        waitForElementByXpathAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigate button My list",
                5);
                waitForElementByIdAndClick(
                By.xpath("//*[@text='" + nameOfFolder + "']"),
                "Cannot find created folder",
                5);
              swipeElementToLeft(
              By.xpath("//*[@text='Java (programming language)']"),
              "Cannot find saved folder");
        waitForElementNotPresent(
                By.xpath("//*[@text='Island of Indonesia, Southeast Asia']"),
                "Cannot delete saved article",
                5);
        waitForElementByXpathAndClick(
                By.xpath("//android.widget.TextView[@text='Java']"),
                "Cannot click to element Java",
                5);
        assertElementHasText(
                By.xpath("//android.widget.TextView[@text='Java']"),
                "The article is missing");

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
    protected boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = assertElementHasText(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;



    }

    protected void swipeUp(int timeOfSwipe) {

        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.9);
        int end_y = (int) (size.height * 0.1);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeQuick() {
        swipeUp(400);
    }

    protected void swipeUpFindElemet(By by, String errorMessage, int maxSwiped) {

        int already = 0;
        while (driver.findElements(by).size() == 0) {
            if (already > maxSwiped) {
                assertElementHasText(by, "Cannot find element \n" + errorMessage, 0);
                return;
            }
            swipeQuick();
            ++already;

        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = assertElementHasText(
                by,
                errorMessage,
                15);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(400)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}