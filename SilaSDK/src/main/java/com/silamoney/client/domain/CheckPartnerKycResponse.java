package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class CheckPartnerKycResponse {

    private boolean success;
    private String reference;
    private String message;
    private String status;
    @SerializedName("entity_type")
    private String entityType;
    @SerializedName("verification_status")
    private String verificationStatus;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
