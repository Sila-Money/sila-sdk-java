package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class GetSilaBalanceResponse extends BaseResponse {

    @SerializedName("sila_balance")
    @Getter
    private double silaBalance;
    @Getter
    private String address;
    
}