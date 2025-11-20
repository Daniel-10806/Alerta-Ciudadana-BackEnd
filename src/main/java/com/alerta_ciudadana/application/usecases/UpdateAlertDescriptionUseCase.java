package com.alerta_ciudadana.application.usecases;

import com.alerta_ciudadana.domain.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateAlertDescriptionUseCase {

    private final AlertRepository alertRepository;

    public UpdateAlertDescriptionUseCase(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void execute(UUID id, String newDescription) {
        var alert = alertRepository.findById(id);
        if (alert == null) return;
        alert.updateDescription(newDescription);
        alertRepository.save(alert);
    }
}