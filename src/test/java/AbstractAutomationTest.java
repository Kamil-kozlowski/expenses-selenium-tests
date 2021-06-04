import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

abstract public class AbstractAutomationTest {

    protected static final String URL = "http://localhost:3000";

    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1400, 900));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
