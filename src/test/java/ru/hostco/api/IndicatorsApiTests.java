package ru.hostco.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static ru.hostco.api.Links.BASE_URI;

public class IndicatorsApiTests extends ApiBase{

    @Test
    public void receiveNumberOfElementsOnPage() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", "2");
        params.put("size", "20");
        params.put("startDate", String.valueOf(LocalDate.now().minusDays(2)));
        params.put("endDate", String.valueOf(LocalDate.now()));
        RestAssured.given()
                .cookie("connect.sid", connectSid)
                .when().get(BASE_URI + "api/pp/rest/health")
                .then().assertThat().statusCode(200)
                .body("paginator.numberOfElements", equalTo(20))
                .log().all();

    }
}

