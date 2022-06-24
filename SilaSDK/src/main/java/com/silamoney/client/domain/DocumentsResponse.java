package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class DocumentsResponse extends BaseResponse{
    @Getter
    @SerializedName("reference_id")
    private String referenceId;
    @Getter
    @SerializedName("document_id")
    private String documentId;
}
