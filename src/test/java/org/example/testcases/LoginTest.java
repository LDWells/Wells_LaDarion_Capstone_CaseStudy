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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    AlexAndNovaHomePage homePage;
    AlexAndNovaRegistrationPage registrationPage;
    AlexAndNovaLoginPage loginPage;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/LoginReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "localhost:8080");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "LaDarion Wells");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting AlexAndNova Login Report");
        htmlReporter.config().setReportName("AlexAndNova Login Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeTest
    public void setUpBrowser() {
        driver = SelectBrowser.StartBrowser("Firefox");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get("https://www.alexandnova.com/");
    }

    @Test(priority = 1)
    public void positive_user_login_test() throws InterruptedException, IOException {
        test = extent.createTest("positive_user_login_test", "Test Passed");
        homePage = new AlexAndNovaHomePage(driver);
        homePage.clickAccount();

        Thread.sleep(2000);
        loginPage = new AlexAndNovaLoginPage(driver);
        loginPage.inputEmail("LDWells@gmail.com");//register this on firefox web browser
        loginPage.inputPassword("12345");
        loginPage.clickLogin();

        if(ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true))
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();

        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[1]/p[1]/span[1]"))); //path to name on the side

        String expected = "Welcome, LD";                            //welcome message element
        String actual = driver.findElement(By.xpath("/html/body/div[3]/h1")).getText(); //welcome text
        Assert.assertEquals(actual, expected);
        //takes screenshot
        File shopPay = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shopPay, new File("src/test/resources/screenshots/positiveLogin.png"));

        //logout for the following test

        WebElement ele = driver.findElement(By.linkText("My account"));
        //Creating object of an Actions class
        Actions action = new Actions(driver);
        //Performing the mouse hover action on the target element.
        action.moveToElement(ele).perform();
        loginPage.clickLogout();
        Thread.sleep(5000);

    }

    @Test(priority = 2)
    public void negative_user_login_test() throws InterruptedException, IOException {

        test = extent.createTest("positive_user_login_test", "Test Passed");
        homePage = new AlexAndNovaHomePage(driver);
        homePage.clickAccount();

        Thread.sleep(2000);
        loginPage = new AlexAndNovaLoginPage(driver);
        loginPage.inputEmail("LDWell@gmail.com");
        loginPage.inputPassword("12345");
        loginPage.clickLogin();
        Thread.sleep(10000);

        if(ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true)) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();
        }
            Thread.sleep(10000);
            String expected = "Sorry! Please try that again.";
                                                                        //error message element
            String actual = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/form/p")).getText();
            Assert.assertEquals(actual, expected);
        //takes screenshot
        File shopPay = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shopPay, new File("src/test/resources/screenshots/negativeLogin.png"));

    }



    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException
    {
        Thread.sleep(20000);
        driver.quit();
    }
}
