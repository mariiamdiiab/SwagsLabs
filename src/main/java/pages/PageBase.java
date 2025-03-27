package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected WebDriverWait wait;

    //constructor
    public PageBase(WebDriver driver) {
        this.driver = driver;  // Store the driver reference
        this.jse = (JavascriptExecutor) driver;  // Initialize jse
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    //important methods to use along
    protected void clickButton(WebElement button) {
        button.click();
    }

    protected static void setTextToTxtBox(WebElement textBox, String txt) {
        textBox.sendKeys(txt);
    }

    protected void clearText(WebElement element) {
        element.clear();
    }

    protected void scrollToBottom() {
        jse.executeScript("scrollBy(0,2500)");
    }
}