package io.github.luankuhlmann.service.impl;

import io.github.luankuhlmann.model.Joke;
import io.github.luankuhlmann.model.User;
import io.github.luankuhlmann.repository.JokeRepository;
import io.github.luankuhlmann.repository.UserRepository;
import io.github.luankuhlmann.service.JokeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
public class JokeServiceImpl implements JokeService {
    @Inject
    JokeRepository jokeRepository;

    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public Response saveJoke(SecurityContext securityContext, Joke joke) {
//        userRepository.findByUsername(securityContext.getUserPrincipal().getName());
        User user = userRepository.find("username", securityContext.getUserPrincipal().getName()).firstResult();

        joke.setUserId(user.getId());
        jokeRepository.persist(joke);

        return Response.ok().build();
    }
}
