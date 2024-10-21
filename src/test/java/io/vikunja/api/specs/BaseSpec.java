package io.vikunja.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.vikunja.helpers.CustomAllureListener;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class BaseSpec {
    public static final RequestSpecification requestSpec = with()
            .filter(CustomAllureListener.withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static final ResponseSpecification responseSpecWithCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static final ResponseSpecification responseSpecWithCode201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();

    public static final ResponseSpecification responseSpecWithCode400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();

    public static final ResponseSpecification responseSpecWithCode403 = new ResponseSpecBuilder()
            .expectStatusCode(403)
            .log(ALL)
            .build();

    public static final ResponseSpecification responseSpecWithCode404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(ALL)
            .build();
}
