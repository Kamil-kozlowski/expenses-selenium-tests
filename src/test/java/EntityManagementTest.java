import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EntityManagementTest extends AbstractSuccessfulLoginTest {

    @Test
    public void addEditRemoveCategory() throws InterruptedException {
        addCategory();
        editCategory();
        removeCategory();
    }

    @Test
    public void addEditRemoveExpense() throws InterruptedException {
        addCategory();
        driver.findElement(By.xpath("//a[contains(.,'Add')]")).click();
        driver.findElement(By.xpath("//a[contains(@href, '/expenses/edit')]")).click();
        driver.findElement(By.xpath("//input[@id='formCategory']")).sendKeys("Wydatek");
        driver.findElement(By.xpath("//button[contains(.,'Category')]")).click();

        List<WebElement> category = driver.findElements(By.xpath("//a[contains(.,'Testowa Kategoria')]"));
        assertTrue(category.size() > 0);

        driver.findElement(By.xpath("//a[contains(.,'Testowa Kategoria')]")).click();
        driver.findElement(By.xpath("//input[@id='formValue']")).sendKeys("100");
        driver.findElement(By.xpath("//button[contains(.,'Create')]")).click();

        List<WebElement> expenseName = driver.findElements(By.xpath("//td[contains(.,'Wydatek')]"));
        assertTrue(expenseName.size() > 0);

        List<WebElement> expenseCategory = driver.findElements(By.xpath("//td[contains(.,'Testowa Kategoria')]"));
        assertTrue(expenseCategory.size() > 0);

        List<WebElement> expenseValue = driver.findElements(By.xpath("//td[contains(.,'100')]"));
        assertTrue(expenseValue.size() > 0);

        driver.findElement(By.xpath("//a[contains(.,'✎')]")).click();
        driver.findElement(By.xpath("//input[@id='formCategory']")).sendKeys(" zmieniony");
        driver.findElement(By.xpath("//input[@id='formValue']")).sendKeys(".5");
        driver.findElement(By.xpath("//button[contains(.,'Update')]")).click();

        List<WebElement> editedExpenseName = driver.findElements(By.xpath("//td[contains(.,'Wydatek zmieniony')]"));
        assertTrue(editedExpenseName.size() > 0);

        List<WebElement> editedExpenseValue = driver.findElements(By.xpath("//td[contains(.,'100.5')]"));
        assertTrue(editedExpenseValue.size() > 0);

        driver.findElement(By.xpath("//button[contains(.,'🅧')]")).click();

        Thread.sleep(1000);
        List<WebElement> removedExpense = driver.findElements(By.xpath("//td[contains(.,'Wydatek')]"));
        assertEquals(0, removedExpense.size());

        driver.findElement(By.xpath("//a[contains(text(),'Categories')]")).click();
        removeCategory();
    }

    @Test
    public void categoryCannotBeRemovedWhenInUse() throws InterruptedException {
        addCategory();
        driver.findElement(By.xpath("//a[contains(.,'Add')]")).click();
        driver.findElement(By.xpath("//a[contains(@href, '/expenses/edit')]")).click();
        driver.findElement(By.xpath("//input[@id='formCategory']")).sendKeys("Wydatek");
        driver.findElement(By.xpath("//button[contains(.,'Category')]")).click();

        List<WebElement> category = driver.findElements(By.xpath("//a[contains(.,'Testowa Kategoria')]"));
        assertTrue(category.size() > 0);

        driver.findElement(By.xpath("//a[contains(.,'Testowa Kategoria')]")).click();
        driver.findElement(By.xpath("//input[@id='formValue']")).sendKeys("100");
        driver.findElement(By.xpath("//button[contains(.,'Create')]")).click();

        List<WebElement> expenseName = driver.findElements(By.xpath("//td[contains(.,'Wydatek')]"));
        assertTrue(expenseName.size() > 0);

        List<WebElement> expenseCategory = driver.findElements(By.xpath("//td[contains(.,'Testowa Kategoria')]"));
        assertTrue(expenseCategory.size() > 0);

        List<WebElement> expenseValue = driver.findElements(By.xpath("//td[contains(.,'100')]"));
        assertTrue(expenseValue.size() > 0);

        driver.findElement(By.xpath("//a[contains(text(),'Categories')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'🅧')]")).click();

        Thread.sleep(1000);
        final List<WebElement> categoryToBeRemoved = driver.findElements(By.xpath("//p[contains(.,'Category is in use or a server error occurred')]"));
        assertEquals(1, categoryToBeRemoved.size());

        driver.findElement(By.xpath("//span[contains(.,'×')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Expenses')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'🅧')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Categories')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'🅧')]")).click();

        Thread.sleep(1000);
        final List<WebElement> removedCategory = driver.findElements(By.xpath("//td[contains(.,'Testowa Kategoria')]"));
        assertEquals(0, removedCategory.size());
    }

    private void removeCategory() throws InterruptedException {
        driver.findElement(By.xpath("//button[contains(.,'🅧')]")).click();

        Thread.sleep(1000);
        final List<WebElement> removedCategory = driver.findElements(By.xpath("//td[contains(.,'Testowa Kategoria - zmieniona')]"));
        assertEquals(0, removedCategory.size());
    }

    private void editCategory() {
        driver.findElement(By.xpath("//a[contains(.,'✎')]")).click();
        driver.findElement(By.xpath("//input[@id='formName']")).sendKeys("Testowa Kategoria - zmieniona");
        driver.findElement(By.xpath("//button[contains(.,'Update')]")).click();

        final List<WebElement> editedCategory = driver.findElements(By.xpath("//td[contains(.,'Testowa Kategoria - zmieniona')]"));
        assertTrue(editedCategory.size() > 0);
    }

    private void addCategory() {
        driver.findElement(By.xpath("//a[contains(.,'Add')]")).click();
        driver.findElement(By.linkText("Category")).click();
        driver.findElement(By.xpath("//input[@id='formName']")).sendKeys("Testowa Kategoria");
        driver.findElement(By.xpath("//button[contains(.,'Create')]")).click();

        final List<WebElement> category = driver.findElements(By.xpath("//td[contains(.,'Testowa Kategoria')]"));
        assertTrue(category.size() > 0);
    }
}
