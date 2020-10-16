package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class EntityBase {
    @Getter
    @SerializedName("created_epoch")
    protected Integer createdEpoch;
    @Getter
    @SerializedName("entity_name")
    protected String entityName;
    @Getter
    @SerializedName("birthdate")
    protected String birthdate;
}
