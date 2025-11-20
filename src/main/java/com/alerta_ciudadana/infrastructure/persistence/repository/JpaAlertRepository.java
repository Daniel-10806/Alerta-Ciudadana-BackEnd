package com.alerta_ciudadana.infrastructure.persistence.repository;

import com.alerta_ciudadana.infrastructure.persistence.entity.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaAlertRepository extends JpaRepository<AlertEntity, UUID> {

    List<AlertEntity> findByStatus(String status);

    @Modifying
    @Query("UPDATE AlertEntity a SET a.status = :status WHERE a.id = :id")
    void updateStatus(UUID id, String status);

    List<AlertEntity> findByType(String type);

    List<AlertEntity> findByCitizenDni(String dni);
}