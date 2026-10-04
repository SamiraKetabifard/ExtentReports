import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtent() {
        if (extent == null) {
            try {
                Files.createDirectories(Paths.get("reports"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("reports/extent-report.html");

            spark.config().setDocumentTitle("Google Test Report");
            spark.config().setReportName("Google Automation Report");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }
    public static ExtentTest getTest() {
        return test;
    }
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}