package io.github.luankuhlmann.repository;

import io.github.luankuhlmann.model.Joke;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JokeRepository implements PanacheRepository<Joke> {
}
