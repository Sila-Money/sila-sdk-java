package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the Plaid SameDay Auth method
 *
 * @author wzelada
 */
public class PlaidSameDayAuthMsg {

    @SerializedName("account_name")
    private final String accountName;

    @SerializedName("header")
    private final Header header;

    public PlaidSameDayAuthMsg(String userHandle,
            String accountName,
            String appHandle,String reference) {
        this.accountName = accountName;
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
    }
}
