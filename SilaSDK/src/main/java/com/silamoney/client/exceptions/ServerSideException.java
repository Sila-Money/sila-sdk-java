package com.silamoney.client.exceptions;

/**
 * Exception thrown when a server side problem ocurrs.
 *
 * @author Karlo Lorenzana
 */
public class ServerSideException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 8124844664921246754L;

    /**
     * Constructor for ServerSideException.
     *
     * @param errorMessage
     */
    public ServerSideException(String errorMessage) {
        super("Server Side Exception: " + errorMessage);
    }
}
