package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Object used to map Link Account response.
 *
 * @author Karlo Lorenzana
 */
public class LinkAccountResponse extends BaseResponse {
    @Getter
    @SerializedName(value = "account_name")
    private String accountName;

    @Getter
    @SerializedName(value = "match_score")
    private Float matchScore;
}
