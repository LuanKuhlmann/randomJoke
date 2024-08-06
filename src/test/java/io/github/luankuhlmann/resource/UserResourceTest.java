package io.github.luankuhlmann.resource;

import io.github.luankuhlmann.model.User;
import io.github.luankuhlmann.service.UserService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static utils.TestUtils.*;

@QuarkusTest
public class UserResourceTest {
    @InjectMock
    UserService userService;

    @Test
    @DisplayName("Should successfully register a new user")
    public void shouldRegisterAUser() {
        User mockUser = generateAUser("ADMIN");
        Mockito.when(userService.register(any(User.class)))
                .thenReturn(Response.status(201).entity(mockUser).build());

        var response = given()
                .contentType(ContentType.JSON)
                .body(mockUser)
                .when()
                .post("/user/register")
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Test
    @DisplayName("Should deactivate a user")
    public void shouldDeactivateUser() throws Exception {
        // Arrange
        Long mockUserId = 1L;
        Mockito.when(userService.deactivate(mockUserId))
                .thenReturn(Response.ok().build());

        String jwtToken = generateToken("ADMIN");

        var response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + jwtToken)
                .queryParam("id", mockUserId)
                .when()
                .delete("/user/deactivate")
                .then()
                .extract().response();

        assertEquals(204, response.getStatusCode());
    }
}