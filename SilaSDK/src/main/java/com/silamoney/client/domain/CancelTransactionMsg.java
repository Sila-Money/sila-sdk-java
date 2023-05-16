package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class CancelTransactionMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("transaction_id")
    private final String transactionId;

    public CancelTransactionMsg(String authHandle, CancelTransactionMessage message) {
        this.header = new Header(message.getUserHandle(),authHandle);
        this.header.setReference(message.getReference());
        this.transactionId = message.getTransactionId();
    }
}
