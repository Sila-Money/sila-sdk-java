package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class EmailResponse extends BaseResponse {
    @Getter
    @SerializedName("email")
    private EmailData email;
}
