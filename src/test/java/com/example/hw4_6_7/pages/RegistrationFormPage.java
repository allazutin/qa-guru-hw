package com.example.hw4_6_7.pages;

import com.example.pages.components.CalendarComponent;

import com.codeborne.selenide.SelenideElement;
import com.example.pages.components.ResultsModal;

import static com.codeborne.selenide.Condition.pseudo;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

        private final static String TITLE_TEXT = "Student Registration Form";
        private CalendarComponent calendarComponent = new CalendarComponent();
        private ResultsModal resultsModal = new ResultsModal();
        private SelenideElement
                firstNameInput = $("#firstName"),
                lastNameInput = $("#lastName"),
                emailInput = $("#userEmail");

        public RegistrationFormPage openPage() {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");

            return this;
        }

        public RegistrationFormPage setFirstName(String value) {
            firstNameInput.setValue(value);

            return this;
        }

        public RegistrationFormPage clearFirstName() {
            firstNameInput.clear();

            return this;
        }

        public RegistrationFormPage setLastName(String value) {
            lastNameInput.setValue(value);

            return this;
        }

        public RegistrationFormPage setEmail(String value) {
            emailInput.setValue(value);

            return this;
        }

        public RegistrationFormPage setGender(String value) {
            $("#genterWrapper").$(byText(value)).click();

            return this;
        }

        public RegistrationFormPage setNumber(String value) {
            $("#userNumber").setValue(value);

            return this;
        }

        public RegistrationFormPage setSubjects(String value) {
            $("#subjectsInput").setValue(value).pressEnter();

            return this;
        }

        public  RegistrationFormPage setAddress(String value) {
            $("#currentAddress").setValue(value);

            return this;
        }

        public RegistrationFormPage setHobbies(String value) {
            $("#hobbiesWrapper").$(byText(value)).click();

            return this;
        }

        public RegistrationFormPage uploadPicture (String path) {
            $("#uploadPicture").uploadFromClasspath(path);

            return this;
        }

        public RegistrationFormPage setState(String value) {
            $("#state").click();
            $("#stateCity-wrapper").$(byText(value)).click();

            return this;
        }

        public RegistrationFormPage submit() {
            $("#submit").click();

            return this;
        }

    public RegistrationFormPage setCity(String value) {

        $("#city").click();
        $("#stateCity-wrapper").$(byText(value)).click();

        return this;
    }

        public RegistrationFormPage setBirthDate(String day, String month, String year) {
            $("#dateOfBirthInput").click();
            calendarComponent.setDate(day, month, year);

            return this;
        }

        public RegistrationFormPage checkResultsTableVisible() {
            resultsModal.checkVisible();

            return this;
        }

        public RegistrationFormPage checkResult(String key, String value) {
            resultsModal.checkResult(key, value);

            return this;
        }
    }