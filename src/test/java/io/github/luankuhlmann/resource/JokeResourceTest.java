package io.github.luankuhlmann.resource;

import io.github.luankuhlmann.model.Joke;
import io.github.luankuhlmann.model.User;
import io.github.luankuhlmann.repository.JokeRepository;
import io.github.luankuhlmann.repository.UserRepository;
import io.github.luankuhlmann.service.JokeService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Principal;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static utils.TestUtils.*;

@QuarkusTest
public class JokeResourceTest {
    @InjectMock
    JokeService jokeService;

    @InjectMock
    JokeRepository jokeRepository;

    @InjectMock
    UserRepository userRepository;

    @InjectMock
    SecurityContext securityContext;

    @Test
    @DisplayName("Should successfully send a joke")
    public void shouldSendJoke() throws Exception {
        User mockUser = generateAUser("USER");
        Joke mockJoke = generateAJoke();

        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn(mockUser.getUsername());
        Mockito.when(securityContext.getUserPrincipal()).thenReturn(principal);

        Mockito.when(userRepository.findByUsername(mockUser.getUsername())).thenReturn(mockUser);
        Mockito.when(jokeService.saveJoke(any(SecurityContext.class), any(Joke.class)))
                .thenReturn(Response.status(201).entity(mockJoke).build());


        String jwtToken = generateToken("USER");

        var response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + jwtToken)
                .body(mockJoke)
                .when()
                .post("/jokes/send")
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }
}