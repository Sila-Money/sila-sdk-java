package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class ListDocumentsResponse {
    @Getter
    @SerializedName("success")
    private Boolean success;
    @Getter
    @SerializedName("status")
    private String status;
    @Getter
    @SerializedName("documents")
    private List<Document> documents;
    @Getter
    @SerializedName("pagination")
    private Pagination pagination;
}
