package com.silamoney.client.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class GetWalletResponse extends BaseResponse {

    @Getter
    private Wallet wallet;
    @SerializedName("is_whitelisted")
    @Getter
    private boolean isWhitelisted;

    @Expose
    @SerializedName("sila_balance")
    @Getter
    private double silaBalance;

    /**
     * double field used for the sila available balance.
     */
    @Getter
    @SerializedName("sila_available_balance")
    private double silaAvailableBalance;

    /**
     * double field used for the sila pending balance.
     */
    @Getter
    @SerializedName("sila_pending_balance")
    private double silaPendingBalance;
}