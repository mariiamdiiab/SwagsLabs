package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class P01_LoginPage extends PageBase{

    public P01_LoginPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    @FindBy(id = "user-name")
    WebElement userNameTxtBox;

    @FindBy(id="password")
    WebElement passwordTxtBox;

    @FindBy(id = "login-button")
    WebElement loginBtn;



    //methods
    public void userCanLogin(String name, String password){
        setTextToTxtBox(userNameTxtBox,name);
        setTextToTxtBox(passwordTxtBox,password);
        clickButton(loginBtn);
    }
}