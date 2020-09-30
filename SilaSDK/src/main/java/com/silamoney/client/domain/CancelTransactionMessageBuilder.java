package com.silamoney.client.domain;

public class CancelTransactionMessageBuilder {
    private final String userHandle;
    private final String userPrivateKey;
    private final String transactionId;

    public CancelTransactionMessageBuilder(String userHandle, String userPrivateKey, String transactionId) {
        this.userHandle = userHandle;
        this.userPrivateKey = userPrivateKey;
        this.transactionId = transactionId;
    }

    public CancelTransactionMessage build() {
        return new CancelTransactionMessage(userHandle, userPrivateKey, transactionId);
    }
}
