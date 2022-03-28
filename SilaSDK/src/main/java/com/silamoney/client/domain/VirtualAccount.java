package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class VirtualAccount {
    @SerializedName("virtual_account_id")
    private String virtualAccountId;
    @SerializedName("virtual_account_name")
    private String virtualAccountName;
    @SerializedName("account_number")
    private String accountNumber;
    @SerializedName("routing_number")
    private String routingNumber;
    @SerializedName("account_type")
    private String accountType;
    @SerializedName("created_epoch")
    private String created_epoch;
    @SerializedName("closed_epoch")
    private String closedEpoch;
    private boolean active;
    private boolean closed;
    @SerializedName("ach_credit_enabled")
    private boolean achCreditEnabled;
    @SerializedName("ach_debit_enabled")
    private boolean achDebitEnabled;
}
