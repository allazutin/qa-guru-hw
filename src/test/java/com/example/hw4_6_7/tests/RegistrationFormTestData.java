package com.example.hw4_6_7.tests;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class RegistrationFormTestData {
    static Faker enFakeData = new Faker(new Locale("en-US"));

    static String firstname = enFakeData.name().firstName(),
            lastname = enFakeData.name().lastName(),
            email = enFakeData.internet().emailAddress(),
            gender = "Male",
            phonenumber = enFakeData.number().digits(10),
            day = "15",
            month = "July",
            year = "2000",
            subject = "Math",
            uploadPicture = "img.jpg",
            hobbies = "Sports",
            address = enFakeData.address().fullAddress(),
            state = "Uttar Pradesh",
            city = "Agra";
}
