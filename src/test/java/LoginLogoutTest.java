import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LoginLogoutTest extends AbstractSuccessfulLoginTest {

    @Test
    public void successfulLogoutTest() {
        final List<WebElement> logoutElement = driver.findElements(By.linkText("Logout"));
        assertTrue(logoutElement.size() > 0);

        driver.findElement(By.linkText("Logout")).click();

        final List<WebElement> formUsername = driver.findElements(By.id("formUsername"));
        assertTrue(formUsername.size() > 0);


        final List<WebElement> formPassword = driver.findElements(By.id("formPassword"));
        assertTrue(formPassword.size() > 0);
    }

}
