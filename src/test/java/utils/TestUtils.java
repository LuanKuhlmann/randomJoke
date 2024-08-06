package utils;

import io.github.luankuhlmann.model.Joke;
import io.github.luankuhlmann.model.Role;
import io.github.luankuhlmann.model.User;
import io.github.luankuhlmann.security.TokenUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestUtils {
    public static User generateAUser(String roleName) {
        User user = new User();
        user.setName("resourcetest");
        user.setEmail("resourcetest@mail.com");
        user.setPassword("12345");
        user.setCpf("12345678910");
        user.setUsername("resourcetest");
        user.setActive(true);

        Role role = generateRole(roleName);

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        return user;
    }

    public static String generateToken(String roleName) throws Exception {
        Set<Role> roles = new HashSet<>();
        Role role = generateRole(roleName);
        roles.add(role);
        return TokenUtils.generateToken("resourcetest", roles, 3600L, "https://github.com/luankuhlmann");
    }

    public static Role generateRole(String roleName) {
        Role role = new Role();
        role.setRole(roleName);
        return role;
    }

    public static Joke generateAJoke() {
        Joke joke = new Joke();
        joke.setType("General");
        joke.setSetup("Why did the chicken cross the road?");
        joke.setPunchline("To get to the other side!");
        joke.setUserId(1L);

        return joke;
    }
}
