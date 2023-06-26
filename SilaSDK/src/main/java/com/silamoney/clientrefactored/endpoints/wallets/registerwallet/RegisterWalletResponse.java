package com.silamoney.clientrefactored.endpoints.wallets.registerwallet;

import com.google.gson.annotations.SerializedName;

import com.silamoney.clientrefactored.domain.BaseResponse;
import lombok.Getter;

@Getter
public class RegisterWalletResponse extends BaseResponse {

    @SerializedName("wallet_nickname")
    private String walletNickname;

    /**
     * boolean field used for the statements enabled.
     */
    @SerializedName("statements_enabled")
    public boolean statementsEnabled;
}
