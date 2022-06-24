package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetVirtualAccountMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("virtual_account_id")
    private final String virtualAccountId;
    public GetVirtualAccountMsg(String userHandle, String authHandle, String virtualAccountId) {
        this.header = new Header(userHandle, authHandle);
        this.virtualAccountId = virtualAccountId;
    }
}
