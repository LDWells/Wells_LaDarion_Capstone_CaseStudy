package org.example.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.example.library.SelectBrowser;
import org.example.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CheckoutTest {
    WebDriver driver;
    AlexAndNovaHomePage homePage;
    AlexAndNovaRegistrationPage registrationPage;
    AlexAndNovaLoginPage loginPage;

    AlexAndNovaCheckoutPage checkoutPage;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/CheckoutReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "localhost:8080");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "LaDarion Wells");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting AlexAndNova Checkout Report");
        htmlReporter.config().setReportName("AlexAndNova Checkout Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeTest
    public void setUpBrowser() {
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get("https://www.alexandnova.com/");
    }

    @Test(priority = 1)
    public void apply_for_discount_test() throws IOException, InterruptedException {
        test = extent.createTest("apply_for_discount_test", "Test Passed");
        homePage = new AlexAndNovaHomePage(driver);
        homePage.clickOnClearance();
        Thread.sleep(2000);

        driver.findElement(By.linkText("Skipper Mesh First Walker Sneakers Shoes")).click();

        //6.5 shoe size
        By years = By.xpath("/html/body/div[3]/div[1]/section/div/div[3]/div/form/div[1]/div[1]/div[1]/div[2]/div[4]/label");
        driver.findElement(years).click();

        //navy blue option
        By color = By.xpath("/html/body/div[3]/div[1]/section/div/div[3]/div/form/div[1]/div[1]/div[2]/div[2]/div[5]/label");
        driver.findElement(color).click();

        //to access checkout page
        By addToCart = By.className("add-to-cart");
        driver.findElement(addToCart).click();
        Thread.sleep(2500);
        driver.findElement(By.className("cart-count-number")).click();
        Thread.sleep(7000);
        driver.findElement(By.xpath("/html/body/div[3]/div/section/form/div/div/div[2]/button/span")).click();
        Thread.sleep(10000);


           // checkoutPage = new AlexAndNovaCheckoutPage(driver);
           // checkoutPage.enterDiscount("OHBABY15");
            driver.findElement(By.id("checkout_reduction_code")).sendKeys("OHBABY15");
            //checkoutPage.clickApplyDiscount();
            driver.findElement(By.id("checkout_submit")).click();
            Thread.sleep(3000);

        //takes screenshot
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/discount.png"));

    }

    @Test(priority = 2)
    public void payment_mode_check_test() throws IOException, InterruptedException {

        //testing shopPay
      // checkoutPage = new AlexAndNovaCheckoutPage(driver);
      // checkoutPage.payWithShopPay();
        driver.findElement(By.className("h7yAG5cgdBp_GhZjXmVIA")).click();
        Thread.sleep(10000);
        //takes screenshot
        File shopPay = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shopPay, new File("src/test/resources/screenshots/shopPay.png"));
        //navigates back to payment page with current selected item in cart
        //https://www.alexandnova.com/10856759353/checkouts/045dab2cf4bef8adc54649b4af8f2026
        driver.navigate().to("https://www.alexandnova.com/10856759353/checkouts/ee3a8e3a4ffd6be2c706e2e4bb154580?skip_shopify_pay=true&locale=en");
        Thread.sleep(15000);


    }

    @Test(priority = 3)
    public void checkout_negative_test() throws InterruptedException {
        //to add an item to the cart
        test = extent.createTest("checkout_negative_test", "Test Passed");

       // checkoutPage = new AlexAndNovaCheckoutPage(driver);
       // checkoutPage.clickContinueToShipping();
        driver.findElement(By.id("continue_button")).click();



        String expectedEmail = "Enter a valid email";
        String actualEmail = driver.findElement(By.id("error-for-email")).getText();
        Assert.assertEquals(actualEmail, expectedEmail);

        String expectedFirstName = "Enter a first name";
        String actualFirstName = driver.findElement(By.id("error-for-first_name")).getText();
        Assert.assertEquals(actualFirstName, expectedFirstName);


        String expectedLastName = "Enter a last name";
        String actualLastName = driver.findElement(By.id("error-for-last_name")).getText();
        Assert.assertEquals(actualLastName, expectedLastName);


        String expectedAddress = "Enter an address";
        String actualAddress = driver.findElement(By.id("error-for-address1")).getText();
        Assert.assertEquals(actualAddress, expectedAddress);


        String expectedCity = "Enter a city";
        String actualCity = driver.findElement(By.id("error-for-city")).getText();
        Assert.assertEquals(actualCity, expectedCity);

        String expectedZip = "Enter a ZIP code";
        String actualZip = driver.findElement(By.id("error-for-zip")).getText();
        Assert.assertEquals(actualZip, expectedZip);

    }

    //to be performed after negative test
    @Test(priority = 4)
    public void checkout_positive_test() throws IOException, InterruptedException {

        //page elements stopped working
        //checkoutPage = new AlexAndNovaCheckoutPage(driver);
        //checkoutPage.enterEmail("LDWells@gmail.com");
        driver.findElement(By.id("checkout_email")).sendKeys("LDWells@gmail.com");
        //checkoutPage.enterFirstName("LD");
        driver.findElement(By.id("checkout_shipping_address_first_name")).sendKeys("LD");
       //checkoutPage.enterLastName("Wells");
        driver.findElement(By.id("checkout_shipping_address_last_name")).sendKeys("Wells");
       // checkoutPage.enterAddress("123 E. Main St");
        driver.findElement(By.id("checkout_shipping_address_address1")).sendKeys("123 E. Main St");
        //checkoutPage.enterCity("Denver");
        driver.findElement(By.id("checkout_shipping_address_city")).sendKeys("Denver");
        //checkoutPage.enterZipCode("80115");
        driver.findElement(By.id("checkout_shipping_address_zip")).sendKeys("80015");
        //checkoutPage.clickContinueToShipping();
        driver.findElement(By.id("continue_button")).click();
        Thread.sleep(3000);
        //checkoutPage.clickContinueToPayment();
        driver.findElement(By.id("continue_button")).click();
        Thread.sleep(3000);

        //take screenshot
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/positiveCheckout.png"));



    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException
    {
        Thread.sleep(10000);
        driver.quit();
    }
}
