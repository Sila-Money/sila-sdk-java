package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class UploadDocumentsResponse extends BaseResponse{
    @Getter
    @SerializedName("reference_id")
    private String referenceId;
    @Getter
    @SerializedName("document_id")
    private Object documentIds;
    @Getter
    @SerializedName("unsuccessful_uploads")
    private Object unsuccessfulUploads;
}
