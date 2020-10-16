package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class DocumentTypesResponse {
    @Getter
    @SerializedName("success")
    private Boolean success;
    @Getter
    @SerializedName("status")
    private String status;
    @Getter
    @SerializedName("message")
    private String message;
    @Getter
    @SerializedName("document_types")
    private List<DocumentType> documentTypes;
    @Getter
    @SerializedName("pagination")
    private Pagination pagination;
}
