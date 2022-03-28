package com.silamoney.clientrefactored.endpoints.wallets.registerwallet;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class RegisterWalletResponse {

    private boolean success;
    private String reference;
    private String message;
    private String status;
    @SerializedName("wallet_nickname")
    private String walletNickname;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
