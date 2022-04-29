package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Object used to map Link Account response.
 *
 * @author Karlo Lorenzana
 */
@Getter
public class LinkAccountResponse extends BaseResponse {
    
    @SerializedName(value = "account_name")
    private String accountName;
    @SerializedName(value = "match_score")
    private Float matchScore;
    @SerializedName(value = "account_owner_name")
    private String accountOwnerName;
    @SerializedName(value = "entity_name")
    private String entityName;
    /**
     * String field used for the account validation.
     */
    @SerializedName("web_debit_verified")
    private Boolean webDebitVerified;

}
