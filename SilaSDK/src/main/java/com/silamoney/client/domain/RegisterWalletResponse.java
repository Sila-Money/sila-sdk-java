package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class RegisterWalletResponse extends BaseResponse {
    
    @SerializedName(value = "wallet_nickname")
    private String walletNickname;

}
