package com.example.hw5_selenide;

import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    @Test
            void checkJUnitTest () {
        // Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");

        // Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

        // Откройте страницу SoftAssertions,
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();

        // проверьте что внутри есть пример кода для JUnit5
        $("#wiki-content").shouldHave(text("Using JUnit5 extend test class:"));
    }
}
