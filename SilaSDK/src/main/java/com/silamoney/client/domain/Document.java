package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class Document {
    @Getter
    @SerializedName("user_handle")
    private String userHandle;
    @Getter
    @SerializedName("document_id")
    private String documentId;
    @Getter
    @SerializedName("name")
    private String name;
    @Getter
    @SerializedName("filename")
    private String filename;
    @Getter
    @SerializedName("hash")
    private String hash;
    @Getter
    @SerializedName("type")
    private String type;
    @Getter
    @SerializedName("size")
    private String size;
    @Getter
    @SerializedName("created")
    private String created;
}
