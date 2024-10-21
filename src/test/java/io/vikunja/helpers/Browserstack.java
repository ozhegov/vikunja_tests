package io.vikunja.helpers;

import io.vikunja.drivers.ConfigDriver;

import static io.restassured.RestAssured.given;

public class Browserstack {
    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json",
                sessionId);

        return given()
                .auth().basic(
                        ConfigDriver.getAuthConfig().browserstackUsername(),
                        ConfigDriver.getAuthConfig().browserstackPassword()
                )
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
