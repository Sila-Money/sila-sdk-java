package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class ApproveWireMsg {

    @SerializedName("header")
    private final Header header;
    @SerializedName("transaction_id")
    private final String transactionId;
    @SerializedName("approve")
    private final boolean approve;
    @SerializedName("notes")
    private final String notes;
    @SerializedName("mock_wire_account_name")
    private String mockWireAccountName;

    public ApproveWireMsg(String userHandle, String authHandle, String transactionId, boolean approve, String notes,String mockWireAccountName) {
        this.header = new Header(userHandle, authHandle);
        this.transactionId = transactionId;
        this.approve = approve;
        this.notes = notes;
        this.mockWireAccountName=mockWireAccountName;
    }
}
