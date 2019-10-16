package com.silamoney.client.api;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Karlo Lorenzana
 */
public class ApiResponse {

    /**
     * Response status code.
     */
    public int statusCode;

    /**
     * Response headers.
     */
    public Map<String, List<String>> headers;

    /**
     * Response data.
     */
    public Object data;

    /**
     * Constructor for ApiResponse.
     *
     * @param statusCode
     * @param headers
     * @param data
     */
    public ApiResponse(int statusCode, Map<String, List<String>> headers, Object data) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.data = data;
    }
}
