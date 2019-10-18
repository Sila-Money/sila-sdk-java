package com.silamoney.client.exceptions;

/**
 * Exception thrown when a forbidden request is made.
 *
 * @author Karlo Lorenzana
 */
public class ForbiddenException extends Exception {

    /**
     * Constructor for BadRequestException.
     *
     * @param errorMessage
     */
    public ForbiddenException(String errorMessage) {
        super("Forbidden: " + errorMessage);
    }
}
