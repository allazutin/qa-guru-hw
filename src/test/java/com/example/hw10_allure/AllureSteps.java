package com.example.hw10_allure;

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
    public void clickOnRepoByName(String repoName) {
        $$(".repo-list").findBy(text(repoName)).click();
    }

    @Step("Переходим в раздел Issues")
    public void clickOnIssues() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issues с названием: {issueName}")
    public void findIssueByName(String issueName) {
        $$(".Link--primary").findBy(text(issueName));
    }


}
