package com.alerta_ciudadana.application.usecases;

import com.alerta_ciudadana.application.dto.CreateAlertCommand;
import com.alerta_ciudadana.domain.aggregates.Alert;
import com.alerta_ciudadana.domain.repository.AlertRepository;
import com.alerta_ciudadana.domain.valueobjects.AlertType;
import com.alerta_ciudadana.domain.valueobjects.Coordinates;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreateAlertUseCase {

    private final AlertRepository alertRepository;

    public CreateAlertUseCase(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert execute(CreateAlertCommand command) {

    AlertType type;
    try {
        type = AlertType.valueOf(command.type().trim().toUpperCase());
    } catch (Exception e) {
        throw new IllegalArgumentException("Tipo de alerta inválido. Tipos permitidos: ROBO, EXTORSION, MARCA, ASALTO, TENTATIVA");
    }

    Coordinates coordinates = new Coordinates(
            command.lat(),
            command.lon()
    );

    String citizenId = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    Alert alert = Alert.create(
            type,
            command.description(),
            coordinates,
            citizenId
    );

    return alertRepository.save(alert);
}
}