package com.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AllureTest {

    String url = "https://github.com/";
    String projectName = "allure-framework/allure-java";
    String issueName = "Ability to choose the position of attachments";

    @Test
    void selenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        // открыть github
        open(url);
        // через поиск найти нужный репо
        $(".header-search-input").setValue(projectName).submit();
        // из результата поиска открыть нужный github проект
        $$(".repo-list").findBy(text(projectName)).click();
        // перейти в раздел issue
        $("#issues-tab").click();
        // проверка что issue с название существует
        $$(".Link--primary").findBy(text(issueName));
    }

    @Test
    void lambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем сайт " + url, () -> {
            open(url);
        });

        step("Ищем репозиторий " + projectName, () -> {
            $(".header-search-input").setValue(projectName).submit();
        });

        step("Нажимаем на репозиторий  " + projectName, () -> {
            $$(".repo-list").findBy(text(projectName)).click();
        });

        step("Переходим в раздел Issues " + projectName, () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issues с названием " + issueName, () -> {
            $$(".Link--primary").findBy(text(issueName));
        });
    }

    @Test
    void stepsTest() {
        AllureSteps steps = new AllureSteps();
        steps.openMainPage(url);
        steps.searchByRepoName(projectName);
        steps.ClickOnRepoByName(projectName);
        steps.ClickOnIssues();
        steps.FindIssueByName(issueName);
    }
}

