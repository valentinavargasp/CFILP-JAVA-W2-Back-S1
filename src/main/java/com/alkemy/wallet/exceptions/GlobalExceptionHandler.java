package com.alkemy.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entidad no encontrada: " + e.getMessage());
    }

    // Manejamos cuando un argumento no es válido
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Argumento inválido: " + e.getMessage());
    }

    // Otras excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
    }

    // Manejamos cuando el método no es soportado
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("Método no permitido para esta ruta: " + e.getMethod());
    }

    // Manejamos cuando no se encuentra una ruta
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNotFound(NoHandlerFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ruta no encontrada: " + e.getRequestURL());
    }


    //FONDOS INSUFICIENTES PARA TRANSACCIONES O PAGOS
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFunds(InsufficientFundsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Fondos insuficientes: " + e.getMessage());
    }


}
