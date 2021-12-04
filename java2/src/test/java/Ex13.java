import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class Ex13 {
        private WebDriver driver;

        @Test
        public void testChrome13()  {
            driver = new ChromeDriver();
            test13();
        }

        public void test13()  {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            for (int i =0;i<=2;i++) {
                driver.navigate().to("http://localhost/litecart/");
                driver.findElement(By.cssSelector(".image-wrapper")).click();
                WebElement count = driver.findElement(By.cssSelector("span.quantity"));
                int countInC = Integer.parseInt(count.getText());
                List<WebElement> size= driver.findElements(By.cssSelector("[name='options[Size]']"));
                if (size.size()>0)
                {
                    size.get(0).click();
                    size.get(0).findElement(By.cssSelector("option[value=Small]")).click();
                }
                driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
                wait.until(ExpectedConditions.textToBePresentInElement(count,String.valueOf(countInC+1)));
            }
            driver.findElement(By.cssSelector("#cart .link")).click();
            //удаление
            wait.until(	ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=remove_cart_item]")));//Ожидание для проверки наличия элемента в DOM страницы.
            int tableSize = driver.findElements(By.cssSelector("[name=remove_cart_item]")).size();
            int ts = tableSize;
            for (int i =0;i<tableSize;i++)
            {
                int rc = driver.findElements(By.cssSelector("[name=remove_cart_item]")).size();
                if (rc==1)
                {
                    driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".dataTable.rounded-corners")));
                }
                else {
                    wait.until(	ExpectedConditions.presenceOfElementLocated(By.cssSelector(".shortcut")));//Ожидание для проверки наличия элемента в DOM страницы.
                    driver.findElement(By.cssSelector(".shortcut")).click();
                    wait.until(	ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[name=remove_cart_item]"))));
                    driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
                    wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".dataTable.rounded-corners td.item"),ts));//Ожидание проверки количества WebElements с заданным локатором меньше заданного числа
                    ts--;
                }
            }
        }
    @AfterEach
    public void stop() {
        driver.quit(); //остановить все ресурсы и процессы
        driver = null;
    }
}


