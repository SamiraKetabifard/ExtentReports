import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        ExtentManager.getExtent();
    }
    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.setTest(ExtentManager.getExtent().createTest(result.getMethod().getMethodName()));
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
        Object instance = result.getInstance();
        if (!(instance instanceof BaseTest)) {
            return;
        }
        WebDriver driver = ((BaseTest) instance).getDriver();
        if (driver == null) {
            return;
        }
        try {
            Files.createDirectories(Paths.get("reports"));
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";
            File destination = Paths.get("reports", fileName).toFile();
            FileUtils.copyFile(screenshot, destination);
            ExtentManager.getTest().fail("Failure Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, "Test skipped");
    }
    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }
}
