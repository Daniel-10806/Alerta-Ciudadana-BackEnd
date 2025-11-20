package com.alerta_ciudadana.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alerts")
public class AlertEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, length = 1000)
    private String description;

    private double lat;
    private double lon;
    private LocalDateTime reportedAt;

    private String status;

    private String citizenDni;

    public AlertEntity() {
    }

    public AlertEntity(UUID id, String type, String description, double lat, double lon, LocalDateTime reportedAt,
            String status, String citizenDni) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.reportedAt = reportedAt;
        this.status = status;
        this.citizenDni = citizenDni;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public String getStatus() {
        return status;
    }

    public String getCitizenDni() { 
        return citizenDni; 
    }
}