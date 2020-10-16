package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class EntityResponse {
    @Getter
    @SerializedName("success")
    protected Boolean success;
    @Getter
    @SerializedName("message")
    protected String message;
    @Getter
    @SerializedName("user_handle")
    protected String userHandle;
    @Getter
    @SerializedName("entity_type")
    protected String entityType;
    @Getter
    @SerializedName("status")
    protected String status;
}
