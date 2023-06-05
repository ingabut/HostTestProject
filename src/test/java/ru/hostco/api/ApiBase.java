package ru.hostco.api;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static ru.hostco.api.Links.*;
import static ru.hostco.utils.GeneralProperties.generalConfig;

public class ApiBase {
    private static final String username = generalConfig.login();
    private static final String password = generalConfig.password();
    String connectSid = null;

    @BeforeClass(alwaysRun = true)
    public static void setUpSpecifications() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeMethod
    public void makeAuth() {

        Map<String, String> loginParams = new HashMap<String, String>();
        loginParams.put("redirectURI", redirectUrl);
        loginParams.put("service", serviceUrl);

        Response response = RestAssured.given().formParams(loginParams).get(authorizeUrl);
        String cookieValue = response.getCookie("AUTH_SESSION_ID");
        String cookieLegacyValue = response.getCookie("AUTH_SESSION_ID_LEGACY");
        String KC_Restart = response.getCookie("KC_RESTART");

        String authUrlWithParams = response.htmlPath().getString("'**'.find{node -> node.name()=='form'}*.@action");
        Map<String, String> codeParams = new HashMap<String, String>();
        codeParams.put("username", username);
        codeParams.put("password", password);
        response = RestAssured.given()
                .cookie("AUTH_SESSION_ID", cookieValue)
                .cookie("AUTH_SESSION_ID_LEGACY", cookieLegacyValue)
                .cookie("KC_RESTART", KC_Restart)
                .formParams(codeParams)
                .post(authUrlWithParams);

        final String location = response.getHeader(HttpHeaders.LOCATION);
        if(location != null) {
            var cookies = response.getCookies();
            response = RestAssured.given()
                    .cookies(cookies)
                    .when().redirects().follow(false)
                    .get(location);
            connectSid = response.getCookie("connect.sid");
        }

    }
}