package io.github.luankuhlmann.resource;

import io.github.luankuhlmann.model.Joke;
import io.github.luankuhlmann.model.Role;
import io.github.luankuhlmann.model.User;
import io.github.luankuhlmann.repository.JokeRepository;
import io.github.luankuhlmann.repository.UserRepository;
import io.github.luankuhlmann.service.JokeService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/jokes")
public class JokeResource {
    @Inject
    JokeRepository jokeRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    JokeService jokeService;

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("USER")
    public Response sendJoke(@Context SecurityContext securityContext, Joke joke) {
        return jokeService.saveJoke(securityContext, joke);
    }
}