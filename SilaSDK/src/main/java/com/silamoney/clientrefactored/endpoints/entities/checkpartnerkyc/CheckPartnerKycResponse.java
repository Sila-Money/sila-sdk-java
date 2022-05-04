package com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc;

import com.google.gson.annotations.SerializedName;

import com.silamoney.clientrefactored.domain.BaseResponse;
import lombok.Getter;

@Getter
public class CheckPartnerKycResponse extends BaseResponse {
    @SerializedName("entity_type")
    private String entityType;
    @SerializedName("verification_status")
    private String verificationStatus;
}
