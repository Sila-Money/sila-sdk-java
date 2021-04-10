package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class BusinessType extends Business {
    @SerializedName(value = "requires_certification")
    private boolean requiresCertification;
}