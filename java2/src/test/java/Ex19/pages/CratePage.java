package Ex19.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CratePage extends Page {
    public CratePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);}

    public void open() {driver.navigate().to("http://localhost/litecart/en/checkout");}

    @FindBy(css = ".shortcut")
    public WebElement shortcut; //мини картинки в корзине (нет если 1 товар)

    @FindBy(name = "remove_cart_item")
    public WebElement removeButton; //мини картинки в корзине (нет если 1 товар)

    @FindBy(name = "remove_cart_item")
    public List<WebElement> removeButtons; //кнопки удаления (может быть не одна)

    /*public List<WebElement> removeButtons() {return driver.findElements(By.cssSelector("[name=remove_cart_item]"));}*/ //кнопки удаления (может быть не одна)
    /*public WebElement shortcut() {return driver.findElement(By.cssSelector(".shortcut"));} *///мини картинки в корзине (нет если 1 товар)
    /*public WebElement removeButton() {return driver.findElement(By.cssSelector("[name=remove_cart_item]"));}*/
}
