package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Header {
    private int created;
    @SerializedName("app_handle")
    private String appHandle;
    @SerializedName("user_handle")
    private String userHandle;
    private final String version = "0.2";
    private final String crypto = "ETH";
    private String reference;
}
