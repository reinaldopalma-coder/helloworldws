package com.example.demo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Error 400: Campos del JSON inválidos (falla el @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarErroresValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> detalles = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            detalles.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse respuesta = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Error de Validación",
            "Los datos enviados en la petición no cumplen con los requisitos",
            detalles
        );

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    // 2. Error 400: Estructura JSON rota o tipos de datos incorrectos
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> manejarJsonMalFormado(HttpMessageNotReadableException ex) {
        ErrorResponse respuesta = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "JSON Inválido",
            "El cuerpo de la petición (JSON) tiene un formato incorrecto o tipos de datos incompatibles"
        );

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    // 3. Error 500: Errores inesperados del servidor
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarErroresGlobales(Exception ex) {
        ErrorResponse respuesta = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error Interno del Servidor",
            "Ocurrió un problema inesperado. Por favor, contacte al administrador"
        );

        // Aquí deberías registrar la traza real del error en tus logs para debugging:
        // logger.error("Error no controlado: ", ex);

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
