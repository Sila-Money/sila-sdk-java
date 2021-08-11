package com.silamoney.client.domain;

import lombok.Getter;

@Getter
public class LinkBusinessOperationResponse {

    private String message;
    private boolean success;
    private String role;
    private String status;

}