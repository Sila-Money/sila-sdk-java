package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import org.apache.http.util.TextUtils;

public class UpdateAccountMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("account_name")
    private final String accountName;
    @SerializedName("new_account_name")
    private final String newAccountName;
    @SerializedName("active")
    private Boolean active;

    public UpdateAccountMsg(String userHandle, String authHandle, String accountName, String newAccountName, Boolean active, String activeType,String reference) {
        this.header = new Header(userHandle, authHandle);
        this.header.setReference(reference);
        this.accountName = accountName;
        this.newAccountName = newAccountName;
        if (!TextUtils.isEmpty(activeType)) {
            this.active = active;
        }
    }
}
