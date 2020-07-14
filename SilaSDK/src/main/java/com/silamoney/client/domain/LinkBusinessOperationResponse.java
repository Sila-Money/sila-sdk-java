package com.silamoney.client.domain;

public class LinkBusinessOperationResponse {

    private String message;
    private boolean success;
    private String role;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

}