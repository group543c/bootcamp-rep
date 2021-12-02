import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ex11 {
    private WebDriver driver;
    private WebDriverWait wait;
    private Object WebDriverFactory;
    private Object Color;

    @Test
    public void testChrome(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        test10();
    }

    public void test10() { //Задание 10 правильная страница товара
        driver.navigate().to("http://localhost/litecart/");
        WebElement toy = driver.findElement(By.cssSelector("#box-campaigns .content li a"));
        WebElement toyOldPrice = toy.findElement(By.cssSelector(".regular-price"));
        WebElement toyNewPrice = toy.findElement(By.cssSelector(".campaign-price"));
        String nameToy = toy.findElement(By.cssSelector(".name")).getText();
    }
}

