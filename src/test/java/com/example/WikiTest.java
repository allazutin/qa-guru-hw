package com.example;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;

public class WikiTest {

    static Stream<Arguments> wikiMenuOtherProjectsTest() {
        return Stream.of(
                Arguments.of("ru", List.of("Викисклад", "Медиавики", "Мета-вики", "Многоязычная Викитека",
                        "Викивиды", "Викиучебник", "Викиданные", "Викимания",
                        "Викиновости", "Викицитатник", "Викитека", "Викиверситет",
                        "Викигид", "Викисловарь", "Элемент Викиданных")),
                Arguments.of("en", List.of("Wikimedia Commons", "MediaWiki", "Meta-Wiki",
                        "Multilingual Wikisource", "Wikispecies", "Wikibooks", "Wikidata", "Wikimania",
                        "Wikinews", "Wikiquote", "Wikisource", "Wikiversity", "Wikivoyage", "Wiktionary"))
        );
    }
    @MethodSource()
    @ParameterizedTest(name = "На локали {0} сайта есть меню остальных проектов с текстом: {1}")

    void wikiMenuTest (String lang, List<String> expectedTexts){
        // открыть сайт
        String url = String.format("https://%s.wikipedia.org/", lang);
        open(url);
        $$(".wb-otherproject-link").shouldHave(CollectionCondition.texts(expectedTexts));

    }

    @CsvSource(value = {
            "en, Welcome to ",
            "ru, Добро пожаловать в ",
    })
    @ParameterizedTest(name = "На локали {0} открывается страница с текстом {1}")
    void wikiOpenLocalePage(String lang, String expectedResult) {
        String url = String.format("https://%s.wikipedia.org/", lang);
        open(url);
        $(".mw-headline").shouldHave(text(expectedResult));
    }

    @ValueSource(strings = {"Викиучебник", "Медиавики"})
    @ParameterizedTest(name = "На страницах {0} есть кнопка History")
    void wikiHistoryButtonIsAvailableTest(String testData) {
        open("https://ru.wikipedia.org");
        $$(".wb-otherproject-link").find(text(testData)).click();
        $$("span").filter(text("История"));
    }
}


