package com.alerta_ciudadana.domain.repository;

import com.alerta_ciudadana.domain.aggregates.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByDni(String dni);
}