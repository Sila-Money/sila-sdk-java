package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class AddressResponse extends RegistrationDataResponse {
    @Getter
    @SerializedName("address")
    private AddressData address;
}
