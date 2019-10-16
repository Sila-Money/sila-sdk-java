package com.silamoney.client.exceptions;

/**
 * Exception thrown when an invalid signature is sent.
 *
 * @author Karlo Lorenzana
 */
public class InvalidSignatureException extends Exception {

    /**
     * Constructor for InvalidSignatureException.
     *
     * @param errorMessage
     */
    public InvalidSignatureException(String errorMessage) {
        super("Invalid Signature: " + errorMessage);
    }
}
