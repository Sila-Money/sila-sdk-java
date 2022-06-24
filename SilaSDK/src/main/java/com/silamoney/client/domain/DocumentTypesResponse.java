package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class DocumentTypesResponse extends BaseResponse{
    @Getter
    @SerializedName("document_types")
    private List<DocumentType> documentTypes;
    @Getter
    @SerializedName("pagination")
    private Pagination pagination;
}
