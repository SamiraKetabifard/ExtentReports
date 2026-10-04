import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class GoogleTest extends BaseTest {

    @Test
    public void googleTitleTest() {

        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"), "Google title is incorrect");
    }
}