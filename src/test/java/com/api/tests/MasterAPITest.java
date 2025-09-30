package com.api.tests;

import static io.restassured.RestAssured.*;

import static com.api.utils.ConfigManager.*;

import static com.api.constant.Roles.*;
import static com.api.utils.AuthTokenProvider.*;

import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

public class MasterAPITest {

    @Test
    public void masterAPITest(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header("Authorization", getToken(FD))
                .and()
                .contentType("")
                .log().uri()
                .log().method()
                .log().headers()
                .and()
        .when()
                .post("/master")
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("message",equalTo("Success"))
                .body("data",notNullValue())
                .body("data",hasKey("mst_oem"))
                .body("data",hasKey("mst_model"))
                .body("$",hasKey("message"))
                .body("$",hasKey("data"))
                .body("data.mst_oem.size()",equalTo(2))
                .body("data.mst_model.size()",greaterThan(0))
                .body("data.mst_oem.id",everyItem(notNullValue()))
                .body("data.mst_oem.name",everyItem(notNullValue()))
                .body(matchesJsonSchemaInClasspath("response-schema/masterAPIResponseSchema.json"));


    }


    @Test
    public void invalidTokenMasterAPI(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header("Authorization", "")
                .and()
                .contentType("")
                .log().uri()
                .log().method()
                .log().headers()
                .and()
                .when()
                .post("/master")
                .then()
                .log().all()
                .statusCode(401);
    }
}
