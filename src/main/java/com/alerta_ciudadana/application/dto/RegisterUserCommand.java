package com.alerta_ciudadana.application.dto;

public record RegisterUserCommand(
        String dni,
        String fullName,
        String role,
        String password) {
}