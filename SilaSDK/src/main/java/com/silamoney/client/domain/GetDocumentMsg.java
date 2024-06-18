package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetDocumentMsg {
    @SerializedName("header")
    private Header header;
    @SerializedName("document_id")
    private String documentId;

    public GetDocumentMsg(String authHandle, GetDocumentMessage message) {
        this.header = new Header(message.getUserHandle(),authHandle);
        this.header.setReference(message.getReference());
        this.documentId = message.getDocumentId();
    }
}
