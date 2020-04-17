package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetAccountBalanceMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("account_name")
    private final String accountName;

    /**
     * Constructor for GetAccountsMsg object.
     *
     * @param userHandle
     * @param appHandle
     */
    public GetAccountBalanceMsg(String userHandle, String appHandle, String accountName) {
        this.header = new Header(userHandle, appHandle);
        this.accountName = accountName;
    }
}
