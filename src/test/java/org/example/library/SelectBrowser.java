package org.example.library;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowser {

    static WebDriver driver;

//    ChromeOptions options = new ChromeOptions();
//    options.setCapability("Site key", "6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI");
//    options.setCapability("Secret key","6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe");
//    options.addArguments("--headless");
//    options
    public static WebDriver StartBrowser(String browsername) {
        // ---If the browser is Firefox----
        if (browsername.equalsIgnoreCase("Firefox")) {
            // Set the path for geckodriver.exe
            System.setProperty("webdriver.gecko.driver","C:\\Users\\LaDar\\Documents\\SpringBootApp\\Wells_LaDarion_Capstone_CaseStudy\\src\\test\\resources\\geckodriver");
            driver = new FirefoxDriver();
        }
        //---- If the browser is Chrome--
        else if (browsername.equalsIgnoreCase("Chrome")) {
            // Set the path for chromedriver.exe
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\LaDar\\Documents\\SpringBootApp\\Wells_LaDarion_Capstone_CaseStudy\\src\\test\\resources\\chromedriver");
            driver = new ChromeDriver();
        }
        // ----If the browser is  EdgeIE----
        else if (browsername.equalsIgnoreCase("EdgeExplore")) {
            // Set the path for IEdriver
            System.setProperty("webdriver.edge.driver", "C:\\Users\\LaDar\\Documents\\SpringBootApp\\Wells_LaDarion_Capstone_CaseStudy\\src\\test\\resources\\msedgedriver");
            // Instantiate a EdgeDriverclass.
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }
        driver.manage().window().maximize();
        return driver;
    }

}
