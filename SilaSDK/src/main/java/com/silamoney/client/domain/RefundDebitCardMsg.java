package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the refund debit card method.
 *
 * @author Anuj Kalal
 */
public class RefundDebitCardMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("transaction_id")
    private final String transactionId;

    /**
     * Constructor for refundDebitCard object.
     *
     * @param message
     */
    public RefundDebitCardMsg(String authHandle, RefundDebitCardMessage message) {
        this.header = new Header(message.getUserHandle(), authHandle);
        this.header.setReference(message.getReference());
        this.transactionId = message.getTransactionId();
    }
}
