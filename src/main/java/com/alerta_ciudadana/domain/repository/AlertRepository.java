package com.alerta_ciudadana.domain.repository;

import com.alerta_ciudadana.domain.aggregates.Alert;
import com.alerta_ciudadana.domain.valueobjects.AlertStatus;
import com.alerta_ciudadana.domain.valueobjects.AlertType;

import java.util.List;
import java.util.UUID;

public interface AlertRepository {
    Alert save(Alert alert);

    List<Alert> findAll();

    Alert findById(UUID id);

    List<Alert> findByStatus(AlertStatus status);

    void updateStatus(UUID id, AlertStatus status);

    List<Alert> findByCitizen(String dni);

    List<Alert> findByType(AlertType type);
}