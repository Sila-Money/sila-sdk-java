package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class CancelTransactionMsg {
    @SerializedName("header")
    private final HeaderBase header;
    @SerializedName("transaction_id")
    private final String transactionId;

    public CancelTransactionMsg(String authHandle, CancelTransactionMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(message.getUserHandle()).useVersion(VersionEnum.V0_2)
                .withCrypto(CryptoEnum.ETH).withReference().build();
        this.transactionId = message.getTransactionId();
    }
}
