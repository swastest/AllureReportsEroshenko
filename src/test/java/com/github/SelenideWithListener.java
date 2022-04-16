package com.github;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class SelenideWithListener {

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
 //   Configuration.holdBrowserOpen = true;
    SelenideLogger.addListener("allure", new AllureSelenide());
}
@Test
@DisplayName("У меня нет другого Issue, sorry")
    void WithListenerOnly (){

    Selenide.open("https://github.com/");
    $(".header-search-input").setValue(REPOSITORY).submit();
    $(By.linkText(REPOSITORY)).click(); // простой поиск элемента по тексту
    $(By.partialLinkText("Issues")).click(); // поиск по частичному тексту
    $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);



}
}
