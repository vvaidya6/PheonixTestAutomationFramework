package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAPITest {

    UserCredentials userCredentials = new UserCredentials("iamfd","password");


    @Test
    public void loginAPITest() throws IOException {
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .contentType(ContentType.JSON)
                .and()
                .accept(ContentType.JSON)
                .and()
                .body(userCredentials)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()
        .when()
                .post("/login")
        .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .and()
                .body("message",equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"))
                .log().all();


    }
}
