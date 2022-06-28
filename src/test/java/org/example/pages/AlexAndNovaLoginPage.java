package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlexAndNovaLoginPage {

    WebDriver driver;

    By email = By.id("ispbxii_1");//By.cssSelector("input[placeholder='your@email.com']");
    By password = By.name("customer[password]");
    By login = By.xpath("*//div/input[@type='submit']");
    By forgotPassword = By.className("toggle-forgetfulness");
    By registerOnLoginPage = By.xpath("/html/body/div[3]/div[2]/div[3]/a");
    ///html/body/div[3]/h1

    By welcomeMsg = By.xpath("/html/body/div[3]/h1");

   By logout = By.id("customer_logout_link");
    //By errMsg = By.className("error-message banner");

    public AlexAndNovaLoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void inputEmail(String arg)
    {
        driver.findElement(email).sendKeys(arg);
    }

    public void inputPassword(String arg)
    {
        driver.findElement(password).sendKeys(arg);
    }

    public void clickLogin()
    {
        driver.findElement(login).click();
    }

    public void clickForgotPassword()
    {
        driver.findElement(forgotPassword).click();
    }

    public void clickRegister()
    {
        driver.findElement(registerOnLoginPage).click();
    }

    public String getWelcomeMessage()
    {
        return driver.findElement(welcomeMsg).getText();
    }

    public void clickLogout()
    {
        driver.findElement(logout).click();
    }

//    public String getErrorMessage()
//    {
//        return driver.findElement(errMsg).getText();
//    }
}
