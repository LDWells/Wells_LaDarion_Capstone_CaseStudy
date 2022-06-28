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

        /*
        //testing paypal
        //checkoutPage.payWithPaypal();
         driver.findElement(By.className("paypal-button-label-container")).click();
        Thread.sleep(7000);
        //navigates to paypal popup
        // driver.navigate().to("https://www.paypal.com/checkoutnow?locale.x=en_US&fundingSource=paypal&sessionID=uid_d8eb55cb8d_mty6mdk6mdi&buttonSessionID=uid_e3e90667a3_mty6mta6mdi&env=production&fundingOffered=paypal&logLevel=warn&sdkMeta=eyJ1cmwiOiJodHRwczovL3d3dy5wYXlwYWxvYmplY3RzLmNvbS9hcGkvY2hlY2tvdXQubWluLmpzIn0&uid=0aba00466c&version=min&token=EC-5CJ775373A863743F&xcomponent=1");
        driver.get("https://www.paypal.com/checkoutnow?locale.x=en_US&fundingSource=paypal&sessionID=uid_d8eb55cb8d_mty6mdk6mdi&buttonSessionID=uid_e3e90667a3_mty6mta6mdi&env=production&fundingOffered=paypal&logLevel=warn&sdkMeta=eyJ1cmwiOiJodHRwczovL3d3dy5wYXlwYWxvYmplY3RzLmNvbS9hcGkvY2hlY2tvdXQubWluLmpzIn0&uid=0aba00466c&version=min&token=EC-5CJ775373A863743F&xcomponent=1");
        Thread.sleep(10000);
        File paypal = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(paypal, new File("src/test/resources/screenshots/paypal.png"));
        driver.navigate().to("https://www.alexandnova.com/10856759353/checkouts/ee3a8e3a4ffd6be2c706e2e4bb154580?skip_shopify_pay=true&locale=en");
        Thread.sleep(15000);


        // checkoutPage.payWithGooglePay();
        driver.findElement(By.className("kEwctmM5pguv6XkPR8mx6")).click();
        Thread.sleep(5000);
        File googlePay = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(googlePay, new File("src/test/resources/screenshots/googlePay.png"));
        driver.navigate().to("https://www.alexandnova.com/10856759353/checkouts/ee3a8e3a4ffd6be2c706e2e4bb154580?skip_shopify_pay=true&locale=en");
        Thread.sleep(10000);


        //checkoutPage.payWithFacebookPay();
        driver.findElement(By.className("_1LP9NPTft85QosIXd3_zOV _3G6VJhJYno-AX3-X38f1TA _2zarRkvJ2j83NID3Q3t0Ix _24a_n1dXCrAWpcF2hfyvI5")).click();
        Thread.sleep(2500);
        //navigates to Facebook popup
        driver.navigate().to("https://www.facebook.com/login/?next=%2Fpayments%2Foffsite%2Fcheckout%2F%3Fopener%3Dhttps%253A%252F%252Fwww.alexandnova.com%26receiver_id%3DSHOPIFY_LIVE%253A%253A10856759353%26request_id%3Dc55cd1f6-1b9c-4e95-a240-14ea9051ca4e%26is_deskt%3D1%26is_login_redirect%3D1%26supported_container_types%255B0%255D%3Dbasic-card-v1%26mode%3DLIVE%26session_id%3Dupl_1656178651715_bc047609-c888-4fe7-9040-2b3f74609faa&no_next_msg&hide_registration=1");
        Thread.sleep(5000);
        File facebookPay = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(facebookPay, new File("src/test/resources/screenshots/facebookPay.png"));
        driver.navigate().to("https://www.alexandnova.com/10856759353/checkouts/ee3a8e3a4ffd6be2c706e2e4bb154580?skip_shopify_pay=true&locale=en");
        Thread.sleep(10000);


         */


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
