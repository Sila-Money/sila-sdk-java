package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class ReverseTransactionMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("transaction_id")
    private final String transactionId;

    public ReverseTransactionMsg(String userHandle, String authHandle, String transactionId) {
        this.header = new Header(userHandle, authHandle);
        this.transactionId = transactionId;

    }
}
