package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlexAndNovaRegistrationPage {

    WebDriver driver;

    //elements for registration
    @FindBy(id = "ispbxii_1")
    WebElement firstName;

    @FindBy(id = "ispbxii_2")
    WebElement lastName;

    @FindBy(css = "input[placeholder='your@email.com']")
    WebElement email;

    @FindBy(xpath = "*//input[@type='password']")
    WebElement password;

    @FindBy(xpath = "/html/body/div[3]/div[2]/form/div[5]/input")
    WebElement register;



    public AlexAndNovaRegistrationPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //methods for registration
    public void inputFirstName(String arg)
    {
        firstName.sendKeys(arg);
    }

    public void inputLastName(String arg)
    {
        lastName.sendKeys(arg);
    }

    public void inputEmail(String arg)
    {
        email.sendKeys(arg);
    }

    public void inputPassword(String arg)
    {
        password.sendKeys(arg);
    }

    public void clickOnRegister()
    {
        register.click();
    }


}
