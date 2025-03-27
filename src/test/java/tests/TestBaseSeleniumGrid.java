package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBaseSeleniumGrid  {
    public static String BaseUrl="https://www.saucedemo.com/v1/index.html";
    //to make browser open more than one browser at time
    protected ThreadLocal<RemoteWebDriver> driver=null;
    @BeforeClass
    @Parameters(value = "browser")
    public void setUp(@Optional ("chrome") String browser) throws MalformedURLException {
        driver=new ThreadLocal<>();
        DesiredCapabilities caps=new DesiredCapabilities();


        caps.setCapability("browserName",browser);
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setPlatformName("WINDOWS");
            caps.setCapability(ChromeOptions.CAPABILITY, options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.setPlatformName("WINDOWS");
            caps.setCapability(EdgeOptions.CAPABILITY, options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setPlatformName("WINDOWS");
            caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        } else if (browser.equalsIgnoreCase("headless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920x1080");
            caps.setCapability(ChromeOptions.CAPABILITY, options);
        }
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps));

        getDriver().navigate().to(BaseUrl);
    }
    public WebDriver getDriver(){
        return driver.get();
    }

    @AfterMethod
    public void takeScreenShotWhenFail(ITestResult result) {
        // Only capture screenshot for failed tests
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Capturing screenshot for failed test: " + result.getName());
            Helper.captureScreenshot(getDriver(), result.getName());
        }
    }

    @AfterClass
    public void stopDriver() {
        // Close driver only once after all tests complete
        getDriver().close();
    }



}
