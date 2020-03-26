package com.silamoney.client.exceptions;

/**
 * Exception thrown when a bad request is made.
 *
 * @author Karlo Lorenzana
 */
public class BadRequestException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4559785420326242558L;

    /**
     * Constructor for BadRequestException.
     *
     * @param errorMessage
     */
    public BadRequestException(String errorMessage) {
        super("Bad Request: " + errorMessage);
    }
}
