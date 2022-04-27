package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class PaymentMethods {
    @SerializedName("payment_method_type")
    public String paymentMethodType;

    //payment_method_type=blockchain_address
    @SerializedName("blockchain_address_id")
    public String blockchainAddressId;
    @SerializedName("blockchain_address")
    public String blockChainAddress;
    @SerializedName("blockchain_network")
    public String blockChainNetwork;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("default")
    public boolean defaultPayment;
    public boolean frozen;

    //payment_method_type=bank_account
    @SerializedName("bank_account_id")
    public String bankAccountId;
    @SerializedName("account_number")
    public String accountNumber;
    @SerializedName("routing_number")
    public String routingNumber;
    @SerializedName("account_name")
    public String accountName;
    @SerializedName("account_type")
    public String accountType;
    @SerializedName("account_status")
    public String accountStatus;
    @SerializedName("account_link_status")
    public String accountLinkStatus;
    @SerializedName("match_score")
    public String matchScore;
    @SerializedName("entity_name")
    public String entityName;
    @SerializedName("account_owner_name")
    public String accountOwnerName;

    //payment_method_type=card
    @SerializedName("card_id")
    public String cardId;
    @SerializedName("card_name")
    public String cardName;
    @SerializedName("card_last_4")
    public String cardLast4;
    public String expiration;
    @SerializedName("card_type")
    public String cardType;
    @SerializedName("pull_enabled")
    public Boolean pullEnabled;
    @SerializedName("push_enabled")
    public Boolean pushEnabled;
    @SerializedName("push_availability")
    public String pushAvailability;
    @SerializedName("country")
    public String country;
    @SerializedName("currency")
    public String currency;

    //payment_method_type=virtual_account
    @SerializedName("virtual_account_id")
    private String virtualAccountId;
    @SerializedName("virtual_account_name")
    private String virtualAccountName;
    @SerializedName("created_epoch")
    private String created_epoch;
    @SerializedName("closed_epoch")
    private String closedEpoch;
    private boolean closed;
    @SerializedName("ach_credit_enabled")
    private boolean achCreditEnabled;
    @SerializedName("ach_debit_enabled")
    private boolean achDebitEnabled;


    public boolean active;
}
