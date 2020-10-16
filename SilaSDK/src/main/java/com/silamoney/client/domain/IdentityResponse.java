package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class IdentityResponse extends RegistrationDataResponse {
    @Getter
    @SerializedName("identity")
    private IdentityData identity;
}
