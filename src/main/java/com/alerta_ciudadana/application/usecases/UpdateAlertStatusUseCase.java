package com.alerta_ciudadana.application.usecases;

import com.alerta_ciudadana.domain.repository.AlertRepository;
import com.alerta_ciudadana.domain.valueobjects.AlertStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateAlertStatusUseCase {

    private final AlertRepository repository;

    public UpdateAlertStatusUseCase(AlertRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, String newStatus) {
        repository.updateStatus(id, AlertStatus.valueOf(newStatus.toUpperCase()));
    }
}