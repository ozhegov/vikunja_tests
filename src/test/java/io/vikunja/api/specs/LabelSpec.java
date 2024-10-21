package io.vikunja.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LabelSpec {
    public static final ResponseSpecification labelCreateResponseSpecWithCode201 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode201)
            .expectBody(matchesJsonSchemaInClasspath("schemas/success_label_creation.json"))
            .build();

    public static final ResponseSpecification labelGetAllResponseSpecWithCode200 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/success_get_all_labels.json"))
            .build();

    public static final ResponseSpecification labelAddToTaskResponseSpecWithCode201 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode201)
            .expectBody(matchesJsonSchemaInClasspath("schemas/success_label_add_to_task.json"))
            .build();

    public static final ResponseSpecification labelAddToTaskResponseSpecWithCode404 = new ResponseSpecBuilder()
            .addResponseSpecification(BaseSpec.responseSpecWithCode404)
            .expectBody(matchesJsonSchemaInClasspath("schemas/not_found_id_label_add_to_task.json"))
            .build();
}
