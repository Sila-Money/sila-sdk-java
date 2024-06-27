package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class RegisterWalletResponse extends BaseResponse {
    
    @SerializedName(value = "wallet_nickname")
    private String walletNickname;
    /**
     * boolean field used for the statements enabled.
     */
    @SerializedName("statements_enabled")
    public boolean statementsEnabled;

    @SerializedName("wallet_id")
    private String walletId;
}
