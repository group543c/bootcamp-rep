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

public class Ex10 {

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

    @Test
    public void testFireFox(){
        FirefoxOptions options = new FirefoxOptions().setLegacy(false);
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, 10);
        test10();
    }
    @Test
    public void testIE(){
        driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver,10);
        /*wait = new WebDriverWait(driver, 10);*/
        test10();
    }

    public void test10() { //Задание 10 правильная страница товара
        driver.navigate().to("http://localhost/litecart/");
        WebElement toy = driver.findElement(By.cssSelector("#box-campaigns .content li a"));
        WebElement toyOldPrice = toy.findElement(By.cssSelector(".regular-price"));
        WebElement toyNewPrice = toy.findElement(By.cssSelector(".campaign-price"));
        String nameToy = toy.findElement(By.cssSelector(".name")).getText();
        String oldPrice = toyOldPrice.getText();
        String newPrice = toyNewPrice.getText();
        String[] numbers;
        if (toyOldPrice.getCssValue("color").contains("rgba("))
        {
            numbers = toyOldPrice.getCssValue("color").replace("rgba(", "").replace(")", "").split(",");
        }
        else
        {
            numbers = toyOldPrice.getCssValue("color").replace("rgb(", "").replace(")", "").split(",");
        }

        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        if (!(r==g & r==b))//цвет серый если R, G и B одинаковые
        {
            System.out.println("color is not gray");
        }

        String tagOldPrice = toy.findElement(By.cssSelector(".regular-price")).getAttribute("tagName");
        if (!(tagOldPrice.equals("S") || tagOldPrice.equals("STRIKE")))//цена зечеркнута
        {
            System.out.println("old price not crossed out");
        }

        String tagNewPrice = toyNewPrice.getAttribute("tagName");
        if (!tagNewPrice.equals("STRONG"))//цвет жирный
        {
            System.out.println("new price is not strong");
        }

        String[] numbersNew = toyNewPrice.getCssValue("color").replace("rgba(", "").replace(")", "").split(",");
        if (!(Integer.parseInt(numbersNew[1].trim()) == 0 && Integer.parseInt(numbersNew[2].trim()) ==0))//цвет красный
        {
            System.out.println("new price is not red");
        }
        Double sizeOldPrice = Double.parseDouble(toyOldPrice.getCssValue("font-size").replace("px","").trim());
        Double sizeNewPrice = Double.parseDouble(toyNewPrice.getCssValue("font-size").replace("px","").trim());
        if (!(sizeOldPrice<sizeNewPrice))//новая цена крупнее старой
        {
            System.out.println("new price is smaller");
        }

    String hrefq = toy.getAttribute("href");
        driver.navigate().to(hrefq);///переходим на страницу товара
        WebElement toyOldPrice2 = driver.findElement(By.cssSelector(".regular-price"));
        WebElement toyNewPrice2 = driver.findElement(By.cssSelector(".campaign-price"));
        String nameToy2 = driver.findElement(By.cssSelector("[itemprop=name]")).getText();
        String oldPrice2 = toyOldPrice2.getText();
        String newPrice2 = toyNewPrice2.getText();
        if (!nameToy.equals(nameToy2))//названия товара совпадает
        {
            System.out.println("Product name does not match");
        }
        if (!oldPrice.equals(oldPrice2))//старая цена совпадает
        {
            System.out.println("Old price does not match");
        }
        if (!newPrice.equals(newPrice2))//новая цена совпадает
        {
            System.out.println("New price does not match");
        }


        String[] numbers2 = toyOldPrice2.getCssValue("color").replace("rgba(", "").replace(")", "").split(",");
        r = Integer.parseInt(numbers[0].trim());
        g = Integer.parseInt(numbers[1].trim());
        b = Integer.parseInt(numbers[2].trim());
        if (!(r==g & r==b))//цвет серый если R, G и B одинаковые
        {
            System.out.println("color is not gray");
        }

        String tagOldPrice2 = toyOldPrice2.getAttribute("tagName");
        if (!(tagOldPrice2.equals("S") || tagOldPrice2.equals("STRIKE")))//цена зечеркнута
        {
            System.out.println("old price not crossed out");
        }

        String tagNewPrice2 = toyNewPrice2.getAttribute("tagName");
        if (!tagNewPrice2.equals("STRONG"))//цвет жирный
        {
            System.out.println("new price is not strong");
        }

        String[] numbersNew2 = toyNewPrice2.getCssValue("color").replace("rgba(", "").replace(")", "").split(",");
        if (!(Integer.parseInt(numbersNew2[1].trim()) == 0 && Integer.parseInt(numbersNew2[2].trim()) ==0))//цвет красный
        {
            System.out.println("new price is not red");
        }

        Double sizeOldPrice2 = Double.parseDouble(toyOldPrice2.getCssValue("font-size").replace("px","").trim());
        Double sizeNewPrice2 = Double.parseDouble(toyNewPrice2.getCssValue("font-size").replace("px","").trim());
        if (!(sizeOldPrice2<sizeNewPrice2))//новая цена крупнее старой
        {
            System.out.println("new price is smaller");
        }
    }
    @AfterEach
    public void stop() {
        driver.quit(); //остановить все ресурсы и процессы
        driver = null;
    }

}