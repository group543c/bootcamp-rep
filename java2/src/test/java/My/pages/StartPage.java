package My.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends Page {
    public StartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".image-wrapper")
    public WebElement imageWrapper; //первая картинка товара

    public void open() {driver.navigate().to("http://localhost/litecart/");}
/*    public WebElement imageWrapper() {return driver.findElement(By.cssSelector(".image-wrapper"));}*/
}
