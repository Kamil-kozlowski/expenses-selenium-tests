import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UIElementsPresentTest extends AbstractAutomationTest {

    @Test
    public void testElementsPresentOnTheFrontPage() {
        final List<WebElement> fromPassword = driver.findElements(By.xpath("//input[@id='formPassword']"));
        assertTrue(fromPassword.size() > 0);

        final List<WebElement> formUsername = driver.findElements(By.xpath("//input[@id='formUsername']"));
        assertTrue(formUsername.size() > 0);

        final List<WebElement> logInButton = driver.findElements(By.xpath("//button[contains(.,'Log in!')]"));
        assertTrue(logInButton.size() > 0);

        final List<WebElement> savingPigLogo = driver.findElements(By.xpath("//a[contains(.,'Saving pig')]"));
        assertTrue(savingPigLogo.size() > 0);
    }

    @Test
    public void hamburgerMenuPresentInMobileView() {
        driver.manage().window().setSize(new Dimension(375, 812));

        driver.findElement(By.id("formUsername")).sendKeys("test");
        driver.findElement(By.id("formPassword")).sendKeys("test");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        List<WebElement> button = driver.findElements(By.xpath("//div[@id='root']/nav/button"));
        assertEquals(1, button.size());

        List<WebElement> expensesNotPresent = driver.findElements(By.linkText("Expenses"));
        assertEquals(0, expensesNotPresent.size());

        driver.findElement(By.xpath("//div[@id='root']/nav/button")).click();

        List<WebElement> expenses = driver.findElements(By.linkText("Expenses"));
        assertEquals(1, expenses.size());

        List<WebElement> categories = driver.findElements(By.linkText("Categories"));
        assertEquals(1, categories.size());

        List<WebElement> add = driver.findElements(By.xpath("//a[contains(text(),'Add')]"));
        assertEquals(1, add.size());

        List<WebElement> logout = driver.findElements(By.linkText("Logout"));
        assertEquals(1, logout.size());

        driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();

        List<WebElement> addExpense = driver.findElements(By.linkText("Expense"));
        assertEquals(1, addExpense.size());

        List<WebElement> addCategory = driver.findElements(By.linkText("Category"));
        assertEquals(1, addCategory.size());

    }
}
