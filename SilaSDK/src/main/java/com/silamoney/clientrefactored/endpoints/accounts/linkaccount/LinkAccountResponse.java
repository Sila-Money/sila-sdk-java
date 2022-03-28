package com.silamoney.clientrefactored.endpoints.accounts.linkaccount;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class LinkAccountResponse {

    private boolean success;
    private String message;
    private String status;
    private String reference;
    @SerializedName("account_name")
    private String accountName;
    @SerializedName("match_score")
    private String matchScore;
    @SerializedName("account_owner_name")
    private String accountOwnerName;
    @SerializedName("entity_name")
    private String entityName;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}

