import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentReportWithTestNG {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        ExtentSparkReporter spark =
                new ExtentSparkReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
    @Test
    public void googleTestPassed() {

        test = extent.createTest("Google Test - Passed");
        test.info("Opening Google");
        driver.get("https://www.google.com");
        test.info("Checking Google title");
        String title = driver.getTitle();

        if (title.contains("Google")) {
            test.pass("Google title is correct");
        } else {
            test.fail("Google title is incorrect");
        }
    }
    @Test
    public void googleTestFailed() {

        test = extent.createTest("Google Test - Failed");
        test.info("Opening Google");
        driver.get("https://www.google.com");
        test.info("Checking wrong title");
        String title = driver.getTitle();

        if (title.contains("Facebook")) {
            test.pass("Title is correct");
        } else {
            test.fail("Title is incorrect");
        }
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}