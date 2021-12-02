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
import org.openqa.selenium.Keys;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Ex11 {
    private WebDriver driver;
    private WebDriverWait wait;
    private Object WebDriverFactory;
    private Object Color;

    @Test
    public void testChrome11(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        test11();
    }

    public void test11() { //Задание 10 правильная страница товара
        driver.navigate().to("http://localhost/litecart/en/create_account");
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys("firstname");
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys("lastname");
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys("address1");
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys("12345");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("city");
        driver.findElement(By.cssSelector(".select2-selection__rendered")).click();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("United States" + Keys.ENTER);
        SecureRandom random = new SecureRandom();
        String randomEmail = new BigInteger(130, random).toString(32) + "@mail.ru";
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(randomEmail);
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys("12345");
        String pass = "qwerty";
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(pass);
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(pass);
        driver.findElement(By.cssSelector("[name=create_account]")).click();
        logout();
        login(randomEmail, pass);
        logout();

    }
    public void logout() {
        driver.findElement(By.cssSelector("#box-account li:last-child a")).click();
    }
    public void login(String randomEmail, String pass) {
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(randomEmail);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(pass);
        driver.findElement(By.cssSelector("[name=login]")).click();
    }

    @AfterEach
    public void stop() {
        driver.quit(); //остановить все ресурсы и процессы
        driver = null;
    }
    }

