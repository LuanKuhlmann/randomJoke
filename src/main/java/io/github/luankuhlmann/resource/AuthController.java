package io.github.luankuhlmann.resource;

import io.github.luankuhlmann.dto.AuthRequest;
import io.github.luankuhlmann.dto.AuthResponse;
import io.github.luankuhlmann.model.Role;
import io.github.luankuhlmann.model.User;
import io.github.luankuhlmann.repository.UserRepository;
import io.github.luankuhlmann.security.PasswordUtils;
import io.github.luankuhlmann.security.TokenUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/auth")
public class AuthController {
    @Inject
    PasswordUtils passwordUtils;

    @Inject
    UserRepository userRepository;

    @ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration") public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response login(AuthRequest authRequest) {
        User user = userRepository.find("username", authRequest.username).firstResult();
        if (user != null && passwordUtils.checkPassword(authRequest.password, user.getPassword())) {
            try {
                List<Role> rolesList = user.getRoles();
                Set<Role> rolesSet = new HashSet<>(rolesList);
                return Response.ok(new AuthResponse(TokenUtils.generateToken(user.getUsername(), rolesSet, duration, issuer))).build();
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
