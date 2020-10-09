package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class IndividualEntityResponse extends EntityResponse {
    @Getter
    @SerializedName("entity")
    private IndividualEntity entity;
}
