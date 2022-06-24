package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class MockWireOutFileMsg {

    @SerializedName("header")
    private final Header header;
    @SerializedName("transaction_id")
    private final String transactionId;
    @SerializedName("wire_status")
    private final String wireStatus;

    public MockWireOutFileMsg(String userHandle, String authHandle, String transactionId,String wireStatus) {
        this.header = new Header(userHandle, authHandle);
        this.transactionId = transactionId;
        this.wireStatus = wireStatus;
    }
}
