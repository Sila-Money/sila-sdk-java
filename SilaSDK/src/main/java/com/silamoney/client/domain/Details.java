package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Details {
    @SerializedName("entity")
    private String entity;
    @SerializedName("outcome")
    private String outcome;
    @SerializedName("kyc")
    private String kyc;
}
