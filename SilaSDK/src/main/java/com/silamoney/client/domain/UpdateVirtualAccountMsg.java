package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class UpdateVirtualAccountMsg {

    @SerializedName("header")
    private final Header header;

    @SerializedName("virtual_account_id")
    private final String virtualAccountId;
    @SerializedName("virtual_account_name")
    private final String virtualAccountName;
    @SerializedName("active")
    private final Boolean active;
    @SerializedName("ach_credit_enabled")
    private final Boolean achCreditEnabled;
    @SerializedName("ach_debit_enabled")
    private final Boolean achDebitEnabled;

    @SerializedName("statements_enabled")
    private final Boolean statementsEnabled;

    public UpdateVirtualAccountMsg(String userHandle, String appHandle, String virtualAccountId, String virtualAccountName, Boolean active, Boolean achCreditEnabled, Boolean achDebitEnabled,String reference,Boolean statementsEnabled) {
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
        this.virtualAccountId = virtualAccountId;
        this.virtualAccountName = virtualAccountName;
        this.active = active;
        this.achCreditEnabled = achCreditEnabled;
        this.achDebitEnabled = achDebitEnabled;
        this.statementsEnabled=statementsEnabled;
    }
}
