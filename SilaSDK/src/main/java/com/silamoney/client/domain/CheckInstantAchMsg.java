package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class CheckInstantAchMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("account_name")
    private final String accountName;
    @SerializedName("kyc_level")
    private final String kycLevel;
    public CheckInstantAchMsg(String userHandle, String authHandle, String accountName, String kycLevel,String reference) {
        this.header=new Header(userHandle,authHandle);
        this.header.setReference(reference);
        this.accountName=accountName;
        this.kycLevel=kycLevel;
    }
}
