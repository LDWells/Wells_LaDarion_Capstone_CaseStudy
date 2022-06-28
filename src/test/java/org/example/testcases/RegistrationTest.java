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

public class RegistrationTest {

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
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/RegistrationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "localhost:8080");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "LaDarion Wells");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting AlexAndNova Registration Report");
        htmlReporter.config().setReportName("AlexAndNova Registration Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeTest
    public void setUpBrowser() {
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.alexandnova.com/");
    }

    @Test(priority = 1)
    public void user_registration_test() throws InterruptedException {

        test = extent.createTest("user_registration_test", "Test Passed");

        //process to register as a new user
        homePage = new AlexAndNovaHomePage(driver);
        homePage.clickAccount();

        //to perform Scroll on application using Selenium
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,450)", "");
//        Thread.sleep(3000);
//
//        loginPage = new AlexAndNovaLoginPage(driver);
//        loginPage.clickRegister();
//
//         registrationPage = new AlexAndNovaRegistrationPage(driver);
//        registrationPage.inputFirstName("john");
//        registrationPage.inputLastName("fink");
//        registrationPage.inputEmail("johnfink1739@test.com");
//        registrationPage.inputPassword("P@ssword");
//        registrationPage.clickOnRegister();
//
//        if(ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true))
//        {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();
//
//        }
//

    }

    @Test(priority = 2)
    public void verify_new_user_registration_test() throws InterruptedException, IOException {
        test = extent.createTest("verify_new_user_registration_test", "Test Passed");

        //once the user is registered, user will login to verify registration
        Thread.sleep(5000);
        loginPage = new AlexAndNovaLoginPage(driver);
        loginPage.inputEmail("johnfink1739@gmail.com");
        loginPage.inputPassword("P@ssword");
        loginPage.clickLogin();

        //if there is a captcha, pause automation to solve captcha
        if(ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true))
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();

        }

        //waits until we see the users full name on left side of the screen
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[1]/p[1]/span[1]")));


        String expected = "Welcome, john";
        By welcomeMsg = By.xpath("/html/body/div[3]/h1");
        String actual = driver.findElement(welcomeMsg).getText();
        Assert.assertEquals(actual, expected);
        Thread.sleep(5000);
        //takes screenshot
        File shopPay = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shopPay, new File("src/test/resources/screenshots/verifyNewUser.png"));

        //logout for the following test

        WebElement ele = driver.findElement(By.linkText("My account"));
        //Creating object of an Actions class
        Actions action = new Actions(driver);
        //Performing the mouse hover action on the target element.
        action.moveToElement(ele).perform();
        loginPage.clickLogout();
        Thread.sleep(5000);

    }

    @Test(priority = 3)
    public void email_validation_test() throws InterruptedException, IOException {
        test = extent.createTest("email_validation_test", "Test Passed");
        // element that only exists in scope of the test
        // By errMsg = By.xpath("/html/body/div[3]/div[2]/div[1]/form/p");

        homePage = new AlexAndNovaHomePage(driver);
        homePage.clickAccount();
        Thread.sleep(2500);

        //@ written in words
        loginPage = new AlexAndNovaLoginPage(driver);
        loginPage.inputEmail("testAtgmail.com");
        loginPage.clickLogin();
        WebDriverWait waitForInput = new WebDriverWait(driver, Duration.ofSeconds(300));
        waitForInput.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-title")));
        Thread.sleep(10000);

        //if there is a captcha, wait until submit button is clicked
        if (ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true)) {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();

        }

        Thread.sleep(10000);
        String expected = "Sorry! Please try that again.";         //error message element
        String actual = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/form/p")).getText();
        Assert.assertEquals(actual, expected);
        Thread.sleep(5000);

/*
        //email without the dot
        loginPage.inputEmail("test@gmailcom");
        loginPage.clickLogin();
        waitForInput.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-title")));

        if (ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true)) {
            WebDriverWait waitForCaptcha = new WebDriverWait(driver, Duration.ofSeconds(500));
            waitForCaptcha.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();
        }
        Assert.assertEquals(actual, expected);

        //loginPage.inputEmail("test@gmail");
         driver.findElement(By.id("ispbxii_1")).sendKeys("test@gmail");
        loginPage.clickLogin();
        waitForInput.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-title")));


        if (ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true)) {
            WebDriverWait waitForCaptcha = new WebDriverWait(driver, Duration.ofSeconds(500));
            waitForCaptcha.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();

        }
        Thread.sleep(5000);
        String expected2 = "Sorry! Please try that again.";                            //error message element
        String actual2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/form/p")).getText();
        Assert.assertEquals(actual2, expected2);


        //random string
        // loginPage = new AlexAndNovaLoginPage(driver);
        loginPage.inputEmail("@gmail");// target frame detached
        //driver.findElement(By.id("ispbxii_1")).sendKeys("@gmail");
        loginPage.clickLogin();
        waitForInput.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-title")));

        if (ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true)) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();
        }
        Thread.sleep(5000);
        Assert.assertEquals(actual2, expected2);

 */
        //takes screenshot
        File shopPay = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shopPay, new File("src/test/resources/screenshots/emailValidation.png"));

    }


    @Test(priority = 4)
    public void negative_registration_test() throws InterruptedException {
        test = extent.createTest("negative_registration_test", "Test Passed");

        loginPage = new AlexAndNovaLoginPage(driver);
        loginPage.clickRegister();
        Thread.sleep(5000);

        registrationPage = new AlexAndNovaRegistrationPage(driver);
        registrationPage.clickOnRegister();

        if(ExpectedConditions.visibilityOfElementLocated(By.className("recaptcha-checkbox-border")).equals(true))
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopify-challenge__button btn"))).click();

        }

        Thread.sleep(10000);
        String expected = "Sorry! Please try that again.";
                                                                //error message
        String actual = driver.findElement((By.xpath("/html/body/div[3]/div[2]/div[1]/form/p"))).getText();
        Assert.assertEquals(actual, expected);

    }

    @Test(priority = 5)
    public void registration_with_negative_password_test() throws InterruptedException {

        test = extent.createTest("registration_with_negative_password_test", "Test Passed");

        //process to register as a new user
        homePage = new AlexAndNovaHomePage(driver);
        homePage.clickAccount();

        loginPage = new AlexAndNovaLoginPage(driver);
        loginPage.clickRegister();

        registrationPage = new AlexAndNovaRegistrationPage(driver);
        registrationPage.inputFirstName("john");
        registrationPage.inputLastName("fink");
        registrationPage.inputEmail("jftest@gmail.com");
        registrationPage.inputPassword("passw");
        registrationPage.clickOnRegister();

        Thread.sleep(5000);
        String expected = "Sorry! Please try that again.";
        // element that only exists in scope of the test
        By errMsg = By.xpath("/html/body/div[3]/div[2]/div[1]/form/p");
        String actual = driver.findElement((errMsg)).getText();
        Assert.assertEquals(actual, expected);

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
