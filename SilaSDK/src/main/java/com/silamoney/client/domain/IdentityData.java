package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class IdentityData extends RegistrationData {
    @Getter
    @SerializedName("identity_type")
    private String identityType;
    @Getter
    @SerializedName("identity")
    private String identity;
}
