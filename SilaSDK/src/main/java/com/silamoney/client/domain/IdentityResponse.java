package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class IdentityResponse extends RegistrationDataResponse {
    
    @SerializedName("identity")
    private IdentityData identity;

}
