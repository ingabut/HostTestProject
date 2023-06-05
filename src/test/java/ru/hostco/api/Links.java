package ru.hostco.api;

public interface Links {
    String BASE_URI = "https://pp86.hostco.ru/";

    String authorizeUrl = BASE_URI + "api/pp/patient/auth/esia";
    String redirectUrl = BASE_URI + "callback";
    String serviceUrl = BASE_URI + "api/pp/auth";
}
