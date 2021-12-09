package Ex19.app;

import Ex19.pages.CratePage;
import Ex19.pages.StartPage;
import Ex19.pages.ToyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Application
{
    private WebDriverWait wait;
    private WebDriver driver;
    private StartPage startPage;
    private ToyPage toyPage;
    private CratePage cratePage;

    public Application(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        startPage = new StartPage(driver);
        toyPage = new ToyPage(driver);
        cratePage = new CratePage(driver);
    }

    public void quit() {driver.quit();}

    public void openNewToy(){
        startPage.open();
        startPage.imageWrapper.click();
    }

    public void addNewToy(){
        WebElement count = toyPage.quantity;//запоминаем сколько было в корзине до добавления
        int countInC = Integer.parseInt(toyPage.quantity.getText());
        List<WebElement> size = toyPage.chooserSize;
        if (size.size() > 0) {
            toyPage.selectSize();//выбор размера
        }
        toyPage.addToCrateButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(count, String.valueOf(countInC + 1)));
    }

    public void goToCrate(){
        toyPage.cartLink.click();
    }

    public void delToyInCrate(){
        wait.until(	ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=remove_cart_item]")));//Ожидание для проверки наличия элемента в DOM страницы.
        int tableSize = cratePage.removeButtons.size();
        int ts = tableSize;
        for (int i =0;i<tableSize;i++)
        {
            int rc = cratePage.removeButtons.size();
            if (rc==1)
            {
                cratePage.removeButtons.get(0).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".dataTable.rounded-corners")));
            }
            else {
                wait.until(	ExpectedConditions.presenceOfElementLocated(By.cssSelector(".shortcut")));//Ожидание для проверки наличия элемента в DOM страницы.
                cratePage.shortcut.click();
                wait.until(	ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[name=remove_cart_item]"))));
                cratePage.removeButton.click();
                wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".dataTable.rounded-corners td.item"),ts));//Ожидание проверки количества WebElements с заданным локатором меньше заданного числа
                ts--;
            }
        }
    }
}

