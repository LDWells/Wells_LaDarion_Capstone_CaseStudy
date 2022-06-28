package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlexAndNovaCheckoutPage {

    WebDriver driver;

    By checkoutEmail = By.id("checkout_email");
    By firstName = By.id("checkout_shipping_address_first_name");
    By lastName = By.id("checkout_shipping_address_last_name");
    By address = By.id("checkout_shipping_address_address1");
    By city = By.id("checkout_shipping_address_city");
    By zipCode = By.id("checkout_shipping_address_zip");
    By continueShipping = By.id("continue_button");
    By continueToPayment = By.className("btn__content");
    By discountCode = By.id("checkout_reduction_code");
    By applyDiscount = By.id("checkout_submit");
    By shopPay = By.className("h7yAG5cgdBp_GhZjXmVIA");
    By payPal = By.className("paypal-button-label-container");
    By googlePay = By.className("kEwctmM5pguv6XkPR8mx6");
    By facebookPay = By.className("_1LP9NPTft85QosIXd3_zOV _3G6VJhJYno-AX3-X38f1TA _2zarRkvJ2j83NID3Q3t0Ix _24a_n1dXCrAWpcF2hfyvI5");
    public AlexAndNovaCheckoutPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void enterEmail(String arg)
    {
        driver.findElement(checkoutEmail).sendKeys(arg);
    }

    public void enterFirstName(String arg)
    {
        driver.findElement(firstName).sendKeys(arg);
    }

    public void enterLastName(String arg)
    {
        driver.findElement(lastName).sendKeys(arg);
    }

    public void enterAddress(String arg)
    {
        driver.findElement(address).sendKeys(arg);
    }

    public void enterCity(String arg)
    {
        driver.findElement(city).sendKeys(arg);
    }

    public void enterZipCode(String arg)
    {
        driver.findElement(zipCode).sendKeys(arg);
    }
    public void clickContinueToShipping()
    {
        driver.findElement(continueShipping).click();
    }

    public void clickContinueToPayment()
    {
        driver.findElement(continueToPayment).click();
    }

    public void enterDiscount(String arg)
    {
        driver.findElement(discountCode).sendKeys(arg);
    }

    public void clickApplyDiscount()
    {
        driver.findElement(applyDiscount).click();
    }

    public void payWithShopPay()
    {
        driver.findElement(shopPay).click();
    }

    public void payWithPaypal()
    {
        driver.findElement(payPal).click();
            //navigates to paypal popup
//        driver.navigate().to("https://www.paypal.com/checkoutnow?locale.x=en_US&fundingSource=paypal&sessionID=uid_1813885089_mtc6mtk6mty&buttonSessionID=uid_52f9ccb09c_mtc6mtk6mjg&env=production&fundingOffered=paypal&logLevel=warn&sdkMeta=eyJ1cmwiOiJodHRwczovL3d3dy5wYXlwYWxvYmplY3RzLmNvbS9hcGkvY2hlY2tvdXQubWluLmpzIn0&uid=b617a0cac4&version=min&token=EC-15158626L2142872V&xcomponent=1");

    }

    public void payWithGooglePay()
    {
        driver.findElement(googlePay).click();
    }

    public void payWithFacebookPay()
    {
        driver.findElement(facebookPay).click();
        //navigates to facebook popup
//        driver.navigate().to("https://www.facebook.com/login/?next=%2Fpayments%2Foffsite%2Fcheckout%2F%3Fopener%3Dhttps%253A%252F%252Fwww.alexandnova.com%26receiver_id%3DSHOPIFY_LIVE%253A%253A10856759353%26request_id%3Dc55cd1f6-1b9c-4e95-a240-14ea9051ca4e%26is_deskt%3D1%26is_login_redirect%3D1%26supported_container_types%255B0%255D%3Dbasic-card-v1%26mode%3DLIVE%26session_id%3Dupl_1656178651715_bc047609-c888-4fe7-9040-2b3f74609faa&no_next_msg&hide_registration=1");
    }





}
