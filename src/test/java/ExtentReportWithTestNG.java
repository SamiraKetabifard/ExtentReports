import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentReportWithTestNG {

    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setUp() {
        ExtentSparkReporter spark = new ExtentSparkReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
    @Test
    public void testPassed() {

        test = extent.createTest("Passed Test");
        test.info("Test started");
        test.info("Checking something...");
        test.pass("Test passed successfully");
    }
    @Test
    public void testFailed() {

        test = extent.createTest("Failed Test");
        test.info("Test started");
        test.info("Checking something...");
        test.fail("Test failed");
    }
    @AfterTest
    public void tearDown() {
        extent.flush();
    }
}