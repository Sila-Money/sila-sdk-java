package com.silamoney.client.domain;

/**
 * Response used for the majority of endpoints.
 *
 * @author Karlo Lorenzana
 */
public class BaseResponse {

    /**
     *String field use for the response reference.
     */
    public String reference;

    /**
     *String field use for the response message.
     */
    public String message;

    /**
     *String field use for the response status.
     */
    public String status;
}
