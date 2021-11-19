import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Ex7 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void TestMenu() { //Задание 7
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
        int PointSize = driver.findElements(By.cssSelector("#box-apps-menu #app-")).size();
        for (int i = 0; i < PointSize; i++) {
            List<WebElement> Points = driver.findElements(By.cssSelector("#box-apps-menu #app-"));
            Points.get(i).click();
            int PointSize2 = driver.findElements(By.cssSelector(".docs li")).size();
            for (int j = 0; j < PointSize2; j++) {
                List<WebElement> Points2 = driver.findElements(By.cssSelector(".docs li"));
                Points2.get(j).click();
                System.out.println(areElementsPresent(driver,By.cssSelector("#content h1")));
            }


        }

    }
    static boolean areElementsPresent(WebDriver driver1, By locator) {
        return driver1.findElements(locator).size() > 0;
    }
    @AfterEach
    public void stop() {
        driver.quit(); //остановить все ресурсы и процессы
        driver = null;
    }
}