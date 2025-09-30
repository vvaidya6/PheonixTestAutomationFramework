package com.api.tests;

import static io.restassured.RestAssured.*;

import static com.api.utils.ConfigManager.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.constant.Roles.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserDetailAPIRequestTest {

    Header authHeader = new Header("Authorization", getToken(FD));

    @Test
    public void userDetailsAPITest() throws IOException {

        given()
                .baseUri(getProperty("BASE_URI"))
                .contentType(ContentType.JSON)
                .header(authHeader)
                .accept(ContentType.JSON)
                .log().uri()
                .log().headers()
                .log().method()
                .log().body()
        .when()
                .get("/userdetails")
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/userDetailsResponseSchema.json"));

    }
}
