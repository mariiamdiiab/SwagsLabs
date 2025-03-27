package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.P01_LoginPage;

public class T01_LoginTest extends TestBase{
    P01_LoginPage loginPageObject;

    @Test
    public void loginUsingValidData(){
        loginPageObject=new P01_LoginPage(driver);
        loginPageObject.userCanLogin("standard_user","secret_sauce");
    }
}