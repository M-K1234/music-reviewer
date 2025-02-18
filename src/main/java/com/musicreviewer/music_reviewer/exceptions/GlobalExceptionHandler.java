package com.musicreviewer.music_reviewer.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            String message = ex.getCause().getMessage();
            if (message.contains("email") || message.contains("username")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Username or email is already taken"));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "An error occurred during registration"));
    }
}

