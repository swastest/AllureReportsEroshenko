package bonusHomeworck;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class UsersAuthWithAnnotatedStepsTest {
    @BeforeAll
    static void config(){
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
      void authUsers(String login, String password, String userName){
        Allure.parameter("Логин", login);
        Allure.parameter("Пароль",password);
        Allure.parameter("Имя пользователя",userName);
        StepsUsers stepUs = new StepsUsers();
       stepUs.openPage();
       stepUs.setUserLogin(login);
       stepUs.setUserPassword(password);
       stepUs.submitAuthClick();
       stepUs.assertUserName(userName);



    }

}
