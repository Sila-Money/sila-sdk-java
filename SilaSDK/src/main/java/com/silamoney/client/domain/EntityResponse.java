package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class EntityResponse extends BaseResponse{
    @Getter
    @SerializedName("user_handle")
    protected String userHandle;
    @Getter
    @SerializedName("entity_type")
    protected String entityType;
}
