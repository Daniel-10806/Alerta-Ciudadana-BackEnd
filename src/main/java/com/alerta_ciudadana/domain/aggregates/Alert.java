package com.alerta_ciudadana.domain.aggregates;

import com.alerta_ciudadana.domain.valueobjects.AlertStatus;
import com.alerta_ciudadana.domain.valueobjects.AlertType;
import com.alerta_ciudadana.domain.valueobjects.Coordinates;

import java.time.LocalDateTime;
import java.util.UUID;

public class Alert {

    private final UUID id;
    private AlertType type;
    private String description;
    private final Coordinates coordinates;
    private final LocalDateTime reportedAt;
    private AlertStatus status;
    private String assignedOfficer;
    private final String citizenDni; 

    public Alert(UUID id, AlertType type, String description, Coordinates coordinates, LocalDateTime reportedAt,
                 AlertStatus status, String citizenDni) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.coordinates = coordinates;
        this.reportedAt = reportedAt;
        this.status = status;
        this.citizenDni = citizenDni;
    }

    // ========== FACTORY METHOD CORREGIDO ==========

    public static Alert create(AlertType type, String description, Coordinates coordinates, String citizenDni) {
    return new Alert(
            UUID.randomUUID(),
            type,
            description,
            coordinates,
            LocalDateTime.now(),
            AlertStatus.PENDING,
            citizenDni
        );
    }

    public void updateDescription(String newDescription) {
        if (newDescription == null || newDescription.isBlank()) {
            throw new IllegalArgumentException("Descripción inválida");
        }

        this.description = newDescription; 
    }

    public UUID getId() {
        return id;
    }

    public AlertType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public String getAssignedOfficer() { 
        return assignedOfficer; 
    }

    public String getCitizenDni() { 
        return citizenDni; 
    }
}