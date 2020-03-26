package com.silamoney.client.exceptions;

/**
 * Exception thrown when a forbidden request is made.
 *
 * @author Karlo Lorenzana
 */
public class ForbiddenException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 7203269561383249150L;

    /**
     * Constructor for BadRequestException.
     *
     * @param errorMessage
     */
    public ForbiddenException(String errorMessage) {
        super("Forbidden: " + errorMessage);
    }
}
