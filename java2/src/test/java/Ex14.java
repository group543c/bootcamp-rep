import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Ex14 {
    private WebDriver driver;

    @Test
    public void testChrome12()   {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        auth();
        driver.navigate().to(" http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector(".button")).click();
        String originalWindow = driver.getWindowHandle();
        Set<String> existingWindows;
        String newWindow;
        List<WebElement> sq = driver.findElements(By.cssSelector(".fa.fa-external-link"));
        for (int i=0;i<sq.size();i++)
        {
            existingWindows = driver.getWindowHandles();
            sq.get(i).click();

            if (driver.getWindowHandles().size()!=2)//открывается именно 1 окно
            {
                System.out.println("New tab didn't open");
            }
            newWindow = wait.until(anyWindowOtherThen(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
            /*wait.until(ExpectedConditions.numberOfWindowsToBe(2));*/
            
        }
    }

    public ExpectedCondition<String> anyWindowOtherThen(Set<String> oldWindows) {
        return new ExpectedCondition<String>(){
            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    public void auth(){
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
    }
}
