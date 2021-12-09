package My.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ToyPage extends Page {
    public ToyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "span.quantity")
    public WebElement quantity;//счетчик товаров в карзине

    @FindBy(name = "add_cart_product")
    public WebElement addToCrateButton; //кнопка добавления товара в карзину

    @FindBy(css = "#cart .link")
    public WebElement cartLink; //ссылка на карзину справа наверху

    @FindBy(name = "options[Size]")
    public List<WebElement> chooserSize; //если есть выбор размера утки

    /*public WebElement quantity2() {return driver.findElement(By.cssSelector("span.quantity2"));}*///счетчик товаров в карзине
    /*public List<WebElement> chooserSize() {return driver.findElements(By.cssSelector("[name='options[Size]']"));}*///если есть выбор размера утки
    /*public WebElement addToCerteButton2() {return driver.findElement(By.cssSelector("[name=add_cart_product]"));}*///кнопка добавления товара в карзину
    /*public WebElement cartLink() {return driver.findElement(By.cssSelector("#cart .link"));}*///ссылка на карзину справа наверху
    public void selectSize() {
        driver.findElement(By.cssSelector("[name='options[Size]']")).click();
        driver.findElement(By.cssSelector("option[value=Small]")).click();
    }
}
