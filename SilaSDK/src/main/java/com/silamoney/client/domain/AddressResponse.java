package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class AddressResponse extends BaseResponse {
    @Getter
    @SerializedName("address")
    private AddressData address;
}
