package io.github.luankuhlmann.resource;

import io.github.luankuhlmann.model.User;
import io.github.luankuhlmann.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserResource {
    @Inject
    UserService userService;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response registerUser(@Valid User user) {
        return userService.register(user);
    }

    @DELETE
    @Path("/deactivate")
    @RolesAllowed("ADMIN")
    public Response deactivateUser(Long id) {
        return userService.deactivate(id);
    }
}
