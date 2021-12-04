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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.nio.file.FileSystems;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ex12 {
    private WebDriver driver;
    private WebDriverWait wait;
    private Object WebDriverFactory;
    private Object Color;

    @Test
    public void testChrome12()   {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        test12();
    }
    public void test12()   {
        auth();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        wait.until(	ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content a.button:last-child")));
        driver.findElement(By.cssSelector("#content a.button:last-child")).click();
        driver.findElement(By.cssSelector("[type=radio][name=status][value='1']")).click();
        String toyName = "Toy2";
        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys(toyName);
        driver.findElement(By.cssSelector("[name=code]")).sendKeys("code2");
        driver.findElement(By.cssSelector("[name=default_category_id] [value='0']")).click();
        driver.findElement(By.cssSelector("[type=checkbox][name='product_groups[]'][value='1-2']")).click();
        driver.findElement(By.cssSelector("[name=quantity]")).clear();
        driver.findElement(By.cssSelector("[name=quantity]")).sendKeys("8");
        //Upload Images
        String path = "./src/test/resources/BigDuck.jpg";
        String absolutePath = FileSystems.getDefault().getPath(path).normalize().toAbsolutePath().toString();
        System.out.println(absolutePath);
        driver.findElement(By.cssSelector("[type=file]")).sendKeys(absolutePath);
        driver.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys("03122021" );
        driver.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys("31122021");
        //таб information
        driver.findElement(By.cssSelector("[href='#tab-information']")).click();
        wait.until(	ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=manufacturer_id]")));
        driver.findElement(By.cssSelector("[name=manufacturer_id]")).click();
        driver.findElement(By.cssSelector("select[name=manufacturer_id] option[value='1']")).click();
        driver.findElement(By.cssSelector("[name=keywords]")).sendKeys("keyToy");
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys("My Short Description");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("My very very very big Description");
        driver.findElement(By.cssSelector("[name='head_title[en]']")).sendKeys("My Head Title");
        driver.findElement(By.cssSelector("[name='meta_description[en]']")).sendKeys("Meta Description");

        //price
        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();
        wait.until(	ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=purchase_price]")));
        driver.findElement(By.cssSelector("[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("[name=purchase_price]")).sendKeys("120");

        driver.findElement(By.cssSelector("[name=purchase_price_currency_code]")).click();
        driver.findElement(By.cssSelector("select[name=purchase_price_currency_code] option[data-value='1']")).click();
        driver.findElement(By.cssSelector("[name='prices[USD]']")).sendKeys("120");
        driver.findElement(By.cssSelector("[name=save]")).click();
        //admin - catalog tbody
        List<WebElement> listToy = driver.findElements(By.cssSelector(".dataTable tr.row td:nth-child(3)"));
        int size = listToy.size();
        for (int i = 0; i < size; i++)
        {
            if (listToy.get(i).getText().equals(toyName))
            {
                System.out.println("The toy was created");
            }
        }

        logout();
    }

    public void auth(){
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
    }
    public void logout() {
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("[title=Logout]")).click();
    }
    @AfterEach
    public void stop() {
        driver.quit(); //остановить все ресурсы и процессы
        driver = null;
    }
}
