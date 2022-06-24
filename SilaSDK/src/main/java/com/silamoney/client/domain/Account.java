package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Object used in the GetAccounts response.
 *
 * @author Karlo Lorenzana
 */
@Getter
public class Account {

    /**
     * String field used for the account number.
     */
    @SerializedName("account_number")
    public String accountNumber;

    /**
     * String field used for the account name.
     */
    @SerializedName("account_name")
    public String accountName;

    /**
     * String field used for the account type.
     */
    @SerializedName("account_type")
    public String accountType;

    /**
     * String field used for the account status.
     */
    @SerializedName("account_status")
    public String accountStatus;

    /**
     * String field used for the account status.
     */
    @SerializedName("account_link_status")
    public String accountListStatus;

    @SerializedName("routing_number")
    public String routingNumber;

    @SerializedName("active")
    public boolean active;

    @SerializedName("match_score")
    public Float matchScore;

    @SerializedName("account_owner_name")
    public String accountOwnerName;

    @SerializedName("entity_name")
    public String entityName;

    /**
     * String field used for the account validation.
     */
    @SerializedName("web_debit_verified")
    private Boolean webDebitVerified;
}
