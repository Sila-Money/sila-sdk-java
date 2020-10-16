package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class PhoneData extends RegistrationData {
    @Getter
    @SerializedName("phone")
    private String phone;
}
