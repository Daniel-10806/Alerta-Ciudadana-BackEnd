package com.alerta_ciudadana.application.usecases;

import com.alerta_ciudadana.domain.aggregates.Alert;
import com.alerta_ciudadana.domain.repository.AlertRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetCitizenAlertsHistoryUseCase {

    private final AlertRepository repository;

    public GetCitizenAlertsHistoryUseCase(AlertRepository repository) {
        this.repository = repository;
    }

    public List<Alert> execute(String dni) {
        return repository.findByCitizen(dni);
    }
}