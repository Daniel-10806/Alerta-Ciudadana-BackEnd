package com.alerta_ciudadana.interfaces.rest;

import com.alerta_ciudadana.application.dto.CreateAlertCommand;
import com.alerta_ciudadana.application.usecases.CreateAlertUseCase;
import com.alerta_ciudadana.application.usecases.UpdateAlertStatusUseCase;
import com.alerta_ciudadana.domain.repository.AlertRepository;
import com.alerta_ciudadana.domain.aggregates.Alert;
import com.alerta_ciudadana.domain.valueobjects.AlertStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/v1/alerts")
public class AlertController {

    private final CreateAlertUseCase createAlertUseCase;
    private final AlertRepository alertRepository;
    private final UpdateAlertStatusUseCase updateAlertStatusUseCase;

    public AlertController(
            CreateAlertUseCase createAlertUseCase,
            AlertRepository alertRepository,
            UpdateAlertStatusUseCase updateAlertStatusUseCase
    ) {
        this.createAlertUseCase = createAlertUseCase;
        this.alertRepository = alertRepository;
        this.updateAlertStatusUseCase = updateAlertStatusUseCase;
    }

    // =============================================================
    // 1. Crear alerta
    // =============================================================
    @Operation(
            summary = "Crear una nueva alerta",
            description = "Permite a un CIUDADANO registrar una alerta con tipo, descripción y ubicación.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Estructura de la alerta a registrar",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "type": "ROBO",
                                      "description": "Asalto en avenida principal",
                                      "lat": -12.0464,
                                      "lon": -77.0428
                                    }
                                    """
                            )
                    )
            )
    )
    @PreAuthorize("hasRole('CITIZEN')")
    @PostMapping
    public ResponseEntity<?> createAlert(
            @Valid @org.springframework.web.bind.annotation.RequestBody CreateAlertCommand command
    ) {
        return ResponseEntity.ok(createAlertUseCase.execute(command));
    }


    // =============================================================
    // 2. Obtener alertas pendientes
    // =============================================================
    @Operation(
            summary = "Listar alertas pendientes",
            description = "Permite a un OFICIAL ver todas las alertas con estado PENDING."
    )
    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/pending")
    public ResponseEntity<?> getPendingAlerts() {
        return ResponseEntity.ok(alertRepository.findByStatus(AlertStatus.PENDING));
    }

    // =============================================================
    // 3. Actualizar estado
    // =============================================================
    @Operation(
            summary = "Actualizar estado de una alerta",
            description = "Permite a un OFICIAL actualizar el estado de una alerta.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "status": "IN_PROGRESS"
                                    }
                                    """
                            )
                    )
            )
    )
    @PreAuthorize("hasRole('OFFICER')")
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<?> updateStatus(
            @PathVariable UUID id,
            @PathVariable String status
    ) {
        updateAlertStatusUseCase.execute(id, status);
        return ResponseEntity.ok("Estado actualizado a: " + status);
    }


    // =============================================================
    // 4. Obtener por ID
    // =============================================================
    @PreAuthorize("hasAnyRole('CITIZEN','OFFICER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Alert alert = alertRepository.findById(id);
        if (alert == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(alert);
    }

    // =============================================================
    // 5. Obtener todas
    // =============================================================
    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(alertRepository.findAll());
    }
}