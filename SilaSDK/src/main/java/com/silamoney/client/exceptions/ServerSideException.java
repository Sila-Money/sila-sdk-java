package com.silamoney.client.exceptions;

/**
 * Exception thrown when a server side problem ocurrs.
 *
 * @author Karlo Lorenzana
 */
public class ServerSideException extends Exception {

    /**
     * Constructor for ServerSideException.
     *
     * @param errorMessage
     */
    public ServerSideException(String errorMessage) {
        super("Server Side Exception: " + errorMessage);
    }
}
