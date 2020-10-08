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
    @SerializedName("identity_type")
    private final String identityType;
    @SerializedName("description")
    private final String description;

    /**
     * 
     * @param authHandle
     * @param userHandle
     * @param name
     * @param filename
     * @param hash
     * @param mimeType
     * @param documentType
     * @param identityType
     * @param description
     */
    public UploadDocumentMsg(String authHandle, String userHandle, String name, String filename, String hash,
            String mimeType, String documentType, String identityType, String description) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(userHandle).useVersion(VersionEnum.V0_2)
                .withCrypto(CryptoEnum.ETH).withReference().build();
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
        this.name = name;
        this.filename = filename;
        this.hash = hash;
        this.mimeType = mimeType;
        this.documentType = documentType;
        this.identityType = identityType;
        this.description = description;
    }
}
