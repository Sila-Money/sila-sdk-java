package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class PhoneResponse extends BaseResponse {
    @Getter
    @SerializedName("phone")
    private PhoneData phone;
}
