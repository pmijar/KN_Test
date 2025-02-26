package org.example;

import Utils.DataReader;
import Utils.Global_Variables;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


/**
 * Hello world!
 */
public class App {

    static private String productselected = "//div[@role='list' and @aria-label='Product']//a[contains(text(),'iPhone')]";
    //static private String modelselected = "//div[@role='list' and @aria-label='All models.']//h3[contains(.,'16') and contains(., 'Plus')]";
    static private String modelselected = "//div[@role='list' and @aria-label='All models.']//h3[contains(.,'^^^VER^^^') and contains(., '^^^MODEL^^^')]";
    static private String buyOption = "//a[@data-autom='DigitalMat-buynow-button-1-0']"; // Buy button click
    static private String buyModelSize = "//input[@name='dimensionScreensize' and @value='^^^SCREENSIZE^^^']/parent::*";
    static private String buyModelColor = "//input[@name='dimensionColor' and @value='^^^COLOR^^^']/parent::*";
    static private String buyModelCapacity = "//input[@name='dimensionCapacity' and @value='^^^CAPACITY^^^']/parent::*";
    static private String tradein = "//input[@id='noTradeIn']/parent::*";
    static private String financeoptions = "//input[@name='purchase_option_group' and @value='fullprice']/parent::*";//static private String carrierselection = "//input[@name='carrierModel' and @value='TMOBILE_IPHONE16']/parent::*";
    static private String carrierselection = "//input[@name='carrierModel' and @value='UNLOCKED/US']/parent::*";
     static private String coverageoptions = "(//input[starts-with(@id,'applecareplus') and contains(@id, 'noapplecare')]/parent::*)[1]";
    static private String continuebutton = "(//button[@type='submit'])[2]";
    static private String reviewbutton = "(//button[@type='submit'])[2]";
    //Store Details
    static private String storelocation = "//button[@data-autom='bag-seemorestores-link']";
    static private String storeaddressentry = "//input[@data-autom='bag-storelocator-input']";
    static private boolean updateStore = false;
    static private String address = "Somerset, NJ";
    static private String storelocationupdated =
            "((//ul[@class='rt-storelocator-store-group form-selector-group'])[1]/li/*[contains(., 'Available')])[1]";
    static private String storeupdatebutton = "//button[@data-autom='bag-storelocator-confirmstore']";
    static private String checkoutbutton = "//button[@id='shoppingCart.actions.checkoutOtherPayments']";

    static private WebDriver driver = null;
    static private WebDriverWait wait;

    @BeforeTest
    public void setup() throws InterruptedException, IOException {
        driver = new ChromeDriver();
        driver.get(Global_Variables.BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Global_Variables.IMPLICIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Global_Variables.PAGE_LOAD_TIMEOUT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(Global_Variables.EXPLICIT_TIMEOUT));
        List<HashMap<String, String>> data = DataReader.getJsonData(System.getProperty("user.dir") + Global_Variables.TESTDATA_LOCATION);
        updateStore = Global_Variables.UPDATE_STORE_LOCATION;

        modelselected = modelselected.replace("^^^VER^^^", data.get(0).get("VER"));
        modelselected = modelselected.replace("^^^MODEL^^^", data.get(0).get("MODEL"));
        buyModelSize = buyModelSize.replace("^^^SCREENSIZE^^^", data.get(0).get("SCREENSIZE"));
        buyModelColor = buyModelColor.replace("^^^COLOR^^^", data.get(0).get("COLOR"));
        buyModelCapacity = buyModelCapacity.replace("^^^CAPACITY^^^", data.get(0).get("CAPACITY"));
        address = data.get(0).get("ADDRESS");
        System.out.println("data capacity : " + data.get(0).get("CAPACITY"));
        System.out.println(modelselected);
        System.out.println(buyModelSize);
        System.out.println(buyModelColor);
        System.out.println(buyModelCapacity);
    }

    public static void selectProduct() throws InterruptedException {
        // Select iphone product from the store
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productselected))).click();
    }

    public static void selectModelBuy() throws InterruptedException {
        //select the iphone version (15/16) and model name (Pro, Plus)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(modelselected))).click();
        // Click on buy button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buyOption))).click();
    }

    public static void buyOptions() throws InterruptedException {
        //Screen Size selection
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buyModelSize))).click();

        //Color Selection
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buyModelColor))).click();

        //Capacity Selection
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buyModelCapacity))).click();

        //Trade in Selection
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tradein))).click();

        //Finance option Selection
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(financeoptions))).click();
        Thread.sleep(3000);

        //Carrier Selection
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(carrierselection))).click();
        Thread.sleep(3000);

        //coverage Selection
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(coverageoptions))).click();
        Thread.sleep(3000);

        //Submit the selections
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(continuebutton))).click();

    }

    public static void reviewItems() throws InterruptedException {
        //Click on Review Button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reviewbutton))).click();
        // Updating to store address based on updateStore flag
        if (updateStore)
            updateStoreLocation();
        // Check out the product
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkoutbutton))).click();
    }

    private static void updateStoreLocation() throws InterruptedException {

        //Click on store update link in review page
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(storelocation))).click();
        System.out.println("In the Store location update page");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(storeaddressentry))).click();
        // Clearing the existing default store location, before inputing the new address location
        clearField(driver.findElement(By.xpath(storeaddressentry)));

        //Once cleared click on the Address field and enter the new store location e.g: SOMERSET, NJ
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(storeaddressentry))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(storeaddressentry))).sendKeys(address, Keys.ENTER);

        //System.out.println("Update the store location for the customer");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(storelocationupdated))).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(storelocationupdated))).sendKeys(Keys.ENTER);
        //System.out.println("Clicked on first address");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(storeupdatebutton))).click();
        //System.out.println("Clicked on Confirm Store address");
        Thread.sleep(3000);
    }

    private static void clearField(WebElement we) {
        int len = we.getAttribute("value").length();
        System.out.println("text is : " + we.getAttribute("value"));
        System.out.println("length is : " + len);
        while (len > 0) {
            we.sendKeys(Keys.BACK_SPACE);
            len--;
        }
    }

    @Test
    public void runTest() throws InterruptedException {
        selectProduct();
        selectModelBuy();
        buyOptions();
        reviewItems();
        Thread.sleep(7000);
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
