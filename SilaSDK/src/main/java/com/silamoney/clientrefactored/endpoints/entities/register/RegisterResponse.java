package com.silamoney.clientrefactored.endpoints.entities.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class RegisterResponse {

    private boolean success;
    private String reference;
    private String message;
    private String status;
    @Expose
    @SerializedName("business_uuid")
    private String business_uuid;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
