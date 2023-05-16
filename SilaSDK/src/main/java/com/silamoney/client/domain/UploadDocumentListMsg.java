package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class UploadDocumentListMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("file_metadata")
    private final HashMap<String, UploadDocumentMsg> fileMetadata;
    @SerializedName("message")
    private final String message;
    /**
     * @param authHandle
     * @param userHandle
     * @param fileMetadata
     */
    public UploadDocumentListMsg(String authHandle,String userHandle,HashMap<String,UploadDocumentMsg> fileMetadata,String reference) {
        this.header = new Header(userHandle, authHandle);
        this.header.setReference(reference);
        this.message = Message.ValueEnum.HEADER_MSG.getValue();

        this.fileMetadata=fileMetadata;
    }
}
