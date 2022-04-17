package bonusHomeworck;

import io.qameta.allure.Step;
import io.qameta.allure.Story;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.AttachmentsHelper.getSessionId;

public class StepsUsers {
    @Step ("Открыть страницу авторизации")
    void openPage (){
        open("/user/login/index");
    }
    @Step("Ввести логин {login}")
    void setUserLogin (String login){
        $("input[name =login]").setValue(login);
    }
    @Step("Вести пароль {password}")
    void setUserPassword(String password){
        $("input[name = password]").setValue(password);
    }
    @Step("Нажать на копку Авторизоваться")
    void submitAuthClick(){
        $("[value = Авторизоваться]").submit();
    }
    @Step ("Проверка юзера по имени в личном кабинете {userName}")
    void assertUserName (String userName){
        $("#fat-menu").shouldHave(text(userName));
    }

}
