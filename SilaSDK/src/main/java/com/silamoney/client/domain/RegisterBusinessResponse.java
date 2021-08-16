package com.silamoney.client.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class RegisterBusinessResponse extends BaseResponse {
    @Expose
    @SerializedName("business_uuid")
    private String business_uuid;
}
