package com.alerta_ciudadana.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAlertCommand(

        @NotBlank(message = "El tipo de alerta es obligatorio") String type,

        @NotBlank(message = "La descripción es obligatoria") @Size(min = 5, max = 200, message = "La descripción debe tener entre 5 y 200 caracteres") String description,

        @NotNull(message = "Latitud requerida") Double lat,

        @NotNull(message = "Longitud requerida") Double lon) {
}