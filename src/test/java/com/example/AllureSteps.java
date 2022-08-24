package com.example;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AllureSteps {

    @Step("Открываем сайт {url}")
    public void openMainPage(String url) {
        open(url);
    }

    @Step("Ищем репозиторий {repoName}")
    public void searchByRepoName(String repoName) {
        $(".header-search-input").setValue(repoName).submit();
    }

    @Step("Нажимаем на репозиторий {repoName}")
    public void ClickOnRepoByName(String repoName) {
        $$(".repo-list").findBy(text(repoName)).click();
    }

    @Step("Переходим в раздел Issues")
    public void ClickOnIssues() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issues с названием: {issueName}")
    public void FindIssueByName(String issueName) {
        $$(".Link--primary").findBy(text(issueName));
    }


}
