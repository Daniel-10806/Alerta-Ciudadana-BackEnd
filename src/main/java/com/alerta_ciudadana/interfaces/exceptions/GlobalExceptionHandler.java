package com.alerta_ciudadana.interfaces.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // -------------------------------------------------------------------------
    // Errores de validación (Bean Validation)
    // -------------------------------------------------------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(
                        message,
                        "VALIDATION_ERROR",
                        HttpStatus.BAD_REQUEST.value()
                ));
    }

    // -------------------------------------------------------------------------
    // Errores de permisos (Spring Security – Roles insuficientes)
    // -------------------------------------------------------------------------
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(
                        "Acceso denegado: no tienes permisos para esta operación",
                        "ACCESS_DENIED",
                        HttpStatus.FORBIDDEN.value()
                ));
    }

    // -------------------------------------------------------------------------
    // Errores generales (RuntimeException)
    // -------------------------------------------------------------------------
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(
                        ex.getMessage(),
                        "INTERNAL_SERVER_ERROR",
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                ));
    }
}