package com.github;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.webSteps.StepsGit;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SelenideWithAnnotatedSteps {

String REPOSITORY = "eroshenkoam/allure-example";
String ISSUE_NUMBER = "777";

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
@Owner("Kazakova")@Severity(SeverityLevel.MINOR)
@DisplayName("Тест с аннтоцией Step")
    void WithListenerOnly (){
    StepsGit steps = new StepsGit();
    steps.openMainPage();
    steps.searchRepository(REPOSITORY);
    steps.submitRepository(REPOSITORY);
    steps.openIssuesTub();
    steps.assertsIssueNumber(ISSUE_NUMBER);
}
}
