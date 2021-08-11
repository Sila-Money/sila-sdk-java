package com.silamoney.clientrefactored.endpoints.entities.register;

import lombok.Getter;

@Getter
public class RegisterResponse {

    private boolean success;
    private String reference;
    private String message;
    private String status;

}
