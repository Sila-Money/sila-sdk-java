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

    public UpdateVirtualAccountMsg(String userHandle, String appHandle, String virtualAccountId, String virtualAccountName, Boolean active) {
        this.header = new Header(userHandle, appHandle);
        this.virtualAccountId = virtualAccountId;
        this.virtualAccountName = virtualAccountName;
        this.active=active;
    }
}
