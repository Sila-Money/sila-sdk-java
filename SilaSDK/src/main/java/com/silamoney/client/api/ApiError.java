package com.silamoney.client.api;

public class ApiError extends RuntimeException {
    private final int code;
    private final String errorBody;

    public ApiError(int code, String message, String errorBody) {
        super(message);
        this.code = code;
        this.errorBody = errorBody;
    }

    public int getCode() {
        return code;
    }

    public String getErrorBody() {
        return errorBody;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "code=" + code +
                ", errorBody='" + errorBody + '\'' +
                '}';
    }
}
