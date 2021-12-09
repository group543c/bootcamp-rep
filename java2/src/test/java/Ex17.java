import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Ex17 {
    private WebDriver driver;

    @Test
    public void testChrome12()   {
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(chromeOptions);
        test17();
    }
    public void test17()   {
        auth();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        //admin - catalog tbody .dataTable tr.row td:nth-child(3)>a
        List<WebElement> listToy = driver.findElements(By.cssSelector(".dataTable tr.row td:nth-child(3)>a"));
        List<String> listrehref = new ArrayList<>();
        int size = listToy.size();
        for (int i = 0; i < size; i++)
        {
            listrehref.add(i,listToy.get(i).getAttribute("href"));
        }
        for (int j=0; j< size; j++)
        {
            driver.navigate().to(listrehref.get(j));
            driver.manage().logs().get("browser").forEach(l->System.out.println(l));
            driver.manage().logs().get("performance").forEach(l->System.out.println(l));
        }
        /*System.out.println(driver.manage().logs().getAvailableLogTypes ());*/
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
