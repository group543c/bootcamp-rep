package My.tests;


import org.junit.jupiter.api.Test;

public class CrateTests extends TestBase {
    @Test
    public void addToyToCrate() {
        for (int i = 0; i <= 2; i++) {
            app.openNewToy();//открываем страницу игрушки
            app.addNewToy();//добавляем ее в карзину
        }
            app.goToCrate();//переходим в карзину
            app.delToyInCrate();//удаляем, ждем, опять удаляем
    }
}

