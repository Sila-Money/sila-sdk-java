package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class PlaidLinkTokenMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("android_package_name")
    private String androidPackageName;

    public PlaidLinkTokenMsg(String userHandle, String authHandle, String androidPackageName,String reference) {
        this.header = new Header(userHandle, authHandle);
        this.header.setReference(reference);
        if (androidPackageName != null)
            this.androidPackageName = androidPackageName;
    }
}
