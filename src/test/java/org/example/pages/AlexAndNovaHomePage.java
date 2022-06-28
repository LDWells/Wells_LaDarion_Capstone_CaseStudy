package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlexAndNovaHomePage {

    WebDriver driver;

    @FindBy(linkText = "Account")
    WebElement account;

    @FindBy(xpath = "*//div/form/input[@class='header-search-input ui-autocomplete-input']")
    WebElement searchbar;

    // @FindBy(className = "header-search-button")
    @FindBy(xpath = "*//div/form/input[@class='header-search-button']")
    WebElement searchIcon;

    @FindBy(linkText = "Baby Gears")
    WebElement babyGears;

   // @FindBy(xpath = "*//div[@class='home-collection-overlay-wrapper home-collection-overlay-color-white']")
    @FindBy(id = "navigation-best-sellers")
    WebElement bestSellers;

    @FindBy(id = "navigation-clearance")
    WebElement clearance;

    @FindBy(linkText = "Home")
    WebElement home;

    public AlexAndNovaHomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAccount()
    {
        account.click();
    }

    public void inputSearch(String arg)
    {
        searchbar.sendKeys(Keys.CLEAR);
        searchbar.sendKeys(arg);
    }

    public void clickSearch()
    {
        searchIcon.click();
    }

    public void clickOnBabyGears()
    {
        babyGears.click();
    }

    public void clickOnBestSellers()
    {
        bestSellers.click();
    }

    public void clickOnClearance()
    {
        clearance.click();
    }


    public void clickOnHomeTab()
    {
        home.click();
    }


}
