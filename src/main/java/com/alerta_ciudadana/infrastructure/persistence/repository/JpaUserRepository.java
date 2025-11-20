package com.alerta_ciudadana.infrastructure.persistence.repository;

import com.alerta_ciudadana.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByDni(String dni);
}