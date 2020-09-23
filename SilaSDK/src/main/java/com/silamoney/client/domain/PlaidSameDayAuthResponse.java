package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class PlaidSameDayAuthResponse extends BaseResponse {

    @Getter
    @SerializedName("public_token")
    private String publicToken;
}
