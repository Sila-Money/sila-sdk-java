package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class CloseVirtualAccountMsg {

    @SerializedName("header")
    private final Header header;

    @SerializedName("virtual_account_id")
    private final String virtualAccountId;
    @SerializedName("account_number")
    private final String accountNumber;

    public CloseVirtualAccountMsg(String userHandle, String appHandle, String virtualAccountId, String accountNumber) {
        this.header = new Header(userHandle, appHandle);
        this.virtualAccountId = virtualAccountId;
        this.accountNumber = accountNumber;
    }
}
