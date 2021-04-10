package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class LinkBusinessMemberResponse extends LinkBusinessOperationResponse {
    private String details;
    @SerializedName(value = "verification_uuid")
    private String verificationUuid;
}