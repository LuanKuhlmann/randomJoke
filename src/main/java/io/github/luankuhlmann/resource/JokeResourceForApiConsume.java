package io.github.luankuhlmann.resource;

import io.github.luankuhlmann.model.JokeForApiConsume;
import io.github.luankuhlmann.service.JokeServiceForApiConsume;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/jokes")
public class JokeResourceForApiConsume {
    @Inject
    @RestClient
    JokeServiceForApiConsume jokeServiceForApiConsume;

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomJoke() {
        JokeForApiConsume joke = jokeServiceForApiConsume.getRandomJoke();
        return Response.ok(joke).build();
    }
}
