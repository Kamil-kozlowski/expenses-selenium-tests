import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

abstract public class AbstractSuccessfulLoginTest extends AbstractAutomationTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        final List<WebElement> formUsername = driver.findElements(By.id("formUsername"));
        assertTrue(formUsername.size() > 0);

        final List<WebElement> formPassword = driver.findElements(By.id("formPassword"));
        assertTrue(formPassword.size() > 0);

        driver.findElement(By.id("formUsername")).sendKeys("test");
        driver.findElement(By.id("formPassword")).sendKeys("test");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
