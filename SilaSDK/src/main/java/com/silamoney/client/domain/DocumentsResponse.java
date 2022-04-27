package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class DocumentsResponse {
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
    @SerializedName("reference_id")
    private String referenceId;
    @Getter
    @SerializedName("document_id")
    private String documentId;
    @Getter
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
