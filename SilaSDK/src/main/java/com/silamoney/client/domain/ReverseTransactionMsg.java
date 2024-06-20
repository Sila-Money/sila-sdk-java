package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class ReverseTransactionMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("transaction_id")
    private final String transactionId;

    public ReverseTransactionMsg(String userHandle, String authHandle, String transactionId,String reference) {
        this.header = new Header(userHandle, authHandle);
        this.header.setReference(reference);
        this.transactionId = transactionId;

    }
}
