import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Ex15 {
    private WebDriver driver;
    private WebDriver driver2;
    private WebDriver driver3;
    private WebDriverWait wait;
    private Object WebDriverFactory;
    private Object Color;

    @Test
    public void testChrome() throws MalformedURLException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxBinary binary = new FirefoxBinary(new File( "C:\\Program Files\\Mozilla Firefox\\firefox.exe"));
        firefoxOptions.setBinary(binary);
        firefoxOptions.setCapability("browserName", "firefox");
        firefoxOptions.setCapability("platform", "WIN10");

        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.56.102:4444/wd/hub"), firefoxOptions);
        System.out.println(((HasCapabilities) driver).getCapabilities());
/*        driver.navigate().to("https://yandex.ru/");*/
        driver.quit();

        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));*/

/*        FirefoxOptions options = new FirefoxOptions().setLegacy(false);
        driver2 = new RemoteWebDriver(new URL("http://192.168.56.102:4444/wd/hub"),new FirefoxOptions());

        driver3 = new RemoteWebDriver(new URL("http://192.168.56.102:4444/wd/hub"),new InternetExplorerOptions());*/


/*        driver2.navigate().to("https://yandex.ru/");
        driver3.navigate().to("https://yandex.ru/");*/
    }
}
