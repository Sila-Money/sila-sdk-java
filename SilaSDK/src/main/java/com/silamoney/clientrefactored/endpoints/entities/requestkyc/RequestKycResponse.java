package com.silamoney.clientrefactored.endpoints.entities.requestkyc;

import com.google.gson.annotations.SerializedName;

import com.silamoney.clientrefactored.domain.BaseResponse;
import lombok.Getter;

@Getter
public class RequestKycResponse extends BaseResponse {

    @SerializedName("verification_uuid")
    private String verificationUuid;

}
