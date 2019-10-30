package com.silamoney.client.domain;

/**
 * Response used for the majority of endpoints.
 *
 * @author Karlo Lorenzana
 */
public class BaseResponse {

    private String reference;

    private String message;

    private String status;

    /**
     * Gets the response reference.
     * @return reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Gets the response message.
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the response status.
     * @return status
     */
    public String getStatus() {
        return status;
    }
}
