package com.api.tests;

import static io.restassured.RestAssured.*;

import static com.api.constant.Roles.*;
import static com.api.utils.AuthTokenProvider.*;
import static  com.api.utils.ConfigManager.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

public class CountAPITest {


    @Test
    public void verifyCountAPIResponse(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header("Authorization", getToken(FD))
                .log().uri()
                .log().method()
                .log().headers()
                .and()
        .when()
                .get("/dashboard/count")
        .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Success"))
                .time(lessThan(1000L))
                .body("data", notNullValue())
                .body("data.size()",equalTo(3))
                .body("data.count",everyItem(greaterThanOrEqualTo(0)))
                .body("data.label",everyItem(not(blankOrNullString())))
                .body("data.key",containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
                .body(matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema.json"));
    }

    @Test
    public void countAPITest_MissingAuthToken(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .log().uri()
                .log().method()
                .log().headers()
        .when()
                .get("/dashboard/count")
        .then()
                .log().all()
                .statusCode(401);
    }
}
