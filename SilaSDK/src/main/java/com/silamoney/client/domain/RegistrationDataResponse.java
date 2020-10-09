package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class RegistrationDataResponse {
    @Getter
    @SerializedName("success")
    protected Boolean success;
    @Getter
    @SerializedName("status")
    protected String status;
    @Getter
    @SerializedName("message")
    protected String message;
}
