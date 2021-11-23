import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex9 {

    private WebDriver driver;
    private WebDriverWait wait;
    private Object WebDriverFactory;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }
   @Test
       public void testA() { //Задание 9
           auth();
           List<WebElement> list = driver.findElements(By.cssSelector(".dataTable .row td:nth-child(5)"));
           int size = list.size();
           String[] actual = new String[size];
           String[] sorted = new String[size];
           for (int i = 0; i < size; i++)
           {
               actual[i]=sorted[i] = list.get(i).getText();
           }

           Arrays.sort(sorted);
           for(int i = 0;i<size;i++)
           {
               if(!actual[i].equals(sorted[i]))
               {
                   System.out.println(sorted[i]);
               }
           }
       }
    @Test
    public void testB() {
        auth();
        List<WebElement> list = driver.findElements(By.cssSelector(".dataTable .row td:nth-child(5)"));
        int size = list.size();
        List<WebElement> myRows = driver.findElements(By.cssSelector(".dataTable .row"));
        List<String> notNullZoneHref = new ArrayList<String>();
        for(int i = 0;i<size;i++)
        {
            WebElement timeZone = myRows.get(i).findElement(By.cssSelector("td:nth-child(6)"));
            if(Integer.parseInt(timeZone.getAttribute("innerText")) != 0)
            {   
                notNullZoneHref.add(myRows.get(i).findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("href"));
            }
        }
        for(int i = 0;i<notNullZoneHref.size();i++)
        {
            driver.navigate().to(notNullZoneHref.get(i));
            sortTimeZoneCountry();

        }
    }
    @Test
    public void testC() {
        auth();
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> list = driver.findElements(By.cssSelector(".dataTable td:nth-child(3) a"));
        int size = list.size();
        List<String> notNullCountryHref = new ArrayList<String>();
        for (int i = 0; i < size; i++)
        {
            notNullCountryHref.add(list.get(i).getAttribute("href"));
        }
        for (int i = 0; i < notNullCountryHref.size(); i++)
        {
            driver.navigate().to(notNullCountryHref.get(i));
            sortTimeZone();
        }
    }
    @AfterEach
    public void stop() {
        driver.quit(); //остановить все ресурсы и процессы
        driver = null;
    }

    public void auth(){
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
    }

    public void sortTimeZone(){
        List<WebElement> list = driver.findElements(By.cssSelector(".dataTable td:nth-child(3) [selected=selected]"));
        int size = list.size();
        String[] actual = new String[size];
        String[] sorted = new String[size];
        for (int i = 0; i < size; i++)
        {
            actual[i]=sorted[i] = list.get(i).getAttribute("textContent");
        }

        Arrays.sort(sorted);
        for(int i = 0;i<size;i++)
        {
            if(!actual[i].equals(sorted[i]))
            {
                System.out.println("No sort:" + sorted[i]);
            }
        }
    }

    public void sortTimeZoneCountry(){ //Задание 9
        List<WebElement> list = driver.findElements(By.cssSelector(".dataTable td:nth-child(3) [type=hidden] "));
        int size = list.size();
        String[] actual = new String[size];
        String[] sorted = new String[size];
        for (int i = 0; i < size; i++)
        {
            actual[i]=sorted[i] = list.get(i).getAttribute("value");
        }

        Arrays.sort(sorted);
        for(int i = 0;i<size;i++)
        {
            if(!actual[i].equals(sorted[i]))
            {
                System.out.println("No sort:" + sorted[i]);
            }
        }
    }
}