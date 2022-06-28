package org.example.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.example.library.SelectBrowser;
import org.example.pages.AlexAndNovaHomePage;
import org.example.pages.AlexAndNovaLoginPage;
import org.example.pages.AlexAndNovaRegistrationPage;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.animation.model.KeyframeStyle;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ProductAddToCartTest {

    WebDriver driver;

    AlexAndNovaHomePage homePage;

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/AddToCartReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "localhost:8080");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "LaDarion Wells");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting AlexAndNova AddToCart Report");
        htmlReporter.config().setReportName("AlexAndNova AddToCart Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeTest
    public void setUpBrowser() {
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.get("https://www.alexandnova.com/");
    }


    @Test(priority = 1)
    public void verify_price_test() throws IOException, InterruptedException {
        test = extent.createTest("verify_price_test", "Test Passed");

        homePage = new AlexAndNovaHomePage(driver);
        homePage.clickOnBabyGears();

        driver.findElement(By.linkText("Bathing Duck Sprinkler Baby Shower")).click();

        String expected = "$54.95 USD";
        By price = By.xpath("/html/body/div[3]/div[1]/section/div/div[2]/div/p/span[1]/span");
        String actual = driver.findElement(price).getText();
        Assert.assertEquals(actual, expected);
        Thread.sleep(5000);

        //take screenshot
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/verifyPrice.png"));


    }


    @Test(priority = 2)
    public void add_to_cart_test() throws InterruptedException, IOException {
        test = extent.createTest("add_to_cart_test", "Test Passed");
        homePage = new AlexAndNovaHomePage(driver);
       // homePage.clickOnHomeTab(); //navigates back to homepage
        homePage.clickOnBestSellers();
        Thread.sleep(5000);

        //productPage.clickOnCoat();
        driver.findElement(By.linkText("Holly Oversized Denim Fur Hooded Winter Coat Jacket")).click();

        //12-18 Months
        By years = By.xpath("/html/body/div[3]/div[1]/section/div/div[3]/div/form/div[1]/div[1]/div[1]/div[2]/div[1]/label");
        //blue coat
        By color = By.xpath("/html/body/div[3]/div[1]/section/div/div[3]/div/form/div[1]/div[1]/div[2]/div[2]/div[2]/label");
        driver.findElement(years).click();
        driver.findElement(color).click();
       // productPage = new AlexAndNovaProductPage(driver);
        By quantity = By.className("product-option-quantity");
        driver.findElement(quantity).clear();
        driver.findElement(quantity).sendKeys("2");

        //productPage.clickOnAddToCart();
        By addToCart = By.className("add-to-cart");
        driver.findElement(addToCart).click();
        Thread.sleep(3000);

        String expected = "Holly Oversized Denim Fur Hooded Winter Coat Jacket - 12-18 Months / Blue has been successfully added to your cart. Feel free to continue shopping or check out.";
       // By successMsg = By.xpath("/html/body/div[3]/div[1]/section/div/div[3]/div/form/div[5]");
        String actual = driver.findElement(By.xpath("/html/body/div[3]/div[1]/section/div/div[3]/div/form/div[5]")).getText();
        Assert.assertEquals(actual, expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/addToCart.png"));


        Thread.sleep(3000);

    }
    // to be performed immediately after add_to_cart_test
    @Test(priority = 3)
    public void refresh_page_test() throws IOException {
        test = extent.createTest("refresh_page_test", "Test Passed");
        By amountInCart = By.className("cart-count-number");
        //to refresh
        driver.navigate().to(driver.getCurrentUrl());


        String expected = "2";
        String actual = driver.findElement(By.className("cart-count-number")).getText();
        Assert.assertEquals(actual, expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/refresh.png"));
    }

    // to be performed immediately after refresh_page_test
    @Test(priority = 4)
    public void increase_quantity_test() throws InterruptedException, IOException {
        test = extent.createTest("increase_quantity_test", "Test Passed");

        By quantity = By.className("product-option-quantity");
        driver.findElement(quantity).clear();
        driver.findElement(quantity).sendKeys("4");
        By addToCart = By.className("add-to-cart");
        driver.findElement(addToCart).click();
        Thread.sleep(3000);

        String expected = "6"; //there were previously 2 items in cart
        String actual = driver.findElement(By.className("cart-count-number")).getText();
        Assert.assertEquals(actual, expected);
        Thread.sleep(3000);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/increaseQuantity.png"));

    }

    //to be performed after increase_quantity_test
    @Test(priority = 5)
    public void verify_quantity_test() throws InterruptedException, IOException {
        test = extent.createTest("verify_quantity_test", "Test Passed");
        By amountInCart = By.className("cart-count-number");

        String expected = "6"; //there were previously 2 items in cart
        String actual = driver.findElement(By.className("cart-count-number")).getText();
        Assert.assertEquals(actual, expected);
        Thread.sleep(3000);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/verifyQuantity.png"));

    }

    @Test(priority = 6)
    public void remove_from_cart_test() throws InterruptedException, IOException {
        test = extent.createTest("remove_from_cart_test", "Test Passed");

        driver.findElement(By.className("cart-count-text")).click();
        Thread.sleep(7000);


        //productPage.removeItem();
        //driver.findElement(By.className("remove navigable")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/section/form/table/tbody/tr/td[1]/a[2]")).click();


        String expected = "0";
        String actual = driver.findElement(By.className("cart-count-number")).getText();
        Assert.assertEquals(actual, expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/removeFromCart.png"));
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
