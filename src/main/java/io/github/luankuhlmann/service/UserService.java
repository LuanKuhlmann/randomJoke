package io.github.luankuhlmann.service;

import io.github.luankuhlmann.model.User;
import jakarta.ws.rs.core.Response;

public interface UserService {
    Response register(User user);
    Response deactivate(Long id);
}
