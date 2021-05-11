package com.silamoney.clientrefactored.endpoints.accounts.checkinstantach;

import lombok.Getter;

@Getter
public class CheckInstantACHResponse {

    private boolean success;
    private String message;
    private String status;
    private String reference;

}
