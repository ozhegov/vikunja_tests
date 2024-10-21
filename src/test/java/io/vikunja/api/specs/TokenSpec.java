package io.vikunja.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TokenSpec {
    public static final ResponseSpecification tokenCreateResponseSpecWithCode201 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode201)
            .expectBody(matchesJsonSchemaInClasspath("schemas/success_token_creation.json"))
            .build();

    public static final ResponseSpecification tokenDeleteResponseSpecWithCode200 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/error_message_token.json"))
            .build();

    public static final ResponseSpecification tokenCreateResponseSpecWithCode400 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode400)
            .expectBody(matchesJsonSchemaInClasspath("schemas/error_message_token.json"))
            .build();

    public static final ResponseSpecification tokenDeleteResponseSpecWithCode403 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode403)
            .expectBody(matchesJsonSchemaInClasspath("schemas/error_message_token.json"))
            .build();

    public static final ResponseSpecification tokenDeleteResponseSpecWithCode404 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode404)
            .expectBody(matchesJsonSchemaInClasspath("schemas/error_message_token.json"))
            .build();
}
