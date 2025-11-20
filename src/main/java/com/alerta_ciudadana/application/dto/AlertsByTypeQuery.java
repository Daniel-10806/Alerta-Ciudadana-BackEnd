package com.alerta_ciudadana.application.dto;

import com.alerta_ciudadana.domain.valueobjects.AlertType;

public record AlertsByTypeQuery(AlertType type) {}