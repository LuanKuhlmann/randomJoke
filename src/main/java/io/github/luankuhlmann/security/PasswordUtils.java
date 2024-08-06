package io.github.luankuhlmann.security;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordUtils {
    public String hashPassword(String plainPassword) {
        return BcryptUtil.bcryptHash(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BcryptUtil.matches(plainPassword, hashedPassword);
    }
}
