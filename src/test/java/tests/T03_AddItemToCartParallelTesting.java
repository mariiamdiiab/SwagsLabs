package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginPage;
import pages.P02_AddItemToCart;

public class T03_AddItemToCartParallelTesting extends TestBaseSeleniumGrid{
    P01_LoginPage loginPageObject;
    P02_AddItemToCart addItemToCartObject;

    @Test(priority=1)
    public void loginUsingValidData(){
        loginPageObject=new P01_LoginPage(getDriver());
        loginPageObject.userCanLogin("standard_user","secret_sauce");
    }

    @Test(priority = 2)
    public void userCanAddItemToCart(){
        addItemToCartObject=new P02_AddItemToCart(getDriver());
        addItemToCartObject.userClickOnAddToCart();
        Assert.assertTrue(addItemToCartObject.removeFromCart.isDisplayed());
    }
}

