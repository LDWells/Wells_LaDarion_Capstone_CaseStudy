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
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SearchTest {

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
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/SearchReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "localhost:8080");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "LaDarion Wells");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting AlexAndNova Search Report");
        htmlReporter.config().setReportName("AlexAndNova Search Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeTest
    public void setUpBrowser()
    {
        driver = SelectBrowser.StartBrowser("EdgeExplore");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.get("https://www.alexandnova.com/");
    }

    @Test(priority = 1)
    public void search_test() throws InterruptedException, IOException {
        test = extent.createTest("search_test", "Test Passed");

        homePage = new AlexAndNovaHomePage(driver);
        homePage.inputSearch("baby shoes");
        homePage.clickSearch();
        Thread.sleep(5000);

       // By results = By.xpath("isp_title_results_for");
        String expected = "Search results for baby shoes";
        String actual = driver.findElement(By.className("page-title")).getText();
        Assert.assertEquals(actual,expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/search.png"));

    }

    @Test(priority = 2)
    public void null_search_test() throws InterruptedException, IOException {
        test = extent.createTest("null_search_test", "Test Passed");
        driver.navigate().to("https://www.alexandnova.com/");
        homePage = new AlexAndNovaHomePage(driver);
        homePage.inputSearch("");
        homePage.clickSearch();

        Thread.sleep(5000);
        String expected = "No results found. Showing top popular products you might want to consider...";
        String actual = driver.findElement(By.className("isp_no_results_title")).getText();
        Assert.assertEquals(actual, expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/nullSearch.png"));


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
