package io.github.luankuhlmann.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class JokeResourceForApiConsumeTest {
    @Test
    public void shouldGet200StatusCode() {
        RestAssured
                .given()
                .get("randomjokes")
                .then()
                .statusCode(200);
    }
}
