package com.example.hw4_6_7.tests;

import com.codeborne.selenide.Configuration;
import com.example.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.example.hw4_6_7.tests.RegistrationFormTestData.*;
import static java.lang.String.format;

public class RegistrationFormTests {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        registrationFormPage.openPage()
                .setFirstName(firstname)
                .setLastName(lastname)
                .setEmail(email)
                .setGender(gender)
                .setNumber(phonenumber)
                .setBirthDate(day, month, year)
                .setSubjects(subject)
                .uploadPicture(uploadPicture)
                .setHobbies(hobbies)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();

        String expectedFullName = format("%s %s", firstname, lastname),
         expectedDate = format("%s %s,%s", day, month, year),
                expectedStateAndCity = format("%s %s", state, city);

        registrationFormPage.checkResultsTableVisible()

                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", email)
                .checkResult("Gender",gender)
                .checkResult("Mobile",phonenumber)
                .checkResult("Date of Birth", expectedDate)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", uploadPicture)
                .checkResult("Address", address)
                .checkResult("State and City", expectedStateAndCity);
    }
}

