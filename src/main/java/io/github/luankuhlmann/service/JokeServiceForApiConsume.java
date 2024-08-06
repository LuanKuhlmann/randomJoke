package io.github.luankuhlmann.service;

import io.github.luankuhlmann.model.JokeForApiConsume;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/random_joke")
@RegisterRestClient
public interface JokeServiceForApiConsume {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    JokeForApiConsume getRandomJoke();
}
