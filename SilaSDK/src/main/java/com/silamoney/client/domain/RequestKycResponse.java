package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class RequestKycResponse extends BaseResponse {
    
    @SerializedName(value = "verification_uuid")
    private String verificationUuid;
    @SerializedName(value = "verification_uuid_by_entity")
    private Object verificationUuidByEntity;
    @SerializedName(value = "accounts")
    private Object accounts;
}
