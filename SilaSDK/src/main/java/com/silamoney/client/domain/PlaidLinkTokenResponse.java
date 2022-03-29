package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class PlaidLinkTokenResponse {

    private boolean success;
    private String status;
    @SerializedName("link_token")
    private String linkToken;
    private String message;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
