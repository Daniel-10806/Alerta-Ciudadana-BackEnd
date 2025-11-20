package com.alerta_ciudadana.infrastructure.persistence.repository;

import com.alerta_ciudadana.domain.aggregates.User;
import com.alerta_ciudadana.domain.repository.UserRepository;
import com.alerta_ciudadana.domain.valueobjects.Role;
import com.alerta_ciudadana.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpa;

    public UserRepositoryImpl(JpaUserRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(
                user.getDni(),
                user.getFullName(),
                user.getPasswordHash(),
                user.getRole().name()
        );

        UserEntity saved = jpa.save(entity);

        return new User(
                saved.getDni(),
                saved.getFullName(),
                saved.getPasswordHash(),
                Role.valueOf(saved.getRole())
        );
    }

    @Override
    public Optional<User> findByDni(String dni) {
        return jpa.findByDni(dni)
                .map(entity -> new User(
                        entity.getDni(),
                        entity.getFullName(),
                        entity.getPasswordHash(),
                        Role.valueOf(entity.getRole())
                ));
    }
}