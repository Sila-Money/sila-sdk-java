package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class DocumentType {
    @Getter
    @SerializedName("name")
    private String name;
    @Getter
    @SerializedName("label")
    private String label;
    @Getter
    @SerializedName("identity_type")
    private String identityType;
}
