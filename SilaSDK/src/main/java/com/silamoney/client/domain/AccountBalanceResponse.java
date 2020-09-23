package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class AccountBalanceResponse extends BaseResponse {

    @Getter
    @SerializedName("available_balance")
    private int availableBalance;
    @Getter
    @SerializedName("current_balance")
    private int currentBalance;
    @Getter
    @SerializedName("masked_account_number")
    private String maskedAccountNumber;
    @Getter
    @SerializedName("routing_number")
    private int routingNumber;
    @Getter
    @SerializedName("account_name")
    private String accountName;
    
}
