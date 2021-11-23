import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Ex8 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void TestStickers() { //Задание 8
        driver.navigate().to("http://localhost/litecart/");
        int ProductsCount = driver.findElements(By.cssSelector(".product")).size();
        List<WebElement> Products = driver.findElements(By.cssSelector(".product"));
        for (int i = 0; i < ProductsCount; i++) {
            WebElement Product = Products.get(i);
            if(Product.findElements(By.cssSelector(".sticker")).size() != 1)//Ровно 1 стикер
            {
                System.out.println("False");
            }

        }
    }
    @AfterEach
    public void stop() {
        driver.quit(); //остановить все ресурсы и процессы
        driver = null;
    }
}