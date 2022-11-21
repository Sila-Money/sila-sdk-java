package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {

    @SerializedName("account_number")
    private String accountNumber;
    @SerializedName("routing_number")
    private String routingNumber;
    @SerializedName("account_name")
    private String accountName;
    @SerializedName("account_type")
    private String accountType;
    @SerializedName("account_status")
    private String accountStatus;
    private boolean active;
    @SerializedName("account_link_status")
    private String accountLinkStatus;
    @SerializedName("match_score")
    private Float matchScore;
    @SerializedName("account_owner_name")
    private String accountOwnerName;
    @SerializedName("entity_name")
    private String entityName;

    /**
     * String field used for the account validation.
     */
    @SerializedName("web_debit_verified")
    private Boolean webDebitVerified;

    /**
     * String field used for the MX integration.
     */
    @SerializedName("provider_name")
    private Boolean providerName;
}
