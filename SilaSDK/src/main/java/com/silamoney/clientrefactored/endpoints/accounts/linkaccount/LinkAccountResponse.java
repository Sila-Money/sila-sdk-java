package com.silamoney.clientrefactored.endpoints.accounts.linkaccount;

import com.google.gson.annotations.SerializedName;

import com.silamoney.clientrefactored.domain.BaseResponse;
import lombok.Getter;

@Getter
public class LinkAccountResponse extends BaseResponse {

    @SerializedName("account_name")
    private String accountName;
    @SerializedName("match_score")
    private String matchScore;
    @SerializedName("account_owner_name")
    private String accountOwnerName;
    @SerializedName("entity_name")
    private String entityName;

    /**
     * String field used for the account validation.
     */
    @SerializedName("web_debit_verified")
    private Boolean webDebitVerified;
}

