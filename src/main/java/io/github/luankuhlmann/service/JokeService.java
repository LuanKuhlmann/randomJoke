package io.github.luankuhlmann.service;

import io.github.luankuhlmann.model.Joke;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

public interface JokeService {
    Response saveJoke(@Context SecurityContext securityContext, Joke joke);
}
