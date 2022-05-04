package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class ListDocumentsResponse extends BaseResponse {
    @Getter
    @SerializedName("documents")
    private List<Document> documents;
    @Getter
    @SerializedName("pagination")
    private Pagination pagination;
}
