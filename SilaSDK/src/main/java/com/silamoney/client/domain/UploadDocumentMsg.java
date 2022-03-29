package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class UploadDocumentMsg {
    @SerializedName("header")
    private final HeaderBase header;
    @SerializedName("message")
    private final String message;
    @SerializedName("name")
    private final String name;
    @SerializedName("filename")
    private final String filename;
    @SerializedName("hash")
    private final String hash;
    @SerializedName("mime_type")
    private final String mimeType;
    @SerializedName("document_type")
    private final String documentType;
    @SerializedName("description")
    private final String description;

    /**
     * 
     * @param authHandle
     * @param hash
     * @param message
     */
    public UploadDocumentMsg(String authHandle, String hash, UploadDocumentMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(message.getUserHandle()).useVersion(VersionEnum.V0_2)
                .withCrypto(CryptoEnum.ETH).withReference().build();
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
        this.name = message.getName();
        this.filename = message.getFilename();
        this.hash = hash;
        this.mimeType = message.getMimeType();
        this.documentType = message.getDocumentType();
        this.description = message.getDescription();
    }
}
