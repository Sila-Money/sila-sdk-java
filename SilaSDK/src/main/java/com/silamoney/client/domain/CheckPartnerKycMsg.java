package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class CheckPartnerKycMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("query_app_handle")
    private final String queryAppHandle;
    @SerializedName("query_user_handle")
    private final String queryUserHandle;
    public CheckPartnerKycMsg(String authHandle, String queryAppHandle, String queryUserHandle,String reference) {
        this.header = new Header(null, authHandle);
        this.header.setReference(reference);
        this.queryAppHandle = queryAppHandle;
        this.queryUserHandle = queryUserHandle;
    }
}
