package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import com.silamoney.client.security.EcdsaUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UploadDocumentMsg {
    @SerializedName("header")
    private  Header header;
    @SerializedName("message")
    private  String message;
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
        this.header = new Header(message.getUserHandle(),authHandle);
        this.header.setReference(message.getReference());
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
        this.name = message.getName();
        this.filename = message.getFilename();
        this.hash = hash;
        this.mimeType = message.getMimeType();
        this.documentType = message.getDocumentType();
        this.description = message.getDescription();
    }

    public UploadDocumentMsg(UploadDocument message) {
        this.name = message.getName();
        this.filename = message.getFilename();
        this.mimeType = message.getMimeType();
        this.documentType = message.getDocumentType();
        this.description = message.getDescription();
        try {
            hash = EcdsaUtil.hashFile(message.getFilePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
