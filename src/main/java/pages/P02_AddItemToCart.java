package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P02_AddItemToCart extends PageBase {
    public P02_AddItemToCart(WebDriver driver) {
        super(driver);
    }

    //Locators
    @FindBy(css = "button.btn_primary.btn_inventory")
    WebElement addToCartBtn;

    @FindBy(css="button.btn_secondary.btn_inventory")
    public WebElement removeFromCart;


    //methods
    public void userClickOnAddToCart(){
        clickButton(addToCartBtn);
        wait.until(ExpectedConditions.visibilityOf(removeFromCart));
    }
}