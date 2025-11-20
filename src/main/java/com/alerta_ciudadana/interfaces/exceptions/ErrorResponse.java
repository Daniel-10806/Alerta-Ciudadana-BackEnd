package com.alerta_ciudadana.interfaces.exceptions;

public record ErrorResponse(
        String message,
        String error,
        int status) {
}