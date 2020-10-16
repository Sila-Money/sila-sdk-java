package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetDocumentMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("document_id")
    private String documentId;

    public GetDocumentMsg(String authHandle, GetDocumentMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(message.getUserHandle()).useVersion(VersionEnum.V0_2)
                .withCrypto(CryptoEnum.ETH).withReference().build();
        this.documentId = message.getDocumentId();
    }
}
