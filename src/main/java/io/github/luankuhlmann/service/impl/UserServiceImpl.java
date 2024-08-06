package io.github.luankuhlmann.service.impl;

import io.github.luankuhlmann.model.User;
import io.github.luankuhlmann.repository.UserRepository;
import io.github.luankuhlmann.security.PasswordUtils;
import io.github.luankuhlmann.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepository;

    @Inject
    PasswordUtils passwordUtils;

    @Override
    @Transactional
    public Response register(User user) {
        user.setPassword(passwordUtils.hashPassword(user.getPassword()));
        user.setActive(true);
        userRepository.persist(user);

        return Response.ok().build();
    }

    @Override
    @Transactional
    public Response deactivate(Long id) {
        Optional<User> findUser = userRepository.findByIdOptional(id);

        User user = findUser.orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);

        return Response.noContent().build();
    }
}
