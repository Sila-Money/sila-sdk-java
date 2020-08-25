package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class GetWalletResponse extends BaseResponse {

    @Getter
    private Wallet wallet;
    @SerializedName("is_whitelisted")
    @Getter
    private boolean isWhitelisted;
    @SerializedName("sila_balance")
    @Getter
    private double silaBalance;
    
}