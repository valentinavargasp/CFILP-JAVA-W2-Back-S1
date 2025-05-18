package com.alkemy.wallet.exceptions;

import java.io.Serial;

/**
 * Excepción que se lanza cuando no hay fondos suficientes para realizar una operación.
 */
public class InsufficientFundsException extends Exception {
    
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción con mensaje null.
     */
    public InsufficientFundsException() {
        super();
    }

    /**
     * Construye una nueva excepción con el mensaje especificado.
     *
     * @param message el mensaje detallado
     */
    public InsufficientFundsException(String message) {
        super(message);
    }

    /**
     * Construye una nueva excepción con el mensaje y causa especificados.
     *
     * @param message el mensaje detallado
     * @param cause la causa de la excepción
     */
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construye una nueva excepción con la causa especificada.
     *
     * @param cause la causa de la excepción
     */
    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }
}