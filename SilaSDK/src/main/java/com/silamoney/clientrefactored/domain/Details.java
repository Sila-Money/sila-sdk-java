package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Details {
    @SerializedName("entity")
    private String entity;
    @SerializedName("account")
    private String account;
    @SerializedName("outcome")
    private String outcome;
}
