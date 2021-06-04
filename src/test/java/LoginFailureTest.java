import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LoginFailureTest extends AbstractAutomationTest {

    @Test
    public void testLoginFailure() {
        final List<WebElement> formUsername = driver.findElements(By.id("formUsername"));
        assertTrue(formUsername.size() > 0);

        final List<WebElement> formPassword = driver.findElements(By.id("formPassword"));
        assertTrue(formPassword.size() > 0);

        driver.findElement(By.id("formUsername")).sendKeys("dummy");
        driver.findElement(By.id("formPassword")).sendKeys("dummy");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        final List<WebElement> elements = driver.findElements(By.xpath("//p[contains(.,'Unable to login')]"));
        assertTrue(elements.size() > 0);
    }

}
