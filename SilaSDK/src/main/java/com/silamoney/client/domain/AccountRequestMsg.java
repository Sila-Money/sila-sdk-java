package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class AccountRequestMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("account_name")
    private final String accountName;
    public AccountRequestMsg(String userHandle, String authHandle, String accountName,String reference) {
        this.header=new Header(userHandle,authHandle);
        this.header.setReference(reference);
        this.accountName=accountName;
    }
}
