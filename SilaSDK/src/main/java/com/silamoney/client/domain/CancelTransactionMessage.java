package com.silamoney.client.domain;

import lombok.Getter;

public class CancelTransactionMessage {
    @Getter
    private final String userHandle;
    @Getter
    private final String userPrivateKey;
    @Getter
    private final String transactionId;

    CancelTransactionMessage(String userHandle, String userPrivateKey, String transactionId) {
        this.userHandle = userHandle;
        this.userPrivateKey = userPrivateKey;
        this.transactionId = transactionId;
    }
}
