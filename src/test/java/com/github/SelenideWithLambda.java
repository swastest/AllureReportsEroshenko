package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class SelenideWithLambda {

String REPOSITORY = "eroshenkoam/allure-example";
        //"eroshenkoam/allure-example";"swastest/lesson_3";
String ISSUE_NUMBER = "76";


@Severity(SeverityLevel.MINOR)
@Owner("Kazakova")
@Feature("Задачи в репо")
@Story("Просмотр Issue")
@BeforeAll
     static void setUp(){
    Configuration.browserSize ="1920x1080";
    Configuration.holdBrowserOpen = true;
    SelenideLogger.addListener("allure", new AllureSelenide());
}
@Test
@DisplayName("Тест с лямбдой Гит")
    void WithListenerOnly (){

    step ("Открыть главную страницу", () -> {
        Selenide.open("https://github.com/");
    });

    step ("Найти репозиторий в поиске " +REPOSITORY, () ->{
        $(".header-search-input").setValue(REPOSITORY).submit();
    });

    step ("Выбрать репозиторий " + REPOSITORY, ()-> {
        $(By.linkText(REPOSITORY)).click(); // простой поиск элемента по тексту
    });

  step ("Посмотреть Issues", ()->{
      $(By.partialLinkText("Issues")).click(); // поиск по частичному тексту
  });
   step ("Проверка ISSUE_NUMBER # " +ISSUE_NUMBER, ()-> {
       $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);

       //Сделать аттач формат html
       Allure.getLifecycle().addAttachment(
               "Исходники страницы",
               "text/html",
               "html",
               WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
       );
   });

}
}
