package com.silamoney.clientrefactored.endpoints.entities.requestkyc;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class RequestKycResponse {

    private boolean success;
    private String reference;
    private String message;
    private String status;
    @SerializedName("verification_uuid")
    private String verificationUuid;
    @SerializedName("response_time_ms")
    private String responseTimeMs;

}
