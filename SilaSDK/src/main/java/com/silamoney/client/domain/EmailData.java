package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class EmailData extends RegistrationData {
    @Getter
    @SerializedName("email")
    private String email;
}
