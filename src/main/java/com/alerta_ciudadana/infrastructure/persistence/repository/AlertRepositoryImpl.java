package com.alerta_ciudadana.infrastructure.persistence.repository;

import com.alerta_ciudadana.domain.aggregates.Alert;
import com.alerta_ciudadana.domain.repository.AlertRepository;
import com.alerta_ciudadana.domain.valueobjects.AlertStatus;
import com.alerta_ciudadana.domain.valueobjects.AlertType;
import com.alerta_ciudadana.domain.valueobjects.Coordinates;
import com.alerta_ciudadana.infrastructure.persistence.entity.AlertEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class AlertRepositoryImpl implements AlertRepository {

    private final JpaAlertRepository jpa;

    public AlertRepositoryImpl(JpaAlertRepository jpa) {
        this.jpa = jpa;
    }

    // ========================
    // SAVE
    // ========================
    @Override
    public Alert save(Alert alert) {
        AlertEntity entity = new AlertEntity(
                alert.getId(),
                alert.getType().name(),
                alert.getDescription(),
                alert.getCoordinates().lat(),
                alert.getCoordinates().lon(),
                alert.getReportedAt(),
                alert.getStatus().name(),
                alert.getCitizenDni()
        );

        jpa.save(entity);
        return alert;
    }

    // ========================
    // FIND ALL
    // ========================
    @Override
    public List<Alert> findAll() {
        return jpa.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    // ========================
    // FIND BY ID
    // ========================
    @Override
    public Alert findById(UUID id) {
        return jpa.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    // ========================
    // FIND BY STATUS
    // ========================
    @Override
    public List<Alert> findByStatus(AlertStatus status) {
        return jpa.findByStatus(status.name())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    // ========================
    // UPDATE STATUS
    // ========================
    @Transactional
    @Override
    public void updateStatus(UUID id, AlertStatus status) {
        jpa.updateStatus(id, status.name());
    }

    // ========================
    // FIND BY TYPE
    // ========================
    @Override
    public List<Alert> findByType(AlertType type) {
        return jpa.findByType(type.name())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    // ========================
    // FIND BY CITIZEN DNI
    // ========================
    @Override
    public List<Alert> findByCitizen(String dni) {
        return jpa.findByCitizenDni(dni)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    // ========================
    // MAPPER → DOMAIN
    // ========================
    private Alert toDomain(AlertEntity e) {
        return new Alert(
                e.getId(),
                AlertType.valueOf(e.getType()),
                e.getDescription(),
                new Coordinates(e.getLat(), e.getLon()),
                e.getReportedAt(),
                AlertStatus.valueOf(e.getStatus()),
                e.getCitizenDni()
        );
    }
}