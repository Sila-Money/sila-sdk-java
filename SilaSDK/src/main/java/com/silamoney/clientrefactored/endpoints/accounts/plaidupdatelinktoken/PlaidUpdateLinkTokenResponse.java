package com.silamoney.clientrefactored.endpoints.accounts.plaidupdatelinktoken;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class PlaidUpdateLinkTokenResponse {

    private boolean success;
    private String message;
    private String status;
    @SerializedName(value = "link_token")
    private String linkToken;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
