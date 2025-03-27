package tests;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import utilities.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class TestBase {
    protected static WebDriver driver; // static to maintain single instance across suite

    @BeforeClass
    @Parameters("browser")
    public void startDriver(@Optional("chrome") String browserName) {
        // Initialize driver only once per test suite
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.setBrowserVersion("stable"); // or your specific version
            options.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }else if (browserName.equalsIgnoreCase("headless")) {
            // Use Chrome in headless mode for headless testing
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");          // Enable headless mode
            options.addArguments("--disable-gpu");      // Disable GPU (recommended for headless)
            options.addArguments("--window-size=1920x1080"); // Set a specific window size for headless mode
            driver = new ChromeDriver(options);         // Initialize ChromeDriver with options
        }
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/v1/index.html");
    }

    @AfterMethod
    public void takeScreenShotWhenFail(ITestResult result) {
        // Only capture screenshot for failed tests
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Capturing screenshot for failed test: " + result.getName());
            Helper.captureScreenshot(driver, result.getName());
        }
    }

    @AfterClass
    public void stopDriver() {
        // Close driver only once after all tests complete
        driver.close();
    }
}