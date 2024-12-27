package org.ajc.exceptions;

/**
 * Custom exception to handle validation errors.
 * It is thrown when an entity fails to meet its validation constraints.
 */
public class ValidationException extends RuntimeException {
    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message the detail message
     */
    public ValidationException(String message) {
        super(message);
    }
}
