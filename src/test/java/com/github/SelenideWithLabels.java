package com.github;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SelenideWithLabels {
@Test
@Owner("Kazakova") // Юзернейм свой
@Severity(SeverityLevel.CRITICAL) //
@DisplayName("Мой любимый тест")
@Feature("Задачи в репозитории") // Некая функциональность
@Story("Пользователь должен иметь возможность просматривать созданные задачи в репозитории") // Пользовательский сценарий
@Link (value = "Тестовая страница, например", url = "https://github.com/")
// Фича и Стори не отображаются в алюре у меня, почему????
void tesTest(){

}

@Test
@DisplayName("Параметрайз") //В отчете отображаются введенные параметры
void ParametersTest (){
    Allure.parameter( "Регион", "Москвоская Облась");
    Allure.parameter("Город", "Москва");
}

}
