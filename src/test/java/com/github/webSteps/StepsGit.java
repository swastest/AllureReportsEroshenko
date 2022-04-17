package com.github.webSteps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class StepsGit {

    @Step ("Открыть главную страницу")
    public StepsGit openMainPage(){
        Selenide.open("https://github.com/");
        return this;
    }

    @Step ("Найти репозиторий в поиске {repository}")
    public StepsGit searchRepository(String repository) {
        $(".header-search-input").setValue(repository).submit();
        return this;
    }
    @Step ("Выбрать репозиторий {repository}")
    public StepsGit submitRepository(String repository){
        $(By.linkText(repository)).click(); // простой поиск элемента по тексту
        return this;
    }
    @Step ("Посмотреть Issues")
    public StepsGit openIssuesTub(){
        $(By.partialLinkText("Issues")).click(); // поиск по частичному тексту
        return this;
    }
    @Step("Проверка ISSUE_NUMBER # + {issueNumber}")
    public StepsGit assertsIssueNumber(String issueNumber){
        $(withText("#" + issueNumber)).should(Condition.exist);
        addAttachScreenshot(); // вот сюда
        return this;
    }

    //Добавляем Аттач (потом название метода пишем в конце степа addAttachScreenshot()  См. выше в "Проверка ISSUE_NUMBER
@Attachment (value = "Важное название скриншота", type ="img/png", fileExtension = "png")
    byte[] addAttachScreenshot(){
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
}
}
