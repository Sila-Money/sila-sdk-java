package com.silamoney.clientrefactored.endpoints.entities.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import lombok.Getter;

@Getter
public class RegisterResponse extends BaseResponse {

    @Expose
    @SerializedName("business_uuid")
    private String business_uuid;
}
