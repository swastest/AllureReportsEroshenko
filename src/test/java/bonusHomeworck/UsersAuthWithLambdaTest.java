package bonusHomeworck;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class UsersAuthWithLambdaTest {
    @BeforeAll
    static void Config(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl ="http://users.bugred.ru";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    // Правда тут я не поняла, как подружить csvSours с Allure.parameter
    // Поэтому в отчете отображается криво-задвоенно
    @CsvSource(value = {
            "ggg@ggg.com, 1234, ggg",
            "fff@fff.ru, 4321, foo"
    })

    @Owner("Kazakova")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Действия с авторизацией")
    @Story("Авторизация доступна всем существующим пользователям")
    @Link(value = "Тестовая площадка для тренировки", url = "http://users.bugred.ru")
    @DisplayName("Авторизация существующих пользователей")
    @ParameterizedTest (name = "Авторизация существующих пользователей")
      void AuthUsers(String login, String password, String userName){
        Allure.parameter("Логин", login);
        Allure.parameter("Пароль",password);
        Allure.parameter("Имя пользователя",userName);
        step ("Открыть страницу авторизации", ()->{
            open("/user/login/index");
        });
        step("Ввести логин " +login, ()->{
            $("input[name =login]").setValue(login);
        });
        step ("Вести пароль " +password, ()->{
            $("input[name = password]").setValue(password);
        });
       step("Нажать на копку Авторизоваться", ()->{
           $("[value = Авторизоваться]").submit();
       });
       step("Проверка юзера по имени в личном кабинете "+userName, ()->{
           $("#fat-menu").shouldHave(text(userName));
       });



    }
}
