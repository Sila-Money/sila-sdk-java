package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class BusinessEntityResponse extends EntityResponse {
    @Getter
    @SerializedName("entity")
    private BusinessEntity entity;
}
