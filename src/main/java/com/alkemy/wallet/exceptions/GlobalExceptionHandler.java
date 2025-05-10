package com.alkemy.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
    Esta clase maneja excepciones globalmente.
    Se utiliza la anotación @RestControllerAdvice para definir un controlador de
    excepciones global.
    Se manejan tres tipos de excepciones: EntityNotFoundException,
    IllegalArgumentException y Exception.
    Para cada tipo de excepción, se define un método que devuelve una respuesta
    HTTP con un mensaje de error
    y un código de estado adecuado.
     */

    
    // Manejamos cuando no se encuentra un  recurso
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
    }

    // Manejamos cuando un argumento no es válido
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid argument: " + e.getMessage());
    }

    // Otras excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
    }
}
